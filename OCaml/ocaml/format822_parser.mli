type token =
  | FIELD of (string * (Format822.loc * string))
  | CONT of (Format822.loc * string)
  | BLANKLINE
  | EOF
  | PGPHEAD

val doc_822 :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> (string * (Format822.loc * string)) list list
val stanza_822 :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> (string * (Format822.loc * string)) list option
val doc_822_sign :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> (string * (Format822.loc * string)) list option
