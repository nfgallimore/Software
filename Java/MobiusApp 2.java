import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Mobius;
import PointDialog;
import CircleDialog;
import MobiusDialog;

public class MobiusApp extends Applet
 {
  Mobius mobius;
  Choice mode, colorChoice;
  Button clearButton,deleteButton,updateButton,simplifyButton, invertButton;
  Button add1Button, addiButton, sub1Button, subiButton;
  Button halfButton, doubleButton, minusButton, clockButton, counterButton;
  Button halfclockButton, halfcounterButton;
  Button identityButton, inversionButton, smithButton, ismithButton, cayleyButton;
  Button moveButton;
  Button inverseButton;
  Button originButton, realButton, imagButton, unitButton;
  Button pointButton, circleButton, lineButton;
  public TextField TransformField;
  float sqrt2_2;



   public boolean action(Event evt, Object what)
    {
     if (evt.target==mode) 
      {
       mobius.setMode(mode.getSelectedItem());
       return true;
      }
     if (evt.target==colorChoice)
      {
       mobius.setColor(colorChoice.getSelectedItem());
       return true;
      }
     if (evt.target==deleteButton)
      {
       mobius.delete();
       return true;
      }
     if (evt.target==clearButton)
      {
       mobius.clear();
       return true;
      }
     if (evt.target==updateButton)
      {
       Frame frame = new Frame("New Transform");
       MobiusDialog dialog = new MobiusDialog(frame, "New Transform", true, this, mobius.a, mobius.b, mobius.c, mobius.d);
       dialog.show();
       return true;
      }

     if (evt.target == simplifyButton)
      {
       Complex a = mobius.a;
       Complex b = mobius.b;
       Complex c = mobius.c;
       Complex d = mobius.d;
       
       if (a.norm() > 0.001)
        {
         mobius.setTransform(new Complex(1,0),b.over(a),c.over(a),d.over(a));
         return true;
        }
       if (b.norm() > 0.001)
        {
         mobius.setTransform(a.over(b),new Complex(1,0), c.over(b), d.over(b));
         return true;
        }
       return false;
      }

     if (evt.target == add1Button) { translate(0.5f,0);  return true; }
     if (evt.target == addiButton) { translate(0,0.5f);  return true; }
     if (evt.target == sub1Button) { translate(-0.5f,0); return true; }
     if (evt.target == subiButton) { translate(0,-0.5f); return true; }

     if (evt.target == halfButton)     { dilate(0.5f,0); return true; }
     if (evt.target == doubleButton)   { dilate(2,0);    return true; }
     if (evt.target == minusButton)    { dilate(-1,0);   return true; }
     if (evt.target == counterButton)  { dilate(0,1);    return true; }
     if (evt.target == clockButton)    { dilate(0,-1);   return true; }
     if (evt.target == halfcounterButton)  
        { dilate(sqrt2_2,sqrt2_2);    return true; }
     if (evt.target == halfclockButton)    
        { dilate(sqrt2_2,sqrt2_2);   return true; }

     if (evt.target == identityButton)
      {
       mobius.setTransform(new Complex(1,0), new Complex(0,0),
                           new Complex(0,0), new Complex(1,0));
       return true;
      }

     if (evt.target == inversionButton)
      {
       mobius.setTransform(new Complex(0,0), new Complex(1,0),
                           new Complex(1,0), new Complex(0,0));
       return true;
      }

     if (evt.target == smithButton)
      {
       mobius.setTransform(new Complex(1,0), new Complex(-1,0),
                           new Complex(1,0), new Complex(1,0));
       return true;
      }

     if (evt.target == ismithButton)
      {
       mobius.setTransform(new Complex(1,0), new Complex(1,0),
                           new Complex(1,0), new Complex(-1,0));
       return true;
      }

     if (evt.target == invertButton)
      {
       mobius.invert();
       return true;
      }

     if (evt.target == inverseButton)
      {
       Complex a = mobius.a;
       Complex b = mobius.b;
       Complex c = mobius.c;
       Complex d = mobius.d;

       float cos,sin;
       for (int i = 1; i < 8; i++)
        {
         cos = (float)Math.cos(Math.PI * i / 8);
         sin = (float)Math.sin(Math.PI * i / 8);
         Complex z = new Complex((1+cos)/2, sin/2);
         Complex w = new Complex((1-cos)/2, -sin/2);
         mobius.setTransform(a.times(z).plus(c.times(w)),
                             b.times(z).plus(d.times(w)),
                             a.times(w).plus(c.times(z)),
                             b.times(w).plus(d.times(z)));
        }
       mobius.setTransform(c,d,a,b);
       return true;
      }

     if (evt.target == originButton)
      {
       newPoint(0,0);
       return true;
      }

     if (evt.target == unitButton)
      {
       Shape shape = new Shape();
       shape.circle(0,0,1);
       mobius.add(shape, true);
       return true;
      }

     if (evt.target == realButton)
      {
       Shape shape = new Shape();
       shape.line(0,0,1,0);
       mobius.add(shape, true);
       return true;
      }

     if (evt.target == imagButton)
      {
       Shape shape = new Shape();
       shape.line(0,0,0,1);
       mobius.add(shape, true);
       return true;
      }

     if (evt.target == pointButton)
      {
       Frame frame = new Frame("New Point");
       PointDialog dialog = new PointDialog(frame, "New Point", true, this);
       dialog.show();
       return true;
      }

     if (evt.target == circleButton)
      {
       Frame frame = new Frame("New Circle");
       CircleDialog dialog = new CircleDialog(frame, "New Circle", true, this);
       dialog.show();
       return true;
      }

     if (evt.target == lineButton)
      {
       Frame frame = new Frame("New Line");
       LineDialog dialog = new LineDialog(frame, "New Line", true, this);
       dialog.show();
       return true;
      }

     if (evt.target == moveButton)
      {
       mobius.move();
       return true;
      }

     return false;
    }

  public void dilate(float x, float y)
   {
    Complex a = mobius.a;
    Complex b = mobius.b;
    Complex c = mobius.c;
    Complex d = mobius.d;
    Complex t = new Complex(x, y);
    mobius.setTransform(a.times(t),b.times(t),c,d);
   }

  Panel LowerPanel()
   {
    Panel controls = new Panel();

    controls.add(new Label("Moebius transform:"));
    controls.add(TransformField = new TextField(35));
    TransformField.setEditable(false);
    controls.add(updateButton = new Button("Enter new values"));
 
    return controls;
   }

  public void setTransform(Complex a, Complex b, Complex c, Complex d)
   {
    if (a.times(d).minus(b.times(c)).norm() < 0.001)
     {
      System.out.println("Mobius transform is degenerate (ad-bc = 0)");
      return;
     }
    mobius.setTransform(a,b,c,d);
   }

  public void translate(float x, float y)
   {
    Complex a = mobius.a;
    Complex b = mobius.b;
    Complex c = mobius.c;
    Complex d = mobius.d;
    Complex t = new Complex(x, y);
    mobius.setTransform(a.plus(c.times(t)),b.plus(d.times(t)),c,d);
   }

  public void init()
   {
    setLayout(new BorderLayout(15,15));
    add("North", UpperPanel());
    add("Center", mobius = new Mobius(this));
    add("South", LowerPanel());

    sqrt2_2 = (float) Math.sqrt(2) / 2;
   }

  public void newCircle(float x, float y, float r)
   {
    if (r < 0.01) 
     {
      System.out.println("Circle too small!");
      return;
     }

    Shape shape = new Shape();
    shape.circle(x,y,r);
    mobius.add(shape, true);
   }

  public void newLine(float x1, float y1, float x2, float y2)
   {
    if ((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) < 0.01) 
     {
      System.out.println("Points too close together!");
      return;
     }

    Shape shape = new Shape();
    shape.line(x1,y1,x2,y2);
    mobius.add(shape, true);
   }

  public void newPoint(float x, float y)
   {
    Shape shape = new Shape();
    shape.point(x,y);
    mobius.add(shape, true);
   }

  Panel UpperPanel()
   {
    Panel fullControls = new Panel();
    Panel controls = new Panel();

    fullControls.setLayout(new GridLayout(4,1));

    controls.add(new Label("Draw: "));
 //   controls.add(originButton = new Button("Origin"));
 //   controls.add(unitButton = new Button("Unit circle"));
 //   controls.add(realButton = new Button("Real axis"));
 //   controls.add(imagButton = new Button("Imaginary axis"));

    controls.add(colorChoice = new Choice());
    colorChoice.addItem("Red");
    colorChoice.addItem("Blue");
    colorChoice.addItem("Cyan");
    colorChoice.addItem("Dark Gray");
    colorChoice.addItem("Light Gray");
    colorChoice.addItem("Black");
    colorChoice.addItem("Magenta");
    colorChoice.addItem("Orange");
    colorChoice.addItem("Pink");
    colorChoice.addItem("Green");
    colorChoice.addItem("Yellow");

    controls.add(mode = new Choice());
    mode.addItem("Points");
    mode.addItem("Lines");
    mode.addItem("Circles");

    controls.add(new Label(" "));
    controls.add(pointButton = new Button("Point"));
    controls.add(circleButton = new Button("Circle"));
    controls.add(lineButton = new Button("Line"));

    controls.add(deleteButton = new Button("Delete"));
    controls.add(clearButton = new Button("Clear"));

    fullControls.add(controls);

    controls = new Panel();
    controls.add(new Label("Translate w by: ")); 
    controls.add(add1Button = new Button("0.5"));
    controls.add(sub1Button = new Button("-0.5"));
    controls.add(addiButton = new Button("0.5i"));
    controls.add(subiButton = new Button("-0.5i"));
    fullControls.add(controls);

    controls = new Panel();
    controls.add(new Label("Dilate w by: ")); 
    controls.add(halfButton   = new Button("0.5"));
    controls.add(doubleButton = new Button("2"));
    controls.add(minusButton = new Button("-1"));
    controls.add(counterButton = new Button("i"));
    controls.add(clockButton = new Button("-i"));
    controls.add(halfcounterButton = new Button("exp(pi i/4)"));
    controls.add(halfclockButton = new Button("exp(-pi i/4)"));
    fullControls.add(controls);

    controls = new Panel();
    controls.add(inverseButton = new Button("Invert w"));
    controls.add(simplifyButton = new Button("Simplify w"));
    controls.add(invertButton = new Button("Swap w and z"));
    controls.add(moveButton = new Button("Move w to z"));
//    fullControls.add(controls);

//    controls = new Panel();
//    controls.add(new Label("Set map to: "));
    controls.add(identityButton = new Button("Identity map"));
    controls.add(inversionButton = new Button("Inversion map"));
    controls.add(smithButton = new Button("Smith map"));
 //   controls.add(ismithButton = new Button("Inverse smith map"));
    fullControls.add(controls);

    return fullControls;
   }

 }
