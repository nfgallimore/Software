import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import ResApp;

public class ResGrid extends Canvas
{
  Image offImage;
  Graphics l_offG, r_offG;

  int xscale, yscale, leftx, lefty, rightx, righty;

  Complex w,fz,twopi;
  int oldx, oldy;

  boolean contact;

  ResApp parent;

  public float x[], y[], xRes[], yRes[];
  public boolean on[];

  final int N = 7, M = 3;

  public Color xcolor, ycolor;

  ResGrid(ResApp parent)
  {
    offImage = null;
    w = new Complex();
    fz = new Complex();
    oldx = oldy = 0;
    contact = false;
    xcolor = Color.green;
    ycolor = Color.cyan;
    this.parent = parent;
    x = new float[4];
    y = new float[4];
    xRes = new float[4];
    yRes = new float[4];
    on = new boolean[4];
    twopi = new Complex(2 * (float) Math.PI,0);

    for (int i = 0; i < 4; i++)
     {
      x[i] = y[i] = xRes[i] = yRes[i] = 0;
      on[i] = false;
     }
   }

  public Complex func(Complex Z)
   {
    Complex W = new Complex(0,0);
    int i;

    for (i = 0; i < 4; i++)
      if (on[i])
        W = W.plus(new Complex(xRes[i],yRes[i]).over(Z.minus(new Complex(x[i],y[i]))));

    return W;
   }

  public void doGrid(Graphics g, int x, int y, int xscale, int yscale, int N)
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

      int X = (int)(rightx + w.x * xscale / M);
      int Y = (int)(righty - w.y * yscale / M);

      Complex Z = fz.over(twopi);

      int X2 = (int)(rightx + (w.x + Z.x) * xscale / M);
      int Y2 = (int)(righty - (w.y + Z.y) * yscale / M);
      
      int X3 = (int)(rightx + (w.x - Z.y) * xscale / M);
      int Y3 = (int)(righty - (w.y + Z.x) * yscale / M);

      g.setColor(xcolor);
      g.drawLine(X2, Y2, X, Y);

      g.setColor(ycolor);
      g.drawLine(X3, Y3, X, Y);
     }
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
    clearW();
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
    Complex dz = new Complex(((float)x-oldx)/xscale * N, (oldy-(float)y)/yscale*N).over(twopi);
    Complex neww = dz.times(fz).plus(w);

    int wX = (int)(rightx + w.x * xscale / M);
    int wY = (int)(righty - w.y * yscale / M);

    int wX2 = (int)(rightx + neww.x * xscale / M);
    int wY2 = (int)(righty - neww.y * yscale / M);
    
    l_offG.setColor(Color.red);
    l_offG.drawLine(oldx, oldy, x, y);

    if (w.minus(neww).abs() < 2)
     {
      r_offG.setColor(Color.red);
      r_offG.drawLine(wX2, wY2, wX, wY);
     }

    setW(neww);
    oldx = x; oldy = y;
    this.getGraphics().drawImage(offImage,0,0,this);
    drawArrows(x,y);
    return true;
   }

  public void setW(Complex Z)
   {
    w = Z;
    parent.IntegField.setText(w.toString());
   }

  public void clearW()
   {
    w = new Complex(0,0);
   }

  public boolean mouseUp(Event e, int x, int y)
   {
    if (!inside(x,y,leftx,lefty) || !contact)
     {
      return contact = false;
     }
    contact = false;
    this.getGraphics().drawImage(offImage,0,0,this);
    clearW();
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

    fz = func(Z); 
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
      setFunction();
     }
    g.clipRect(0,0,size().width, size().height);
    g.drawImage(offImage,0,0,this);
  }

  public void setFunction()
   {
    clearW();

    if (offImage == null) return;

    doGrid(l_offG,leftx,lefty,xscale,yscale,N);
    doGrid(r_offG,rightx,righty,xscale,yscale,M);

    int i,j,X,Y,X2,Y2,r;
    Complex Z;

    l_offG.setColor(Color.blue);
    for (i = 1-N; i <= N; i++)
      for (j = 1-N; j <= N; j++)
       {
        Z = func(new Complex(i, j));
        if (Z.abs() > 1) Z = Z.times(1/Z.abs());
        X = leftx+xscale*i/N;
        Y = lefty-yscale*j/N;
        X2 = X + (int) (Z.x * xscale / N);
        Y2 = Y - (int) (Z.y * yscale / N);
        l_offG.drawLine(X,Y,X2,Y2);
       }
    l_offG.setColor(Color.pink);
    for (i = 0; i < 4; i++)
      if (on[i])
       {
          X = (int)(leftx+xscale*x[i]/N);
          Y = (int)(lefty-yscale*y[i]/N);
          r = (int) Math.sqrt(9 * new Complex(xRes[i],yRes[i]).abs());
          if (r > 10) r=10;
          l_offG.fillOval(X-r,Y-r,2*r,2*r);
       }
    this.getGraphics().drawImage(offImage,0,0,this);
   }

}
