import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/* import Complex;
import Grid; */

public class LaurentGrid extends Grid
{
  Complex z0;

  LaurentGrid()
  {
    super();
    z0 = new Complex(0,0);
  }

  protected void gridInit()
  {
   drawAnnuli(z0);
   drawGrid();
   drawSingularities(z0);
  }

  void drawAnnuli(Complex z0)
   {
    float a = Complex.dist(z0, new Complex(1,0));
    float b = Complex.dist(z0, new Complex(2,0));
    float r1, r2;

    if (a < b)
     { r1 = a; r2 = b; }
    else
     { r1 = b; r2 = a; }
    
    clearGrid(Color.cyan);
    setColor(Color.green);
    fillOval(z0,r2,r2);
    setColor(Color.red);
    fillOval(z0,r1,r1);

    setColor(Color.white);
    drawOval(z0,r2,r2);
    drawOval(z0,r1,r1);

   }

  void drawSingularities(Complex z0)
   {
    setColor(Color.black);
    drawLine(new Complex(0.8,0.2),  new Complex(1.2,-0.2));
    drawLine(new Complex(0.8,-0.2), new Complex(1.2,0.2));
    drawLine(new Complex(1.8,0.2),  new Complex(2.2,-0.2));
    drawLine(new Complex(1.8,-0.2), new Complex(2.2,0.2));

    setColor(Color.white);
    drawLine(z0.plus(new Complex(0,0.2)), z0.minus(new Complex(0,0.2)));
    drawLine(z0.plus(new Complex(0.2,0)), z0.minus(new Complex(0.2,0)));
   }

  public void setZ0(Complex z0)
  {
   this.z0 = z0;

   drawAnnuli(z0);
   drawGrid();
   drawSingularities(z0);
   repaint();
  }

}
