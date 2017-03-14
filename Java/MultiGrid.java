import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MultiGrid extends Canvas
{
  Image offImage;
  Graphics offGraphics;

  int xscale, yscale, leftx, lefty, rightx, righty;

  int last_x, last_y;

  int function;
  boolean legit;
  boolean show;

  public final int PM = 1;
  public final int SQRT = 2;
  public final int CUBERT = 3;
  public final int FOURTHRT = 4;
  public final int FIFTHRT = 5;
  public final int SIXTHRT = 6;
  public final int LOG = 7;

  Vector points;
  Vector oldpoints;

  MultiGrid()
  {
    function = PM;
    offImage = null;
    legit = true;
    show = true;
    points = new Vector(10,10);
    oldpoints = new Vector(10,10);
  }

  public void add(float x, float y)
   {
    int X = rightx + (int)(x * xscale / 5);
    int Y = righty - (int)(y * yscale / 5);

    if (show || (points.size() == 0))
      points.addElement(new Point(X,Y));
   }

  public void doRoot(Complex Z, int n)
   {
    float R;
    float theta;
    int i;
 
    R = (float) Math.exp(Math.log(Z.abs())/n);
    theta = Z.Arg()/n; 

    for (i = 0; i < n; i++)
     {
      add(R * (float) Math.cos(theta), R * (float) Math.sin(theta));
      theta += (float) Math.PI * 2 / n;
     }
   }

  public void compute(int x, int y)
   {
    float X = ((float)x-leftx)/xscale * 5;
    float Y = (lefty-(float)y)/yscale * 5;
    Complex Z = new Complex(X,Y);
 
    points.removeAllElements();

    switch (function)
     {
      case PM:
        add(X, Y);
        add(-X, -Y);
        break;
      case SQRT:
        doRoot(Z,2);
        break;
      case CUBERT:
        doRoot(Z,3);
        break;
      case FOURTHRT:
        doRoot(Z,4);
        break;
      case FIFTHRT:
        doRoot(Z,5);
        break;
      case SIXTHRT:
        doRoot(Z,6);
        break;
      case LOG:
        float R;
        float theta;
        int i;
 
        R = (float) Math.log(Z.abs());
        theta = Z.Arg(); 

        add(R,theta);
        add(R,theta + (float) Math.PI*2);
        add(R,theta - (float) Math.PI*2);
        break;
     }

   }

  public void doGrid(Graphics g, int x, int y, int xscale, int yscale)
   {
    g.setColor(Color.black);
    g.drawRect(x-xscale, y-yscale, xscale*2, yscale*2);

    g.drawLine(x-xscale, y, x+xscale,y);
    g.drawLine(x,y-yscale,x,y+yscale);

    int i,j;

    for (i = -4; i <= 4; i++)
     {
      g.drawLine(x-3, y+yscale*i/5, x+3, y+yscale*i/5);
      g.drawLine(x+xscale*i/5, y-3, x+xscale*i/5, y+3);
     }

    for (i = -4; i <= 4; i++)
      for (j = -4; j <= 4; j++)
       {
        if (i != 0 && j != 0)
          g.drawRect(x+xscale*i/5,y+yscale*j/5,1,1);
       }
   }

  public void drawArrow(int x, int y, int xn, int yn, Color color)
   {
    Graphics g = this.getGraphics();

    g.setColor(color);
    g.clipRect(x-xscale,y-yscale,xscale*2,yscale*2);
    g.drawLine(x,y,xn,yn);
   }

  public void drawArrows(int x, int y)
   {
    int i;

    drawArrow(leftx, lefty, x, y, Color.blue);
    for (i = 0; i < points.size(); i++)
     {
      Point pn = (Point) points.elementAt(i);
      drawArrow(rightx, righty, pn.x, pn.y, Color.blue);
     }
    
    Graphics g = this.getGraphics();
    g.clipRect(rightx-xscale,righty-yscale,xscale*2,yscale*2);
    for (i = 0; i < points.size(); i++)
     {
      Point pn = (Point) points.elementAt(i);
      g.setColor((i == 0) ? Color.red : Color.blue);
      g.fillOval(pn.x-3, pn.y-3, 6, 6);
     }
   }

  public int getFunction()
   {
    return function;
   }

  public boolean inside(int x, int y, int xc, int yc)
   {
    return !(x < xc - xscale || x > xc + xscale ||
             y < yc - yscale || y > yc + yscale);
   }

  public boolean mouseDown(Event e, int x, int y)
   {
    if (!inside(x,y,leftx,lefty))
     {
      oldpoints = null;
      legit = false;
      return false;
     }

    compute(x,y);

    last_x = x; last_y = y; 
    oldpoints = (Vector) points.clone();

    legit = true;
 
    return true;
   }
 
  public boolean mouseDrag(Event e, int x, int y)
   {
    if (!inside(x,y,leftx,lefty))
     {
      legit = false;
      return false;
     }

    compute(x,y);

    Graphics g = offImage.getGraphics();
    g.setColor(Color.red);
    g.drawLine(last_x,last_y,x,y);

    g.clipRect(rightx-xscale, righty-yscale, xscale*2, yscale*2);
    if (legit && oldpoints != null)
      for (int i = 0; i < points.size(); i++)
       {
        g.setColor((i == 0) ? Color.red : Color.blue);
        Point po = (Point) oldpoints.elementAt(i);
        Point pn = (Point) points.elementAt(i);
        if ((po.x - pn.x)*(po.x - pn.x) + (po.y - pn.y)*(po.y - pn.y) < 1000)
          g.drawLine(po.x, po.y, pn.x, pn.y);
       }
 
    this.getGraphics().drawImage(offImage,0,0,this);

    drawArrows(x,y);

    last_x = x; last_y = y; 
    oldpoints = (Vector) points.clone();

    legit = true;
    return true;
   }

  public boolean mouseMove(Event e, int x, int y)
   {
    if (!inside(x,y,leftx,lefty))
      return false;

    Graphics g = this.getGraphics();

    g.drawImage(offImage,0,0,this);

    compute(x,y);
    drawArrows(x,y);
    return true;
   }

  public boolean mouseUp(Event e, int x, int y)
   {
    if (!inside(x,y,leftx,lefty))
      return false;

    Graphics g = offImage.getGraphics();
    g.setColor(Color.red);
    g.drawLine(x,y,x,y);

    compute(x,y);
    for (int i = 0; i < points.size(); i++)
     {
      g.setColor((i == 0) ? Color.red : Color.blue);
      Point pn = (Point) points.elementAt(i);
      g.drawLine(pn.x, pn.y, pn.x, pn.y);
     }
 
    this.getGraphics().drawImage(offImage,0,0,this);

    return true;
   }

  public void paint(Graphics g)
  {
    if (offImage == null) 
     {
      offImage = createImage(size().width, size().height);
      offGraphics = offImage.getGraphics();

      xscale = size().width/4 - 25;
      yscale = size().height/2 - 5;
      lefty = righty = 5 + yscale;
      leftx = 10 + xscale;
      rightx = 20 + 3*xscale;

      if (yscale > xscale) yscale = xscale;
      if (xscale > yscale) xscale = yscale;
    
      doGrid(offGraphics, leftx, lefty, xscale, yscale);
      doGrid(offGraphics, rightx, righty, xscale, yscale);
     }
    g.clipRect(0,0,size().width, size().height);
    g.drawImage(offImage,0,0,this);
  }

  public void setFunction(int function)
   {
    this.function = function;
    offImage.flush();
    offGraphics.dispose();
    offImage = null;
    paint(this.getGraphics());
   }

  public void setShow(boolean show)
   {
    this.show = show;
   }
}
