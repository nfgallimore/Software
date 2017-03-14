import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Rational;

public class Restriction extends Panel 
 {
  TextField n_field, p_field, q_field; 
  Button compute_p;
  Button compute_q;

  Restriction()
   { 
    Panel controls;
 
    setLayout(new GridLayout(8,1));
 
 
    add(new Label("  If f is an L^p measure on R^n, then its Fourier transform when restricted to the unit sphere"));
    add(new Label("or paraboloid is in L^q, where"));
 
    controls = new Panel();

    controls.add(new Label("n =")); 
    controls.add(n_field = new TextField("3",10));
    controls.add(new Label("  p ="));
    controls.add(p_field = new TextField("4/3",10));
    controls.add(new Label("  q ="));
    controls.add(q_field = new TextField("2",10));
    add(controls);

    controls = new Panel();
    controls.add(compute_p = new Button("Compute p from q,n"));
    controls.add(compute_q = new Button("Compute q from p,n"));
    add(controls);

    add(new Label("  Formula: (n-1)/q = (n+1)(1 - 1/p)"));

    add(new Label("  Restriction (conjectured): p \\leq 2n/(n+1)"));
 
    add(new Label("  Restrictions (known): p \\leq 4/3 for n=2,   p \\leq 4/3 + 8/255 for n=3,   p \\leq 2(n+1)/(n+3) for n>3"));

    add(new Label("If the restriction surface is compact one can decrease p and q arbitrarily."));
   }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==compute_p) 
      {
       try
        {
         Rational n = Rational.valueOf(n_field);
         Rational nd = Rational.quotient(Rational.difference(n,new Rational(1)),
                                               Rational.sum(n,new Rational(1)));
         Rational q = Rational.quotient(nd,Rational.valueOf(q_field));
         Rational p = Rational.reciprocal(Rational.difference(new Rational(1),q));
         p.simplify(); 
         p_field.setText(p.toString());
        }
       catch (NumberFormatException e)
        {
         p_field.setText("Syntax error!");
         return false;
        }
       return true;
      }

     if (evt.target==compute_q) 
      {
       try
        {
         Rational n = Rational.valueOf(n_field);
         Rational nd = Rational.quotient(Rational.difference(n,new Rational(1)),
                                  Rational.sum(n,new Rational(1)));
         Rational p = Rational.difference(new Rational(1),Rational.recip_valueOf(p_field));
         Rational q = Rational.quotient(nd,p);
         q.simplify(); 
         q_field.setText(q.toString());
        }
       catch (NumberFormatException e)
        {
         q_field.setText("Syntax error!");
         return false;
        }
       return true;
      }


     return false;
    }

 }
