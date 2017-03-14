import java.awt.*;
import MyCanvas;

public class MyGraphics extends Object
 {
  public ReferenceFrame f;
  public double scale;  // how many units in an axis
  public MyCanvas c;

  MyGraphics(MyCanvas c)
   {
    f = new ReferenceFrame();
    scale = 5;
    this.c = c;
   }


  int x(SpacetimePoint p)
   {
    return (int) (((f.x(p) / scale) + 1) * c.getSize().width / 2.0);
   } 

  int y(SpacetimePoint p)
   {
    return (int) ((1 - (f.t(p) / scale)) * c.getSize().height / 2.0);
   }

  public void setColor(Color col)
   {
    c.offGraphics.setColor(col);
   }

  public void drawDot(SpacetimePoint p)
   {
    int x = x(p);
    int y = y(p);

    c.offGraphics.fillOval(x,y,5,5); 
   } 

  public void drawLine(SpacetimePoint p, SpacetimePoint q)
   {
    int x1 = x(p), y1 = y(p);
    int x2 = x(q), y2 = y(q);

    c.offGraphics.drawLine(x1,y1,x2,y2);
   }

  public void drawForwardLine(SpacetimePoint p, double abs_v)
   {
    int x1 = x(p), y1 = y(p);

    if (y1 <= 0) return;

    double v = f.v(abs_v);

    int x2 = x1 + (int) (y1 * v);

    c.offGraphics.drawLine(x1,y1,x2,0);
   }

  public void drawBackwardLine(SpacetimePoint p, double abs_v)
   {
    int x1 = x(p), y1 = y(p);

    if (y1 >= c.getSize().height) return;

    double v = f.v(abs_v);

    int x2 = x1 + (int) ((y1-c.getSize().height) * v);

    c.offGraphics.drawLine(x1,y1,x2,c.getSize().height);
   }
 
  public void clearScreen()
   {
    c.offGraphics.fillRect(0,0,c.getSize().width,c.getSize().height);
   } 

  public void repaint()
   {
    c.repaint();
   }
   
 }
