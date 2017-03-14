//
//  simple_loop_2.s
//  simple_loop
//
//  Created by Nick Gallimore on 12/9/13.
//  Copyright (c) 2013 Virtual Theologies. All rights reserved.
//

0x100000ec0:  pushq  %rbp

0x100000ec1:  movq   %rsp, %rbp
0x100000ec4:  subq   $48, %rsp
0x100000ec8:  movl   $0, -4(%rbp)
0x100000ecf:  movl   %edi, -8(%rbp)
0x100000ed2:  movq   %rsi, -16(%rbp)
0x100000ed6:  callq  0x100000f2e               ; symbol stub for: objc_autoreleasePoolPush
0x100000edb:  movb   $0, -17(%rbp)
0x100000edf:  movq   %rax, -32(%rbp)
0x100000ee3:  movzbl -17(%rbp), %eax
0x100000ee7:  cmpl   $16, %eax
0x100000eec:  jge    0x100000f14               ; main + 84 at main.m:19
0x100000ef2:  leaq   113(%rip), %rdi           ; "i = %d\n"
0x100000ef9:  movzbl -17(%rbp), %esi
0x100000efd:  movb   $0, %al
0x100000eff:  callq  0x100000f34               ; symbol stub for: printf
0x100000f04:  movl   %eax, -36(%rbp)
0x100000f07:  movb   -17(%rbp), %al
0x100000f0a:  addb   $1, %al
0x100000f0c:  movb   %al, -17(%rbp)
0x100000f0f:  jmpq   0x100000ee3               ; main + 35 at main.m:16
0x100000f14:  movq   -32(%rbp), %rdi
0x100000f18:  callq  0x100000f28               ; symbol stub for: objc_autoreleasePoolPop
0x100000f1d:  movl   $0, %eax
0x100000f22:  addq   $48, %rsp

0x100000f26:  popq   %rbp
0x100000f27:  ret   