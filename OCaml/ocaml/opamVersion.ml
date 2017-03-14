(**************************************************************************)
(*                                                                        *)
(*    Copyright 2012-2013 OCamlPro                                        *)
(*    Copyright 2012 INRIA                                                *)
(*                                                                        *)
(*  All rights reserved.This file is distributed under the terms of the   *)
(*  GNU Lesser General Public License version 3.0 with linking            *)
(*  exception.                                                            *)
(*                                                                        *)
(*  OPAM is distributed in the hope that it will be useful, but WITHOUT   *)
(*  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY    *)
(*  or FITNESS FOR A PARTICULAR PURPOSE.See the GNU General Public        *)
(*  License for more details.                                             *)
(*                                                                        *)
(**************************************************************************)

type t = string

let to_string x = x

let of_string x = x

let to_json x = `String x

let compare v w =
  (* Ignore -xxx suffixes for version comparisons *)
  let cut s = match OpamMisc.cut_at s '-' with
    | Some (s,_) -> s
    | None -> s in
  Debian.Version.compare (cut v) (cut w)

module O = struct
  type t = string
  let to_string = to_string
  let to_json = to_json
  let compare = compare
end

module Set = OpamMisc.Set.Make(O)

module Map = OpamMisc.Map.Make(O)

let current_raw = "1.1.0"

let current = of_string current_raw

let message () =
  Printf.printf "\n\
    %s version %s\n\
    \n\
    Copyright (C) 2012 OCamlPro - INRIA\n\
    \n\
    This is free software; see the source for copying conditions.  There is NO\n\
    warranty; not even for MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.\n"
    Sys.argv.(0) current_raw;
  exit 0

let git =
  match OpamGitVersion.version with
  | None   -> None
  | Some v -> Some (of_string v)

let full =
  let git_version = match git with
    | None   -> ""
    | Some v -> Printf.sprintf " (%s)" (to_string v) in
  Printf.sprintf "%s%s" (to_string current) git_version

let magic =
  let hash = Hashtbl.hash full in
  Printf.sprintf "%08X" (hash mod (Int32.to_int Int32.max_int))
