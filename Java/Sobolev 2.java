import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Rational;

public class Sobolev extends Panel 
 {
  TextField n_field, s_field, p_field, q_field; 
  Button compute_p;
  Button compute_q;
  Button compute_s;

  Sobolev()
   { 
    Panel controls;
 
    setLayout(new GridLayout(8,1));
 
 
    add(new Label("  If \\nabla^a f is in L^p(R^n), then \\nabla^{a-s} f is in L^q(R^n), where"));
 
    controls = new Panel();

    controls.add(new Label("n =")); 
    controls.add(n_field = new TextField("3",10));
    controls.add(new Label("  s ="));
    controls.add(s_field = new TextField("1",10));
    controls.add(new Label("  p ="));
    controls.add(p_field = new TextField("2",10));
    controls.add(new Label("  q ="));
    controls.add(q_field = new TextField("6",10));
    add(controls);

    controls = new Panel();
    controls.add(compute_s = new Button("Compute s from p,q,n"));
    controls.add(compute_p = new Button("Compute p from s,q,n"));
    controls.add(compute_q = new Button("Compute q from s,p,n"));
    add(controls);

    add(new Label("  Formula: 1/p = 1/q + s/n"));

    add(new Label("  Restrictions: "));
 
    add(new Label("    s >= 0;  1 < p < q < \\infty"));
    add(new Label("    Endpoints are possible with epsilon more (inhomogenous) derivatives, or one can use H^1 and BMO"));
   }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==compute_p) 
      {
       try
        {
         Rational n = Rational.valueOf(n_field.getText());
         Rational q = Rational.reciprocal(Rational.valueOf(q_field.getText()));
         Rational s = Rational.product(Rational.valueOf(s_field.getText()),n,false);
         Rational p = Rational.reciprocal(Rational.sum(q,s,true));
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
         Rational n = Rational.valueOf(n_field.getText());
         Rational p = Rational.reciprocal(Rational.valueOf(p_field.getText()));
         Rational s = Rational.product(Rational.valueOf(s_field.getText()),n,false);
         Rational q = Rational.reciprocal(Rational.sum(p,s,false));
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

     if (evt.target==compute_s) 
      {
       try
        {
         Rational n = Rational.valueOf(n_field.getText());
         Rational p = Rational.reciprocal(Rational.valueOf(p_field.getText()));
         Rational q = Rational.reciprocal(Rational.valueOf(q_field.getText()));
         Rational s = Rational.product(Rational.sum(p,q,false),n,true);
         s.simplify();
         s_field.setText(s.toString());
        }
       catch (NumberFormatException e)
        {
         s_field.setText("Syntax error!");
         return false;
        }
       return true;
      }

     return false;
    }

 }
