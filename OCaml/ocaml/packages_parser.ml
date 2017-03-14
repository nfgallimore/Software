type token =
  | IDENT of (string)
  | VIDENT of (string)
  | STRING of (string)
  | RELOP of (string)
  | LBRACKET
  | RBRACKET
  | LPAREN
  | RPAREN
  | LT
  | GT
  | COMMA
  | PIPE
  | EQ
  | BANG
  | PLUS
  | MINUS
  | COLON
  | SLASH
  | EOL

open Parsing;;
let _ = parse_error;;
# 3 "src_ext/dose/deb/packages_parser.mly"

open ExtLib

let parse_relop = function
  | "="  -> `Eq
  | "!=" -> `Neq
  | ">=" -> `Geq
  | ">" | ">>"  -> `Gt
  | "<=" -> `Leq
  | "<" | "<<"  -> `Lt
  | _ -> assert false   (* lexer shouldn't have returned such a RELOP! *)

let parse_multiarch = function
  |("None"|"none") -> `None
  |("Allowed"|"allowed") -> `Allowed
  |("Foreign"|"foreign") -> `Foreign
  |("Same"|"same") -> `Same
  |s -> raise (Format822.Type_error ("Field Multi-Arch has a wrong value : "^ s))

# 45 "src_ext/dose/deb/packages_parser.ml"
let yytransl_const = [|
  261 (* LBRACKET *);
  262 (* RBRACKET *);
  263 (* LPAREN *);
  264 (* RPAREN *);
  265 (* LT *);
  266 (* GT *);
  267 (* COMMA *);
  268 (* PIPE *);
  269 (* EQ *);
  270 (* BANG *);
  271 (* PLUS *);
  272 (* MINUS *);
  273 (* COLON *);
  274 (* SLASH *);
  275 (* EOL *);
    0|]

let yytransl_block = [|
  257 (* IDENT *);
  258 (* VIDENT *);
  259 (* STRING *);
  260 (* RELOP *);
    0|]

let yylhs = "\255\255\
\001\000\002\000\004\000\005\000\006\000\007\000\008\000\009\000\
\010\000\011\000\013\000\012\000\003\000\014\000\015\000\016\000\
\017\000\017\000\027\000\027\000\027\000\027\000\018\000\028\000\
\028\000\019\000\020\000\020\000\029\000\029\000\021\000\021\000\
\030\000\030\000\031\000\031\000\031\000\031\000\023\000\023\000\
\034\000\034\000\022\000\022\000\035\000\035\000\036\000\036\000\
\032\000\032\000\037\000\037\000\038\000\038\000\033\000\033\000\
\039\000\039\000\026\000\026\000\040\000\040\000\025\000\025\000\
\025\000\041\000\041\000\041\000\024\000\024\000\042\000\042\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000"

let yylen = "\002\000\
\002\000\002\000\002\000\002\000\002\000\002\000\002\000\002\000\
\002\000\002\000\002\000\002\000\002\000\001\000\001\000\001\000\
\001\000\004\000\001\000\001\000\001\000\001\000\001\000\000\000\
\004\000\002\000\000\000\001\000\001\000\003\000\001\000\003\000\
\001\000\003\000\001\000\004\000\004\000\007\000\000\000\001\000\
\001\000\003\000\001\000\003\000\001\000\003\000\002\000\001\000\
\000\000\001\000\001\000\002\000\002\000\001\000\000\000\001\000\
\001\000\002\000\000\000\001\000\001\000\002\000\002\000\002\000\
\001\000\001\000\003\000\003\000\000\000\001\000\001\000\002\000\
\002\000\002\000\002\000\002\000\002\000\002\000\002\000\002\000\
\002\000\002\000\002\000\002\000\002\000"

let yydefred = "\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\014\000\073\000\
\000\000\015\000\074\000\000\000\000\000\075\000\000\000\060\000\
\016\000\076\000\000\000\000\000\077\000\000\000\023\000\078\000\
\000\000\079\000\000\000\000\000\080\000\000\000\000\000\028\000\
\081\000\000\000\000\000\000\000\082\000\000\000\000\000\000\000\
\000\000\083\000\000\000\000\000\040\000\000\000\000\000\084\000\
\000\000\000\000\065\000\085\000\000\000\000\000\070\000\001\000\
\002\000\062\000\013\000\003\000\000\000\004\000\005\000\000\000\
\026\000\006\000\000\000\007\000\000\000\008\000\000\000\000\000\
\000\000\009\000\000\000\000\000\010\000\000\000\063\000\064\000\
\000\000\000\000\012\000\011\000\072\000\000\000\019\000\020\000\
\021\000\022\000\000\000\030\000\034\000\032\000\048\000\000\000\
\000\000\000\000\050\000\054\000\000\000\000\000\000\000\056\000\
\046\000\044\000\042\000\067\000\068\000\018\000\000\000\047\000\
\000\000\052\000\053\000\037\000\058\000\025\000\000\000\000\000\
\038\000"

let yydgoto = "\014\000\
\016\000\019\000\022\000\026\000\029\000\032\000\034\000\037\000\
\041\000\045\000\050\000\056\000\060\000\017\000\020\000\027\000\
\030\000\035\000\046\000\039\000\043\000\047\000\051\000\061\000\
\062\000\023\000\099\000\073\000\040\000\044\000\048\000\105\000\
\110\000\053\000\049\000\106\000\107\000\111\000\112\000\024\000\
\059\000\063\000"

let yysindex = "\075\000\
\028\255\046\255\048\255\049\255\050\255\053\255\053\255\053\255\
\053\255\053\255\053\255\007\255\007\255\000\000\000\000\000\000\
\012\255\000\000\000\000\037\255\048\255\000\000\038\255\000\000\
\000\000\000\000\039\255\052\255\000\000\042\255\000\000\000\000\
\043\255\000\000\056\255\045\255\000\000\044\255\047\255\000\000\
\000\000\055\255\051\255\054\255\000\000\036\255\070\255\060\255\
\057\255\000\000\071\255\063\255\000\000\053\255\053\255\000\000\
\030\255\072\255\000\000\000\000\073\255\007\255\000\000\000\000\
\000\000\000\000\000\000\000\000\046\255\000\000\000\000\003\255\
\000\000\000\000\053\255\000\000\053\255\000\000\053\255\013\255\
\014\255\000\000\053\255\053\255\000\000\053\255\000\000\000\000\
\046\255\092\255\000\000\000\000\000\000\086\255\000\000\000\000\
\000\000\000\000\046\255\000\000\000\000\000\000\000\000\094\255\
\090\255\013\255\000\000\000\000\096\255\088\255\014\255\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\091\255\000\000\
\093\255\000\000\000\000\000\000\000\000\000\000\014\255\095\255\
\000\000"

let yyrindex = "\000\000\
\000\000\000\000\081\255\000\000\000\000\000\000\000\000\082\255\
\000\000\000\000\084\255\000\000\085\255\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\087\255\000\000\000\000\000\000\
\000\000\000\000\000\000\089\255\000\000\000\000\000\000\000\000\
\000\000\000\000\021\255\000\000\000\000\097\255\000\000\000\000\
\000\000\254\254\000\000\098\255\000\000\025\255\000\000\023\255\
\099\255\000\000\000\000\100\255\000\000\000\000\000\000\000\000\
\005\255\000\000\000\000\000\000\000\000\043\255\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\101\255\
\102\255\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\103\255\000\000\000\000\000\000\000\000\104\255\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\027\255\000\000\000\000\000\000\000\000\000\000\102\255\000\000\
\000\000"

let yygindex = "\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\192\255\000\000\
\000\000\254\255\250\255\000\000\031\000\027\000\000\000\000\000\
\101\000\000\000\000\000\000\000\040\000\043\000\245\255\000\000\
\251\255\035\000\041\000\000\000\017\000\000\000\014\000\105\000\
\220\255\065\000"

let yytablesize = 127
let yytable = "\052\000\
\036\000\038\000\042\000\033\000\094\000\066\000\095\000\031\000\
\033\000\057\000\057\000\096\000\097\000\103\000\108\000\098\000\
\033\000\087\000\088\000\066\000\066\000\054\000\055\000\066\000\
\116\000\024\000\104\000\109\000\015\000\024\000\064\000\024\000\
\024\000\045\000\119\000\035\000\035\000\036\000\036\000\024\000\
\080\000\045\000\089\000\035\000\081\000\036\000\018\000\090\000\
\021\000\025\000\028\000\057\000\057\000\031\000\075\000\065\000\
\067\000\068\000\069\000\057\000\070\000\071\000\072\000\074\000\
\079\000\076\000\077\000\084\000\038\000\078\000\042\000\083\000\
\042\000\086\000\052\000\001\000\002\000\004\000\005\000\006\000\
\007\000\008\000\009\000\010\000\011\000\012\000\013\000\003\000\
\082\000\085\000\091\000\092\000\117\000\118\000\120\000\121\000\
\123\000\124\000\126\000\059\000\027\000\127\000\039\000\069\000\
\129\000\061\000\049\000\017\000\051\000\102\000\114\000\055\000\
\058\000\057\000\100\000\029\000\031\000\043\000\041\000\101\000\
\115\000\128\000\122\000\113\000\125\000\066\000\093\000"

let yycheck = "\011\000\
\007\000\008\000\009\000\006\000\069\000\001\001\004\001\001\001\
\011\001\012\000\013\000\009\001\010\001\001\001\001\001\013\001\
\019\001\054\000\055\000\015\001\016\001\015\001\016\001\019\001\
\089\000\005\001\014\001\014\001\001\001\009\001\019\001\011\001\
\012\001\011\001\099\000\011\001\012\001\011\001\012\001\019\001\
\005\001\019\001\013\001\019\001\009\001\019\001\001\001\018\001\
\001\001\001\001\001\001\054\000\055\000\001\001\011\001\019\001\
\019\001\019\001\007\001\062\000\019\001\019\001\007\001\019\001\
\011\001\019\001\012\001\011\001\075\000\019\001\077\000\012\001\
\079\000\011\001\086\000\001\000\002\000\003\000\004\000\005\000\
\006\000\007\000\008\000\009\000\010\000\011\000\012\000\013\000\
\019\001\019\001\019\001\019\001\001\001\008\001\001\001\006\001\
\001\001\010\001\008\001\019\001\019\001\009\001\019\001\019\001\
\010\001\019\001\006\001\019\001\006\001\079\000\084\000\010\001\
\012\000\010\001\075\000\019\001\019\001\019\001\019\001\077\000\
\086\000\127\000\106\000\083\000\111\000\021\000\062\000"

let yynames_const = "\
  LBRACKET\000\
  RBRACKET\000\
  LPAREN\000\
  RPAREN\000\
  LT\000\
  GT\000\
  COMMA\000\
  PIPE\000\
  EQ\000\
  BANG\000\
  PLUS\000\
  MINUS\000\
  COLON\000\
  SLASH\000\
  EOL\000\
  "

let yynames_block = "\
  IDENT\000\
  VIDENT\000\
  STRING\000\
  RELOP\000\
  "

let yyact = [|
  (fun _ -> failwith "parser")
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'pkgname) in
    Obj.repr(
# 57 "src_ext/dose/deb/packages_parser.mly"
                         ( _1 )
# 239 "src_ext/dose/deb/packages_parser.ml"
               : Format822.name))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'version) in
    Obj.repr(
# 58 "src_ext/dose/deb/packages_parser.mly"
                         ( _1 )
# 246 "src_ext/dose/deb/packages_parser.ml"
               : Format822.version))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'multiarch) in
    Obj.repr(
# 59 "src_ext/dose/deb/packages_parser.mly"
                             ( _1 )
# 253 "src_ext/dose/deb/packages_parser.ml"
               : Format822.multiarch))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'source) in
    Obj.repr(
# 60 "src_ext/dose/deb/packages_parser.mly"
                       ( _1 )
# 260 "src_ext/dose/deb/packages_parser.ml"
               : Format822.source))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'vpkgname) in
    Obj.repr(
# 62 "src_ext/dose/deb/packages_parser.mly"
                           ( _1 )
# 267 "src_ext/dose/deb/packages_parser.ml"
               : Format822.vpkgname))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'vpkg) in
    Obj.repr(
# 63 "src_ext/dose/deb/packages_parser.mly"
                   ( _1 )
# 274 "src_ext/dose/deb/packages_parser.ml"
               : Format822.vpkg))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'vpkglist) in
    Obj.repr(
# 65 "src_ext/dose/deb/packages_parser.mly"
                           ( _1 )
# 281 "src_ext/dose/deb/packages_parser.ml"
               : Format822.vpkglist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'vpkgformula) in
    Obj.repr(
# 66 "src_ext/dose/deb/packages_parser.mly"
                                 ( _1 )
# 288 "src_ext/dose/deb/packages_parser.ml"
               : Format822.vpkgformula))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'builddepsformula) in
    Obj.repr(
# 68 "src_ext/dose/deb/packages_parser.mly"
                                           ( _1 )
# 295 "src_ext/dose/deb/packages_parser.ml"
               : Format822.builddepsformula))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'builddepslist) in
    Obj.repr(
# 69 "src_ext/dose/deb/packages_parser.mly"
                                     ( _1 )
# 302 "src_ext/dose/deb/packages_parser.ml"
               : Format822.builddepslist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'reqlist) in
    Obj.repr(
# 71 "src_ext/dose/deb/packages_parser.mly"
                             ( _1 )
# 309 "src_ext/dose/deb/packages_parser.ml"
               : Format822.vpkgreq list))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'request) in
    Obj.repr(
# 72 "src_ext/dose/deb/packages_parser.mly"
                         ( _1 )
# 316 "src_ext/dose/deb/packages_parser.ml"
               : Format822.vpkgreq))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'archlist) in
    Obj.repr(
# 73 "src_ext/dose/deb/packages_parser.mly"
                           ( _1 )
# 323 "src_ext/dose/deb/packages_parser.ml"
               : Format822.architecture list))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 77 "src_ext/dose/deb/packages_parser.mly"
               ( _1 )
