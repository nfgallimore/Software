(* # generated by ./configure -prefix /usr *)
let prefix = "/usr";;
let bindir = prefix^"/bin";;
let libdir = prefix^"/lib/ocaml";;
let stublibdir = libdir^"/stublibs";;
let mandir = prefix^"/man";;
let manext = "1";;
let ranlib = "ranlib";;
let ranlibcmd = "ranlib";;
let arcmd = "ar";;
let sharpbangscripts = true;;
let bng_arch = "amd64";;
let bng_asm_level = "1";;
let pthread_link = "-cclib -lpthread";;
let x11_includes = "-I/opt/local/include ";;
let x11_link = "-L/opt/local/lib -lX11 ";;
let tk_defs = " "^x11_includes;;
let tk_link = " -ltk8.5 -ltcl8.5  "^x11_link;;
let libbfd_link = "";;
let bytecc = "gcc";;
let bytecccompopts = " -fno-defer-pop -Wall -D_FILE_OFFSET_BITS=64 -D_REENTRANT";;
let bytecclinkopts = "";;
let bytecclibs = "   -lcurses -lpthread";;
let byteccrpath = "";;
let exe = "";;
let supports_shared_libraries = true;;
let sharedcccompopts = "";;
let mksharedlibrpath = "";;
let natdynlinkopts = "";;
(* SYSLIB=-l"^1^" *)
let syslib x = "-l"^x;;

(* ## *)
(* MKLIB=ar rc "^1^" "^2^"; ranlib "^1^" *)
let mklib out files opts = Printf.sprintf "ar rc %s %s %s; ranlib %s" out opts files out;;
let arch = "amd64";;
let model = "default";;
let system = "macosx";;
let nativecc = "gcc";;
let nativecccompopts = " -D_FILE_OFFSET_BITS=64 -D_REENTRANT";;
let nativeccprofopts = " -D_FILE_OFFSET_BITS=64 -D_REENTRANT";;
let nativecclinkopts = "";;
let nativeccrpath = "";;
let nativecclibs = "  ";;
let asm = "clang -arch x86_64 -c";;
let aspp = "clang -arch x86_64 -c";;
let asppprofflags = "-DPROFILING";;
let profiling = "prof";;
let dynlinkopts = "";;
let otherlibraries = "unix str num dynlink bigarray systhreads threads graph labltk";;
let debugger = "ocamldebugger";;
let cc_profile = "-pg";;
let systhread_support = true;;
let partialld = "ld -r -arch x86_64";;
let packld = partialld^" "^nativecclinkopts^" -o\ ";;
let dllcccompopts = "";;

let o = "o";;
let a = "a";;
let so = "so";;
let ext_obj = ".o";;
let ext_asm = ".s";;
let ext_lib = ".a";;
let ext_dll = ".so";;
let extralibs = "";;
let ccomptype = "cc";;
let toolchain = "cc";;
let natdynlink = true;;
let cmxs = "cmxs";;
let mkexe = bytecc^" -Wl,-no_compact_unwind";;
let mkexedebugflag = "-g";;
let mkdll = "gcc -bundle -flat_namespace -undefined suppress -Wl,-no_compact_unwind";;
let mkmaindll = "gcc -bundle -flat_namespace -undefined suppress -Wl,-no_compact_unwind";;
let runtimed = "noruntimed";;
let camlp4 = "camlp4";;
let asm_cfi_supported = true;;
let with_frame_pointers = false;;
