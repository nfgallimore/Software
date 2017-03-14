import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DiffGrid extends Canvas
{
  Image offImage;
  Graphics offGraphics;

  int xscale, yscale, leftx, lefty, rightx, righty;

  int function;

  Complex w, dx, dy;

  public final int IDENT = 1;
  public final int AFFINE = 2;
  public final int SQUARE = 3;
  public final int INVERSE = 4;
  public final int EXP = 5;
  public final int XYYI = 6;
  public final int XY2YI = 7;
  public final int ZBAR = 8;

  Color xcolor, ycolor;

  DiffGrid()
  {
    function = IDENT;
    offImage = null;
    w = new Complex();
    dx = new Complex();
    dy = new Complex();
    xcolor = Color.green;
    ycolor = Color.cyan;
  }

  public Complex func(Complex Z)
   {
    switch (function)
     {
      case IDENT:
        return Z;
      case AFFINE:
        return Z.times(new Complex(1,1)).plus(new Complex(0,-1));
      case SQUARE:
        return Z.times(Z).times(new Complex(0.25f,0));
      case INVERSE:
        return Z.inverse().times(new Complex(5,0));
      case EXP:
        return new Complex((float)(Math.exp(Z.x)*Math.cos(Z.y)),
                           (float)(Math.exp(Z.x)*Math.sin(Z.y)));
      case XYYI:
        return new Complex(Z.x+Z.y, Z.y);
      case ZBAR:
        return new Complex(Z.x, -Z.y);
      case XY2YI:
        return new Complex(Z.x+Z.y*Z.y, Z.y);
     }
    return null;
   }

  public Complex dx_func(Complex Z)
   {
    switch (function)
     {
      case IDENT:
      case XYYI:
      case XY2YI:
      case ZBAR:
        return new Complex(1,0);
      case AFFINE:
        return new Complex(1,1);
      case SQUARE:
        return Z.times(new Complex(0.5f,0));
      case INVERSE:
        return Z.times(Z).inverse().times(new Complex(-5,0));
      case EXP:
        return new Complex((float)(Math.exp(Z.x)*Math.cos(Z.y)),
                           (float)(Math.exp(Z.x)*Math.sin(Z.y)));
     }
    return null;
   }

  public Complex dy_func(Complex Z)
   {
    switch (function)
     {
      case IDENT: case AFFINE: case SQUARE: case INVERSE: case EXP:
        return dx_func(Z).times(new Complex(0,1));
      case XYYI:
        return new Complex(1,1);
      case ZBAR:
        return new Complex(0,-1);
      case XY2YI: 
        return new Complex(2*Z.y,1);
     }
    return null;
   }
   

  public void compute(int x, int y)
   {
    float X = ((float)x-leftx)/xscale * 5;
    float Y = (lefty-(float)y)/yscale * 5;
    Complex Z = new Complex(X,Y);

    w = func(Z);
    dx = dx_func(Z);
    dy = dy_func(Z); 
   }

  public void doGrid(Graphics g, int x, int y, int xscale, int yscale)
   {
    g.clearRect(x-xscale, y-yscale, xscale*2, yscale*2);

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

  public void drawArrows(int x, int y)
   {
    int i;

    Graphics g = this.getGraphics();

    g.clipRect(leftx-xscale,lefty-yscale,xscale*2,yscale*2);
    g.setColor(Color.blue);
    g.drawLine(leftx, lefty, x, y);
    g.setColor(xcolor);
    g.drawLine(x + xscale/5, y, x, y);
    g.setColor(ycolor);
    g.drawLine(x, y -  yscale/5, x, y);

    if (!w.infinite)
     {
      g = this.getGraphics();

      g.clipRect(rightx-xscale,righty-yscale,xscale*2,yscale*2);

      int X = (int)(rightx + w.x * xscale / 5);
      int Y = (int)(righty - w.y * yscale / 5);

      int X2 = (int)(rightx + (w.x + dx.x) * xscale / 5);
      int Y2 = (int)(righty - (w.y + dx.y) * yscale / 5);
      
      int X3 = (int)(rightx + (w.x + dy.x) * xscale / 5);
      int Y3 = (int)(righty - (w.y + dy.y) * yscale / 5);

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
    float X = ((float)x-leftx)/xscale * 5;
    float Y = (lefty-(float)y)/yscale * 5;
    drawGrids(X,Y);
    compute(x,y);
    drawArrows(x,y);
    return true;
   }

  public boolean mouseDrag(Event e, int x, int y)
   {
    if (!inside(x,y,leftx,lefty))
     {
      return false;
     }
    float X = ((float)x-leftx)/xscale * 5;
    float Y = (lefty-(float)y)/yscale * 5;
    drawGrids(X,Y);
    compute(x,y);
    drawArrows(x,y);
    return true;
   }



  public void drawGrids(float X, float Y)
   {
    Graphics gl = offImage.getGraphics();
    Graphics gr = offImage.getGraphics();
    int i;

    doGrid(gl, leftx, lefty, xscale, yscale);
    doGrid(gr, rightx, righty, xscale, yscale);

    gl.clipRect(leftx-xscale,lefty-yscale,xscale*2,yscale*2);
    gr.clipRect(rightx-xscale,righty-yscale,xscale*2,yscale*2);


    gl.setColor(Color.red);
    int x = leftx + (int) (X * xscale / 5);
    int y = lefty - (int) (Y * yscale / 5);

    for (i = -2; i <= 2; i++)
     {
      gl.drawLine(x-2*xscale/5, y+i*yscale/5, x+2*xscale/5, y+i*yscale/5);
      gl.drawLine(x+i*xscale/5, y-2*yscale/5, x+i*xscale/5, y+2*yscale/5);
     }

    float s;
    Complex a = new Complex();
    int ax = 0, ay = 0, bx = 0, by = 0;
    boolean infinite, first;
    

    gr.setColor(Color.red);
    for (i = -2; i <= 2; i++)
     {
      infinite = false;
      first = true;
      for (s = -2; s <= 2.05; s += 0.1)
       {
        a = func(new Complex(X+s, Y+i));
        ax = (int)(rightx + a.x * xscale / 5);
        ay = (int)(righty - a.y * yscale / 5);
        if (!first && !infinite && !a.infinite)
         { 
          gr.drawLine(bx, by, ax, ay);
         }
        bx = ax; by = ay;
        infinite = a.infinite;
        first = false;
       }
  
      infinite = false;
      first = true; 
      for (s = -2; s <= 2.05; s += 0.1)
       {
        a = func(new Complex(X+i, Y+s));
        ax = (int)(rightx + a.x * xscale / 5);
        ay = (int)(righty - a.y * yscale / 5);

        if (!first && !infinite && !a.infinite)
         { 
          gr.drawLine(bx, by, ax, ay);
         }
        bx = ax; by = ay;
        infinite = a.infinite;
        first = false;
       }
     }
    getGraphics().drawImage(offImage,0,0,this);
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
      setFunction(IDENT);
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
    switch (function)
     {
      case IDENT:
      case AFFINE:
      case EXP:
      case XYYI:
      case XY2YI:
      case ZBAR:
        drawGrids(0, 0);
        break;
      case SQUARE:
        drawGrids(3,0);
        break;
      case INVERSE:
        drawGrids(2,0);
        break;
     }
   }

}
