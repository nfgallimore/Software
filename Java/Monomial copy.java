import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Monomial extends Object
 {
  public static final int MAX_EXP = 26;
  public int coeff;
  public int exponent[];
  public static String var_names[];
  public static int num_vars = 0;

  static
   {
    var_names = new String[MAX_EXP];
    for (int i = 0; i < MAX_EXP; i++) var_names[i] = new String();
   }

  Monomial()
   {
    coeff = 0;
    exponent = new int[MAX_EXP];
    for (int i = 0; i < MAX_EXP; i++) exponent[i] = 0;
   }

  Monomial(int coeff)
   {
    this.coeff = coeff;
    exponent = new int[MAX_EXP];
    for (int i = 0; i < MAX_EXP; i++) exponent[i] = 0;
   }
  
  Monomial(String name)
   {
    int i;
    boolean foundit = false;

    coeff = 1;
    exponent = new int[MAX_EXP];
    for (i = 0; i < MAX_EXP; i++) exponent[i] = 0;

    for (i = 0; i < num_vars; i++)
      if (var_names[i].equals(name))
       {
        exponent[i] = 1; foundit = true; break;
       }

    if (!foundit && num_vars < MAX_EXP)
     {
      var_names[num_vars] = name;
      exponent[num_vars] = 1;
      num_vars ++;
     }
   }     


  public boolean like(Monomial x)
   { 
    for (int i = 0; i < num_vars; i++)
      if (exponent[i] != x.exponent[i]) 
        return false;
    return true;
   }

  public void add(Monomial x) // assumes this is like x.
   {
    coeff += x.coeff;
   }

  public void sub(Monomial x) // assumes this is like x.
   {
    coeff -= x.coeff;
   }

  public void mult(Monomial x)
   {
    coeff *= x.coeff;
    for (int i = 0; i < num_vars; i++)
      exponent[i] += x.exponent[i];
   }

  public static Monomial product(Monomial x, Monomial y)
   {
    Monomial z = new Monomial();
    z.coeff = x.coeff * y.coeff;
    for (int i = 0; i < num_vars; i++)
      z.exponent[i] = x.exponent[i] + y.exponent[i];
    return z;
   }

  public boolean positive()
   {
    return (coeff > 0);
   }

  public String toString()
   {
    int i,j;

    String string = new String();

    boolean terms = false;
    boolean first = true;

    for (i = 0; i < num_vars; i++)
      if (exponent[i] > 0) terms = true;

    if (coeff == 1 && terms) {}
    else if (coeff == -1 && terms)
      string += "-";
    else 
      string += String.valueOf(coeff);

    if (terms)
    for (i = 0; i < num_vars; i++)
      for (j = 0; j < exponent[i]; j++)
       { 
        if (first) first = false;
        else string += " ";
        string += var_names[i];
       }

    return string;
   } 

}
