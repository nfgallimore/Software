open Core.Std ;;

(* Function to square x *)
let square x = x * x ;;

(* Multiargument function to the Float module within the Core Std library *)
let ratio x y = 
  Float.of_int x /. Float.of_int y
;;

(* Function that takes two int's and returns a float *)
let sum_if_true' (test : int -> bool) (x : int) (y : int) : int =
  (if test x then x else 0)
    + (if test y then y else 0)
;;

(* Test to see if integer is even *)
let even x = 
  x mod 2 = 0 ;;

(* Expects a boolean function, returns x if true, y if false *)
let first_if_true test x y = 
  if test x then x else y
;;

(* Determines if string is longer than 6 characters *)
let long_string s = String.length s > 6 ;;

(* Determines if y is a multiple of x *)
let is_a_multiple x y = 
  x mod y = 0 ;;

(* Function to compute the distance between two points on the plane, where each point is represented as a pair of floats, ** is for raising a float to a power *)
let distance (x1, y1) (x2, y2) =
  sqrt ((x1 -. x2) ** 2. +. (y1 -. y2) ** 2.)
;;

(* Defining a list of languages *)
let languages = ["OCaml"; "Perl"; "C"] ;;

(* Function to return length of list *)
List.length languages ;;
List.map languages ~f:String.length;;
"French" :: "Spanish" :: languages ;;
languages ;;

(* All equivalent declarations *)
1, 2, 3 ;;
[1; 2; 3] ;;
1 :: (2 :: (3 :: [])) ;;
1 :: 2 :: 3 :: [] ;;

(* Demonstrates list concatenation *)
List.length ([1; 2; 3] @ [4; 5; 6]) ;;

let my_favorite_language languages =
  match languages with
  | first :: the_rest -> first
  | [] -> "OCaml" (* A good default! *)
;;

my_favorite_language ["English"; "Spanish"; "French"] ;;
my_favorite_language [] ;;

(* Sums up elements of list *)
let rec sum l =
  match l with
  | [] -> 0                       (* base case *)
  | hd :: tl -> hd + sum tl       (* inductive case *)
;;

(* Removes sequential duplicates from a list *)
let rec destutter list =
  match list with
  | [] -> []
  | [hd] -> [hd]
  | hd1 :: hd2 :: tl ->
     if hd1 = hd2 then destutter (hd2 :: tl)
     else hd1 :: destutter (hd2 :: tl)
;;

(* Usage *)
destutter ["hey"; "hey"; "man!"];;

(* Demonstrates None and Some constructors, function to divide x by y *)
let divide x y =
  if y = 0 then None else Some (x / y) ;;

(* Creates a log entry string given an optional time and a message *)
let log_entry maybe_time message =
  let time = 
    match maybe_time with
    | Some x -> x
    | none -> Time.now()
  in
  Time.to_sec_string time ^ " -- " ^ message
;;

(* Usage *)
log_entry (Some Time.epoch) "A long long time ago";;
log_entry None "Up to the minute" ;;

let x = 7 in
    let y = x * x in
;;    

type point2d = { x : float; y : float };;
let p = { x = 3.; y = -4. };;

let magnitude { x ; y } =
  sqrt (x ** 2. +. y ** 2.) ;;

let distance v1 v2 =
  magnitude { x = v1.x -. v2.x; y = v1.y -. v2.y } ;;

type circle_desc = { center: point2d; radius: float }
type rect_desc = { lower_left: point2d; width: float; height: float }
type segment_desc = { endpoint1: point2d; endpoint2: point2d } ;;

type scene_element =
  | Circle of circle_desc
  | Rect of rect_desc
  | Segment of segment_desc
;;

let is_inside_scene_element point scene_element =
  match scene_element with
  | Circle { center; radius } ->
     distance center point < radius
  | Rect { lower_left; width; height } ->
     point.x > lower_left.x && point.x < lower_left.x +. width
     && point.y > lower_left.y && point.y < lower_left.y +. height
  | Segment { endpoint1; endpoint2 } -> false
;;

let is_inside_scene point scene =
  List.exists scene
	      ~f:(fun el -> is_inside_scene_element point el)
;;

is_inside_scene {x=3.; y=7.} [ Circle {center = {x=4.; y=4.}; radius = 0.5 } ]
;;

is_inside_scene {x=3.; y=7.} [ Circle {center = {x=4.; y=4.}; radius = 5.0 } ]
;;

let numbers = [| 1; 2; 3; 4 |];;
numbers.(2) <- 4;;
numbers;;

type running_sum =
{ mutable sum: float;
  mutable sum_sq: float; (* sum of squares *)
  mutable samples: int;
}
;;

let mean rsum = rsum.sum /. float rsum.samples ;;
let stdev rsum =
  sqrt (rsum.sum_sq /. float rsum.samples 
	-. (rsum.sum /. float rsum.samples) ** 2.) 
;;
let create () = { sum = 0.; sum_sq = 0.; samples = 0 }
let update rsum x = 
  rsum.samples <- rsum.samples + 1;
  rsum.sum <- rsum.sum +. x;
  rsum.sum_sq <- rsum.sum_sq +. x *. x
;;

let rsum = create ();;
List.iter [1.; 3.; 2.; -7.; 4.; 5.] ~f:(fun x -> update rsum x) ;;
mean rsum ;;
stdev rsum ;;

let x = { contents = 0 } ;;
x.contents <- x.contents + 1 ;;
x ;;

(* create a ref, i.e., { contents = 0 } *)
let x = ref 0 ;;
!x ;;
x := !x + 1 ;;
!x ;;

(* Implementation of ref *)
type 'a ref = { mutable contents : 'a }

