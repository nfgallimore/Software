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

val main :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> string -> OpamTypes.file
val value :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> OpamTypes.value
