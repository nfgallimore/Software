import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Triangle;
import Edge;

public class Puzzle extends Object
 {
  public int k, nk, n;

  public static final int ZEROES = 0;
  public static final int ONES = 1;
  public static final int RHOMBUS_NW = 2;
  public static final int RHOMBUS_N = 3;
  public static final int RHOMBUS_NE = 4;

  public static final int ZERO = 10;
  public static final int ONE = 11;
  public static final int HYBRID = 12;

  public static final int NULL = 666;  

  int[] triangle;		// indexed from 0 to n^2-1


  public Puzzle(int k, int nk)
   {
    this.k = k;
    this.nk = nk;
    this.n = k + nk;
    triangle = new int[n*n];

    for (int enum = 0; enum < n*n; enum++)
     {
      Triangle t = new Triangle(enum);
      if (t.i > k)
        triangle[enum] = ZEROES;
      else if (t.level() <= k)
        triangle[enum] = ONES;
      else
        triangle[enum] = RHOMBUS_NW;
     }
   }

  public int valueAt(int enum)
   {
    return triangle[enum];
   }

  public int valueAt(Triangle t)
   {
    if (t.inside(n))  return valueAt(t.enum());

    if (t.level() <= 0 && t.j <= 0) return ZEROES;		// puzzle is zero in the three corner sectors
    if (t.i > n && t.level() > n) return ZEROES;	
    if (t.i <= 0 && t.j > n) return ZEROES;

    if (t.i <= 0)
      if (valueAt(new Edge(0,t.j,Edge.WEST)) == ZERO) return ZEROES; else return RHOMBUS_NE;

    if (t.j <= 0)
      if (valueAt(new Edge(t.level(), 0,Edge.SOUTHWEST)) == ZERO) return ZEROES; else return RHOMBUS_N;
     
    if (t.level() > n)
      if (valueAt(new Edge(t.i, n-t.i, Edge.SOUTHEAST)) == ZERO) return ZEROES; else return RHOMBUS_NW;

    return NULL;
   }

  public int valueAt(int i, int j, boolean up)
   {
    return valueAt(new Triangle(i,j,up));
   }

  public int valueAt(Edge e)
   {
    if (!e.inPuzzle(n)) return NULL;

    if (e.isCardinal()) return valueAt(e.reverse());

    switch(valueAt(e.leftTriangle()))
     {
      case ZEROES: return ZERO;
      case ONES: return ONE;
      case RHOMBUS_NW:
       switch(e.orientation)
        {
         case Edge.SOUTHWEST: return HYBRID;
         case Edge.NORTHWEST: return ONE;
         case Edge.EAST:      return ZERO;
        }
      case RHOMBUS_N:
       switch(e.orientation)
        {
         case Edge.SOUTHWEST: return ONE;
         case Edge.NORTHWEST: return ZERO;
         case Edge.EAST:      return HYBRID;
        }
      case RHOMBUS_NE:
       switch(e.orientation)
        {
         case Edge.SOUTHWEST: return ZERO;
         case Edge.NORTHWEST: return HYBRID;
         case Edge.EAST:      return ONE;
        }
     }
    return NULL;
   }

  public boolean oriented(Edge e) // is the edge e oriented in the puzzle ordering?
   {
    int left = valueAt(e.leftTriangle());
    int right = valueAt(e.rightTriangle());

    if (left == right) return false;
    if (left == ONES || right == ZEROES) return true;
    return false;
   }

  public void setValue(Triangle t, int value)
   {
    if (!t.inside(n)) return;
    triangle[t.enum()] = value;
   }

 }
