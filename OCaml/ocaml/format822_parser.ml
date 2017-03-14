type token =
  | FIELD of (string * (Format822.loc * string))
  | CONT of (Format822.loc * string)
  | BLANKLINE
  | EOF
  | PGPHEAD

open Parsing;;
let _ = parse_error;;
# 16 "src_ext/dose/deb/format822_parser.mly"

open ExtLib
(* exception Dup_stanza *)

(* XXX instead of ^ and triggering a reallocation everytime, I could use
 * String.concat to allocate the string of the right size once for all *)
let join (r1, v) (r2, cont) = (Format822.extend_loc r1 r2, v ^ cont)

# 20 "src_ext/dose/deb/format822_parser.ml"
let yytransl_const = [|
  259 (* BLANKLINE *);
    0 (* EOF *);
  260 (* PGPHEAD *);
    0|]

let yytransl_block = [|
  257 (* FIELD *);
  258 (* CONT *);
    0|]

let yylhs = "\255\255\
\003\000\003\000\001\000\001\000\002\000\002\000\002\000\002\000\
\004\000\004\000\006\000\006\000\007\000\008\000\008\000\005\000\
\005\000\009\000\009\000\000\000\000\000\000\000"

let yylen = "\002\000\
\005\000\001\000\001\000\002\000\001\000\002\000\002\000\001\000\
\001\000\002\000\002\000\003\000\001\000\001\000\002\000\002\000\
\003\000\002\000\003\000\002\000\002\000\002\000"

let yydefred = "\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\020\000\000\000\
\000\000\003\000\000\000\013\000\008\000\021\000\000\000\005\000\
\000\000\002\000\022\000\000\000\010\000\004\000\015\000\011\000\
\000\000\007\000\006\000\000\000\000\000\017\000\012\000\000\000\
\000\000\000\000\019\000\001\000"

let yydgoto = "\004\000\
\007\000\014\000\019\000\015\000\009\000\010\000\011\000\012\000\
\030\000"

let yysindex = "\022\000\
\015\255\009\000\001\000\000\000\001\255\004\255\000\000\010\255\
\010\255\000\000\010\000\000\000\000\000\000\000\002\000\000\000\
\004\255\000\000\000\000\017\255\000\000\000\000\000\000\000\000\
\010\255\000\000\000\000\010\255\018\255\000\000\000\000\023\255\
\017\255\009\000\000\000\000\000"

let yyrindex = "\000\000\
\000\000\000\000\000\000\000\000\000\000\013\000\000\000\000\000\
\008\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\005\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\006\000\000\000\000\000\000\000"

let yygindex = "\000\000\
\000\000\253\255\000\000\011\000\004\000\251\255\012\000\020\000\
\003\000"

let yytablesize = 270
let yytable = "\018\000\
\013\000\026\000\022\000\020\000\016\000\018\000\006\000\014\000\
\013\000\024\000\005\000\008\000\009\000\016\000\016\000\005\000\
\021\000\006\000\029\000\031\000\033\000\025\000\001\000\003\000\
\002\000\034\000\027\000\028\000\023\000\000\000\036\000\032\000\
\000\000\000\000\000\000\035\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\016\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\005\000\005\000\006\000\017\000\016\000\018\000\016\000\
\018\000\005\000\014\000\006\000\006\000\009\000"

let yycheck = "\003\000\
\000\000\000\000\008\000\003\001\000\000\000\000\003\001\000\000\
\000\000\000\000\001\001\001\000\000\000\002\000\003\000\001\001\
\006\000\003\001\002\001\025\000\003\001\011\000\001\000\002\000\
\003\000\003\001\015\000\017\000\009\000\255\255\034\000\028\000\
\255\255\255\255\255\255\033\000\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\034\000\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\255\
\255\255\001\001\001\001\003\001\004\001\001\001\001\001\003\001\
\003\001\001\001\003\001\003\001\003\001\001\001"

let yynames_const = "\
  BLANKLINE\000\
  EOF\000\
  PGPHEAD\000\
  "

let yynames_block = "\
  FIELD\000\
  CONT\000\
  "

let yyact = [|
  (fun _ -> failwith "parser")
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 3 : 'blanklines) in
    let _3 = (Parsing.peek_val __caml_parser_env 2 : 'field) in
    let _5 = (Parsing.peek_val __caml_parser_env 0 : (string * (Format822.loc * string)) list option) in
    Obj.repr(
# 39 "src_ext/dose/deb/format822_parser.mly"
                                                  ( _5 )
# 164 "src_ext/dose/deb/format822_parser.ml"
               : (string * (Format822.loc * string)) list option))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : (string * (Format822.loc * string)) list option) in
    Obj.repr(
# 40 "src_ext/dose/deb/format822_parser.mly"
               ( _1 )
# 171 "src_ext/dose/deb/format822_parser.ml"
               : (string * (Format822.loc * string)) list option))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'stanzas) in
    Obj.repr(
# 44 "src_ext/dose/deb/format822_parser.mly"
                        ( _1 )
# 178 "src_ext/dose/deb/format822_parser.ml"
               : (string * (Format822.loc * string)) list list))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'blanklines) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'stanzas) in
    Obj.repr(
# 45 "src_ext/dose/deb/format822_parser.mly"
                        ( _2 )
# 186 "src_ext/dose/deb/format822_parser.ml"
               : (string * (Format822.loc * string)) list list))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'stanza) in
    Obj.repr(
# 49 "src_ext/dose/deb/format822_parser.mly"
                      ( Some _1 )
# 193 "src_ext/dose/deb/format822_parser.ml"
               : (string * (Format822.loc * string)) list option))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'blanklines) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'stanza) in
    Obj.repr(
# 50 "src_ext/dose/deb/format822_parser.mly"
                      ( Some _2 )
# 201 "src_ext/dose/deb/format822_parser.ml"
               : (string * (Format822.loc * string)) list option))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'blanklines) in
    Obj.repr(
# 51 "src_ext/dose/deb/format822_parser.mly"
                      ( None )
# 208 "src_ext/dose/deb/format822_parser.ml"
               : (string * (Format822.loc * string)) list option))
