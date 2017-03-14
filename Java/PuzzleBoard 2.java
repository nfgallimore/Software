import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Puzzle;
import Triangle;
import Edge;

public class PuzzleBoard extends Canvas
{
  Puzzle p;
  Image offImage;
  Graphics offGraphics;     	// virtual image & graphics handler
  int xScale, xOffset, yScale, yOffset;

 
  public PuzzleBoard(int k, int nk)
  {
    super();
    reset(k,nk);
    addMouseListener(new MouseAdapter()
     {
      public void mousePressed(MouseEvent e)
       {
        rescale();  
        Triangle t = getTriangle(e.getX(), e.getY());
        if (e.isMetaDown())
          p.setValue(t,Puzzle.ZEROES);
        else
          p.setValue(t,Puzzle.ONES);
        repaint();
       }
     });
  }

  public void rescale()
   {
    int a = (size().height - 20) / (p.n+2);
    int b = (size().width - 20) / (p.n+2);

    xScale = (a < b) ? a : b;
    yScale = (a < b) ? a : b;

    xOffset = 10 + xScale;
    yOffset = size().height - 10 - yScale;
   }

  Triangle getTriangle(int x, int y)
   {
    int i, j;
    boolean up;
    
    i = (yOffset - y)/yScale + 1;
    j = ((x-xOffset) - (yOffset-y)/2)/xScale + 1;
    up = ( ((x-xOffset) + (yOffset-y)/2)/xScale == i+j-2 );
    return new Triangle(i,j,up);
   }

  public void reset(int k, int nk)
   {
    p = new Puzzle(k,nk);
    repaint();
   }

  Point point(int i, int j)
   {
    return new Point(xOffset + j * xScale + i * xScale / 2, yOffset - i * yScale);
   }

//          30
//          /\			
//         /31\
//       20____21
//       /\ 21 /\
//      /21\  /22\		point locations using i-j notation
//    10____11____12
//    /\ 11 /\ 12 /\
//   /11\  /12\  /13\
//  00___01____02____03


  void drawDisk(Graphics g, Color c, Point pt)
   {
    g.setColor(c);
    g.fillOval(pt.x - 3, pt.y - 3, 6, 6);
   }

  void drawTriangle(Graphics g, Color c, Point pa, Point pb, Point pc)
   {
    g.setColor(c);
    Polygon poly = new Polygon();
    poly.addPoint(pa.x, pa.y);
    poly.addPoint(pb.x, pb.y);    
    poly.addPoint(pc.x, pc.y);
    g.fillPolygon(poly);
   }

  void drawTriangle(Graphics g, Color c, Triangle t)
   {
    if (t.up)
      drawTriangle(g, c, point(t.i-1,t.j-1), point(t.i-1, t.j), point(t.i, t.j-1));
    else
      drawTriangle(g, c, point(t.i,t.j), point(t.i-1, t.j), point(t.i, t.j-1));
   }

  void drawEdge(Graphics g, Color c, Edge e)
   {
    g.setColor(c);
    Edge left = e.rotateLeft();
    Edge right = e.rotateRight();
    Point p0 = point(e.i0, e.j0);
    Point p1 = point(e.i1, e.j1);
    Point pLeft = point(left.i1, left.j1);
    Point pRight = point(right.i1, right.j1);

    g.drawLine(p0.x, p0.y, p1.x, p1.y);

    int xm = (p0.x + p1.x)/2;
    int ym = (p0.y + p1.y)/2;

    g.drawLine(xm, ym, xm - (pRight.x-p0.x)/6, ym - (pRight.y-p0.y)/6);
    g.drawLine(xm, ym, xm - (pLeft.x-p0.x)/6, ym - (pLeft.y-p0.y)/6);
   }

  void drawTemp(Graphics g, Edge e)		// colors an edge by its value
   {
    switch (p.valueAt(e))
     {
      case Puzzle.ONE: g.setColor(Color.red); break;
      case Puzzle.ZERO: g.setColor(Color.green); break;
      case Puzzle.HYBRID: g.setColor(Color.cyan); break;
      default: g.setColor(Color.black); break;
     }

    Point p0 = point(e.i0, e.j0);
    Point p1 = point(e.i1, e.j1);

    g.drawLine(p0.x, p0.y, p1.x, p1.y);
   }

  Color colorCode(int x)
   {
    switch(x)
     {
      case Puzzle.ONES: return Color.red;
      case Puzzle.ZEROES: return Color.green;
	case Puzzle.RHOMBUS_NW: case Puzzle.RHOMBUS_N: case Puzzle.RHOMBUS_NE: return Color.cyan;
     }
    return Color.white;
   }


  public void drawPuzzle(Graphics g)
   {
    int i,j,enum;

    rescale();
    g.setColor(Color.black);
    g.fillRect(0,0,size().width, size().height);

    for (enum = 0; enum < p.n*p.n; enum++)
      drawTriangle(g, colorCode(p.valueAt(enum)), new Triangle(enum)); 

//    for (i = 0; i <= p.n; i++)			// Colors the triangles a little outside the puzzle too
//      for (j = 0; j <= p.n-i; j++)
//       {
//        Edge e = new Edge(i, j, Edge.WEST);
//        do
//         {
//          Triangle t = e.leftTriangle();
//          drawTriangle(g, colorCode(p.valueAt(t)), t);
//          e = e.rotateRight();
//         }
//        while (e.orientation != Edge.WEST);
//       }


    for (i = 0; i <= p.n; i++)
      for (j = 0; j <= p.n-i; j++)
       {
        Edge e = new Edge(i, j, Edge.EAST);
        do
         {
//  	    drawTemp(g, e);
          if (p.oriented(e)) drawEdge(g, Color.white, e);
          if (p.oriented(e.reverse())) drawEdge(g, Color.white, e.reverse());		// this is to orient edges
          e = e.rotateRight();									// a little outside the puzzle
          e = e.rotateRight();
         }
        while (e.orientation != Edge.EAST);
       }

    for (i = 0; i <= p.n; i++)
      for (j = 0; j <= p.n-i; j++)
        drawDisk(g, Color.white, point(i,j));
    
   }

  public void paint(Graphics g)
  {
    if (offImage == null) 
     {
      offImage = createImage(size().width, size().height);
      offGraphics = offImage.getGraphics();
     }
    drawPuzzle(offGraphics);
    g.drawImage(offImage,0,0,this);
  }
   
  public void repaint()
   {
    if (offImage != null)
     {
      drawPuzzle(offGraphics);
      getGraphics().drawImage(offImage,0,0,this);
     }
   }  

}
