import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Fraction;

public class Regularity extends Fraction
 {
  Regularity() 
   {
    super(0,1);
   }

  Regularity(int s)
   {
    super(s,1);
   }

  Regularity(int p, int q)
   {
    super(p,q);
   }
 }
