import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import ArgumentApp;
import Complex;

public class ArgumentGrid extends Canvas
{
  Image offImage;
  Graphics l_offG, r_offG;

  int xscale, yscale, leftx, lefty, rightx, righty;

  int oldx, oldy;
  Complex z, fz;

  ArgumentApp parent;

  float a,b,c,d;
  final int N = 7, M = 7;

  boolean contact;

  ArgumentGrid(ArgumentApp parent)
  {
    offImage = null;
    oldx = oldy = 0;
    this.parent = parent;
    a=b=c=d=(float)0.0;

    contact = false;

    z = new Complex();
    fz = new Complex();
   }

  public Complex func(Complex Z)
   {
    Complex Z_squared = Z.times(Z);
    Complex numerator = Z_squared.plus(Z.times(a)).plus(b);
    Complex denominator = Z_squared.plus(Z.times(c)).plus(d);
 
    return numerator.over(denominator);
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

  public void drawArrows(Complex z)
   {
    int i;

    Graphics g = this.getGraphics();

    g.clipRect(leftx-xscale,lefty-yscale,xscale*2,yscale*2);
    g.setColor(Color.blue);

    int x = (int)(leftx + z.x * xscale / N);
    int y = (int)(lefty - z.y * yscale / N);

    g.drawLine(leftx, lefty, x, y);

    Complex fz = func(z);

    if (!fz.infinite)
     {
      g = this.getGraphics();

      g.clipRect(rightx-xscale,righty-yscale,xscale*2,yscale*2);
      g.setColor(Color.blue);

      int X = (int)(rightx + fz.x * xscale / M);
      int Y = (int)(righty - fz.y * yscale / M);

      g.drawLine(rightx, righty, X, Y);
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
    z.x = ((float)x-leftx)/xscale * N;
    z.y = (lefty-(float)y)/yscale * N;
    fz = func(z);

    drawArrows(z);
    return true;
   }

  public boolean mouseDrag(Event e, int x, int y)
   {
    if (!contact)
     {
      contact = false;
      return false;
     }

    int oldx = (int)(leftx + z.x * xscale / N);
    int oldy = (int)(lefty - z.y * yscale / N);

    fz = func(z);

    int oldfx = (int)(rightx + fz.x * xscale / M);
    int oldfy = (int)(righty - fz.y * yscale / M);

    z.x = ((float)x-leftx)/xscale * N;
    z.y = (lefty-(float)y)/yscale * N;

    fz = func(z);

    int fx = (int)(rightx + fz.x * xscale / M);
    int fy = (int)(righty - fz.y * yscale / M);
    
    l_offG.setColor(Color.red);
    l_offG.drawLine(oldx, oldy, x, y);

    r_offG.setColor(Color.red);
    r_offG.drawLine(oldfx, oldfy, fx, fy);

    this.getGraphics().drawImage(offImage,0,0,this);
    drawArrows(z);
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

    z.x = ((float)x-leftx)/xscale * N;
    z.y = (lefty-(float)y)/yscale * N;

    drawArrows(z);
    return true;
   }

  public boolean mouseMove(Event e, int x, int y)
   {
    if (!inside(x,y,leftx,lefty))
      return false;

    z.x = ((float)x-leftx)/xscale * N;
    z.y = (lefty-(float)y)/yscale * N;

    fz = func(z); 

    this.getGraphics().drawImage(offImage,0,0,this);

    Graphics g = this.getGraphics();

    g.clipRect(leftx-xscale,lefty-yscale,xscale*2,yscale*2);
    g.setColor(Color.yellow);
    g.drawOval(x-xscale*2/N, y-yscale*2/N, xscale*4/N, yscale*4/N);

    int i;
    final int K = 100;
    int newx = 0, newy = 0,oldx = 0, oldy = 0;
    Complex Z = new Complex();

    g = this.getGraphics();

    g.clipRect(rightx-xscale,righty-yscale,xscale*2,yscale*2);
    g.setColor(Color.blue);

    for (i = 0; i <= K; i++)
     {
      Z.x = 2 * (float) Math.cos(i*2*Math.PI/K) + z.x;
      Z.y = 2 * (float) Math.sin(i*2*Math.PI/K) + z.y;
      Z = func(Z);
      newx = (int)(rightx + Z.x * xscale / M);
      newy = (int)(righty - Z.y * yscale / M);      
      
      if (i > 0)
        g.drawLine(oldx,oldy,newx,newy);

      oldx = newx; oldy = newy;
     }

//    drawArrows(z);
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
      resetGrid();
      return;
     }
    g.clipRect(0,0,size().width, size().height);
    g.drawImage(offImage,0,0,this);
  }

  void cross(float a, float b)
   {
    int x = (int)(leftx + a * xscale / N);
    int y = (int)(lefty - b * yscale / N);
    
    l_offG.drawLine(x-3,y-3,x+3,y+3);
    l_offG.drawLine(x-3,y+3,x+3,y-3);
   }

  public void resetGrid()
   {
    if (offImage == null) return;

    doGrid(l_offG,leftx,lefty,xscale,yscale,N);
    doGrid(r_offG,rightx,righty,xscale,yscale,M);

    l_offG.setColor(Color.magenta);
    float q = a*a/4-b;

    if (q > 0)
     {
      cross(- a/2 - (float)Math.sqrt(q), 0);
      cross(- a/2 + (float)Math.sqrt(q), 0);
     }
    else
     {
      cross(- a/2, (float)Math.sqrt(-q));
      cross(- a/2, -(float)Math.sqrt(-q));
     }

    l_offG.setColor(Color.black);
    q = c*c/4-d;

    if (q > 0)
     {
      cross(- c/2 - (float)Math.sqrt(q), 0);
      cross(- c/2 + (float)Math.sqrt(q), 0);
     }
    else
     {
      cross(- c/2, (float)Math.sqrt(-q));
      cross(- c/2, -(float)Math.sqrt(-q));
     }

    this.getGraphics().drawImage(offImage,0,0,this);
   }

}
