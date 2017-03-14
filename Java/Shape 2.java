import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Complex;

public class Shape extends Object
 {
  public int type;
  public final static int POINT = 1;   // the point z1
  public final static int CIRCLE = 2;  // thru z1,z2,z3: center center and radius r
  public final static int LINE = 3;    // thru z1,z2,z3, or start, end.

  public Complex z1, z2, z3;  

  public Complex center;
  public float r;
  public Complex start, end;

  public Color color;

  Shape()
   {
    type = POINT;
    z1 = new Complex();
    z2 = new Complex();
    z3 = new Complex();
    center = new Complex();
    r = 0;
    start = new Complex();
    end = new Complex();
    color = Color.red;
   }

  public void point(float x, float y)
   {
    type = POINT;
    z1 = new Complex(x,y);
   }

  public void circle(float x, float y, float r)
   {
    type = CIRCLE;
    z1 = new Complex(x-r,y);
    z2 = new Complex(x+r,y);
    z3 = new Complex(x, y+r);
    center = new Complex(x,y);
    this.r = r;
   }

  public void line(float x1, float y1, float x2, float y2)
   {
    type = LINE;
    z1 = new Complex(x1, y1);
    z2 = new Complex(x2, y2);
    z3 = new Complex(true);
    start = z1;
    end = z2;
   }

  public static Complex Mobius(Complex z, Complex a, Complex b, Complex c, Complex d)
   {
    if (z.infinite)
     {
      if (c.norm() < 0.01) return new Complex(true);
      return a.over(c);
     }

    Complex denom = c.times(z).plus(d);

    if (denom.norm() < 0.01) return new Complex(true);
    return a.times(z).plus(b).over(denom);
   }


  public boolean equals(Shape sh)
   {
    if (type != sh.type) return false;
    if (color != sh.color) return false;

    if (type == POINT)
     {
      return z1.equals(sh.z1);
     }

    if (type == LINE)
     {
      Complex z = start.minus(end).phase();
      Complex w = sh.start.minus(sh.end).phase();
    
      if (!z.equals(w)) return false; 

      float dev = start.minus(sh.start).over(z).y;

      if (dev*dev > 0.001) return false; 
      return true;
     }

    if (type == CIRCLE)
     {
      if (center.minus(sh.center).norm() > 0.001) return false;
      if ((r - sh.r) * (r - sh.r) > 0.001) return false;
      return true;
     }

    return false;
   }


  public Shape transform(Complex a, Complex b, Complex c, Complex d)
   {
    Shape shape = new Shape();

    shape.color = color;
    shape.type = type;

    if (type == POINT)
     {
      Complex w = Mobius(z1,a,b,c,d);
      shape.point(w.x, w.y);
      return shape;
     }

    Complex w1 = Mobius(z1,a,b,c,d);
    Complex w2 = Mobius(z2,a,b,c,d);
    Complex w3 = Mobius(z3,a,b,c,d);

    if (w1.infinite)
     {
      shape.line(w2.x,w2.y, w3.x,w3.y);
      return shape;
     }

    if (w2.infinite)
     {
      shape.line(w1.x,w1.y, w3.x,w3.y);
      return shape;
     }

    if (w3.infinite)
     {
      shape.line(w1.x,w1.y, w2.x,w2.y);
      return shape;
     }

    Complex ratio = w1.minus(w2).over(w1.minus(w3));

    if (Math.abs(ratio.y) < 0.01 * Math.abs(ratio.x))
     {
      shape.line(w1.x,w1.y, w2.x,w2.y);
      return shape;
     }

    float A,B,C,D,X,Y,det, xc, yc;

    A = w2.x - w1.x;   B = w2.y - w1.y;
    C = w3.x - w1.x;   D = w3.y - w1.y;

    X = (w2.norm() - w1.norm()) / 2;
    Y = (w3.norm() - w1.norm()) / 2;

    det = A*D - B*C;

    xc = (D*X - B*Y) / det;
    yc = (-C*X + A*Y) / det;

    float av = (w1.x-xc)*(w1.x-xc) +  (w1.y-yc)*(w1.y-yc);
//    av += (w2.x-xc)*(w2.x-xc) +  (w2.y-yc)*(w2.y-yc);
//    av += (w3.x-xc)*(w3.x-xc) +  (w3.y-yc)*(w3.y-yc);
//    av /= 3;

    shape.circle(xc, yc, (float) Math.sqrt(av));
    return shape;
  }
 }
