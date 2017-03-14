type token =
  | STRING of (string)
  | IDENT of (string)
  | SYMBOL of (string)
  | BOOL of (bool)
  | EOF
  | LBRACKET
  | RBRACKET
  | LPAR
  | RPAR
  | LBRACE
  | RBRACE
  | COLON
  | INT of (int)

open Parsing;;
let _ = parse_error;;
# 18 "src/core/opamParser.mly"

open OpamTypes

# 23 "src/core/opamParser.ml"
let yytransl_const = [|
    0 (* EOF *);
  261 (* LBRACKET *);
  262 (* RBRACKET *);
  263 (* LPAR *);
  264 (* RPAR *);
  265 (* LBRACE *);
  266 (* RBRACE *);
  267 (* COLON *);
    0|]

let yytransl_block = [|
  257 (* STRING *);
  258 (* IDENT *);
  259 (* SYMBOL *);
  260 (* BOOL *);
  268 (* INT *);
    0|]

let yylhs = "\255\255\
\001\000\003\000\003\000\004\000\004\000\002\000\002\000\002\000\
\002\000\002\000\002\000\002\000\002\000\005\000\005\000\000\000\
\000\000"

let yylen = "\002\000\
\002\000\002\000\000\000\003\000\005\000\001\000\001\000\001\000\
\001\000\001\000\003\000\003\000\004\000\000\000\002\000\002\000\
\002\000"

let yydefred = "\000\000\
\000\000\000\000\000\000\000\000\016\000\000\000\000\000\008\000\
\010\000\009\000\006\000\000\000\000\000\007\000\000\000\000\000\
\000\000\001\000\002\000\000\000\000\000\000\000\000\000\000\000\
\000\000\015\000\012\000\011\000\000\000\000\000\013\000\005\000"

let yydgoto = "\003\000\
\005\000\020\000\006\000\007\000\021\000"

let yysindex = "\035\000\
\001\255\020\255\000\000\003\255\000\000\012\000\001\255\000\000\
\000\000\000\000\000\000\020\255\020\255\000\000\253\254\007\255\
\020\255\000\000\000\000\006\255\022\255\026\255\020\255\001\255\
\253\254\000\000\000\000\000\000\028\255\029\255\000\000\000\000"

let yyrindex = "\000\000\
\030\000\000\000\000\000\000\000\000\000\000\000\002\000\000\000\
\000\000\000\000\000\000\034\255\033\255\000\000\042\000\000\000\
\000\000\000\000\000\000\025\255\000\000\000\000\035\255\036\255\
\001\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"

let yygindex = "\000\000\
\000\000\003\000\249\255\000\000\006\000"

let yytablesize = 268
let yytable = "\019\000\
\004\000\003\000\004\000\016\000\015\000\023\000\008\000\009\000\
\010\000\011\000\012\000\018\000\013\000\017\000\023\000\024\000\
\030\000\014\000\022\000\025\000\008\000\009\000\010\000\011\000\
\012\000\026\000\013\000\027\000\029\000\003\000\014\000\014\000\
\014\000\028\000\014\000\001\000\002\000\031\000\032\000\014\000\
\014\000\017\000\000\000\000\000\014\000\003\000\000\000\000\000\
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
\000\000\000\000\004\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\004\000\003\000"

let yycheck = "\007\000\
\000\000\000\000\002\001\001\001\002\000\009\001\001\001\002\001\
\003\001\004\001\005\001\000\000\007\001\011\001\009\001\009\001\
\024\000\012\001\013\000\017\000\001\001\002\001\003\001\004\001\
\005\001\020\000\007\001\006\001\023\000\000\000\006\001\012\001\
\008\001\008\001\010\001\001\000\002\000\010\001\010\001\006\001\
\008\001\000\000\255\255\255\255\010\001\010\001\255\255\255\255\
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
\255\255\255\255\002\001\255\255\255\255\255\255\255\255\255\255\
\255\255\255\255\010\001\010\001"

let yynames_const = "\
  EOF\000\
  LBRACKET\000\
  RBRACKET\000\
  LPAR\000\
  RPAR\000\
  LBRACE\000\
  RBRACE\000\
  COLON\000\
  "

