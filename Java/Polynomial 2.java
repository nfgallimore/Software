import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Monomial;

public class Polynomial extends Object
 {
  public Vector terms;

  Polynomial()
   {
    terms = new Vector();
   }
  
  Polynomial(int coeff)
   {
    terms = new Vector();

    terms.addElement(new Monomial(coeff));
   }

  Polynomial(String name)
   {
    terms = new Vector();
    terms.addElement(new Monomial(name));
   }

  public static Polynomial concat(Polynomial x, Polynomial y)
   {
    Polynomial p = new Polynomial();
    int i,n;

    n = x.terms.size();
    for (i = 0; i < n; i++)
      p.terms.addElement(x.term(i));

    n = y.terms.size();
    for (i = 0; i < n; i++)
      p.terms.addElement(y.term(i));

    return p;
   }

  public void add(Monomial x)
   {
    terms.addElement(x);
   }

  public void add(Polynomial x)
   {
    int n = x.terms.size();
    for (int i = 0; i < n; i++)
      add(x.term(i));
    simplify();
   }

  public void sub(Polynomial x)
   {
    int n = x.terms.size();
    for (int i = 0; i < n; i++)
     {
      Monomial m = x.term(i);
      m.coeff = -m.coeff;
      terms.addElement(m);
     }
    simplify();
   }

  public static Polynomial product(Polynomial x, Polynomial y)
   {
    int n = x.terms.size(), m = y.terms.size();
    Polynomial z = new Polynomial();

    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        z.add(Monomial.product(x.term(i), y.term(j)));
    z.simplify();

    return z;
   }        

  public Monomial term(int i)
   {
    return (Monomial) terms.elementAt(i);
   }

  public void mult(Monomial z)
   {
    for (int i = 0; i < terms.size(); i++)
     {
      term(i).mult(z);
     }
   }

  public void simplify()
   {
    int i,j;
 
    for (i = terms.size()-1; i >= 0; i--)
     {
      Monomial m = term(i);
 
      for (j = terms.size()-1; j > i; j--)
       {
        Monomial n = term(j);
        if (m.like(n))
         {
          m.add(n);
          terms.removeElement(n);
         }
       }
     }

    for (i = terms.size()-1; i>= 0;i--)
     {
      Monomial m = term(i);
      
      if (m.coeff == 0 && terms.size() > 1)
        terms.removeElement(m);
     }
   }

 
  public String toString()
   {
    if (terms.size() == 0) return "0";

    int i;
    String string = new String();

    for (i = 0; i < terms.size(); i++)
     {
      Monomial m = term(i);

      if (i > 0 && m.positive())
        string += "+";

      string += m.toString();
     }
    return string;
   } 

  public void divide(Monomial m)
   {
    int i;
 
    for (int j = 0; j < terms.size(); j++)
     {
//      System.out.println("dividing " + String.valueOf(term(j).coeff)
//                        + " by " + String.valueOf(m.coeff)); 
      term(j).coeff /= m.coeff;
//      System.out.println("We obtain " + String.valueOf(term(j).coeff));
      for (i = 0; i <Monomial.num_vars; i++)
        term(j).exponent[i] -= m.exponent[i];
     }
   }

  public Monomial common_factor()
   {
    Monomial m = new Monomial();
    int i,j,abs,k,gcd;

    if (terms.size() == 0) return m;

//    System.out.println("Factorizing " + toString());

    Monomial n = term(0);

    m.coeff = (n.coeff > 0) ? n.coeff : -n.coeff;
    for (i = 0; i < Monomial.num_vars; i++) m.exponent[i] = n.exponent[i];

//    System.out.println("Initial mcoeff " + String.valueOf(m.coeff));
    for (j = 1; j < terms.size(); j++)
     {
      n = term(j);
      abs = (n.coeff > 0) ? n.coeff : -n.coeff;
      gcd = 0;
      for (k = 1; k <= m.coeff; k++)
        if (m.coeff % k == 0 && abs % k == 0) gcd = k;
      m.coeff = gcd;
      for (i = 0; i < Monomial.num_vars; i++) 
        if (m.exponent[i] > n.exponent[i]) m.exponent[i] = n.exponent[i];
 //     System.out.println("New mcoeff " + String.valueOf(m.coeff));
     }

    return m;
   }         
}
