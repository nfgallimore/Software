import Triangle;

public class Edge extends Object
 {
  public int i0,j0,i1,j1;
  public int orientation;

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

  public static final int NORTHWEST = 6;
  public static final int NORTHEAST = 7;
  public static final int EAST = 5; 
  public static final int SOUTHEAST = 2;	// these numbers are convenient for modular arithmetic; don't change them!
  public static final int SOUTHWEST = 1;
  public static final int WEST = 3;
  public static final int ZERO = 4;		// This should only ever come up if there's an error somewhere.

  public static final int STRAIGHT = 10;
  public static final int GENTLE_LEFT = 11;
  public static final int GENTLE_RIGHT = 12;
  public static final int BACKWARD = 13;

  public Edge()
   {
    this.i0 = 1;
    this.j0 = 1;
    this.i1 = 1;
    this.j1 = 1;
    this.orientation=ZERO;
   }

  public Edge(int i0, int j0, int i1, int j1)
   {
    this.i0 = i0;
    this.j0 = j0;
    this.i1 = i1;
    this.j1 = j1;

    orientation = (i1 - i0)*3 + (j1-j0) + 4;
   }

  public Edge(int i0, int j0, int orientation)
   {
    constructor(i0,j0,orientation);
   }

  void constructor(int i0, int j0, int orientation)
   {
    this.i0 = i0;
    this.j0 = j0;
    this.orientation = orientation;

    i1 = i0 + (orientation/3) - 1;
    j1 = j0 + (orientation%3) - 1;
   }


  public Edge(Triangle t, int orientation)
   {
    this();		// to keep compiler happy about initialization

    if (t.up)
      switch(orientation)
       {
        case NORTHEAST: case EAST:
          constructor(t.i-1, t.j-1, orientation); break;
        case SOUTHWEST: case SOUTHEAST: 
          constructor(t.i, t.j-1, orientation); break;
        case NORTHWEST: case WEST:
          constructor(t.i-1, t.j, orientation); break;
       }
    else
      switch(orientation)
       {
        case NORTHEAST: case NORTHWEST:
          constructor(t.i-1, t.j, orientation); break;
        case SOUTHEAST: case EAST: 
          constructor(t.i, t.j-1, orientation); break;
        case SOUTHWEST: case WEST:
          constructor(t.i, t.j, orientation); break;
       }
   }

  public Triangle leftTriangle()		// returns triangle to the left of edge
   {
    switch(orientation)
     {
      case NORTHWEST: return new Triangle(i0+1, j0, true);
      case NORTHEAST: return new Triangle(i0+1, j0, false);
      case EAST:      return new Triangle(i0+1, j0+1, true);
      case SOUTHEAST: return new Triangle(i0, j0+1, false);
      case SOUTHWEST: return new Triangle(i0, j0+1, true);
      case WEST:      return new Triangle(i0, j0, false);
     }
    return new Triangle();
   }

  public Edge rotateRight()
   {
    return new Edge(i0,j0,rotateRight(orientation));
   }

  public int rotateRight(int direction)
   {
    switch(direction)
     {
      case NORTHWEST: return NORTHEAST;
      case NORTHEAST: return EAST;
      case EAST:      return SOUTHEAST;
      case SOUTHEAST: return SOUTHWEST;
      case SOUTHWEST: return WEST;
      case WEST:      return NORTHWEST;
     }
    return ZERO;
   }

  public Edge rotateLeft()
   {
    return new Edge(i0,j0,rotateLeft(orientation));
   }

  public int rotateLeft(int direction)
   {
    switch(direction)
     {
      case NORTHWEST: return WEST;
      case NORTHEAST: return NORTHWEST;
      case EAST:      return NORTHEAST;
      case SOUTHEAST: return EAST;
      case SOUTHWEST: return SOUTHEAST;
      case WEST:      return SOUTHWEST;
     }
    return ZERO;
   }

  public boolean isCardinal()
   {
    switch(orientation)
     {
      case SOUTHEAST: case NORTHEAST: case WEST:  return true;
      default:						  return false;
     }    
   }

  public Triangle rightTriangle()		// returns triangle to the right of edge
   {
    return rotateRight().leftTriangle();
   }

  public Edge reverse()
   {
    return new Edge(i1,j1,i0,j0);
   }

  public Edge move(int direction)
   {
    switch(direction)
     {
      case STRAIGHT:     return new Edge(i1,j1,orientation);
      case GENTLE_LEFT:  return move(STRAIGHT).rotateLeft();
      case GENTLE_RIGHT: return move(STRAIGHT).rotateRight();
      case BACKWARD:     return reverse().move(STRAIGHT).reverse();
     }
    return new Edge();
   }

  boolean inside(int i, int j, int n)
   {
    return (i >= 0) && (j >= 0) && (i+j <= n+1);
   }

  public boolean touchPuzzle(int n) // does the edge touch the puzzle?
   {
    return inside(i0,j0,n) || inside(i1,j1,n);    
   }

  public boolean inPuzzle(int n) // does the edge lie in the puzzle?
   {
    return inside(i0,j0,n) && inside(i1,j1,n);    
   }

 }
