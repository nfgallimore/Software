import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Rational;

public class Interp extends Panel 
 {
  TextField p1_field, q1_field, N1_field; 
  TextField p2_field, q2_field, N2_field; 
  TextField p_field, q_field, N_field, t_field; 
  Button compute_t, compute_N, compute_p, compute_q;

  Interp()
   { 
    Panel controls;
 
    setLayout(new GridLayout(9,1));
 
    add(new Label("  If T maps L^{p_i} to L^{q_i} with constant 2^{N_i} for i=1,2, "));
 
    add(new Label("we may interpolate with parameter t, so that T maps L^p to L^q with constant 2^N, where"));
 
    controls = new Panel();

    controls.add(new Label("p_1 =")); 
    controls.add(p1_field = new TextField("1",10));
    controls.add(new Label("  q_1 ="));
    controls.add(q1_field = new TextField("1",10));
    controls.add(new Label("  N_1 ="));
    controls.add(N1_field = new TextField("0",10));
    add(controls);

    controls = new Panel();

    controls.add(new Label("p_2 =")); 
    controls.add(p2_field = new TextField("1/0",10));
    controls.add(new Label("  q_2 ="));
    controls.add(q2_field = new TextField("1/0",10));
    controls.add(new Label("  N_2 ="));
    controls.add(N2_field = new TextField("0",10));
    add(controls);

    controls = new Panel();

    controls.add(new Label("p =")); 
    controls.add(p_field = new TextField("2",10));
    controls.add(new Label("  q ="));
    controls.add(q_field = new TextField("2",10));
    controls.add(new Label("  N ="));
    controls.add(N_field = new TextField("0",10));
    controls.add(new Label("  t ="));
    controls.add(t_field = new TextField("1/2",10));
    add(controls);

    controls = new Panel();
    controls.add(compute_p = new Button("Compute q,N,t from p"));
    controls.add(compute_q = new Button("Compute p,N,t from q"));
    controls.add(compute_N = new Button("Compute p,q,t from N"));
    controls.add(compute_t = new Button("Compute p,q,N from t"));
    add(controls);

    add(new Label("  Formulae: 1/p = t/p_1 + (1-t)/p_2;  1/q = t/q_1 + (1-t)/q_2;  N = t N_1 + (1-t) N_2"));

    add(new Label("  Restrictions: depends on whether one uses the real or complex method."));
    add(new Label("  One can easily do backwards interpolation (extrapolation) by swapping p,q,N with p_2,q_2,N_2 (say)."));

   }

   Rational interpolate(Rational a, Rational b, Rational t) throws NumberFormatException
    {
     Rational t1 = Rational.sum(new Rational(1),t,false);
     return Rational.sum(Rational.product(a,t1,true),Rational.product(b,t,true),true);
    }

   Rational parameter(Rational a, Rational b, Rational c) throws NumberFormatException
    {
     return Rational.product(Rational.sum(c,a,false),Rational.sum(b,a,false),false);
    }

   public boolean action(Event evt, Object what)
    {
     if (evt.target instanceof Button)
       try
        {
         Rational p1 = Rational.reciprocal(Rational.valueOf(p1_field.getText()));
         Rational p2 = Rational.reciprocal(Rational.valueOf(p2_field.getText()));
         Rational q1 = Rational.reciprocal(Rational.valueOf(q1_field.getText()));
         Rational q2 = Rational.reciprocal(Rational.valueOf(q2_field.getText()));
         Rational N1 = Rational.valueOf(N1_field.getText());
         Rational N2 = Rational.valueOf(N2_field.getText());

         Rational t = new Rational();

         if (evt.target == compute_t)
           t = Rational.valueOf(t_field.getText());

         if (evt.target == compute_p)
           t = parameter(p1,p2,Rational.reciprocal(Rational.valueOf(p_field.getText())));

         if (evt.target == compute_q)
           t = parameter(q1,q2,Rational.reciprocal(Rational.valueOf(q_field.getText())));

         if (evt.target == compute_N)
           t = parameter(N1,N2,Rational.valueOf(N_field.getText()));

         if (evt.target != compute_t)
           t_field.setText(t.simplify().toString());

         if (evt.target != compute_p)
           p_field.setText(Rational.reciprocal(interpolate(p1,p2,t)).simplify().toString());

         if (evt.target != compute_q)
           q_field.setText(Rational.reciprocal(interpolate(q1,q2,t)).simplify().toString());

         if (evt.target != compute_N)
           N_field.setText(interpolate(N1,N2,t).simplify().toString());
         return true;
        }
       catch (NumberFormatException e)
        {
         p_field.setText("Syntax error!");
         q_field.setText("Syntax error!");
         N_field.setText("Syntax error!");
         t_field.setText("Syntax error!");
         return false;
        }
     return false;
    }

 }
