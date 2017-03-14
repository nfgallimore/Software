(* CS51 Section 0: Intro to ML
 *
 * Exercises: The purpose of these exercises is to help you start
 * getting comfortable with Ocaml.  The focus is on pattern matching,
 * list operations, and a bit of basic arithmetic.
 *
 * A goal throughout the semester will be writing code that's clear,
 * concise, and beautiful -- not just correct.  Try to make your
 * solutions as simple possible.  Once you have a version that works,
 * look for ways to simplify it. *)

(* Make it so that that x equals 42, by adding 22 to 20 *)
let x = 20 + 22 ;;

(* Make it so that x1 equals 42.0, by adding 2 numbers. *)
let x1 = 20.0 +. 22.0 ;;

(* Write a function takes a string, and appends
 * ", and that is why I love CS51" to the end of it. *)
let cs51_loveifier input = input ^ ", and that is why I love CS51" ;;

(* Write a function that takes a number and returns
 * the difference between that number and 42.
 * Eg, if 'num' is 50, the result should be 8.
 * If 'num' is 30, the result should be -12 *)
let difference_between_x_and_42 num = num - 42 ;;


(* One more simple arithmetic example...
 * Write a function that returns the volume of a cylinder
 * with height h, radius r. *)
let volume_cylinder (h:float) (r:float) : float =  (4. *. atan 1.) *. (r *. r) *. h ;;

(* Here, you might have a solution in mind, but not know how to
 * implement it in OCaml.  See if you can Google for how to do it... *)
let even (x: int) : bool =
  if x mod 2 = 0 then true else false;;


(* Can you write odd /in terms of/ even? *)
let odd (x: int) : bool =
  if even x then false else true;;


