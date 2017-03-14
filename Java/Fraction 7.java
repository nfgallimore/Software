import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Fraction extends Object
 {
  public int denom, num; 

  Fraction(int num, int denom)
   {
    this.denom = denom;
    this.num = num;
   }

  public void reduce()
   {
    int i = 2;

    while (i < denom)
     {
      if (denom % i == 0)
       {
        denom /= i; num /= i;
        continue;
       }
      i++;
     }
   }

  public void add(int num, int denom)
   {
    int D = denom*this.denom;
    int N = this.num*denom + this.denom*num;

    this.num = N;
    this.denom = D;
    reduce(); 
   }

  public void add(Fraction e)
   {
    add(e.num, e.denom);
   }

  public String toString()
   {
    if (denom==1)
      return String.valueOf(num);
 
    String string = new String();
 
    string += String.valueOf(num);
 
    string += "/";
    string += String.valueOf(denom);
    return string;
   }

 }
