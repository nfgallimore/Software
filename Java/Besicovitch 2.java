import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Kakeya;

public class Besicovitch extends Applet
 {
  TextField alpha_field, n_field, area_field; 
  Kakeya kakeya;
  Button redraw_button;

   public boolean action(Event evt, Object what)
    {
     if (evt.target==redraw_button) 
      {
       float alpha = 1;
       int n=0;

       try
        {
         alpha = Float.valueOf(alpha_field.getText()).floatValue();
         n = Integer.valueOf(n_field.getText()).intValue();
        }
       catch (NumberFormatException e)
        {
         return false;
        }

       if (n < 0) return false;
       if (n >= 10) return false;
       if (alpha < 0.5) return false;
       if (alpha > 1) return false;

       kakeya.redraw(alpha,n);
       area_field.setText(Float.toString(kakeya.area_est));
       return true;
      }
     return false;
    }

  public void init()
   {
    setLayout(new BorderLayout(15,15));
    add("North", UpperPanel());
    add("Center", kakeya = new Kakeya());
    add("South", LowerPanel());
   }

  Panel LowerPanel()
   {
    Panel buttons = new Panel();
 
    buttons.add(new Label("Upper bound for area:"));

    buttons.add(area_field = new TextField("1.2",15));

    area_field.setEditable(false);
    
    return buttons;
   }

  Panel UpperPanel()
   {
    Panel controls = new Panel();

    controls.add(new Label("alpha")); 		controls.add(alpha_field = new TextField("0.8",4));
    controls.add(new Label("n")); 		controls.add(n_field = new TextField("0",4));
    controls.add(redraw_button = new Button("Redraw"));

    return controls;
   }
 }
