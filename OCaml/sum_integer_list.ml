(* TEMPLATE FOR PROCESSING LISTS *)

(*
let rec process_list (xs : int list) : int = 
  match xs with
  | [] -> 0
  | x :: xs' -> ... x process_list xs' ...
*)

(* To sum a list of integers *)
let rec sum_list (xs : int list) : int = 
  match xs with
  | [] -> 0
  | x :: xs' -> x + sum_list xs'

assert(sum_list [] = 0)
assert(sum_list [3] = 3)
assert(sum_list [2; 3; 5] = 10)
