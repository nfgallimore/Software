import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Shape;
import Complex;

public class Mobius extends Canvas
{
  Image offImage;
  MobiusApp applet;

  int xscale, yscale, leftx, lefty, rightx, righty;
  int xnew, ynew;

  Complex a,b,c,d;
  Complex start;
  Shape shape;

  int mode;
  final int POINT_MODE = 1;
  final int LINE_MODE = 2;
  final int CIRCLE_MODE = 3;

  boolean legit;  // whether anything is worth drawing 
  Color color;

  boolean leftFocus;
  int x0,y0,x1,y1;

  Vector objects;

  Mobius(MobiusApp applet)
  {
    mode = POINT_MODE;
    offImage = null;
    this.applet = applet;
    legit = true;
    a = new Complex(1, 0);
    b = new Complex(0, 0);
    c = new Complex(0, 0);
    d = new Complex(1, 0);
    objects = new Vector(10,10);
    start = new Complex();
    shape = new Shape();
    color = Color.red;
    leftFocus = true;
  }

  public void add(Shape shape, boolean leftFocus)
   {
    shape.color = color;
    draw(shape,offImage.getGraphics(),leftFocus);
    this.getGraphics().drawImage(offImage,0,0,this);
    if (leftFocus)
      objects.addElement(shape);
    else
      objects.addElement(shape.transform(d.minus(),b,c,a.minus()));
   }

  public void clear()
   {
    objects.removeAllElements();
    repaint();
   }

  public void compute(int x, int y)
   {
    float X = ((float)x-x0)/xscale * 5;
    float Y = (y0-(float)y)/yscale * 5;

    legit = true;

    Complex w;

    if (leftFocus)
      w = Shape.Mobius(new Complex(X,Y),a,b,c,d);
    else
      w = Shape.Mobius(new Complex(X,Y),d.minus(),b,c,a.minus());

    if (w.infinite) {legit = false; return;}

    xnew = x1 + (int)(w.x * xscale / 5);
    ynew = y1 - (int)(w.y * yscale / 5);
   }

  public void delete()
   {
    int length = objects.size();
    if (length == 0) return;

    objects.removeElementAt(length-1);
    repaint();
   }

  public void doGrid(Graphics g, int x, int y, int xscale, int yscale, String title, Complex pole)
   {
    g.setColor(Color.black);
    g.clipRect(x-xscale, y-yscale, xscale*2+1, yscale*2+1);
    g.drawRect(x-xscale, y-yscale, xscale*2, yscale*2);

    g.drawLine(x-xscale, y, x+xscale,y);
    g.drawLine(x,y-yscale,x,y+yscale);

    int i,j;
    int xp,yp;

    for (i = -4; i <= 4; i++)
     {
      g.drawLine(x-3, y+yscale*i/5, x+3, y+yscale*i/5);
      g.drawLine(x+xscale*i/5, y-3, x+xscale*i/5, y+3);
     }

    for (i = -4; i <= 4; i++)
      for (j = -4; j <= 4; j++)
       {
        if (i != 0 && j != 0)
         {
          xp = x + xscale*i/5;
          yp = y + yscale*j/5;
          g.drawLine(xp-1,yp-1,xp+1,yp+1);
          g.drawLine(xp-1,yp+1,xp+1,yp-1);
         }
       }

    g.drawString(title, x+xscale - 10, y-yscale + 10);

    if (pole.infinite) return;

    xp = x + (int) (xscale*pole.x/5);
    yp = y - (int) (yscale*pole.y/5);

    g.setColor(Color.blue);
    g.drawLine(xp-3,yp-3,xp+3,yp+3);
    g.drawLine(xp-3,yp+3,xp+3,yp-3);
   }

  public void draw(Shape sh, Graphics g, boolean leftFocus)
   {
    Graphics g2 = g.create(); // create() prevents double clipping
    Graphics g3 = g.create();

    if (leftFocus)
     {
      drawHalf(sh, leftx, lefty, g2); 
      drawHalf(sh.transform(a,b,c,d), rightx, righty, g3);
     }
    else
     {
      drawHalf(sh, rightx, righty, g2); 
      drawHalf(sh.transform(d.minus(),b,c,a.minus()), leftx, lefty, g3);
     }
   }

