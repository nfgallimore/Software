import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Rational;

public class Extension extends Panel 
 {
  TextField n_field, p_field, q_field; 
  Button compute_p;
  Button compute_q;

  Extension()
   { 
    Panel controls;
 
    setLayout(new GridLayout(8,1));
 
 
    add(new Label("  If f is an L^p measure on the unit sphere or paraboloid in R^n, then the Fourier transform of f is in L^q, where"));
 
    controls = new Panel();

    controls.add(new Label("n =")); 
    controls.add(n_field = new TextField("3",10));
    controls.add(new Label("  p ="));
    controls.add(p_field = new TextField("2",10));
    controls.add(new Label("  q ="));
    controls.add(q_field = new TextField("4",10));
    add(controls);

    controls = new Panel();
    controls.add(compute_p = new Button("Compute p from q,n"));
    controls.add(compute_q = new Button("Compute q from p,n"));
    add(controls);

    add(new Label("  Formula: (n+1)/q = (n-1)(1 - 1/p)"));

    add(new Label("  Restriction (conjectured): q \\geq 2n/(n-1)"));
 
    add(new Label("  Restrictions (known): q \\geq 4 for n=2,   q \\geq 4 - 8/31 for n=3,   q \\geq 2(n+1)/(n-1) for n>3"));

    add(new Label("When f is compactly supported one can increase p and q arbitrarily."));
   }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==compute_p) 
      {
       try
        {
         Rational n = Rational.valueOf(n_field.getText());
         Rational nd = Rational.product(Rational.sum(n,new Rational(1),true),
                                        Rational.sum(n,new Rational(1),false),
                                        false);
         Rational q = Rational.product(nd,Rational.valueOf(q_field.getText()),false);
         Rational p = Rational.reciprocal(Rational.sum(new Rational(1),q,false));
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
         Rational nd = Rational.quotient(Rational.sum(n,new Rational(1)),
                                  Rational.difference(n,new Rational(1)));
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
