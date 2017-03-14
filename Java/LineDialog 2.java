import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class LineDialog extends Dialog
 {
  TextField x1Text, y1Text, x2Text, y2Text;
  MobiusApp applet;

  LineDialog(Frame f, String t, boolean b,MobiusApp applet)
    {
     super(f,t,b);
     this.applet = applet;
     resize(600,130);
     setLayout(new BorderLayout());
     add("North", new Label("Draw a line {z0 + t(z1-z0): t real}, where"));
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
        float x1,y1,x2,y2;
        try
         {
          x1 = Float.valueOf(x1Text.getText()).floatValue();
          y1 = Float.valueOf(y1Text.getText()).floatValue();
          x2 = Float.valueOf(x2Text.getText()).floatValue();
          y2 = Float.valueOf(y2Text.getText()).floatValue();
         }
        catch (NumberFormatException e)
         { 
          System.out.println("Number format exception!");
          dispose();
          return true;
         }
        applet.newLine(x1,y1,x2,y2);
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
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints end = new GridBagConstraints();
    Label label;

    end.gridwidth = GridBagConstraints.REMAINDER;

    panel.setLayout(gridbag);

    panel.add(new Label("z0 = "));
    panel.add(x1Text = new TextField("0",8));
    panel.add(new Label(" + "));
    panel.add(y1Text = new TextField("0",8));
    panel.add(label = new Label("i  and"));
    gridbag.setConstraints(label,end);

    panel.add(new Label("z1 = "));
    panel.add(x2Text = new TextField("0",8));
    panel.add(new Label(" + "));
    panel.add(y2Text = new TextField("0",8));
    panel.add(label = new Label("i  are points on the line."));
    gridbag.setConstraints(label,end);
 
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
