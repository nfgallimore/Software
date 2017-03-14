__at 0x86D7 unsigned int Pen;

void ClrLCDFull() __naked 
{ 
   __asm 
      push ix 
      rst #0x28 
      .dw #0x4540 
      pop ix 
      ret 
     __endasm; 
} 
void NewLine() __naked 
{ 
   __asm 
      push ix 
      rst #0x28 
      .dw #0x452E 
      pop ix 
      ret 
     __endasm; 
} 
void PutS(char *s) // non-naked, let SDCC do its thing 
{ 
  s; // Stop SDCC from saying 'Unused variable', but 
  //    outputs no code for that. 

   __asm 
     ld l,4(ix) 
     ld h,5(ix) 
      rst #0x28 
      .dw #0x450A 
     __endasm; 
    
     // Note that there was no ret above. 
     // If we manually return from inline asm, 
     // SDCC will ignore it and not restore IX 
} 

void main() 
{ 
  ClrLCDFull(); 
  Pen = 0; 
  PutS("Hello World!"); 
  NewLine(); 
}