let yynames_block = "\
  STRING\000\
  IDENT\000\
  SYMBOL\000\
  BOOL\000\
  INT\000\
  "

let yyact = [|
  (fun _ -> failwith "parser")
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'items) in
    Obj.repr(
# 44 "src/core/opamParser.mly"
            ( fun file_name ->
        { file_contents = _1; file_name; file_format = OpamVersion.current } )
# 177 "src/core/opamParser.ml"
               : string -> OpamTypes.file))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'item) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'items) in
    Obj.repr(
# 49 "src/core/opamParser.mly"
             ( _1 :: _2 )
# 185 "src/core/opamParser.ml"
               : 'items))
; (fun __caml_parser_env ->
    Obj.repr(
# 50 "src/core/opamParser.mly"
             ( [] )
# 191 "src/core/opamParser.ml"
               : 'items))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : string) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : OpamTypes.value) in
    Obj.repr(
# 54 "src/core/opamParser.mly"
                                   ( Variable (_1, _3) )
# 199 "src/core/opamParser.ml"
               : 'item))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 4 : string) in
    let _2 = (Parsing.peek_val __caml_parser_env 3 : string) in
    let _4 = (Parsing.peek_val __caml_parser_env 1 : 'items) in
    Obj.repr(
# 55 "src/core/opamParser.mly"
                                   ( Section {section_kind=_1; section_name=_2; section_items= _4} )
# 208 "src/core/opamParser.ml"
               : 'item))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : bool) in
    Obj.repr(
# 59 "src/core/opamParser.mly"
                             ( Bool _1 )
# 215 "src/core/opamParser.ml"
               : OpamTypes.value))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 60 "src/core/opamParser.mly"
                             ( Int _1 )
# 222 "src/core/opamParser.ml"
               : OpamTypes.value))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 61 "src/core/opamParser.mly"
                             ( String _1 )
# 229 "src/core/opamParser.ml"
               : OpamTypes.value))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 62 "src/core/opamParser.mly"
                             ( Symbol _1 )
# 236 "src/core/opamParser.ml"
               : OpamTypes.value))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 63 "src/core/opamParser.mly"
                             ( Ident _1 )
# 243 "src/core/opamParser.ml"
               : OpamTypes.value))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 1 : 'values) in
    Obj.repr(
# 64 "src/core/opamParser.mly"
                             ( Group _2 )
# 250 "src/core/opamParser.ml"
               : OpamTypes.value))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 1 : 'values) in
    Obj.repr(
# 65 "src/core/opamParser.mly"
                             ( List _2 )
# 257 "src/core/opamParser.ml"
               : OpamTypes.value))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 3 : OpamTypes.value) in
    let _3 = (Parsing.peek_val __caml_parser_env 1 : 'values) in
    Obj.repr(
# 66 "src/core/opamParser.mly"
                             ( Option (_1, _3) )
# 265 "src/core/opamParser.ml"
               : OpamTypes.value))
; (fun __caml_parser_env ->
    Obj.repr(
# 70 "src/core/opamParser.mly"
               ( [] )
# 271 "src/core/opamParser.ml"
               : 'values))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : OpamTypes.value) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'values) in
    Obj.repr(
# 71 "src/core/opamParser.mly"
               ( _1 :: _2 )
# 279 "src/core/opamParser.ml"
               : 'values))
(* Entry main *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry value *)
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
let main (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 1 lexfun lexbuf : string -> OpamTypes.file)
let value (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 2 lexfun lexbuf : OpamTypes.value)
;;
# 75 "src/core/opamParser.mly"

let error lexbuf exn msg =
  let curr = lexbuf.Lexing.lex_curr_p in
  let start = lexbuf.Lexing.lex_start_p in
  OpamGlobals.error
      "File %S, line %d, character %d-%d: %s."
      curr.Lexing.pos_fname
      start.Lexing.pos_lnum
      (start.Lexing.pos_cnum - start.Lexing.pos_bol)
      (curr.Lexing.pos_cnum - curr.Lexing.pos_bol)
      msg;
  raise exn

let main t l f =
  try main t l f
  with
  | Lexer_error msg     as e -> error l e msg
  | Parsing.Parse_error as e -> error l e "parse error"
# 327 "src/core/opamParser.ml"