  public void drawHalf(Shape sh, int x0, int y0, Graphics g)
   {
    g.clipRect(x0-xscale,y0-yscale,xscale*2,yscale*2);
    g.setColor(sh.color);

    switch(sh.type)
     {
      case Shape.POINT:
        if (sh.z1.infinite) return;
        int x = x0 + (int)(sh.z1.x * xscale / 5);
        int y = y0 - (int)(sh.z1.y * yscale / 5);
        g.fillOval(x-4, y-4, 8, 8);
        return;
      case Shape.LINE:
        int x1 = x0 + (int)(sh.start.x * xscale / 5);
        int y1 = y0 - (int)(sh.start.y * yscale / 5);
        float vx = sh.end.x - sh.start.x;
        float vy = sh.start.y - sh.end.y;
        float norm = (float) Math.sqrt(vx*vx + vy*vy);
        int dx = (int) (400*vx/norm); // normalize length of line
        int dy = (int) (400*vy/norm);

        g.drawLine(x1-dx,y1-dy,x1+dx,y1+dy);
        return;
      case Shape.CIRCLE:
        int xc = x0 + (int)(sh.center.x * xscale / 5);
        int yc = y0 - (int)(sh.center.y * yscale / 5);
        int r = (int)(sh.r * xscale / 5);  // aspect kludge
        g.drawOval(xc-r,yc-r,2*r,2*r);
        return;
     }
   }

  public void drawArrow(int x0, int y0, int xn, int yn, Color color)
   {
    Graphics g = this.getGraphics();

    g.setColor(color);
    g.clipRect(x0-xscale,y0-yscale,xscale*2,yscale*2);
    g.drawLine(x0,y0,xn,yn);
   }

  public void focus(boolean leftFocus)
   {
    if (this.leftFocus = leftFocus)
     {
      x0 = leftx; y0 = lefty;
      x1 = rightx; y1 = righty;
     }
    else
     {
      x1 = leftx; y1 = lefty;
      x0 = rightx; y0 = righty;
     }
   }
 
  public boolean inside(int x, int y, int xc, int yc)
   {
    return !(x < xc - xscale || x > xc + xscale ||
             y < yc - yscale || y > yc + yscale);
   }

  public void invert()
   {
    Complex A = d.minus();
    Complex B = b;
    Complex C = c;
    Complex D = a.minus();

    Vector newObjects = new Vector(10,10);
    
    for (int i = 0; i < objects.size(); i++)
     {
      newObjects.addElement(((Shape)objects.elementAt(i)).transform(a,b,c,d));
     }

    objects = newObjects;
    setTransform(A,B,C,D);
   }

  String linearString(Complex a, Complex b, boolean bracketing)
   {
    String string = new String();
    boolean afill = false;
    boolean multi = false;

    if (Math.abs(a.x-1) < 0.001 && Math.abs(a.y) < 0.001)
     {
      string = "z ";
      afill = true;
     }
    else if (Math.abs(a.x) > 0.001 && Math.abs(a.y) > 0.001)
     {
      string = "(";
      string += a.toString();
      string += ")z ";
      afill = true;
     }
    else if (Math.abs(a.x) > 0.001 || Math.abs(a.y) > 0.001)
     {
      string = a.toString();
      string += "z ";
      afill = true;
     }
 
    if (Math.abs(b.x) > 0.001 || Math.abs(b.y) > 0.001)
     {
      string += b.toString(afill);
      if (afill) multi = true;
      if (Math.abs(b.x) > 0.001 && Math.abs(b.y) > 0.001) multi = true;
     }
    else if (!afill)
      string = "0";

    if (multi && bracketing) return "(" + string + ")"; 
    else return string;
   }

  public boolean mouseDown(Event e, int x, int y)
   {
    if (inside(x,y,leftx,lefty))
      focus(true);
    else if (inside(x,y,rightx,righty))
      focus(false);
    else 
      return false;

    float X = ((float)x-x0)/xscale * 5;
    float Y = (y0-(float)y)/yscale * 5;

    switch (mode)
     {
      case POINT_MODE:
        Shape sh = new Shape();
        sh.point(X,Y);
        add(sh, leftFocus);
        return true;
      case LINE_MODE: case CIRCLE_MODE:
        legit = false;
        shape = new Shape();  // keep shape for future reference
        start = new Complex(X,Y);
        legit = false;
        return true;
     }
    return true;
   }
 
