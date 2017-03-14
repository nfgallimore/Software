import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FtocGrid extends Canvas
{
  Image offImage;
  Graphics l_offG, r_offG;

  int xscale, yscale, leftx, lefty, rightx, righty;

  int function;

  Complex w,fz;
  int oldx, oldy;

  boolean contact;

  final int N = 7;

  public final int IDENT = 1;
  public final int INVERSE = 4;
  public final int EXP = 5;
  public final int ONE = 9;
  public final int ONEI = 10;
  public final int INVERSE2 = 11;

  Color xcolor, ycolor;

  FtocGrid()
  {
    function = ONE;
    offImage = null;
    w = new Complex();
    fz = new Complex();
    oldx = oldy = 0;
    contact = false;
    xcolor = Color.green;
    ycolor = Color.cyan;
  }

  public Complex func(Complex Z)
   {
    switch (function)
     {
      case IDENT:
        return Z;
      case INVERSE: case INVERSE2:
        return Z.inverse();
      case EXP:
        return new Complex((float)(Math.exp(Z.x)*Math.cos(Z.y)),
                           (float)(Math.exp(Z.x)*Math.sin(Z.y)));
      case ONE:
        return new Complex(1,0);
      case ONEI:
        return new Complex(1,1);
     }
    return null;
   }

  public Complex primitive(Complex Z)
   {
    switch (function)
     {
      case IDENT:
        return Z.times(Z).times((float) 0.5);
      case INVERSE: 
        return Z.Log();
      case INVERSE2:
        return Z.Log(0);
      case EXP:
        return new Complex((float)(Math.exp(Z.x)*Math.cos(Z.y)),
                           (float)(Math.exp(Z.x)*Math.sin(Z.y)));
      case ONE:
        return Z;
      case ONEI:
        return Z.times(new Complex(1,1));
     }
    return null;
   }
   

  public void doGrid(Graphics g, int x, int y, int xscale, int yscale)
   {
    g.clearRect(x-xscale, y-yscale, xscale*2, yscale*2);

    g.setColor(Color.black);
    g.drawRect(x-xscale, y-yscale, xscale*2, yscale*2);

    g.drawLine(x-xscale, y, x+xscale,y);
    g.drawLine(x,y-yscale,x,y+yscale);

    int i,j;

    for (i = 1-N; i < N; i++)
     {
      g.drawLine(x-3, y+yscale*i/N, x+3, y+yscale*i/N);
      g.drawLine(x+xscale*i/N, y-3, x+xscale*i/N, y+3);
     }

    for (i = 1-N; i <= N; i++)
      for (j = 1-N; j <= N; j++)
       {
        if (i != 0 && j != 0)
          g.drawRect(x+xscale*i/N,y+yscale*j/N,1,1);
       }
   }

  public void drawArrows(int x, int y)
   {
    int i;

    Graphics g = this.getGraphics();

    g.clipRect(leftx-xscale,lefty-yscale,xscale*2,yscale*2);
    g.setColor(Color.blue);
    g.drawLine(leftx, lefty, x, y);
    g.setColor(xcolor);
    g.drawLine(x + xscale/N, y, x, y);
    g.setColor(ycolor);
    g.drawLine(x, y -  yscale/N, x, y);

    if (!w.infinite)
     {
      g = this.getGraphics();

      g.clipRect(rightx-xscale,righty-yscale,xscale*2,yscale*2);

      int X = (int)(rightx + w.x * xscale / N);
      int Y = (int)(righty - w.y * yscale / N);

      int X2 = (int)(rightx + (w.x + fz.x) * xscale / N);
      int Y2 = (int)(righty - (w.y + fz.y) * yscale / N);
      
      int X3 = (int)(rightx + (w.x - fz.y) * xscale / N);
      int Y3 = (int)(righty - (w.y + fz.x) * yscale / N);

      g.setColor(Color.blue);
      g.drawLine(rightx, righty, X, Y);

      g.setColor(xcolor);
      g.drawLine(X2, Y2, X, Y);

      g.setColor(ycolor);
      g.drawLine(X3, Y3, X, Y);
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
      return false;
     }

    contact = true;
    float X = ((float)x-leftx)/xscale * N;
    float Y = (lefty-(float)y)/yscale * N;
    Complex Z = new Complex(X,Y);

    oldx = x; oldy = y;
    fz = func(Z);
    w = primitive(Z);
    drawArrows(x,y);
    return true;
   }

  public boolean mouseDrag(Event e, int x, int y)
   {
    if (!inside(x,y,leftx,lefty) || !contact)
     {
      contact = false; 
      return false;
     }

    float X = ((float)x-leftx)/xscale * N;
    float Y = (lefty-(float)y)/yscale * N;
    Complex Z = new Complex(X,Y);

    fz = func(Z);
    Complex neww = primitive(Z);

    int wX = (int)(rightx + w.x * xscale / N);
    int wY = (int)(righty - w.y * yscale / N);

    int wX2 = (int)(rightx + neww.x * xscale / N);
    int wY2 = (int)(righty - neww.y * yscale / N);
    
    l_offG.setColor(Color.red);
    l_offG.drawLine(oldx, oldy, x, y);

    if (w.minus(neww).abs() < 2)
     {
      r_offG.setColor(Color.red);
      r_offG.drawLine(wX2, wY2, wX, wY);
     }

    w = neww;
    oldx = x; oldy = y;
    this.getGraphics().drawImage(offImage,0,0,this);
    drawArrows(x,y);
    return true;
   }


  public boolean mouseUp(Event e, int x, int y)
   {
    if (!inside(x,y,leftx,lefty) || !contact)
     {
      return contact = false;
     }
    contact = false;
    this.getGraphics().drawImage(offImage,0,0,this);
    drawArrows(x,y);
    return true;
   }

  public boolean mouseMove(Event e, int x, int y)
   {
    if (!inside(x,y,leftx,lefty))
      return false;

    float X = ((float)x-leftx)/xscale * N;
    float Y = (lefty-(float)y)/yscale * N;
    Complex Z = new Complex(X,Y);

    fz = func(Z); w = primitive(Z);
    this.getGraphics().drawImage(offImage,0,0,this);
    drawArrows(x,y);
    return true;
   }

  public void paint(Graphics g)
  {
    if (offImage == null) 
     {
      offImage = createImage(size().width, size().height);
      l_offG = offImage.getGraphics();
      r_offG = offImage.getGraphics();

      xscale = size().width/4 - 20;
      yscale = size().height/2 - 5;
      lefty = righty = 5 + yscale;
      leftx = 10 + xscale;
      rightx = 20 + 3*xscale;

      if (yscale > xscale) yscale = xscale;
      if (xscale > yscale) xscale = yscale;
      
      l_offG.clipRect(leftx - xscale, lefty - yscale, 2*xscale+1, 2*yscale+1);
      r_offG.clipRect(rightx - xscale, righty - yscale, 2*xscale+1, 2*yscale+1);
      setFunction(ONE);
     }
    g.clipRect(0,0,size().width, size().height);
    g.drawImage(offImage,0,0,this);
  }

  public void setColor(String colorString, boolean isX)
   {
    Color color = Color.black; // default

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
    if (colorString.equals("White")) color = Color.white;

    if (isX) xcolor = color; else ycolor = color;
   }


  public void setFunction(int function)
   {
    this.function = function;
    w = new Complex(0,0);
    doGrid(l_offG,leftx,lefty,xscale,yscale);
    doGrid(r_offG,rightx,righty,xscale,yscale);

    int mul = 3;

    if (function == INVERSE) mul = 1;

    int i,j,X,Y,X2,Y2;
    Complex Z;

    l_offG.setColor(Color.blue);
    for (i = 1-N; i <= N; i++)
      for (j = 1-N; j <= N; j++)
       {
        Z = func(new Complex(i, j)).times((float)1.0/mul);
        if (Z.abs() > 1) Z = Z.times(1/Z.abs());
        X = leftx+xscale*i/N;
        Y = lefty-yscale*j/N;
        X2 = X + (int) (Z.x * xscale / N);
        Y2 = Y - (int) (Z.y * xscale / N);
        l_offG.drawLine(X,Y,X2,Y2);
       }

    this.getGraphics().drawImage(offImage,0,0,this);
   }

}
