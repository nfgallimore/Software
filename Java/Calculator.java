import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Holder;
import Sobolev;
import Young;
import Dual;
import Wave_Strichartz;
import Schrodinger;
import Interp;
import Restriction;
import Extension;
import Fractint;
import Scaling;

public class Calculator extends Applet
 {
  Choice choice;
  Panel center;
  CardLayout layout = new CardLayout();

  public void init()
   {
    setLayout(new BorderLayout(15,15));
    add("North", UpperPanel());
    add("Center", center = new Panel());

    center.setLayout(layout);
    addc("Holder's inequality", new Holder());
    addc("Sobolev inequality", new Sobolev());
    addc("Young's inequality", new Young());
    addc("Fractional integration", new Fractint());
    addc("Dual exponent", new Dual());
    addc("Wave Strichartz", new Wave_Strichartz());
    addc("Schrodinger Strichartz", new Schrodinger());
    addc("Linear interpolation", new Interp());
    addc("Restriction conjecture", new Restriction());
    addc("Extension conjecture", new Extension());
    addc("Scaling exponent", new Scaling());
    layout.first(center);
   }
  
   public void addc(String string, Component component)
    {
     choice.addItem(string);
     center.add(string, component);
    }
 
   Panel UpperPanel()
   {
    Panel controls = new Panel();
 
    controls.add(new Label("Select a calculator:")); 
    controls.add(choice = new Choice());
 
    return controls;
   }

  public boolean action(Event evt, Object what)
    {
     if (evt.target==choice)
      {
       layout.show(center,choice.getSelectedItem());
       repaint();
       center.repaint();
       return true;
      }
     return false;
   }
 }
