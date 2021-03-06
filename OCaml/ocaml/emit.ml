(***********************************************************************)
(*                                                                     *)
(*                                OCaml                                *)
(*                                                                     *)
(*            Xavier Leroy, projet Cristal, INRIA Rocquencourt         *)
(*                                                                     *)
(*  Copyright 1996 Institut National de Recherche en Informatique et   *)
(*  en Automatique.  All rights reserved.  This file is distributed    *)
(*  under the terms of the Q Public License version 1.0.               *)
(*                                                                     *)
(***********************************************************************)

(* Emission of x86-64 (AMD 64) assembly code *)

open Cmm
open Arch
open Proc
open Reg
open Mach
open Linearize
open Emitaux

let macosx = (Config.system = "macosx")
let mingw64 = (Config.system = "mingw64")
let cygwin = (Config.system = "cygwin")

let fp = Config.with_frame_pointers

(* Tradeoff between code size and code speed *)

let fastcode_flag = ref true

let stack_offset = ref 0

(* Layout of the stack frame *)

let frame_required () =
  fp || !contains_calls || num_stack_slots.(0) > 0 || num_stack_slots.(1) > 0

let frame_size () =                     (* includes return address *)
  if frame_required() then begin
    let sz =
      (!stack_offset + 8 * (num_stack_slots.(0) + num_stack_slots.(1)) + 8
      + (if fp then 8 else 0) )
    in Misc.align sz 16
  end else
    !stack_offset + 8

let slot_offset loc cl =
  match loc with
    Incoming n -> frame_size() + n
  | Local n ->
      if cl = 0
      then !stack_offset + n * 8
      else !stack_offset + (num_stack_slots.(0) + n) * 8
  | Outgoing n -> n

(* Symbols *)

let emit_symbol s =
    if macosx then emit_string "_";
    Emitaux.emit_symbol '$' s

let emit_call s =
  if !Clflags.dlcode && not macosx && not mingw64 && not cygwin
  then (emit_string "call	"; emit_symbol s; emit_string "@PLT")
  else (emit_string "call	"; emit_symbol s)

let emit_jump s =
  if !Clflags.dlcode && not macosx && not mingw64 && not cygwin
  then (emit_string "jmp	"; emit_symbol s; emit_string "@PLT")
  else (emit_string "jmp	"; emit_symbol s)

let load_symbol_addr s =
  if !Clflags.dlcode && not mingw64 && not cygwin
  then (emit_string "movq	"; emit_symbol s; emit_string "@GOTPCREL(%rip)")
  else if !pic_code
  then (emit_string "leaq	"; emit_symbol s; emit_string "(%rip)")
  else (emit_string "movq	$"; emit_symbol s)

(* Output a label *)

let emit_label lbl =
  emit_string ".L"; emit_int lbl

let emit_data_label lbl =
  emit_string ".Ld"; emit_int lbl

(* Output a .align directive. *)

let emit_align n =
    let n = if macosx then Misc.log2 n else n in
  (emit_string "	.align	"; emit_int n; emit_char '\n')

let emit_Llabel fallthrough lbl =
  if not fallthrough && !fastcode_flag then emit_align 4;
  emit_label lbl

(* Output a pseudo-register *)

let emit_reg = function
    { loc = Reg r } ->
      emit_string (register_name r)
  | { loc = Stack s } as r ->
      let ofs = slot_offset s (register_class r) in
      (emit_int ofs; emit_string "(%rsp)")
  | { loc = Unknown } ->
      assert false

(* Output a reference to the lower 8, 16 or 32 bits of a register *)

let reg_low_8_name =
  [| "%al"; "%bl"; "%dil"; "%sil"; "%dl"; "%cl"; "%r8b"; "%r9b";
     "%r12b"; "%r13b"; "%r10b"; "%r11b"; "%bpl" |]
let reg_low_16_name =
  [| "%ax"; "%bx"; "%di"; "%si"; "%dx"; "%cx"; "%r8w"; "%r9w";
     "%r12w"; "%r13w"; "%r10w"; "%r11w"; "%bp" |]
let reg_low_32_name =
  [| "%eax"; "%ebx"; "%edi"; "%esi"; "%edx"; "%ecx"; "%r8d"; "%r9d";
     "%r12d"; "%r13d"; "%r10d"; "%r11d"; "%ebp" |]

let emit_subreg tbl r =
  match r.loc with
    Reg r when r < 13 ->
      emit_string tbl.(r)
  | Stack s ->
      let ofs = slot_offset s (register_class r) in
      (emit_int ofs; emit_string "(%rsp)")
  | _ ->
      assert false

let emit_reg8 r = emit_subreg reg_low_8_name r
let emit_reg16 r = emit_subreg reg_low_16_name r
let emit_reg32 r = emit_subreg reg_low_32_name r

(* Output an addressing mode *)

let emit_addressing addr r n =
  match addr with
  | Ibased _ when !Clflags.dlcode -> assert false
  | Ibased(s, d) ->
      (emit_symbol s);
      if d <> 0 then (emit_string " + "; emit_int d);
      (emit_string "(%rip)")
  | Iindexed d ->
      if d <> 0 then emit_int d;
      (emit_char '('; emit_reg r.(n); emit_char ')')
  | Iindexed2 d ->
      if d <> 0 then emit_int d;
      (emit_char '('; emit_reg r.(n); emit_string ", "; emit_reg r.(n+1); emit_char ')')
  | Iscaled(2, d) ->
      if d <> 0 then emit_int d;
      (emit_char '('; emit_reg r.(n); emit_string ", "; emit_reg r.(n); emit_char ')')
  | Iscaled(scale, d) ->
      if d <> 0 then emit_int d;
      (emit_string "(, "; emit_reg r.(n); emit_string ", "; emit_int scale; emit_char ')')
  | Iindexed2scaled(scale, d) ->
      if d <> 0 then emit_int d;
      (emit_char '('; emit_reg r.(n); emit_string ", "; emit_reg r.(n+1); emit_string ", "; emit_int scale; emit_char ')')

