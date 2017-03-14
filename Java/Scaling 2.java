import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Rational;

public class Scaling extends Panel 
 {
  TextField n_field, b_field, k_field, l_field, s_field; 
  Button compute_k;
  Button compute_s;

  Scaling()
   { 
    Panel controls;
 
    setLayout(new GridLayout(8,1));
 
 
    add(new Label("  A non-linear equation with the schematic form (\\partial_t^a + \\nabla^b) u = u^k (\\nabla u)^l in R^n has"));

    add(new Label("critical regularity H^s, where"));
 
    controls = new Panel();

    controls.add(new Label("n =")); 
    controls.add(n_field = new TextField("3",10));
    controls.add(new Label("  b ="));
    controls.add(b_field = new TextField("2",10));
    controls.add(new Label("  k ="));
    controls.add(k_field = new TextField("3",10));
    add(controls);

    controls = new Panel();
    controls.add(new Label("  l ="));
    controls.add(l_field = new TextField("0",10));
    controls.add(new Label("  s ="));
    controls.add(s_field = new TextField("1/2",10));
    add(controls);

    controls = new Panel();
    controls.add(compute_k = new Button("Compute k from n,b,l,s"));
    controls.add(compute_s = new Button("Compute s from n,b,k,l"));
    add(controls);

    add(new Label("  Formula: s = n/2 - (b-l)/(k+l-1)"));

   }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==compute_s) 
      {
       try
        {
         Rational n = Rational.quotient(Rational.valueOf(n_field), new Rational(2));
         Rational l = Rational.valueOf(l_field);
         Rational bl = Rational.difference(Rational.valueOf(b_field),l);
         Rational kl = Rational.sum(Rational.valueOf(k_field),l);
	 Rational kl1 = Rational.difference(kl, new Rational(1));
         Rational s = Rational.difference(n,Rational.quotient(bl,kl1));
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

     if (evt.target==compute_k) 
      {
       try
        {
         Rational n = Rational.quotient(Rational.valueOf(n_field), new Rational(2));
	 Rational sn = Rational.difference(Rational.valueOf(s_field),n);
         Rational l = Rational.valueOf(l_field);
         Rational bl = Rational.difference(Rational.valueOf(b_field),l);
	 Rational k = Rational.difference(Rational.difference(new Rational(1),l), Rational.quotient(bl,sn));         
         k.simplify(); 
         k_field.setText(k.toString());
        }
       catch (NumberFormatException e)
        {
         k_field.setText("Syntax error!");
         return false;
        }
       return true;
      }


     return false;
    }

 }