# 330 "src_ext/dose/deb/packages_parser.ml"
               : 'pkgname))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 78 "src_ext/dose/deb/packages_parser.mly"
               ( _1 )
# 337 "src_ext/dose/deb/packages_parser.ml"
               : 'version))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 79 "src_ext/dose/deb/packages_parser.mly"
                 ( parse_multiarch _1 )
# 344 "src_ext/dose/deb/packages_parser.ml"
               : 'multiarch))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 82 "src_ext/dose/deb/packages_parser.mly"
                                ( (_1,None) )
# 351 "src_ext/dose/deb/packages_parser.ml"
               : 'source))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 3 : string) in
    let _3 = (Parsing.peek_val __caml_parser_env 1 : 'version) in
    Obj.repr(
# 83 "src_ext/dose/deb/packages_parser.mly"
                                ( (_1,Some (_3)) )
# 359 "src_ext/dose/deb/packages_parser.ml"
               : 'source))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 86 "src_ext/dose/deb/packages_parser.mly"
                ( _1 )
# 366 "src_ext/dose/deb/packages_parser.ml"
               : 'relop))
; (fun __caml_parser_env ->
    Obj.repr(
# 87 "src_ext/dose/deb/packages_parser.mly"
                ( "<" )
# 372 "src_ext/dose/deb/packages_parser.ml"
               : 'relop))
; (fun __caml_parser_env ->
    Obj.repr(
# 88 "src_ext/dose/deb/packages_parser.mly"
                ( ">" )
# 378 "src_ext/dose/deb/packages_parser.ml"
               : 'relop))
; (fun __caml_parser_env ->
    Obj.repr(
# 89 "src_ext/dose/deb/packages_parser.mly"
                ( "=" )
# 384 "src_ext/dose/deb/packages_parser.ml"
               : 'relop))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 95 "src_ext/dose/deb/packages_parser.mly"
                      ( try let (n,a) = String.split _1 ":" in (n,Some a) 
                        with Invalid_string -> (_1,None) )
# 392 "src_ext/dose/deb/packages_parser.ml"
               : 'vpkgname))
; (fun __caml_parser_env ->
    Obj.repr(
# 100 "src_ext/dose/deb/packages_parser.mly"
                               ( None )
# 398 "src_ext/dose/deb/packages_parser.ml"
               : 'constr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 2 : 'relop) in
    let _3 = (Parsing.peek_val __caml_parser_env 1 : 'version) in
    Obj.repr(
# 101 "src_ext/dose/deb/packages_parser.mly"
                               ( Some (_2, _3) )
# 406 "src_ext/dose/deb/packages_parser.ml"
               : 'constr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'vpkgname) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'constr) in
    Obj.repr(
# 104 "src_ext/dose/deb/packages_parser.mly"
                      ( (_1, _2) )
# 414 "src_ext/dose/deb/packages_parser.ml"
               : 'vpkg))
; (fun __caml_parser_env ->
    Obj.repr(
# 107 "src_ext/dose/deb/packages_parser.mly"
                ( [] )
# 420 "src_ext/dose/deb/packages_parser.ml"
               : 'vpkglist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'vpkglist_ne) in
    Obj.repr(
# 108 "src_ext/dose/deb/packages_parser.mly"
                ( _1 )
# 427 "src_ext/dose/deb/packages_parser.ml"
               : 'vpkglist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'vpkg) in
    Obj.repr(
# 112 "src_ext/dose/deb/packages_parser.mly"
                                ( [ _1 ] )
# 434 "src_ext/dose/deb/packages_parser.ml"
               : 'vpkglist_ne))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'vpkg) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'vpkglist_ne) in
    Obj.repr(
# 113 "src_ext/dose/deb/packages_parser.mly"
                                ( _1 :: _3 )
# 442 "src_ext/dose/deb/packages_parser.ml"
               : 'vpkglist_ne))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'or_formula) in
    Obj.repr(
# 117 "src_ext/dose/deb/packages_parser.mly"
                                  ( [ _1 ] )
# 449 "src_ext/dose/deb/packages_parser.ml"
               : 'vpkgformula))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'or_formula) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'vpkgformula) in
    Obj.repr(
# 118 "src_ext/dose/deb/packages_parser.mly"
                                  ( _1 :: _3 )
# 457 "src_ext/dose/deb/packages_parser.ml"
               : 'vpkgformula))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'vpkg) in
    Obj.repr(
# 122 "src_ext/dose/deb/packages_parser.mly"
                                ( [ _1 ] )
# 464 "src_ext/dose/deb/packages_parser.ml"
               : 'or_formula))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'vpkg) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'or_formula) in
    Obj.repr(
# 123 "src_ext/dose/deb/packages_parser.mly"
                                ( _1 :: _3 )
# 472 "src_ext/dose/deb/packages_parser.ml"
               : 'or_formula))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'vpkg) in
    Obj.repr(
# 129 "src_ext/dose/deb/packages_parser.mly"
                                   ( (_1,[],[]) )
# 479 "src_ext/dose/deb/packages_parser.ml"
               : 'buidldep))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 3 : 'vpkg) in
    let _3 = (Parsing.peek_val __caml_parser_env 1 : 'buildarchlist) in
    Obj.repr(
# 130 "src_ext/dose/deb/packages_parser.mly"
                                        ( (_1,_3,[]) )
# 487 "src_ext/dose/deb/packages_parser.ml"
               : 'buidldep))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 3 : 'vpkg) in
    let _3 = (Parsing.peek_val __caml_parser_env 1 : 'buildprofilelist) in
    Obj.repr(
# 131 "src_ext/dose/deb/packages_parser.mly"
                               ( (_1,[],_3) )
# 495 "src_ext/dose/deb/packages_parser.ml"
               : 'buidldep))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 6 : 'vpkg) in
    let _3 = (Parsing.peek_val __caml_parser_env 4 : 'buildarchlist) in
    let _6 = (Parsing.peek_val __caml_parser_env 1 : 'buildprofilelist) in
    Obj.repr(
# 132 "src_ext/dose/deb/packages_parser.mly"
                                                               ( (_1,_3,_6) )
# 504 "src_ext/dose/deb/packages_parser.ml"
               : 'buidldep))
; (fun __caml_parser_env ->
    Obj.repr(
# 136 "src_ext/dose/deb/packages_parser.mly"
                     ( [] )
# 510 "src_ext/dose/deb/packages_parser.ml"
               : 'builddepslist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'builddepslist_ne) in
    Obj.repr(
# 137 "src_ext/dose/deb/packages_parser.mly"
                     ( _1 )
# 517 "src_ext/dose/deb/packages_parser.ml"
               : 'builddepslist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'buidldep) in
    Obj.repr(
# 141 "src_ext/dose/deb/packages_parser.mly"
                                     ( [ _1 ] )
# 524 "src_ext/dose/deb/packages_parser.ml"
               : 'builddepslist_ne))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'buidldep) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'builddepslist_ne) in
    Obj.repr(
# 142 "src_ext/dose/deb/packages_parser.mly"
                                     ( _1 :: _3 )
# 532 "src_ext/dose/deb/packages_parser.ml"
               : 'builddepslist_ne))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'builddeps_or_formula) in
    Obj.repr(
# 146 "src_ext/dose/deb/packages_parser.mly"
                                                    ( [ _1 ] )
# 539 "src_ext/dose/deb/packages_parser.ml"
               : 'builddepsformula))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'builddeps_or_formula) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'builddepsformula) in
    Obj.repr(
# 147 "src_ext/dose/deb/packages_parser.mly"
                                                   ( _1 :: _3 )
# 547 "src_ext/dose/deb/packages_parser.ml"
               : 'builddepsformula))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'buidldep) in
    Obj.repr(
# 151 "src_ext/dose/deb/packages_parser.mly"
                                         ( [ _1 ] )
# 554 "src_ext/dose/deb/packages_parser.ml"
               : 'builddeps_or_formula))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'buidldep) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'builddeps_or_formula) in
    Obj.repr(
# 152 "src_ext/dose/deb/packages_parser.mly"
                                         ( _1 :: _3 )
# 562 "src_ext/dose/deb/packages_parser.ml"
               : 'builddeps_or_formula))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 158 "src_ext/dose/deb/packages_parser.mly"
                           ( (false,_2) )
# 569 "src_ext/dose/deb/packages_parser.ml"
               : 'buildarch))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 159 "src_ext/dose/deb/packages_parser.mly"
                           ( (true,_1)  )
# 576 "src_ext/dose/deb/packages_parser.ml"
               : 'buildarch))
; (fun __caml_parser_env ->
    Obj.repr(
# 163 "src_ext/dose/deb/packages_parser.mly"
                ( [] )
# 582 "src_ext/dose/deb/packages_parser.ml"
               : 'buildarchlist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'buildarchlist_ne) in
    Obj.repr(
# 164 "src_ext/dose/deb/packages_parser.mly"
                     ( _1 )
# 589 "src_ext/dose/deb/packages_parser.ml"
               : 'buildarchlist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'buildarch) in
    Obj.repr(
# 168 "src_ext/dose/deb/packages_parser.mly"
                                    ( [ _1 ] )
# 596 "src_ext/dose/deb/packages_parser.ml"
               : 'buildarchlist_ne))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'buildarch) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'buildarchlist_ne) in
    Obj.repr(
# 169 "src_ext/dose/deb/packages_parser.mly"
                                    ( _1 :: _2 )
# 604 "src_ext/dose/deb/packages_parser.ml"
               : 'buildarchlist_ne))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 175 "src_ext/dose/deb/packages_parser.mly"
                           ( (false,_2) )
# 611 "src_ext/dose/deb/packages_parser.ml"
               : 'buildprofile))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 176 "src_ext/dose/deb/packages_parser.mly"
                           ( (true,_1)  )
# 618 "src_ext/dose/deb/packages_parser.ml"
               : 'buildprofile))
; (fun __caml_parser_env ->
    Obj.repr(
# 180 "src_ext/dose/deb/packages_parser.mly"
                ( [] )
# 624 "src_ext/dose/deb/packages_parser.ml"
               : 'buildprofilelist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'buildprofilelist_ne) in
    Obj.repr(
# 181 "src_ext/dose/deb/packages_parser.mly"
                        ( _1 )
# 631 "src_ext/dose/deb/packages_parser.ml"
               : 'buildprofilelist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'buildprofile) in
    Obj.repr(
# 185 "src_ext/dose/deb/packages_parser.mly"
                                      ( [ _1 ] )
# 638 "src_ext/dose/deb/packages_parser.ml"
               : 'buildprofilelist_ne))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'buildprofile) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'buildprofilelist_ne) in
    Obj.repr(
# 186 "src_ext/dose/deb/packages_parser.mly"
                                      ( _1 :: _2 )
# 646 "src_ext/dose/deb/packages_parser.ml"
               : 'buildprofilelist_ne))
; (fun __caml_parser_env ->
    Obj.repr(
# 192 "src_ext/dose/deb/packages_parser.mly"
                ( [] )
# 652 "src_ext/dose/deb/packages_parser.ml"
               : 'archlist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'archlist_ne) in
    Obj.repr(
# 193 "src_ext/dose/deb/packages_parser.mly"
                ( _1 )
# 659 "src_ext/dose/deb/packages_parser.ml"
               : 'archlist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 197 "src_ext/dose/deb/packages_parser.mly"
                            ( [ _1 ] )
# 666 "src_ext/dose/deb/packages_parser.ml"
               : 'archlist_ne))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : string) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'archlist_ne) in
    Obj.repr(
# 198 "src_ext/dose/deb/packages_parser.mly"
                            ( _1 :: _2 )
# 674 "src_ext/dose/deb/packages_parser.ml"
               : 'archlist_ne))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'req_aux) in
    Obj.repr(
# 204 "src_ext/dose/deb/packages_parser.mly"
                        ( let (vpkg,suite) = _2 in (Some Format822.I,vpkg,suite) )
# 681 "src_ext/dose/deb/packages_parser.ml"
               : 'request))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'req_aux) in
    Obj.repr(
# 205 "src_ext/dose/deb/packages_parser.mly"
                        ( let (vpkg,suite) = _2 in (Some Format822.R,vpkg,suite) )
# 688 "src_ext/dose/deb/packages_parser.ml"
               : 'request))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'req_aux) in
    Obj.repr(
# 206 "src_ext/dose/deb/packages_parser.mly"
                        ( let (vpkg,suite) = _1 in (None,vpkg,suite) )
# 695 "src_ext/dose/deb/packages_parser.ml"
               : 'request))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'vpkgname) in
    Obj.repr(
# 210 "src_ext/dose/deb/packages_parser.mly"
                         ( ((_1,None),None) )
# 702 "src_ext/dose/deb/packages_parser.ml"
               : 'req_aux))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'vpkgname) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'version) in
    Obj.repr(
# 211 "src_ext/dose/deb/packages_parser.mly"
                         ( ((_1,Some("=",_3)),None) )
# 710 "src_ext/dose/deb/packages_parser.ml"
               : 'req_aux))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'vpkgname) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 212 "src_ext/dose/deb/packages_parser.mly"
                         ( ((_1,None),Some _3) )
# 718 "src_ext/dose/deb/packages_parser.ml"
               : 'req_aux))
; (fun __caml_parser_env ->
    Obj.repr(
# 216 "src_ext/dose/deb/packages_parser.mly"
               ( [] )
# 724 "src_ext/dose/deb/packages_parser.ml"
               : 'reqlist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'reqlist_ne) in
    Obj.repr(
# 217 "src_ext/dose/deb/packages_parser.mly"
               ( _1 )
# 731 "src_ext/dose/deb/packages_parser.ml"
               : 'reqlist))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'request) in
    Obj.repr(
# 221 "src_ext/dose/deb/packages_parser.mly"
                            ( [ _1 ] )
# 738 "src_ext/dose/deb/packages_parser.ml"
               : 'reqlist_ne))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'request) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'reqlist_ne) in
    Obj.repr(
# 222 "src_ext/dose/deb/packages_parser.mly"
                            ( _1 :: _2 )
# 746 "src_ext/dose/deb/packages_parser.ml"
               : 'reqlist_ne))
(* Entry pkgname_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry version_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry archlist_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry multiarch_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry source_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry vpkgname_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry vpkg_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry vpkglist_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry vpkgformula_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry builddepsformula_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry builddepslist_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry request_top *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
(* Entry requestlist_top *)
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
let pkgname_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 1 lexfun lexbuf : Format822.name)
let version_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 2 lexfun lexbuf : Format822.version)
let archlist_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 13 lexfun lexbuf : Format822.architecture list)
let multiarch_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 3 lexfun lexbuf : Format822.multiarch)
let source_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 4 lexfun lexbuf : Format822.source)
let vpkgname_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 5 lexfun lexbuf : Format822.vpkgname)
let vpkg_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 6 lexfun lexbuf : Format822.vpkg)
let vpkglist_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 7 lexfun lexbuf : Format822.vpkglist)
let vpkgformula_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 8 lexfun lexbuf : Format822.vpkgformula)
let builddepsformula_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 9 lexfun lexbuf : Format822.builddepsformula)
let builddepslist_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 10 lexfun lexbuf : Format822.builddepslist)
let request_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 11 lexfun lexbuf : Format822.vpkgreq)
let requestlist_top (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 12 lexfun lexbuf : Format822.vpkgreq list)
;;
# 226 "src_ext/dose/deb/packages_parser.mly"

let error_wrapper f lexer lexbuf =
  let syntax_error msg =
    raise (Format822.Syntax_error (msg, Format822.loc_of_lexbuf lexbuf)) 
  in
  try f lexer lexbuf with
  |Parsing.Parse_error -> syntax_error "parse error"
  |Failure _m when String.starts_with _m "lexing" -> syntax_error "lexer error"
  |Format822.Type_error _ -> syntax_error "type error"
  |_ -> assert false

let pkgname_top = error_wrapper pkgname_top
let version_top = error_wrapper version_top
let vpkg_top = error_wrapper vpkg_top
let vpkglist_top = error_wrapper vpkglist_top
let vpkgformula_top = error_wrapper vpkgformula_top
let source_top = error_wrapper source_top
let request_top = error_wrapper request_top
let requestlist_top = error_wrapper requestlist_top
# 839 "src_ext/dose/deb/packages_parser.ml"
