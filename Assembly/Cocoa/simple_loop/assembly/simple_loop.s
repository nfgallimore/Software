	.section	__TEXT,__text,regular,pure_instructions
	.globl	_main
	.align	4, 0x90
_main:                                  ## @main
	.cfi_startproc
## BB#0:
	pushq	%rbp
Ltmp2:
	.cfi_def_cfa_offset 16
Ltmp3:
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
Ltmp4:
	.cfi_def_cfa_register %rbp
	subq	$48, %rsp
	movl	$0, -4(%rbp)
	movl	%edi, -8(%rbp)
	movq	%rsi, -16(%rbp)
	callq	_objc_autoreleasePoolPush
	movb	$0, -17(%rbp)
	movq	%rax, -32(%rbp)         ## 8-byte Spill
LBB0_1:                                 ## =>This Inner Loop Header: Depth=1
	movzbl	-17(%rbp), %eax
	cmpl	$16, %eax
	jge	LBB0_4
## BB#2:                                ##   in Loop: Header=BB0_1 Depth=1
	leaq	L_.str(%rip), %rdi
	movzbl	-17(%rbp), %esi
	movb	$0, %al
	callq	_printf
	movl	%eax, -36(%rbp)         ## 4-byte Spill
## BB#3:                                ##   in Loop: Header=BB0_1 Depth=1
	movb	-17(%rbp), %al
	addb	$1, %al
	movb	%al, -17(%rbp)
	jmp	LBB0_1
LBB0_4:
	movq	-32(%rbp), %rdi         ## 8-byte Reload
	callq	_objc_autoreleasePoolPop
	movl	$0, %eax
	addq	$48, %rsp
	popq	%rbp
	ret
	.cfi_endproc

	.section	__TEXT,__cstring,cstring_literals
L_.str:                                 ## @.str
	.asciz	 "i = %d\n"

	.section	__TEXT,__objc_classname,cstring_literals
L_OBJC_CLASS_NAME_:                     ## @"\01L_OBJC_CLASS_NAME_"
	.asciz	 "simple_loop"

	.section	__DATA,__objc_const
	.align	3                       ## @"\01l_OBJC_METACLASS_RO_$_simple_loop"
l_OBJC_METACLASS_RO_$_simple_loop:
	.long	1                       ## 0x1
	.long	40                      ## 0x28
	.long	40                      ## 0x28
	.space	4
	.quad	0
	.quad	L_OBJC_CLASS_NAME_
	.quad	0
	.quad	0
	.quad	0
	.quad	0
	.quad	0

	.section	__DATA,__objc_data
	.globl	_OBJC_METACLASS_$_simple_loop ## @"OBJC_METACLASS_$_simple_loop"
	.align	3
_OBJC_METACLASS_$_simple_loop:
	.quad	_OBJC_METACLASS_$_NSObject
	.quad	_OBJC_METACLASS_$_NSObject
	.quad	__objc_empty_cache
	.quad	__objc_empty_vtable
	.quad	l_OBJC_METACLASS_RO_$_simple_loop

	.section	__DATA,__objc_const
	.align	3                       ## @"\01l_OBJC_CLASS_RO_$_simple_loop"
l_OBJC_CLASS_RO_$_simple_loop:
	.long	0                       ## 0x0
	.long	8                       ## 0x8
	.long	8                       ## 0x8
	.space	4
	.quad	0
	.quad	L_OBJC_CLASS_NAME_
	.quad	0
	.quad	0
	.quad	0
	.quad	0
	.quad	0

	.section	__DATA,__objc_data
	.globl	_OBJC_CLASS_$_simple_loop ## @"OBJC_CLASS_$_simple_loop"
	.align	3
_OBJC_CLASS_$_simple_loop:
	.quad	_OBJC_METACLASS_$_simple_loop
	.quad	_OBJC_CLASS_$_NSObject
	.quad	__objc_empty_cache
	.quad	__objc_empty_vtable
	.quad	l_OBJC_CLASS_RO_$_simple_loop

	.section	__DATA,__objc_classlist,regular,no_dead_strip
	.align	3                       ## @"\01L_OBJC_LABEL_CLASS_$"
L_OBJC_LABEL_CLASS_$:
	.quad	_OBJC_CLASS_$_simple_loop

	.section	__DATA,__objc_imageinfo,regular,no_dead_strip
L_OBJC_IMAGE_INFO:
	.long	0
	.long	0


.subsections_via_symbols
