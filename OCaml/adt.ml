(* ADT Example *)
(* Source: https://ocaml.janestreet.com/?q=node/61 *)

type order = { id: int; price: float; size: int; }
type cancel = { xid: int; }
type cancel_replace = 
	{ xr_id: int; new_price: float; new_size: int; }

type instruction = 
	| Order of order
	| Cancel of cancel

let filter_by_oid instructions oid =
	List.filter instructions
		(fun x -> match x with
				| Order o -> o.id = oid
				| Cancel c -> c.xid = oid)