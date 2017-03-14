import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/* import Complex; */


public class Grid extends Canvas
{
  Image offImage;
  Graphics offGraphics;

  int xScale, yScale, x0, y0;

  Grid()
  {
    offImage = null;
    offGraphics = null;
    x0=y0=0; 
    xScale=yScale=1;
  }

  public void clearGrid(Color c)
  {
    offGraphics.setColor(c);
    offGraphics.fillRect(x0-xScale, y0-yScale, xScale*2, yScale*2);
  }

  public void drawGrid()
  {
    offGraphics.setColor(Color.black);
    offGraphics.clipRect(x0-xScale, y0-yScale, xScale*2+1, yScale*2+1);
    offGraphics.drawRect(x0-xScale, y0-yScale, xScale*2, yScale*2);

    offGraphics.drawLine(x0-xScale, y0, x0+xScale,y0);
    offGraphics.drawLine(x0,y0-yScale,x0,y0+yScale);

    int i,j;

    for (i = -4; i <= 4; i++)
    {
      offGraphics.drawLine(x0-3, y0+yScale*i/5, x0+3, y0+yScale*i/5);
      offGraphics.drawLine(x0+xScale*i/5, y0-3, x0+xScale*i/5, y0+3);
    }

    for (i = -4; i <= 4; i++)
      for (j = -4; j <= 4; j++)
      {
        if (i != 0 && j != 0)
          offGraphics.drawRect(x0+xScale*i/5,y0+yScale*j/5,1,1);
      }
  }

  protected void gridInit()
  {
   drawGrid();
  }

  public Point toPoint(Complex c)
  {
    int x = (int)(x0 + c.x * xScale / 5);
    int y = (int)(y0 - c.y * yScale / 5);
    return new Point(x,y);
  }

  public int scaleX(float x)
   {
    return (int) (x * xScale / 5);
   }

  public int scaleY(float y)
   {
    return (int) (y * yScale / 5);
   }

  public Complex toComplex(Point p)
  {
    float x = ((float)p.x-x0)/xScale * 5;
    float y = (y0-(float)p.y)/yScale * 5;
    return new Complex(x,y);
  }

  public void setColor(Color c)
   {
    offGraphics.setColor(c);
   }

  public void drawLine(Complex z1, Complex z2)
   {
    Point p1 = toPoint(z1);
    Point p2 = toPoint(z2);
    offGraphics.drawLine(p1.x,p1.y,p2.x,p2.y);
   }

  public void drawOval(Complex z, float xRadius, float yRadius)
   {
    Point p = toPoint(z);
    int x = scaleX(xRadius);
    int y = scaleY(yRadius);

    offGraphics.drawOval(p.x-x, p.y-y, 2*x, 2*y);
   }

  public void fillOval(Complex z, float xRadius, float yRadius)
   {
    Point p = toPoint(z);
    int x = scaleX(xRadius);
    int y = scaleY(yRadius);

    offGraphics.fillOval(p.x-x, p.y-y, 2*x, 2*y);
   }
  
  public void paint(Graphics g)
  {
    if (offImage == null) 
    {
      offImage = createImage(getSize().width, getSize().height);
      offGraphics = offImage.getGraphics();

      xScale = getSize().width/2 - 5;
      yScale = getSize().height/2 - 5;
      y0 = 5 + yScale;
      x0 = 5 + xScale;

      if (yScale > xScale) yScale = xScale;
      if (xScale > yScale) xScale = yScale;
    
      gridInit();
    }
    g.clipRect(0,0,getSize().width, getSize().height);
    g.drawImage(offImage,0,0,this);
  }

 public Graphics getBuffer()
 {
   return offGraphics;
 }

}
