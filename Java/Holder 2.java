import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Rational;

public class Holder extends Panel 
 {
  TextField a_field, b_field, ab_field; 
  Button compute_a;
  Button compute_b;
  Button compute_ab;

  Holder()
   { 
    Panel controls;
 
    setLayout(new GridLayout(8,1));
 
 
    add(new Label("  If f is in L^p, and g is in L^q, then fg is in L^r, where"));
 
    controls = new Panel();

    controls.add(new Label("p =")); 
    controls.add(a_field = new TextField("2",10));
    controls.add(new Label("  q ="));
    controls.add(b_field = new TextField("2",10));
    controls.add(new Label("  r ="));
    controls.add(ab_field = new TextField("1",10));
    add(controls);

    controls = new Panel();
    controls.add(compute_a = new Button("Compute p from q,r"));
    controls.add(compute_b = new Button("Compute q from p,r"));
    controls.add(compute_ab = new Button("Compute r from p,q"));
    add(controls);

    add(new Label("  Formula: 1/p + 1/q = 1/r"));

    add(new Label("  Restrictions: "));
 
    add(new Label("    0 < p,q,r \\leq \\infty"));

    add(new Label("  Additional Holder estimates are available if the support is compact."));
   }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==compute_ab) 
      {
       try
        {
         Rational a = Rational.reciprocal(Rational.valueOf(a_field.getText()));
         Rational b = Rational.reciprocal(Rational.valueOf(b_field.getText()));
         Rational c = Rational.reciprocal(Rational.sum(a,b,true));
         c.simplify();
         ab_field.setText(c.toString());
        }
       catch (NumberFormatException e)
        {
         ab_field.setText("Syntax error!");
         return false;
        }
       return true;
      }

     if (evt.target==compute_a) 
      {
       try
        {
         Rational a = Rational.reciprocal(Rational.valueOf(ab_field.getText()));
         Rational b = Rational.reciprocal(Rational.valueOf(b_field.getText()));
         Rational c = Rational.reciprocal(Rational.sum(a,b,false));
         c.simplify();
         a_field.setText(c.toString());
        }
       catch (NumberFormatException e)
        {
         a_field.setText("Syntax error!");
         return false;
        }
       return true;
      }

     if (evt.target==compute_b) 
      {
       try
        {
         Rational a = Rational.reciprocal(Rational.valueOf(ab_field.getText()));
         Rational b = Rational.reciprocal(Rational.valueOf(a_field.getText()));
         Rational c = Rational.reciprocal(Rational.sum(a,b,false));
         c.simplify();
         b_field.setText(c.toString());
        }
       catch (NumberFormatException e)
        {
         b_field.setText("Syntax error!");
         return false;
        }
       return true;
      }



     return false;
    }

 }
