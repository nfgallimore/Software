import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class CircleDialog extends Dialog
 {
  TextField xText, yText, rText;
  MobiusApp applet;

  CircleDialog(Frame f, String t, boolean b,MobiusApp applet)
    {
     super(f,t,b);
     this.applet = applet;
     resize(550,160);
     setLayout(new BorderLayout());
     add("North", new Label("Draw a circle {z: |z - z0| = r}, where"));
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
        float x,y,r;
        try
         {
          x = Float.valueOf(xText.getText()).floatValue();
          y = Float.valueOf(yText.getText()).floatValue();
          r = Float.valueOf(rText.getText()).floatValue();
         }
        catch (NumberFormatException e)
         { 
          System.out.println("Number format exception!");
          dispose();
          return true;
         }
        applet.newCircle(x,y,r);
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
    Panel line;

    panel.setLayout(new GridLayout(2, 1));

    line = new Panel();
    line.add(new Label("z0 = "));
    line.add(xText = new TextField("0",8));
    line.add(new Label(" + "));
    line.add(yText = new TextField("0",8));
    line.add(new Label("i  is the center, and")); 
    panel.add(line);

    line = new Panel();
    line.add(new Label("r = "));
    line.add(rText = new TextField("0",8));
    line.add(new Label(" is the radius."));
    panel.add(line);
 
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
