TITLE Program Template     (template.asm)

; Program Description:
; Author:
; Date Created:
; Last Modification Date:

INCLUDE Along32.inc



section .data
	promt1 byte "Enter a number",0
	invalid byte "Sorry that number is not valid",0
	E dword ?
	O dword ?
	TotalE dword ?
	TotalO dword ?
.code
	main PROC
	mov ecx, 10
	begin:
	mov edx, offset promt1
	call writestring
	call readint
	cmp eax, -51
	JL L2
	cmp eax, 51
	JG L2
	push eax
	shl ax, 1
	jc L4

L2: 
mov edx,offset invalid
call writestring
call crlf
jmp begin
L4:
pop eax
call square
add TotalE, eax
loop begin

exit	
main ENDP

square PROC
mov ebx, eax
imul ebx
call writeint
ret
square ENDP


END main
