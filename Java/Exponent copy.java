import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Exponent extends Fraction
 {
  public int a, b;

  Exponent()
   {
    super(1,2);
   }

  Exponent(int p)
   {
    super(1,p);
   }

  Exponent(int p, int q) 
   {
    super(q,p);
   }

  public String toString()
   {
    if (num==0)
      return "\\infty";
 
    if (num==1)
      return String.valueOf(denom);
 
    String string = new String();
 
    string += String.valueOf(denom);
 
    string += "/";
    string += String.valueOf(num);
    return string;
   }

  public static Exponent valueOf(String string)
   {
    if (string.compareTo("\\infty") == 0)
     {
      return new Exponent(1,0);
     }
    
    int i = string.indexOf('/');

    if (i == -1)
     {
      int p = Integer.parseInt(string);
      return new Exponent(p);
     }
    else
     {
      int p = Integer.parseInt(string.substring(0,i));
      int q = Integer.parseInt(string.substring(i+1));
      Exponent e = new Exponent(p,q);
      e.reduce();
      return e;
     }
   }
 }
