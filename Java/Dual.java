import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Rational;

public class Dual extends Panel 
 {
  TextField p_field, pp_field; 
  Button dualButton;

  Dual()
   { 
    Panel controls;
 
    setLayout(new GridLayout(8,1));
 
 
    controls = new Panel();

    controls.add(new Label("  If p =")); 
    controls.add(p_field = new TextField("2",10));
    controls.add(new Label("  then the dual of L^p is L^{p'}, where p' ="));
    controls.add(pp_field = new TextField("2",10));
    pp_field.setEditable(false);
    add(controls);

    controls = new Panel();
    controls.add(dualButton = new Button("Compute dual"));
    add(controls);

    add(new Label("  Formula: 1/p + 1/p' = 1"));

    add(new Label("  Restrictions: "));
 
    add(new Label("  0 < p \\leq \\infty"));

    add(new Label("  Duality is a symmetric operation."));
   }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==p_field || evt.target==dualButton) 
      {
       try
        {
         Rational p = Rational.reciprocal(Rational.valueOf(p_field.getText()));
         Rational pp = Rational.reciprocal(Rational.sum(new Rational(1),p,false));
         pp.simplify();
         pp_field.setText(pp.toString());
        }
       catch (NumberFormatException e)
        {
         pp_field.setText("Syntax error!");
         return false;
        }
       return true;
      }

     return false;
    }

 }
