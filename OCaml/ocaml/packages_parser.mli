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

val pkgname_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.name
val version_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.version
val archlist_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.architecture list
val multiarch_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.multiarch
val source_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.source
val vpkgname_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.vpkgname
val vpkg_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.vpkg
val vpkglist_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.vpkglist
val vpkgformula_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.vpkgformula
val builddepsformula_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.builddepsformula
val builddepslist_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.builddepslist
val request_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.vpkgreq
val requestlist_top :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Format822.vpkgreq list