(* OCaml comes pre-packaged with a standard library, that includes
 * a lot of utility functions that you don't have to write yourself.
 * For instance, check out the String module
 * (http://caml.inria.fr/pub/docs/manual-ocaml/libref/String.html)
 *
 * Now... write a function that takes a String, and returns whether
 * or not that String is more than 10 characters long. *)
let is_more_than_10_characters_long str = String.length str > 10 ;;

(* LISTS *)
(* We're going to introduce some simple lists.
 * To start, make 'l1' be a list of the following numbers in order: 3, 4, 5 *)
let l1 = [3; 4; 5;] ;;


(* Try to make l2 be a list of 4, followed by "Greg".  Does it work?
 * Why or why not?
 * If it doesn't work, just make l2 be the empty list. *)

(* It doesn't work because the expression has type string 
 * but an expression was expected of type int. *)
let l2 = [] ;;

(* Now we're going to do some basic matching on lists.
 *
 * It's fun to get the hang of 'match'.  In some ways,
 * a 'match' can be thought of as analogous to an 'if' statement
 * or a 'switch' statement, in that you're choosing which branch
 * of code to follow, based on the value of a variable.
 *
 * Here's a really simple example is_empty that takes a list, and returns
 * true if the list is empty, and false if the list is not empty. *)
let list_is_empty lst =
    match lst with
    | [] -> true
    | _ :: _ -> false ;;

(* Now, see if you can tackle the following functions... *)

(* Return the head of a list, or None if empty. *)
let head (x:int list) : int option =
  match x with
    | [] -> None
    | xhd :: xtl -> Some xhd ;;

(* Return the tail of a list, or None if empty. *)
let tail (x:int list) : int list option =
  match x with
    | [] -> None
    | xhd :: xtl -> Some xtl ;;

(* Return the last int of an int list, or None if empty. *)
let rec last_number (x:int list) : int option =
  match x with
    | [] -> None
    | hd :: [] -> Some hd
    | hd :: tl -> last_number tl ;;

(* Retain only even integers *)
let rec filter_even (l:int list) : int list =
  match l with
    | [] -> []
    | hd :: tl -> 
      if hd mod 2 = 0 then hd :: filter_even tl 
      else filter_even tl  ;;


(* Square all the elements of a list. *)
let rec square_all (a:int list) : int list =
  match a with
    | [] -> []
    | hd :: tl -> (hd * hd) :: square_all tl ;;

(* Return the max of a list, or None if the list is empty. *)
(* Note: Might be good to walk through this in English before syntactifying *)
let rec max_of_list (x:int list) : int option =
  match x with
    | [] -> None
    | hd :: tl ->
      (match max_of_list tl with
	| None -> Some hd
	| Some max -> Some (if hd > max then hd else max)) ;;

(* Return the min and max of a list, or None if the list is empty. *)
let rec bounds (x:int list) : (int * int) option =
  match x with
    | [] -> None
    | hd :: tl ->
      (match bounds tl with
	| None -> Some (hd, hd)
	| Some (min, max) ->
	  Some ((if hd < min then hd else min),
		(if hd > max then hd else max))) ;;

(* From a list of pairs, retain only those that are in order. *)
let rec proper_pairs (l:(int * int) list) : (int * int) list =
  match l with 
    | [] -> []
    | (x1, x2) :: ps -> let pps = proper_pairs ps in
			if x1 <= x2 then ((x1, x2) :: pps) else pps ;;

(* Can also introduce abbreviated syntax for proper_pairs: *)
let rec proper_pairs' (l:(int * int) list) : (int * int) list =
  match l with
    | [] -> []
    | ((x1, x2) as p) :: ps -> let pps = proper_pairs' ps in
			       if x1 <= x2 then (p :: pps) else pps ;;

(* Zip three lists. Return None if different lengths. *)
let rec threezip (a:int list) (b:int list) (c:int list) :
    ((int * int * int) list) option =
  match (a, b, c) with
    | ([], [], []) -> Some []
    | ([], _, _) -> None
    | (_, [], _) -> None
    | (_, _, []) -> None
    | (ahd :: atl, bhd :: btl, chd :: ctl) ->
      match threezip atl btl ctl with
	| None -> None
	| Some ntl -> Some ((ahd, bhd, chd) :: ntl) ;;

let rec threezip_short (a:int list) (b:int list) (c:int list) :
    ((int * int * int) list) option =
  match (a, b,  c) with
    | ([], [], []) -> Some []
    | (ahd :: atl, bhd :: btl, chd :: ctl) ->
      (match threezip_short atl btl ctl with
	| None -> None
	| Some ntl -> Some ((ahd, bhd, chd) :: ntl))
    | (_, _, _) -> None ;;


(* Compute the dot product of two lists.
 * Use zip, prods from lecture, write sum. *)
let rec prods (l: (int*int) list) : int list =
  match l with
    | [] -> []
    | (x,y) :: tl -> (x*y) :: (prods tl)
;;

let rec zip (x:int list) (y:int list) : ((int*int) list) option =
  match (x,y) with
    | ([], []) -> Some []
    | (xhd::xtl, yhd::ytl) ->
        (match zip xtl ytl with
           | None -> None
           | Some ztl -> Some ((xhd,yhd)::ztl))
    | (_, _) -> None
;;

let rec sum (l:int list) : int =
  match l with
    | [] -> 0
    | h :: t -> h + (sum t) ;;

let rec dotproduct (a:int list) (b:int list) : int option =
  match zip a b with
    | None -> None
    | Some v -> Some (sum (prods v)) ;;

(* Given a matrix (list of lists), return the transpose.
 * The transpose of a matrix interchanges the rows and columns.
 * For example, transpose [[1;2;3];[4;5;6]];;
 * where [1;2;3] and [4;5;6] are the rows,
 * should return [[1;4];[2;5];[3;6]].
 *
 * Hint: write an auxiliary function, split, that
 * returns the first column of a matrix as a list
 * and the rest of the matrix as a list of rows.
 *
 * For now, don't worry about doing anything smart if the input
 * isn't a valid matrix.
 *)

let rec split (m:int list list) : (int list * int list list) option =
  match m with 
    | [] -> None
    | [] :: ls -> split ls
    | (x :: xs) :: ls ->
      match split ls with
	| Some (c, ls_rest) -> Some (x :: c, xs :: ls_rest)
	| None -> Some ([x], [xs]) ;;

let rec transpose (m:int list list) : int list list =
  match split m with
    | Some (c, m_rest) -> c :: transpose m_rest
    | None -> [] ;;