; (fun __caml_parser_env ->
    Obj.repr(
# 52 "src_ext/dose/deb/format822_parser.mly"
                      ( None )
# 214 "src_ext/dose/deb/format822_parser.ml"
               : (string * (Format822.loc * string)) list option))
; (fun __caml_parser_env ->
    Obj.repr(
# 56 "src_ext/dose/deb/format822_parser.mly"
                         ()
# 220 "src_ext/dose/deb/format822_parser.ml"
               : 'blanklines))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'blanklines) in
    Obj.repr(
# 57 "src_ext/dose/deb/format822_parser.mly"
                         ()
# 227 "src_ext/dose/deb/format822_parser.ml"
               : 'blanklines))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'stanza) in
    Obj.repr(
# 61 "src_ext/dose/deb/format822_parser.mly"
                        ( [ _1 ] )
# 234 "src_ext/dose/deb/format822_parser.ml"
               : 'stanzas))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'stanza) in
    let _2 = (Parsing.peek_val __caml_parser_env 1 : 'blanklines) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'stanzas) in
    Obj.repr(
# 62 "src_ext/dose/deb/format822_parser.mly"
                              ( _1 :: _3 )
# 243 "src_ext/dose/deb/format822_parser.ml"
               : 'stanzas))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'fields) in
    Obj.repr(
# 66 "src_ext/dose/deb/format822_parser.mly"
                ( (* let keys = List.map fst $1 in
                  (* check for re-defined keys *)
                  if List.length (List.unique keys) < List.length keys then
                    raise Dup_stanza
                  else
                    *)
                    _1
                )
# 257 "src_ext/dose/deb/format822_parser.ml"
               : 'stanza))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'field) in
    Obj.repr(
# 77 "src_ext/dose/deb/format822_parser.mly"
                        ( [ _1 ] )
# 264 "src_ext/dose/deb/format822_parser.ml"
               : 'fields))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'field) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'fields) in
    Obj.repr(
# 78 "src_ext/dose/deb/format822_parser.mly"
                        ( _1 :: _2 )
# 272 "src_ext/dose/deb/format822_parser.ml"
               : 'fields))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : string * (Format822.loc * string)) in
    Obj.repr(
# 82 "src_ext/dose/deb/format822_parser.mly"
                              ( _1 )
# 279 "src_ext/dose/deb/format822_parser.ml"
               : 'field))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : string * (Format822.loc * string)) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'linecont) in
    Obj.repr(
# 83 "src_ext/dose/deb/format822_parser.mly"
                              ( let k, v = _1 in k, (join v _3) )
# 287 "src_ext/dose/deb/format822_parser.ml"
               : 'field))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : Format822.loc * string) in
    Obj.repr(
# 87 "src_ext/dose/deb/format822_parser.mly"
                              ( _1 )
# 294 "src_ext/dose/deb/format822_parser.ml"
               : 'linecont))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : Format822.loc * string) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'linecont) in
    Obj.repr(
# 88 "src_ext/dose/deb/format822_parser.mly"
                              ( join _1 _3 )
# 302 "src_ext/dose/deb/format822_parser.ml"
               : 'linecont))
(* Entry doc_822 *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry stanza_822 *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry doc_822_sign *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
|]
let yytables =
  { Parsing.actions=yyact;
    Parsing.transl_const=yytransl_const;
    Parsing.transl_block=yytransl_block;
    Parsing.lhs=yylhs;
    Parsing.len=yylen;
    Parsing.defred=yydefred;
    Parsing.dgoto=yydgoto;
    Parsing.sindex=yysindex;
    Parsing.rindex=yyrindex;
    Parsing.gindex=yygindex;
    Parsing.tablesize=yytablesize;
    Parsing.table=yytable;
    Parsing.check=yycheck;
    Parsing.error_function=parse_error;
    Parsing.names_const=yynames_const;
    Parsing.names_block=yynames_block }
let doc_822 (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 1 lexfun lexbuf : (string * (Format822.loc * string)) list list)
let stanza_822 (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 3 lexfun lexbuf : (string * (Format822.loc * string)) list option)
let doc_822_sign (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 2 lexfun lexbuf : (string * (Format822.loc * string)) list option)
;;
# 92 "src_ext/dose/deb/format822_parser.mly"

let error_wrapper f =
  fun lexer lexbuf ->
    try f lexer lexbuf
    with
      | Parsing.Parse_error ->
          raise (Format822.Parse_error_822
                   ("RFC 822 (stanza structure) parse error",
                    Format822.loc_of_lexbuf lexbuf))
(*      | Dup_stanza ->
          raise (Format822.Parse_error_822
                   ("duplicate keys in stanza",
                    Format822.loc_of_lexbuf lexbuf))
*)
let doc_822 = error_wrapper doc_822
let stanza_822 = error_wrapper stanza_822

# 353 "src_ext/dose/deb/format822_parser.ml"
