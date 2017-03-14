import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class MobiusDialog extends Dialog
 {
  TextField axText, ayText, bxText, byText;
  TextField cxText, cyText, dxText, dyText;
  Complex a,b,c,d;
  MobiusApp applet;

  MobiusDialog(Frame f, String t, boolean bool,MobiusApp applet,
               Complex a, Complex b, Complex c, Complex d)
    {
     super(f,t,bool);
     this.a = a;
     this.b = b;
     this.c = c;
     this.d = d;
     this.applet = applet;
     resize(600,180);
     setLayout(new BorderLayout());
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
        Complex a = new Complex();
        Complex b = new Complex();
        Complex c = new Complex();
        Complex d = new Complex();
        
        try
         {
          a.x = Float.valueOf(axText.getText()).floatValue();
          a.y = Float.valueOf(ayText.getText()).floatValue();
          b.x = Float.valueOf(bxText.getText()).floatValue();
          b.y = Float.valueOf(byText.getText()).floatValue();
          c.x = Float.valueOf(cxText.getText()).floatValue();
          c.y = Float.valueOf(cyText.getText()).floatValue();
          d.x = Float.valueOf(dxText.getText()).floatValue();
          d.y = Float.valueOf(dyText.getText()).floatValue();
         }
        catch (NumberFormatException e)
         { 
          System.out.println("Number format exception!");
          dispose();
          return true;
         }
        applet.setTransform(a,b,c,d);
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

    panel.setLayout(gridbag);
    end.gridwidth = GridBagConstraints.REMAINDER;

    panel.add(new Label(""));
    panel.add(new Label("("));
    panel.add(axText = new TextField(String.valueOf(a.x),7));
    panel.add(new Label(" + "));
    panel.add(ayText = new TextField(String.valueOf(a.y),7));
    panel.add(new Label("i)z + "));
    panel.add(bxText = new TextField(String.valueOf(b.x),7));
    panel.add(new Label(" + "));
    panel.add(byText = new TextField(String.valueOf(b.y),7));
    panel.add(label = new Label("i"));
    gridbag.setConstraints(label, end);

    panel.add(new Label("w = "));
    panel.add(label = new Label("-------------------------------------------"));
    gridbag.setConstraints(label, end);

    panel.add(new Label(""));
    panel.add(new Label("("));
    panel.add(cxText = new TextField(String.valueOf(c.x),7));
    panel.add(new Label(" + "));
    panel.add(cyText = new TextField(String.valueOf(c.y),7));
    panel.add(new Label("i)z + "));
    panel.add(dxText = new TextField(String.valueOf(d.x),7));
    panel.add(new Label(" + "));
    panel.add(dyText = new TextField(String.valueOf(d.y),7));
    panel.add(label = new Label("i"));
    gridbag.setConstraints(label, end);

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
