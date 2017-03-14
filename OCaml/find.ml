(* find "name" "path": tries to find all occurrences of 
   files with the given name, starting at the given path, 
   recursively exploring all sub- directories. Returns a 
   list of the full paths to the files.
*)
let rec find name path = 
  try 
    let fullname = Filename.concat path name in 
      if Sys.file_exists fullname then
        [ fullname ] 
      else 
        if Sys.is_directory path then
          let files = Array.to_list (Sys.readdir path) in 
          let paths = List.map (Filename.concat path) files in 
          let results = List.map (find name) paths in 
            List.flatten results
        else [] 
  with Sys_error _ -> []
;;

#find "M4.mtl" "/Users/nickgallimore/Downloads" ;;
(* Example use: 
   # find "1-overview.pptx" "/Users/greg_morrisett/Courses" ;; 
*)