(* Record live pointers at call points -- see Emitaux *)

let record_frame_label live dbg =
  let lbl = new_label() in
  let live_offset = ref [] in
  Reg.Set.iter
    (function
        {typ = Addr; loc = Reg r} ->
          live_offset := ((r lsl 1) + 1) :: !live_offset
      | {typ = Addr; loc = Stack s} as reg ->
          live_offset := slot_offset s (register_class reg) :: !live_offset
      | _ -> ())
    live;
  frame_descriptors :=
    { fd_lbl = lbl;
      fd_frame_size = frame_size();
      fd_live_offset = !live_offset;
      fd_debuginfo = dbg } :: !frame_descriptors;
  lbl

let record_frame live dbg =
  let lbl = record_frame_label live dbg in (emit_label lbl; emit_string ":\n")

(* Record calls to the GC -- we've moved them out of the way *)

type gc_call =
  { gc_lbl: label;                      (* Entry label *)
    gc_return_lbl: label;               (* Where to branch after GC *)
    gc_frame: label }                   (* Label of frame descriptor *)

let call_gc_sites = ref ([] : gc_call list)

let emit_call_gc gc =
  (emit_label gc.gc_lbl; emit_string ":	"; emit_call "caml_call_gc"; emit_char '\n');
  (emit_label gc.gc_frame; emit_string ":	jmp	"; emit_label gc.gc_return_lbl; emit_char '\n')

(* Record calls to caml_ml_array_bound_error.
   In -g mode, we maintain one call to caml_ml_array_bound_error
   per bound check site.  Without -g, we can share a single call. *)

type bound_error_call =
  { bd_lbl: label;                      (* Entry label *)
    bd_frame: label }                   (* Label of frame descriptor *)

let bound_error_sites = ref ([] : bound_error_call list)
let bound_error_call = ref 0

let bound_error_label dbg =
  if !Clflags.debug then begin
    let lbl_bound_error = new_label() in
    let lbl_frame = record_frame_label Reg.Set.empty dbg in
    bound_error_sites :=
     { bd_lbl = lbl_bound_error; bd_frame = lbl_frame } :: !bound_error_sites;
   lbl_bound_error
 end else begin
   if !bound_error_call = 0 then bound_error_call := new_label();
   !bound_error_call
 end

let emit_call_bound_error bd =
  (emit_label bd.bd_lbl; emit_string ":	"; emit_call "caml_ml_array_bound_error"; emit_char '\n');
  (emit_label bd.bd_frame; emit_string ":\n")

let emit_call_bound_errors () =
  List.iter emit_call_bound_error !bound_error_sites;
  if !bound_error_call > 0 then
    (emit_label !bound_error_call; emit_string ":	"; emit_call "caml_ml_array_bound_error"; emit_char '\n')

(* Names for instructions *)

let instr_for_intop = function
    Iadd -> "addq"
  | Isub -> "subq"
  | Imul -> "imulq"
  | Iand -> "andq"
  | Ior -> "orq"
  | Ixor -> "xorq"
  | Ilsl -> "salq"
  | Ilsr -> "shrq"
  | Iasr -> "sarq"
  | _ -> assert false

let instr_for_floatop = function
    Iaddf -> "addsd"
  | Isubf -> "subsd"
  | Imulf -> "mulsd"
  | Idivf -> "divsd"
  | _ -> assert false

let instr_for_floatarithmem = function
    Ifloatadd -> "addsd"
  | Ifloatsub -> "subsd"
  | Ifloatmul -> "mulsd"
  | Ifloatdiv -> "divsd"

let name_for_cond_branch = function
    Isigned Ceq -> "e"     | Isigned Cne -> "ne"
  | Isigned Cle -> "le"     | Isigned Cgt -> "g"
  | Isigned Clt -> "l"     | Isigned Cge -> "ge"
  | Iunsigned Ceq -> "e"   | Iunsigned Cne -> "ne"
  | Iunsigned Cle -> "be"  | Iunsigned Cgt -> "a"
  | Iunsigned Clt -> "b"  | Iunsigned Cge -> "ae"

(* Output an = 0 or <> 0 test. *)

let output_test_zero arg =
  match arg.loc with
    Reg r -> (emit_string "	testq	"; emit_reg arg; emit_string ", "; emit_reg arg; emit_char '\n')
  | _     -> (emit_string "	cmpq	$0, "; emit_reg arg; emit_char '\n')

(* Output a floating-point compare and branch *)

let emit_float_test cmp neg arg lbl =
  (* Effect of comisd on flags and conditional branches:
                     ZF PF CF  cond. branches taken
        unordered     1  1  1  je, jb, jbe, jp
        >             0  0  0  jne, jae, ja
        <             0  0  1  jne, jbe, jb
        =             1  0  0  je, jae, jbe.
     If FP traps are on (they are off by default),
     comisd traps on QNaN and SNaN but ucomisd traps on SNaN only.
  *)
  match (cmp, neg) with
  | (Ceq, false) | (Cne, true) ->
      let next = new_label() in
      (emit_string "	ucomisd	"; emit_reg arg.(1); emit_string ", "; emit_reg arg.(0); emit_char '\n');
      (emit_string "	jp	"; emit_label next; emit_char '\n');    (* skip if unordered *)
      (emit_string "	je	"; emit_label lbl; emit_char '\n');     (* branch taken if x=y *)
      (emit_label next; emit_string ":\n")
  | (Cne, false) | (Ceq, true) ->
      (emit_string "	ucomisd	"; emit_reg arg.(1); emit_string ", "; emit_reg arg.(0); emit_char '\n');
      (emit_string "	jp	"; emit_label lbl; emit_char '\n');     (* branch taken if unordered *)
      (emit_string "	jne	"; emit_label lbl; emit_char '\n')      (* branch taken if x<y or x>y *)
  | (Clt, _) ->
      (emit_string "	comisd	"; emit_reg arg.(0); emit_string ", "; emit_reg arg.(1); emit_char '\n');  (* swap compare *)
      if not neg then
      (emit_string "	ja	"; emit_label lbl; emit_char '\n')     (* branch taken if y>x i.e. x<y *)
      else
      (emit_string "	jbe	"; emit_label lbl; emit_char '\n') (* taken if unordered or y<=x i.e. !(x<y) *)
  | (Cle, _) ->
      (emit_string "	comisd	"; emit_reg arg.(0); emit_string ", "; emit_reg arg.(1); emit_char '\n');  (* swap compare *)
      if not neg then
      (emit_string "	jae	"; emit_label lbl; emit_char '\n')     (* branch taken if y>=x i.e. x<=y *)
      else
      (emit_string "	jb	"; emit_label lbl; emit_char '\n') (* taken if unordered or y<x i.e. !(x<=y) *)
  | (Cgt, _) ->
      (emit_string "	comisd	"; emit_reg arg.(1); emit_string ", "; emit_reg arg.(0); emit_char '\n');
      if not neg then
      (emit_string "	ja	"; emit_label lbl; emit_char '\n')     (* branch taken if x>y *)
      else
      (emit_string "	jbe	"; emit_label lbl; emit_char '\n') (* taken if unordered or x<=y i.e. !(x>y) *)
  | (Cge, _) ->
      (emit_string "	comisd	"; emit_reg arg.(1); emit_string ", "; emit_reg arg.(0); emit_char '\n');  (* swap compare *)
      if not neg then
      (emit_string "	jae	"; emit_label lbl; emit_char '\n')     (* branch taken if x>=y *)
      else
      (emit_string "	jb	"; emit_label lbl; emit_char '\n') (* taken if unordered or x<y i.e. !(x>=y) *)

(* Deallocate the stack frame before a return or tail call *)

let output_epilogue f =
  if frame_required() then begin
    let n = frame_size() - 8 - (if fp then 8 else 0) in
    (emit_string "	addq	$"; emit_int n; emit_string ", %rsp\n");
    cfi_adjust_cfa_offset (-n);
    if fp then begin
       (emit_string "	popq	%rbp\n")
    end;
    f ();
    (* reset CFA back cause function body may continue *)
    cfi_adjust_cfa_offset n
  end
  else
    f ()

(* Floating-point constants *)

let float_constants = ref ([] : (string * int) list)

let add_float_constant cst =
  try
    List.assoc cst !float_constants
  with
    Not_found ->
      let lbl = new_label() in
      float_constants := (cst, lbl) :: !float_constants;
      lbl

let emit_float_constant (cst, lbl) =
  (emit_label lbl; emit_char ':');
  emit_float64_directive ".quad" cst

(* Output the assembly code for an instruction *)

(* Name of current function *)
let function_name = ref ""
(* Entry point for tail recursive calls *)
let tailrec_entry_point = ref 0

(* Emit an instruction *)
let emit_instr fallthrough i =
    emit_debug_info i.dbg;
    match i.desc with
      Lend -> ()
    | Lop(Imove | Ispill | Ireload) ->
        let src = i.arg.(0) and dst = i.res.(0) in
        if src.loc <> dst.loc then begin
          match src.typ, src.loc, dst.loc with
            Float, Reg _, Reg _ ->
              (emit_string "	movapd	"; emit_reg src; emit_string ", "; emit_reg dst; emit_char '\n')
          | Float, _, _ ->
              (emit_string "	movsd	"; emit_reg src; emit_string ", "; emit_reg dst; emit_char '\n')
          | _ ->
              (emit_string "	movq	"; emit_reg src; emit_string ", "; emit_reg dst; emit_char '\n')
        end
    | Lop(Iconst_int n) ->
        if n = 0n then begin
          match i.res.(0).loc with
            Reg n -> (emit_string "	xorq	"; emit_reg i.res.(0); emit_string ", "; emit_reg i.res.(0); emit_char '\n')
          | _     -> (emit_string "	movq	$0, "; emit_reg i.res.(0); emit_char '\n')
        end else if n <= 0x7FFFFFFFn && n >= -0x80000000n then
          (emit_string "	movq	$"; emit_nativeint n; emit_string ", "; emit_reg i.res.(0); emit_char '\n')
        else
          (emit_string "	movabsq	$"; emit_nativeint n; emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iconst_float s) ->
        begin match Int64.bits_of_float (float_of_string s) with
        | 0x0000_0000_0000_0000L ->       (* +0.0 *)
          (emit_string "	xorpd	"; emit_reg i.res.(0); emit_string ", "; emit_reg i.res.(0); emit_char '\n')
        | _ ->
          let lbl = add_float_constant s in
          (emit_string "	movsd	"; emit_label lbl; emit_string "(%rip), "; emit_reg i.res.(0); emit_char '\n')
        end
    | Lop(Iconst_symbol s) ->
        (emit_char '	'; load_symbol_addr s; emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Icall_ind) ->
        (emit_string "	call	*"; emit_reg i.arg.(0); emit_char '\n');
        record_frame i.live i.dbg
    | Lop(Icall_imm(s)) ->
        (emit_char '	'; emit_call s; emit_char '\n');
        record_frame i.live i.dbg
    | Lop(Itailcall_ind) ->
        output_epilogue begin fun () ->
        (emit_string "	jmp	*"; emit_reg i.arg.(0); emit_char '\n')
        end
    | Lop(Itailcall_imm s) ->
        if s = !function_name then
          (emit_string "	jmp	"; emit_label !tailrec_entry_point; emit_char '\n')
        else begin
          output_epilogue begin fun () ->
          (emit_char '	'; emit_jump s; emit_char '\n')
          end
        end
    | Lop(Iextcall(s, alloc)) ->
        if alloc then begin
          (emit_char '	'; load_symbol_addr s; emit_string ", %rax\n");
          (emit_char '	'; emit_call "caml_c_call"; emit_char '\n');
          record_frame i.live i.dbg;
          (emit_char '	'; load_symbol_addr "caml_young_ptr"; emit_string ", %r11\n");
          (emit_string "	movq    (%r11), %r15\n");
        end else begin
          (emit_char '	'; emit_call s; emit_char '\n')
        end
    | Lop(Istackoffset n) ->
        if n < 0
        then (emit_string "	addq	$"; emit_int(-n); emit_string ", %rsp\n")
        else (emit_string "	subq	$"; emit_int(n); emit_string ", %rsp\n");
        cfi_adjust_cfa_offset n;
        stack_offset := !stack_offset + n
    | Lop(Iload(chunk, addr)) ->
        let dest = i.res.(0) in
        begin match chunk with
          | Word ->
              (emit_string "	movq	"; emit_addressing addr i.arg 0; emit_string ", "; emit_reg dest; emit_char '\n')
          | Byte_unsigned ->
              (emit_string "	movzbq	"; emit_addressing addr i.arg 0; emit_string ", "; emit_reg dest; emit_char '\n')
          | Byte_signed ->
              (emit_string "	movsbq	"; emit_addressing addr i.arg 0; emit_string ", "; emit_reg dest; emit_char '\n')
          | Sixteen_unsigned ->
              (emit_string "	movzwq	"; emit_addressing addr i.arg 0; emit_string ", "; emit_reg dest; emit_char '\n')
          | Sixteen_signed ->
              (emit_string "	movswq	"; emit_addressing addr i.arg 0; emit_string ", "; emit_reg dest; emit_char '\n')
          | Thirtytwo_unsigned ->
              (emit_string "	movl	"; emit_addressing addr i.arg 0; emit_string ", "; emit_reg32 dest; emit_char '\n')
          | Thirtytwo_signed ->
              (emit_string "	movslq	"; emit_addressing addr i.arg 0; emit_string ", "; emit_reg dest; emit_char '\n')
          | Single ->
            (emit_string "	cvtss2sd "; emit_addressing addr i.arg 0; emit_string ", "; emit_reg dest; emit_char '\n')
          | Double | Double_u ->
            (emit_string "	movsd	"; emit_addressing addr i.arg 0; emit_string ", "; emit_reg dest; emit_char '\n')
        end
    | Lop(Istore(chunk, addr)) ->
        begin match chunk with
          | Word ->
            (emit_string "	movq	"; emit_reg i.arg.(0); emit_string ", "; emit_addressing addr i.arg 1; emit_char '\n')
          | Byte_unsigned | Byte_signed ->
            (emit_string "	movb	"; emit_reg8 i.arg.(0); emit_string ", "; emit_addressing addr i.arg 1; emit_char '\n')
          | Sixteen_unsigned | Sixteen_signed ->
            (emit_string "	movw	"; emit_reg16 i.arg.(0); emit_string ", "; emit_addressing addr i.arg 1; emit_char '\n')
          | Thirtytwo_signed | Thirtytwo_unsigned ->
            (emit_string "	movl	"; emit_reg32 i.arg.(0); emit_string ", "; emit_addressing addr i.arg 1; emit_char '\n')
          | Single ->
            (emit_string "	cvtsd2ss "; emit_reg i.arg.(0); emit_string ", %xmm15\n");
            (emit_string "	movss	%xmm15, "; emit_addressing addr i.arg 1; emit_char '\n')
          | Double | Double_u ->
            (emit_string "	movsd	"; emit_reg i.arg.(0); emit_string ", "; emit_addressing addr i.arg 1; emit_char '\n')
        end
    | Lop(Ialloc n) ->
        if !fastcode_flag then begin
          let lbl_redo = new_label() in
          (emit_label lbl_redo; emit_string ":	subq	$"; emit_int n; emit_string ", %r15\n");
          if !Clflags.dlcode then begin
            (emit_char '	'; load_symbol_addr "caml_young_limit"; emit_string ", %rax\n");
            (emit_string "	cmpq	(%rax), %r15\n");
          end else
            (emit_string "	cmpq	"; emit_symbol "caml_young_limit"; emit_string "(%rip), %r15\n");
          let lbl_call_gc = new_label() in
          let lbl_frame = record_frame_label i.live Debuginfo.none in
          (emit_string "	jb	"; emit_label lbl_call_gc; emit_char '\n');
          (emit_string "	leaq	8(%r15), "; emit_reg i.res.(0); emit_char '\n');
          call_gc_sites :=
            { gc_lbl = lbl_call_gc;
              gc_return_lbl = lbl_redo;
              gc_frame = lbl_frame } :: !call_gc_sites
        end else begin
          begin match n with
            16  -> (emit_char '	'; emit_call "caml_alloc1"; emit_char '\n')
          | 24 -> (emit_char '	'; emit_call "caml_alloc2"; emit_char '\n')
          | 32 -> (emit_char '	'; emit_call "caml_alloc3"; emit_char '\n')
          | _  -> (emit_string "	movq	$"; emit_int n; emit_string ", %rax\n");
                  (emit_char '	'; emit_call "caml_allocN"; emit_char '\n')
          end;
          (record_frame i.live Debuginfo.none; emit_string "	leaq	8(%r15), "; emit_reg i.res.(0); emit_char '\n')
        end
    | Lop(Iintop(Icomp cmp)) ->
        (emit_string "	cmpq	"; emit_reg i.arg.(1); emit_string ", "; emit_reg i.arg.(0); emit_char '\n');
        let b = name_for_cond_branch cmp in
        (emit_string "	set"; emit_string b; emit_string "	%al\n");
        (emit_string "	movzbq	%al, "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iintop_imm(Icomp cmp, n)) ->
        (emit_string "	cmpq	$"; emit_int n; emit_string ", "; emit_reg i.arg.(0); emit_char '\n');
        let b = name_for_cond_branch cmp in
        (emit_string "	set"; emit_string b; emit_string "	%al\n");
        (emit_string "	movzbq	%al, "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iintop Icheckbound) ->
        let lbl = bound_error_label i.dbg in
        (emit_string "	cmpq	"; emit_reg i.arg.(1); emit_string ", "; emit_reg i.arg.(0); emit_char '\n');
        (emit_string "	jbe	"; emit_label lbl; emit_char '\n')
    | Lop(Iintop_imm(Icheckbound, n)) ->
        let lbl = bound_error_label i.dbg in
        (emit_string "	cmpq	$"; emit_int n; emit_string ", "; emit_reg i.arg.(0); emit_char '\n');
        (emit_string "	jbe	"; emit_label lbl; emit_char '\n')
    | Lop(Iintop(Idiv | Imod)) ->
        (emit_string "	cqto\n");
        (emit_string "	idivq	"; emit_reg i.arg.(1); emit_char '\n')
    | Lop(Iintop(Ilsl | Ilsr | Iasr as op)) ->
        (* We have i.arg.(0) = i.res.(0) and i.arg.(1) = %rcx *)
        (emit_char '	'; emit_string(instr_for_intop op); emit_string "	%cl, "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iintop op) ->
        (* We have i.arg.(0) = i.res.(0) *)
        (emit_char '	'; emit_string(instr_for_intop op); emit_char '	'; emit_reg i.arg.(1); emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iintop_imm(Iadd, n)) when i.arg.(0).loc <> i.res.(0).loc ->
        (emit_string "	leaq	"; emit_int n; emit_char '('; emit_reg i.arg.(0); emit_string "), "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iintop_imm(Iadd, 1) | Iintop_imm(Isub, -1)) ->
        (emit_string "	incq	"; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iintop_imm(Iadd, -1) | Iintop_imm(Isub, 1)) ->
        (emit_string "	decq	"; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iintop_imm(Idiv, n)) ->
        (* Note: i.arg.(0) = i.res.(0) = rdx  (cf. selection.ml) *)
        let l = Misc.log2 n in
        (emit_string "	movq	"; emit_reg i.arg.(0); emit_string ", %rax\n");
        (emit_string "	addq	$"; emit_int(n-1); emit_string ", "; emit_reg i.arg.(0); emit_char '\n');
        (emit_string "	testq	%rax, %rax\n");
        (emit_string "	cmovns	%rax, "; emit_reg i.arg.(0); emit_char '\n');
        (emit_string "	sarq	$"; emit_int l; emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iintop_imm(Imod, n)) ->
        (* Note: i.arg.(0) = i.res.(0) = rdx  (cf. selection.ml) *)
        (emit_string "	movq	"; emit_reg i.arg.(0); emit_string ", %rax\n");
        (emit_string "	testq	%rax, %rax\n");
        (emit_string "	leaq	"; emit_int(n-1); emit_string "(%rax), %rax\n");
        (emit_string "	cmovns	"; emit_reg i.arg.(0); emit_string ", %rax\n");
        (emit_string "	andq	$"; emit_int (-n); emit_string ", %rax\n");
        (emit_string "	subq	%rax, "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iintop_imm(op, n)) ->
        (* We have i.arg.(0) = i.res.(0) *)
        (emit_char '	'; emit_string(instr_for_intop op); emit_string "	$"; emit_int n; emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Inegf) ->
        (emit_string "	xorpd	"; emit_symbol "caml_negf_mask"; emit_string "(%rip), "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iabsf) ->
        (emit_string "	andpd	"; emit_symbol "caml_absf_mask"; emit_string "(%rip), "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iaddf | Isubf | Imulf | Idivf as floatop) ->
        (emit_char '	'; emit_string(instr_for_floatop floatop); emit_char '	'; emit_reg i.arg.(1); emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Ifloatofint) ->
        (emit_string "	cvtsi2sdq	"; emit_reg i.arg.(0); emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Iintoffloat) ->
        (emit_string "	cvttsd2siq	"; emit_reg i.arg.(0); emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Ispecific(Ilea addr)) ->
        (emit_string "	leaq	"; emit_addressing addr i.arg 0; emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Ispecific(Istore_int(n, addr))) ->
        (emit_string "	movq	$"; emit_nativeint n; emit_string ", "; emit_addressing addr i.arg 0; emit_char '\n')
    | Lop(Ispecific(Istore_symbol(s, addr))) ->
        assert (not !pic_code && not !Clflags.dlcode);
        (emit_string "	movq	$"; emit_symbol s; emit_string ", "; emit_addressing addr i.arg 0; emit_char '\n')
    | Lop(Ispecific(Ioffset_loc(n, addr))) ->
        (emit_string "	addq	$"; emit_int n; emit_string ", "; emit_addressing addr i.arg 0; emit_char '\n')
    | Lop(Ispecific(Ifloatarithmem(op, addr))) ->
        (emit_char '	'; emit_string(instr_for_floatarithmem op); emit_char '	'; emit_addressing addr i.arg 1; emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Ispecific(Ibswap size)) ->
       begin match size with
       | 16 ->
        (emit_string "	xchg	%ah, %al\n");
        (emit_string "	movzwq	"; emit_reg16 i.res.(0); emit_string ", "; emit_reg i.res.(0); emit_char '\n')
       | 32 ->
        (emit_string "	bswap	"; emit_reg32 i.res.(0); emit_char '\n');
        (emit_string "	movslq	"; emit_reg32 i.res.(0); emit_string ", "; emit_reg i.res.(0); emit_char '\n')
       | 64 ->
        (emit_string "	bswap	"; emit_reg i.res.(0); emit_char '\n')
       | _ -> assert false
       end
    | Lop(Ispecific Isqrtf) ->
        (emit_string "	sqrtsd	"; emit_reg i.arg.(0); emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lop(Ispecific(Ifloatsqrtf addr)) ->
        (emit_string "	sqrtsd	"; emit_addressing addr i.arg 0; emit_string ", "; emit_reg i.res.(0); emit_char '\n')
    | Lreloadretaddr ->
        ()
    | Lreturn ->
        output_epilogue begin fun () ->
        (emit_string "	ret\n")
        end
    | Llabel lbl ->
        (emit_Llabel fallthrough lbl; emit_string ":\n")
    | Lbranch lbl ->
        (emit_string "	jmp	"; emit_label lbl; emit_char '\n')
    | Lcondbranch(tst, lbl) ->
        begin match tst with
          Itruetest ->
            output_test_zero i.arg.(0);
            (emit_string "	jne	"; emit_label lbl; emit_char '\n')
        | Ifalsetest ->
            output_test_zero i.arg.(0);
            (emit_string "	je	"; emit_label lbl; emit_char '\n')
        | Iinttest cmp ->
            (emit_string "	cmpq	"; emit_reg i.arg.(1); emit_string ", "; emit_reg i.arg.(0); emit_char '\n');
            let b = name_for_cond_branch cmp in
            (emit_string "	j"; emit_string b; emit_char '	'; emit_label lbl; emit_char '\n')
        | Iinttest_imm((Isigned Ceq | Isigned Cne |
                        Iunsigned Ceq | Iunsigned Cne) as cmp, 0) ->
            output_test_zero i.arg.(0);
            let b = name_for_cond_branch cmp in
            (emit_string "	j"; emit_string b; emit_char '	'; emit_label lbl; emit_char '\n')
        | Iinttest_imm(cmp, n) ->
            (emit_string "	cmpq	$"; emit_int n; emit_string ", "; emit_reg i.arg.(0); emit_char '\n');
            let b = name_for_cond_branch cmp in
            (emit_string "	j"; emit_string b; emit_char '	'; emit_label lbl; emit_char '\n')
        | Ifloattest(cmp, neg) ->
            emit_float_test cmp neg i.arg lbl
        | Ioddtest ->
            (emit_string "	testb	$1, "; emit_reg8 i.arg.(0); emit_char '\n');
            (emit_string "	jne	"; emit_label lbl; emit_char '\n')
        | Ieventest ->
            (emit_string "	testb	$1, "; emit_reg8 i.arg.(0); emit_char '\n');
            (emit_string "	je	"; emit_label lbl; emit_char '\n')
        end
    | Lcondbranch3(lbl0, lbl1, lbl2) ->
            (emit_string "	cmpq	$1, "; emit_reg i.arg.(0); emit_char '\n');
            begin match lbl0 with
              None -> ()
            | Some lbl -> (emit_string "	jb	"; emit_label lbl; emit_char '\n')
            end;
            begin match lbl1 with
              None -> ()
            | Some lbl -> (emit_string "	je	"; emit_label lbl; emit_char '\n')
            end;
            begin match lbl2 with
              None -> ()
            | Some lbl -> (emit_string "	jg	"; emit_label lbl; emit_char '\n')
            end
    | Lswitch jumptbl ->
        let lbl = new_label() in
        (* rax and rdx are clobbered by the Lswitch,
           meaning that no variable that is live across the Lswitch
           is assigned to rax or rdx.  However, the argument to Lswitch
           can still be assigned to one of these two registers, so
           we must be careful not to clobber it before use. *)
        let (tmp1, tmp2) =
          if i.arg.(0).loc = Reg 0 (* rax *)
          then (phys_reg 4 (*rdx*), phys_reg 0 (*rax*))
          else (phys_reg 0 (*rax*), phys_reg 4 (*rdx*)) in
        (emit_string "	leaq	"; emit_label lbl; emit_string "(%rip), "; emit_reg tmp1; emit_char '\n');
        (emit_string "	movslq	("; emit_reg tmp1; emit_string ", "; emit_reg i.arg.(0); emit_string ", 4), "; emit_reg tmp2; emit_char '\n');
        (emit_string "	addq	"; emit_reg tmp2; emit_string ", "; emit_reg tmp1; emit_char '\n');
        (emit_string "	jmp	*"; emit_reg tmp1; emit_char '\n');
        if macosx then
          (emit_string "	.const\n")
        else if mingw64 || cygwin then
          (emit_string "	.section .rdata,\"dr\"\n")
        else
          (emit_string "	.section .rodata\n");
        emit_align 4;
        (emit_label lbl; emit_char ':');
        for i = 0 to Array.length jumptbl - 1 do
          (emit_string "	.long	"; emit_label jumptbl.(i); emit_string " - "; emit_label lbl; emit_char '\n')
        done;
        (emit_string "	.text\n")
    | Lsetuptrap lbl ->
        (emit_string "	call	"; emit_label lbl; emit_char '\n')
    | Lpushtrap ->
        cfi_adjust_cfa_offset 8;
        (emit_string "	pushq	%r14\n");
        cfi_adjust_cfa_offset 8;
        (emit_string "	movq	%rsp, %r14\n");
        stack_offset := !stack_offset + 16
    | Lpoptrap ->
        (emit_string "	popq	%r14\n");
        cfi_adjust_cfa_offset (-8);
        (emit_string "	addq	$8, %rsp\n");
        cfi_adjust_cfa_offset (-8);
        stack_offset := !stack_offset - 16
    | Lraise ->
        if !Clflags.debug then begin
          (emit_char '	'; emit_call "caml_raise_exn"; emit_char '\n');
          record_frame Reg.Set.empty i.dbg
        end else begin
          (emit_string "	movq	%r14, %rsp\n");
          (emit_string "	popq	%r14\n");
          (emit_string "	ret\n")
        end

let rec emit_all fallthrough i =
  match i.desc with
  |  Lend -> ()
  | _ ->
      emit_instr fallthrough i;
      emit_all (Linearize.has_fallthrough i.desc) i.next

(* Emission of the profiling prelude *)

let emit_profile () =
  match Config.system with
  | "linux" | "gnu" ->
      (* mcount preserves rax, rcx, rdx, rsi, rdi, r8, r9 explicitly
         and rbx, rbp, r12-r15 like all C functions.  This includes
         all the registers used for argument passing, so we don't
         need to preserve other regs.  We do need to initialize rbp
         like mcount expects it, though. *)
      (emit_string "	pushq	%r10\n");
      if not fp then
        (emit_string "	movq	%rsp, %rbp\n");
      (emit_char '	'; emit_call "mcount"; emit_char '\n');
      (emit_string "	popq	%r10\n")
  | _ ->
      () (*unsupported yet*)

(* Emission of a function declaration *)

let fundecl fundecl =
  function_name := fundecl.fun_name;
  fastcode_flag := fundecl.fun_fast;
  tailrec_entry_point := new_label();
  stack_offset := 0;
  call_gc_sites := [];
  bound_error_sites := [];
  bound_error_call := 0;
  (emit_string "	.text\n");
  emit_align 16;
  if macosx
  && not !Clflags.output_c_object
  && is_generic_function fundecl.fun_name
  then (* PR#4690 *)
    (emit_string "	.private_extern	"; emit_symbol fundecl.fun_name; emit_char '\n')
  else
    (emit_string "	.globl	"; emit_symbol fundecl.fun_name; emit_char '\n');
  (emit_symbol fundecl.fun_name; emit_string ":\n");
  emit_debug_info fundecl.fun_dbg;
  cfi_startproc ();
  if fp then begin
       (emit_string "	pushq	%rbp\n");
       cfi_adjust_cfa_offset 8;
       (emit_string "	movq	%rsp, %rbp\n");
  end;
  if !Clflags.gprofile then emit_profile();
  if frame_required() then begin
    let n = frame_size() - 8 - (if fp then 8 else 0) in
    (emit_string "	subq	$"; emit_int n; emit_string ", %rsp\n");
    cfi_adjust_cfa_offset n;
  end;
  (emit_label !tailrec_entry_point; emit_string ":\n");
  emit_all true fundecl.fun_body;
  List.iter emit_call_gc !call_gc_sites;
  emit_call_bound_errors ();
  cfi_endproc ();
  begin match Config.system with
    "linux" | "gnu" ->
      (emit_string "	.type	"; emit_symbol fundecl.fun_name; emit_string ",@function\n");
      (emit_string "	.size	"; emit_symbol fundecl.fun_name; emit_string ",.-"; emit_symbol fundecl.fun_name; emit_char '\n')
    | _ -> ()
  end

(* Emission of data *)

let emit_item = function
    Cglobal_symbol s ->
      (emit_string "	.globl	"; emit_symbol s; emit_char '\n');
  | Cdefine_symbol s ->
      (emit_symbol s; emit_string ":\n")
  | Cdefine_label lbl ->
      (emit_data_label lbl; emit_string ":\n")
  | Cint8 n ->
      (emit_string "	.byte	"; emit_int n; emit_char '\n')
  | Cint16 n ->
      (emit_string "	.word	"; emit_int n; emit_char '\n')
  | Cint32 n ->
      (emit_string "	.long	"; emit_nativeint n; emit_char '\n')
  | Cint n ->
      (emit_string "	.quad	"; emit_nativeint n; emit_char '\n')
  | Csingle f ->
      emit_float32_directive ".long" f
  | Cdouble f ->
      emit_float64_directive ".quad" f
  | Csymbol_address s ->
      (emit_string "	.quad	"; emit_symbol s; emit_char '\n')
  | Clabel_address lbl ->
      (emit_string "	.quad	"; emit_data_label lbl; emit_char '\n')
  | Cstring s ->
      emit_string_directive "	.ascii	" s
  | Cskip n ->
      if n > 0 then (emit_string "	.space	"; emit_int n; emit_char '\n')
  | Calign n ->
      emit_align n

let data l =
  (emit_string "	.data\n");
  List.iter emit_item l

(* Beginning / end of an assembly file *)

let begin_assembly() =
  reset_debug_info();                   (* PR#5603 *)
  float_constants := [];
  if !Clflags.dlcode then begin
    (* from amd64.S; could emit these constants on demand *)
    if macosx then
      (emit_string "	.literal16\n")
    else if mingw64 || cygwin then
      (emit_string "	.section .rdata,\"dr\"\n")
    else
      (emit_string "	.section .rodata.cst8,\"a\",@progbits\n");
    emit_align 16;
    (emit_symbol "caml_negf_mask"; emit_string ":	.quad   0x8000000000000000, 0\n");
    emit_align 16;
    (emit_symbol "caml_absf_mask"; emit_string ":	.quad   0x7FFFFFFFFFFFFFFF, 0xFFFFFFFFFFFFFFFF\n")
  end;
  let lbl_begin = Compilenv.make_symbol (Some "data_begin") in
  (emit_string "	.data\n");
  (emit_string "	.globl	"; emit_symbol lbl_begin; emit_char '\n');
  (emit_symbol lbl_begin; emit_string ":\n");
  let lbl_begin = Compilenv.make_symbol (Some "code_begin") in
  (emit_string "	.text\n");
  (emit_string "	.globl	"; emit_symbol lbl_begin; emit_char '\n');
  (emit_symbol lbl_begin; emit_string ":\n");
  if macosx then (emit_string "	nop\n") (* PR#4690 *)

let end_assembly() =
  if !float_constants <> [] then begin
    if macosx then
      (emit_string "	.literal8\n")
    else if mingw64 || cygwin then
      (emit_string "	.section .rdata,\"dr\"\n")
    else
      (emit_string "	.section .rodata.cst8,\"a\",@progbits\n");
    List.iter emit_float_constant !float_constants
  end;
  let lbl_end = Compilenv.make_symbol (Some "code_end") in
  (emit_string "	.text\n");
  if macosx then (emit_string "	nop\n"); (* suppress "ld warning: atom sorting error" *)
  (emit_string "	.globl	"; emit_symbol lbl_end; emit_char '\n');
  (emit_symbol lbl_end; emit_string ":\n");
  (emit_string "	.data\n");
  let lbl_end = Compilenv.make_symbol (Some "data_end") in
  (emit_string "	.globl	"; emit_symbol lbl_end; emit_char '\n');
  (emit_symbol lbl_end; emit_string ":\n");
  (emit_string "	.long	0\n");
  let lbl = Compilenv.make_symbol (Some "frametable") in
  (emit_string "	.globl	"; emit_symbol lbl; emit_char '\n');
  (emit_symbol lbl; emit_string ":\n");
  emit_frames
    { efa_label = (fun l -> (emit_string "	.quad	"; emit_label l; emit_char '\n'));
      efa_16 = (fun n -> (emit_string "	.word	"; emit_int n; emit_char '\n'));
      efa_32 = (fun n -> (emit_string "	.long	"; emit_int32 n; emit_char '\n'));
      efa_word = (fun n -> (emit_string "	.quad	"; emit_int n; emit_char '\n'));
      efa_align = emit_align;
      efa_label_rel =
        if macosx then begin
          let setcnt = ref 0 in
          fun lbl ofs ->
            incr setcnt;
            (emit_string "	.set	L$set$"; emit_int !setcnt; emit_string ", ("; emit_label lbl; emit_string " - .) + "; emit_int32 ofs; emit_char '\n');
            (emit_string "	.long L$set$"; emit_int !setcnt; emit_char '\n')
        end else begin
	  fun lbl ofs ->
             (emit_string "	.long	("; emit_label lbl; emit_string " - .) + "; emit_int32 ofs; emit_char '\n')
	end;
      efa_def_label = (fun l -> (emit_label l; emit_string ":\n"));
      efa_string = (fun s -> emit_string_directive "	.asciz	" s) };
  if Config.system = "linux" then
    (* Mark stack as non-executable, PR#4564 *)
    (emit_string "	.section .note.GNU-stack,\"\",%progbits\n")
