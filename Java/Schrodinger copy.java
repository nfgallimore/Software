import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Rational;

public class Schrodinger extends Panel 
 {
  TextField n_field, q_field, r_field; 
  Button compute_q;
  Button compute_r;

  Schrodinger()
   { 
    Panel controls;
 
    setLayout(new GridLayout(8,1));
 
 
    add(new Label("  If \\phi solves the free Schrodinger equation in R^n with L^2 initial data,"));
    add(new Label("  then \\phi is in L^q_t L^r_x, where"));
 
    controls = new Panel();

    controls.add(new Label("n =")); 
    controls.add(n_field = new TextField("2",10));
    controls.add(new Label("  q ="));
    controls.add(q_field = new TextField("4",10));
    controls.add(new Label("  r ="));
    controls.add(r_field = new TextField("4",10));
    add(controls);

    controls = new Panel();
    controls.add(compute_q = new Button("Compute q from r,n"));
    controls.add(compute_r = new Button("Compute r from q,n"));
    add(controls);

    add(new Label("  Formula: n/2 = 2/q + n/r"));

    add(new Label("  Restrictions: "));
 
    add(new Label("    2 \\leq q,r \\leq \\infty;  (q,r,n) \\neq (\\infty,2,2)"));
   }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==compute_q) 
      {
       try
        {
         Rational n = Rational.product(new Rational(2),Rational.valueOf(n_field.getText()),false);
         Rational r = Rational.reciprocal(Rational.valueOf(r_field.getText()));
         Rational q = Rational.product(n,Rational.sum(new Rational(1,2),r,false),false);
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

     if (evt.target==compute_r) 
      {
       try
        {
         Rational n = Rational.valueOf(n_field.getText());
         Rational q = Rational.product(new Rational(2),Rational.valueOf(q_field.getText()),false);
         Rational q2 = Rational.sum(Rational.product(n,new Rational(2),false),q,false);
         Rational r = Rational.product(n,q2,false);
         r.simplify();
         r_field.setText(r.toString());
        }
       catch (NumberFormatException e)
        {
         r_field.setText("Syntax error!");
         return false;
        }
       return true;
      }

     return false;
    }

 }