  public boolean mouseDrag(Event e, int x, int y)
   {
    if (!inside(x,y,x0,y0))
     {
      legit = false;
      getGraphics().drawImage(offImage,0,0,this);
      return false;
     }

    float X = ((float)x-x0)/xscale * 5;
    float Y = (y0-(float)y)/yscale * 5;
        
    getGraphics().drawImage(offImage,0,0,this);

    drawArrow(x0, y0, x, y, Color.blue);
    compute(x,y);
    if (legit)
      drawArrow(x1, y1, xnew, ynew, Color.blue); 
    legit = false;

    shape.color = Color.black;
    switch (mode)
     {
      case POINT_MODE:
        return true;
      case LINE_MODE:
        if ((X-start.x)*(X-start.x) + (Y-start.y)*(Y-start.y) < 0.01) return false;
        shape.line(start.x, start.y, X, Y);
        break;
      case CIRCLE_MODE:
        float r = (float) Math.sqrt((X-start.x)*(X-start.x) + (Y-start.y)*(Y-start.y));
        if (r < 0.1) return false;
        shape.circle(start.x, start.y, r);
        break;
     }
    legit = true;
    draw(shape,getGraphics(),leftFocus);
    
    return true;
   }

  public boolean mouseMove(Event e, int x, int y)
   {
    if (inside(x,y,leftx,lefty))
      focus(true);
    else if (inside(x,y,rightx,righty))
      focus(false);
    else
     {
      getGraphics().drawImage(offImage,0,0,this);
      return false;
     }

    getGraphics().drawImage(offImage,0,0,this);

    compute(x,y);

    drawArrow(x0, y0, x, y, Color.blue);
    if (legit)
     drawArrow(x1, y1, xnew, ynew, Color.blue); 

    return true;
   }

  public boolean mouseUp(Event e, int x, int y)
   {
    if (!inside(x,y,x0,y0))
      return false;
 
    switch (mode)
     {
      case POINT_MODE:
        return false;
     }
    if (!legit) return false;
    add(shape,leftFocus);
    return true;
   }

  public void move()
   {
    int num = objects.size();
    if (num > 100) return;

    for (int i = 0; i < num; i++)
     {
      Shape sh = ((Shape)objects.elementAt(i)).transform(a,b,c,d);
      boolean match = false;
      for (int j = 0; j < num; j++)
        if (sh.equals((Shape)objects.elementAt(j)))
         { match = true; break; }
      if (!match) objects.addElement(sh);
     }

    setTransform(new Complex(1,0), new Complex(0,0),
                 new Complex(0,0), new Complex(1,0));
    repaint();
   }


  public void paint(Graphics g)
  {
    if (offImage == null) 
     {
      offImage = createImage(size().width, size().height);

      xscale = size().width/4 - 25;
      yscale = size().height/2 - 5;
      lefty = righty = 5 + yscale;
      leftx = 10 + xscale;
      rightx = 20 + 3*xscale;

      if (yscale > xscale) yscale = xscale;
      if (xscale > yscale) xscale = yscale;
      
      repaint();
      return;
     }
//    g.clipRect(0,0,size().width, size().height);
    g.drawImage(offImage,0,0,this);
  }

  public void repaint()
   {
    Graphics g = offImage.getGraphics();

    g.clearRect(0,0,size().width,size().height);
    doGrid(g.create(), leftx, lefty, xscale, yscale,"z",d.over(c).minus());
    doGrid(g.create(), rightx, righty, xscale, yscale,"w",a.over(c));

    for (int i = 0; i < objects.size(); i++)
      draw((Shape)objects.elementAt(i),g,true);

    getGraphics().drawImage(offImage,0,0,this);

    String string = new String();

    if ((Math.abs(c.x) + Math.abs(c.y) + 
        Math.abs(d.x - 1) + Math.abs(d.y)) > 0.004)
      string = "w = " + linearString(a,b,true) + "/" + linearString(c,d,true);
    else
      string = "w = " + linearString(a,b,false);

    applet.TransformField.setText(string);
   }

  public void setColor(String colorString)
   {
    if (colorString.equals("Red")) color = Color.red;
    if (colorString.equals("Green")) color = Color.green;
    if (colorString.equals("Blue")) color = Color.blue;
    if (colorString.equals("Yellow")) color = Color.yellow;
    if (colorString.equals("Cyan")) color = Color.cyan;
    if (colorString.equals("Dark Gray")) color = Color.darkGray;
    if (colorString.equals("Light Gray")) color = Color.lightGray;
    if (colorString.equals("Black")) color = Color.black;
    if (colorString.equals("Magenta")) color = Color.magenta;
    if (colorString.equals("Orange")) color = Color.orange;
    if (colorString.equals("Pink")) color = Color.pink;
   }

  public void setMode(String modeString)
   {
    if (modeString.equals("Points")) mode = POINT_MODE;
    if (modeString.equals("Lines")) mode = LINE_MODE;
    if (modeString.equals("Circles")) mode = CIRCLE_MODE;
   }

  public void setTransform(Complex a, Complex b, Complex c, Complex d)
   {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
    repaint();
   }
}
