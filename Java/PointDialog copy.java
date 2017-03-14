import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class PointDialog extends Dialog
 {
  TextField xText, yText;
  MobiusApp applet;

  PointDialog(Frame f, String t, boolean b,MobiusApp applet)
    {
     super(f,t,b);
     this.applet = applet;
     resize(500,160);
     setLayout(new BorderLayout());
     add("North", new Label("Enter the Cartesian co-ordinates of the new point."));
     add("Center", midPanel());
     add("South", lowerPanel());
    }

  public boolean action(Event ev, Object arg) 
   {
    if (ev.target instanceof Button) 
     {
      String label = (String)arg;
      if (label.equals("Done")) 
       {
        float x,y;
        try
         {
          x = Float.valueOf(xText.getText()).floatValue();
          y = Float.valueOf(yText.getText()).floatValue();
         }
        catch (NumberFormatException e)
         { 
          System.out.println("Number format exception!");
          dispose();
          return true;
         }
        applet.newPoint(x,y);
        dispose();
        return true;
       }
      if (label.equals("Cancel")) 
       {
        dispose();
        return true;
       }
     }
    return false;
   }
       
  Panel midPanel()
   {
    Panel panel = new Panel();

    panel.add(new Label("z = "));
    panel.add(xText = new TextField("0",8));
    panel.add(new Label(" + "));
    panel.add(yText = new TextField("0",8));
    panel.add(new Label("i")); 
    
    return panel;
   }

  Panel lowerPanel()
   {
    Panel panel = new Panel();
 
    panel.add(new Button("Done"));
    panel.add(new Button("Cancel"));
    return panel;
   }
 }