let ref x = { contents = x }
let (!) r = r.contents
let (:=) r x = r.contents <- x
;;

let sum list =
  let sum = ref 0 in
  List.iter list ~f:(fun x -> sum := !sum + x);
  !sum
;;

(* Randomizes seed *)
Random.self_init ;;

(* Permutes an array *)
let permute array =
  let length = Array.length array in
  for i = 0 to length - 2 do
    (* pick a j to swap with *)
    let j = i + Random.int (length - i) in
    (* Swap i and j *)
    let tmp = array.(i) in
    array.(i) <- array.(j);
    array.(j) <- tmp
  done
;;

(* Example usage *)
let ar = Array.init 20 ~f:(fun i -> i) ;;
permute ar ;;
ar ;;

(* Demonstrates while loop *)
let find_first_negative_entry array =
  let pos = ref 0 in
  while !pos < Array.length array && array.(!pos) >= 0 do
    pos := !pos + 1
  done;
  if !pos = Array.length array then None else Some !pos
;;

(* Example usage *)
find_first_negative_entry [|1; 2; 0; 3|] ;;
find_first_negative_entry [|1; -2; 0; 3|] ;;


log_entry None "test";;

let numbers = Array.init 20 ~f:(fun i-> i) ;;
let create_array size = Array.init size ~f:(fun i-> i) ;;

let my_array = create_array 20;;

my_array;;
permute my_array;;
my_array;;
let random_arr = create_array 20;;

permute random_arr;;
random_arr;;

let random_array n = 
  let arr in (Array.init size ~f:(fun i-> i) <- arr) ;;


(* Lists and pattern matching *)

(* To square each element of a list of ints *)
let rec square_list (xs : int list) : int list = 
  match xs with
  | [] -> []
  | x :: xs' -> x * x :: square_list xs'

assert( square_list [] = [] )
assert( square_list[2] = [4] )
assert( square_list [3; 5; 7] = [9; 25; 49] )


(* To multiply each pair of ints in a list *)
let rec prods_list (xsys : (int * int) list) : int list = 
  match xsys with
  | [] -> []
  | x :: xs' -> ... x ... prods_list xs' ...
assert( prods_list [] = [] )
assert( prods_list [(2,3)] = [6] )
assert( prods_list [(2,3); (4,5); (6,7)] = [6; 20; 42;] )



(* TEMPLATE FOR ALTERING LISTS *)
(*let rec prods_list (xs : (int * int ) list) : int list =
  match xs with
  | [] -> ...
  | x :: xs' -> ... x ... prods_list xs' ...
 *)
