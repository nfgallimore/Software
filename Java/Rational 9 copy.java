import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Polynomial;

public class Rational extends Object
 {
  public Polynomial numerator, denominator;

  Rational()
   {
    numerator = new Polynomial();
    denominator = new Polynomial(1);
   } 

  Rational(int coeff)
   {
    numerator = new Polynomial(coeff);
    denominator = new Polynomial(1);
   }

  Rational(int n, int d)
   {
    numerator = new Polynomial(n);
    denominator = new Polynomial(d);
   }

  Rational(String name)
   {
    numerator = new Polynomial(name);
    denominator = new Polynomial(1);
   }

  static int closeParenthesis(String string, int s)
   {
    int depth = 1;
    int l = s+1;

    while (l < string.length())
     {
      if (string.charAt(l) == '(') depth ++;
      if (string.charAt(l) == ')') depth --;
      if (depth == 0) return l;
      l++;
     }
    throw new NumberFormatException();
   }

  public static Rational recip_valueOf(TextField text)
   {
    return reciprocal(valueOf(text.getText()));
   }

  public static Rational valueOf(TextField text)
   {
    return valueOf(text.getText());
   }

  public static Rational valueOf(String string)
   {
    Rational r = new Rational();
    boolean add = true;
    int s = 0, l;

    while (s < string.length())
     {
//      System.out.println("valueOf loop " + r.toString());
      switch (string.charAt(s))
       {
        case '+':
          add = true; s++; break;
        case '-':
          add = false; s++; break;
        default:
          l = s;
          boolean ok = false; 

          while (l < string.length())
           {
            switch (string.charAt(l))
             {
              case '(': l = closeParenthesis(string,l); break;
              case '+': case '-': ok = true; break;
             }
            if (ok) break;
            l++;
           }
          r = sum(r,multdiv(string.substring(s,l)),add);
          s=l; add = true; 
//          System.out.println("valueOf new val " + r.toString());
          break;
       }   
     }
    return r;            
   }


  static Rational multdiv(String string)  // mult/div parsing only
   {
    Rational r = new Rational(1);
    boolean mult = true;
    int s = 0,l;

//    System.out.println("multdiv parsing " + string);

    while (s < string.length())
     {
//      System.out.println("multdiv loop " + r.toString());
      switch (string.charAt(s))
       {
        case '(':
          l = closeParenthesis(string,s);
          r = product(r,valueOf(string.substring(s+1,l)),mult);
          s=l+1;
          mult = true;
          break;
        case '*':
          mult = true; s++; break;
        case '/':
          mult = false; s++; break;
        case '\\':
          l=s+1;
          while (l < string.length() && Character.isLetter(string.charAt(l))) 
            l++;
          r.multiply(string.substring(s,l),mult);
          s=l;
          mult=true;
          break;
        default:
          if (Character.isLetter(string.charAt(s)))
           {
            r.multiply(string.substring(s,s+1),mult);
            s++; mult = true;
            break;
           }
          else if (Character.isDigit(string.charAt(s)))
           {
            l=s+1;
            while (l < string.length() && Character.isDigit(string.charAt(l))) 
              l++;
//            System.out.println("Numeric " + string.substring(s,l));
            r.multiply(Integer.parseInt(string.substring(s,l)),mult);
            s=l; mult = true;
            break;
           }
          else if (Character.isWhitespace(string.charAt(s)))
           {
            s++; break;
           }
          else throw new NumberFormatException();
       }
     }
//    System.out.println("multdiv return " + r.toString());
    return r;            
   }

  public void multiply(String string, boolean mult)
   {
    if (mult)
      numerator.mult(new Monomial(string));
    else
      denominator.mult(new Monomial(string));
   }

  public void multiply(int s, boolean mult)
   {
//    System.out.println("Multiplying " + String.valueOf(s) + " with " + toString());
    if (mult)
      numerator.mult(new Monomial(s));
    else
      denominator.mult(new Monomial(s));
//    System.out.println("Net result was " + toString());
   }

  public static Rational sum(Rational a, Rational b)
   {
    return sum(a,b,true);
   }   

  public static Rational difference(Rational a, Rational b)
   {
    return sum(a,b,false);
   }

  public static Rational product(Rational a, Rational b)
   {
    return product(a,b,true);
   }   

  public static Rational quotient(Rational a, Rational b)
   {
    return product(a,b,false);
   }   

  public static Rational product(Rational a, Rational b, boolean mult)
   {
    Rational r = new Rational();
    
    if (mult)
     {
      r.numerator = Polynomial.product(a.numerator,b.numerator);
      r.denominator = Polynomial.product(a.denominator,b.denominator);
     }
    else
     {
      r.numerator = Polynomial.product(a.numerator,b.denominator);
      r.denominator = Polynomial.product(a.denominator,b.numerator);
     }
    return r;
   }

  public static Rational reciprocal(Rational a)
   {
    Rational r = new Rational();
    r.numerator = a.denominator;
    r.denominator = a.numerator; 
    return r;
   }

  public static Rational sum(Rational a, Rational b, boolean add)
   {
    Rational r = new Rational();

    r.denominator = Polynomial.product(a.denominator,b.denominator);
    r.numerator = Polynomial.product(a.numerator,b.denominator);

    if (add)
      r.numerator.add(Polynomial.product(a.denominator,b.numerator));
    else
      r.numerator.sub(Polynomial.product(a.denominator,b.numerator));

    return r;
   }

  public String toString()
   {
    String string = new String();

//    string = numerator.toString() + "/" + denominator.toString();
//    return string;

    if (numerator.terms.size() > 1)
      string = "(" + numerator.toString() + ")";
    else
      string = numerator.toString();

    int a = denominator.terms.size();
    if (a == 0) { string += "/0"; return string; }

    Monomial m = denominator.term(0);

    boolean needs_paren = false;
    boolean needs_denom = false;

    if (a > 1) needs_paren = needs_denom = true;
    else
     {
      if (m.coeff != 1) needs_denom = true;
      int count = 0;
      for (int i = 0; i < Monomial.num_vars; i++)
        if (m.exponent[i] > 0) count++;
      if (count > 0) needs_denom = true;
      if (count > 1) needs_paren = true;
     }

    if (needs_denom)
     {
      string += "/";
      if (needs_paren)
        string += "(" + denominator.toString() + ")";
      else
        string += denominator.toString();
     }

    return string;
   } 

  public Rational simplify()
   {
    numerator.simplify();
    denominator.simplify();

    boolean null_num =  (numerator.terms.size() == 1 && numerator.term(0).coeff == 0);
    boolean null_den =  (denominator.terms.size() == 1 && denominator.term(0).coeff == 0);

    if (null_num && !null_den)
     {
      numerator = new Polynomial(0);
      denominator = new Polynomial(1);
      return this;
     }

    if (null_den && !null_num)
     {
      numerator = new Polynomial(1);
      denominator = new Polynomial(0);
      return this;
     }

    Monomial m = Polynomial.concat(numerator,denominator).common_factor();

    if (m.coeff == 0) return this;

    if (denominator.term(0).coeff < 0)
      m.coeff = -m.coeff;

//    System.out.println("common factor is " + m.toString());

    numerator.divide(m);
    denominator.divide(m);

    return this;
   }
}
