open Core.Std;;

(* part 0 *)
let x = 3
let y = 4
let z = x + y;;

(* part 1 *)
let languages = "OCaml,Perl,C++,C";;
let dashed_languages = 
  let language_list = String.split languages ~on:',' in
  String.concat ~sep;"-" language_list
;;

(* part 2 *)
language_list;;

(* part 3 *)
let languages = "OCaml,Perl,C++,C";;
let dashed_languages =
  let languages = String.split languages ~on:',' in
  String.concat ~sep:"-" languages
;;

(* part 4 *)
languages;;

(* part 5 *)
let area_of_ring inner_radius outer_radius =
  let pi = acos (-1.) in
  let area_of_circle r = pi *. r *. r in
  area_of_circle outer_radius -. area_of_circle inner_radius
;;

area_of_ring 1. 3.;;

(* part 6 *)
let area_of_ring inner_radius outer_radius =
  let pi = acos (-1.) in
  let area_of_circle r = pi *. r *. r in
  let pi = 0. in
  area_of_circle outer_radius -. area_of_circle inner_radius
;;

(* part 7 *)
let (ints,strings) = List.unzip [(1,"one"); (2,"two"); (3,"three")];;
