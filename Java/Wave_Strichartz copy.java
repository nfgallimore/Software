import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Rational;

public class Wave_Strichartz extends Panel 
 {
  TextField n_field, s_field, q_field, r_field; 
  Button compute_qr;
  Button compute_rs;
  Button compute_qs;

  Wave_Strichartz()
   { 
    Panel controls;
 
    setLayout(new GridLayout(9,1));
 
 
    add(new Label("  If \\phi solves the free wave equation in R^n with initial data in H^s x H^{s-1},"));
    add(new Label("  then \\phi is in L^q_t L^r_x, where"));
 
    controls = new Panel();

    controls.add(new Label("n =")); 
    controls.add(n_field = new TextField("3",10));
    controls.add(new Label("  s ="));
    controls.add(s_field = new TextField("1/2",10));
    controls.add(new Label("  q ="));
    controls.add(q_field = new TextField("4",10));
    controls.add(new Label("  r ="));
    controls.add(r_field = new TextField("4",10));
    add(controls);

    controls = new Panel();
    controls.add(compute_qr = new Button("Compute q,r from s,n"));
    controls.add(compute_rs = new Button("Compute r,s from q,n"));
    controls.add(compute_qs = new Button("Compute q,s from r,n"));
    add(controls);

    add(new Label("  Formulae: n/2 - s = 1/q + n/r;  1/q + (n-1)/2r = (n-1)/4"));

    add(new Label("  Restrictions: "));
 
    add(new Label("    2 \\leq q \\leq \\infty;  2 \\leq r < \\infty"));
    add(new Label("    Besov space versions are possible when r = \\infty unless (q,r,n)=(2,\\infty,3)"));
    add(new Label("  Further estimates can be obtained from Holder and/or Sobolev."));
   }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==compute_qr) 
      {
       try
        {
         Rational n = Rational.valueOf(n_field.getText());
         Rational n1 = Rational.sum(n,new Rational(1),true);
         Rational n2 = Rational.reciprocal(Rational.sum(n,new Rational(1),false));
         Rational s = Rational.product(Rational.valueOf(s_field.getText()),n1,false);
         Rational q = Rational.product(n2,s,false);
         s.multiply(2,true);
         Rational r = Rational.reciprocal(Rational.sum(new Rational(1,2),s,false));
         q.simplify(); r.simplify();
         q_field.setText(q.toString());
         r_field.setText(r.toString());
        }
       catch (NumberFormatException e)
        {
         q_field.setText("Syntax error!");
         r_field.setText("Syntax error!");
         return false;
        }
       return true;
      }

     if (evt.target==compute_qs) 
      {
       try
        {
         Rational n = Rational.valueOf(n_field.getText());
         Rational n1 = Rational.product(new Rational(2),Rational.sum(n,new Rational(1),false),false);
         Rational n2 = Rational.product(Rational.sum(n,new Rational(1),true),new Rational(2),false);
         Rational r = Rational.reciprocal(Rational.valueOf(r_field.getText()));
         Rational r2 = Rational.sum(new Rational(1,2),r,false);
         Rational q = Rational.product(n1,r2,false);
         Rational s = Rational.product(n2,r2,true);
         q.simplify(); s.simplify();
         q_field.setText(q.toString());
         s_field.setText(s.toString());
        }
       catch (NumberFormatException e)
        {
         q_field.setText("Syntax error!");
         s_field.setText("Syntax error!");
         return false;
        }
       return true;
      }

     if (evt.target==compute_rs) 
      {
       try
        {
         Rational n = Rational.valueOf(n_field.getText());
         Rational n1 = Rational.product(Rational.sum(n, new Rational(1),true), Rational.sum(n, new Rational(1),false), false);
         Rational n2 = Rational.product(new Rational(2), Rational.sum(n, new Rational(1),false), false);
         Rational q = Rational.valueOf(q_field.getText());
         Rational s = Rational.product(n1,q,false);
         Rational r = Rational.reciprocal(Rational.sum(new Rational(1,2),Rational.product(n2,q,false),false));
         s.simplify(); r.simplify();
         s_field.setText(s.toString());
         r_field.setText(r.toString());
        }
       catch (NumberFormatException e)
        {
         s_field.setText("Syntax error!");
         r_field.setText("Syntax error!");
         return false;
        }
       return true;
      }

     return false;
    }

 }
