import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Rational;

public class Fractint extends Panel 
 {
  TextField p_field, q_field, a_field, n_field; 
  Button compute_p;
  Button compute_q;
  Button compute_a;

  Fractint()
   { 
    Panel controls;
 
    setLayout(new GridLayout(8,1));
 
 
    add(new Label("  If f is in L^p(R^n), then f * |x|^{-a} is in L^q(R^n), where"));
 
    controls = new Panel();

    controls.add(new Label("n =")); 
    controls.add(n_field = new TextField("3",10));
    controls.add(new Label("  p =")); 
    controls.add(p_field = new TextField("2",10));
    controls.add(new Label("  a =")); 
    controls.add(a_field = new TextField("2",10));
    controls.add(new Label("  q =")); 
    controls.add(q_field = new TextField("6",10));
    add(controls);

    controls = new Panel();
    controls.add(compute_p = new Button("Compute p from a,q"));
    controls.add(compute_a = new Button("Compute a from p,q"));
    controls.add(compute_q = new Button("Compute q from p,a"));
    add(controls);

    add(new Label("  Formula: 1/p + a/n = 1/q + 1"));

    add(new Label("  Restrictions: "));
 
    add(new Label("    1 < p,q < \\infty,  0 < a < n"));

   }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==compute_p) 
      {
       try
        {
         Rational a = Rational.quotient(Rational.valueOf(a_field),Rational.valueOf(n_field));
         Rational q = Rational.sum(Rational.reciprocal(Rational.valueOf(q_field)),new Rational(1));
         Rational p = Rational.reciprocal(Rational.difference(q,a));
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
         Rational a = Rational.quotient(Rational.valueOf(a_field),Rational.valueOf(n_field));
         Rational p = Rational.difference(Rational.reciprocal(Rational.valueOf(p_field)),new Rational(1));
         Rational q = Rational.reciprocal(Rational.sum(p,a));
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


     if (evt.target==compute_a) 
      {
       try
        {
         Rational q = Rational.sum(Rational.reciprocal(Rational.valueOf(q_field)),new Rational(1));
         Rational an = Rational.difference(q,Rational.valueOf(p_field));
         Rational a = Rational.product(an,Rational.valueOf(n_field));
         a.simplify();
         a_field.setText(a.toString());
        }
       catch (NumberFormatException e)
        {
         a_field.setText("Syntax error!");
         return false;
        }
       return true;
      }




     return false;
    }

 }
