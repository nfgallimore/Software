// Honeycomb 1.21
// August 1998
// Terence Tao and Allen Knutson


import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;

public class Honeycomb extends Canvas
{
  public boolean numbers;   // fix this up !
  final int infty = 20;

  Image offImage;
  Graphics offGraphics;     	// virtual image & graphics handler
		            	// avoids having to recompute honeycomb
				// when scrolling etc.
				// plus cuts down on flickering
  int n;
  int MAX_N;
  int[][] hive;			// hive[j][i] = hive variable of
				//  j^th row (from bottom) and i^th
				//  column (from left): i>=0, j>=0, i+j <= n
				//            03
				//          02  12
				//        01  11  21
				//      00  10  20  30
				//  Due to incompetence, the values of
				//  this array don't match the definition
				//  in our paper, being off by a sign.

  int[][] backup_hive;		//  For undo

  boolean[][] selected;		//  For selecting/deselecting

  double sqrt3;			//  \sqrt{3}
  int x0, y0;
  float xscale, yscale;

  final int yaspect = 24;       // for hive

  int mode;
  final int SHRINK_MODE = 1;
  final int ENLARGE_MODE = 2;
  final int SELECT_MODE = 3;

  boolean showHiveNumbers;
  boolean drawSurface;
  boolean drawRGBtriangles;

  int I,J;
 
  int vertscale = -2;

  final Color lineColor = Color.white;

  final int NONNEGATIVE = 0;
  final int POSITIVE = 1;
  final int VIRTUAL = -666;     // -infinity
  int virtual;	 		// selected choice of virtualChoice

  Honeycomb()
  {
    offImage = null;
    MAX_N = 12;
    hive = new int[MAX_N][MAX_N];
    backup_hive = new int[MAX_N][MAX_N];
    selected = new boolean[MAX_N][MAX_N];
    sqrt3 = Math.sqrt(3);

    initHive(5);

    xscale = 0;
    yscale = 0;
    x0 = 0;
    y0 = 0;
    mode = SHRINK_MODE;
    showHiveNumbers = true;
    drawSurface = false;
    drawRGBtriangles = true;
    virtual = NONNEGATIVE;
    numbers = true;
    I=J=0;

  }


  void drawHoneycomb(Graphics g)
   {
    g.setColor(Color.black);
    g.fillRect(0,0,size().width, size().height);

    int i,j;

    Polygon p;
    Point P;

    g.setColor(Color.gray);

    for (j = 0; j <= n; j++)
      for (i = 0; i <= n-j; i++)
        if (selected[j][i])     // shade in selected honeycombs
         {
          p = new Polygon();

          if (j > 0)
           {
            P = point(i,j-1,true); p.addPoint(P.x,P.y);
           }
         
          if (j > 0 || i+j < n)
           { 
            P = point(i+1,j-1,false); p.addPoint(P.x,P.y);
           }
         
          if (i+j < n)
           { 
            P = point(i,j,true); p.addPoint(P.x,P.y);
           }
          
          if (i+j < n || i > 0)
           {
            P = point(i,j,false); p.addPoint(P.x,P.y);
           }
         
          if (i > 0)
           { 
            P = point(i-1,j,true); p.addPoint(P.x,P.y);
           }
         
          if (i > 0 || j > 0)
           { 
            P = point(i,j-1,false); p.addPoint(P.x,P.y);
           }

          g.fillPolygon(p);
         }
    
    for (j = 0; j < n; j++)
      for (i = 0; i < n-j; i++)
       {			// Draw all the Ys that make up honeycomb
        Point A = point(i,j,true);
        Point B = point(i,j,false);
        Point C = point(i+1,j,false);
        Point D = point(i+1,j-1,false);
      
        g.setColor(lineColor);            
        g.drawLine(A.x, A.y, B.x, B.y);   // NW pointing
        g.setColor(Color.cyan);
        if (numbers)
	  g.drawString(String.valueOf(findcoord(i,j,2,true)), 
				    (A.x+B.x)/2+3, (A.y+B.y)/2-3);
        g.setColor(lineColor);            
        g.drawLine(A.x, A.y, C.x, C.y);    // NE pointing
        g.setColor(Color.cyan);
        if (numbers)
	  g.drawString(String.valueOf(findcoord(i,j,3,true)),
				    (A.x+C.x)/2+3, (A.y+C.y)/2+7);
        g.setColor(lineColor);
        g.drawLine(A.x, A.y, D.x, D.y); 
        g.setColor(Color.cyan);                // S pointing
        if (numbers)
  	  g.drawString(String.valueOf(findcoord(i,j,1,true)), 
				    (A.x+D.x)/2+2, (A.y+D.y)/2);
       }

     g.setColor(lineColor);
     String out;		// all purpose string buffer
     int w = size().width - 24*n - 60;


// Boundary co-ordinates
     out = "A = (";
     for (i = 0; i < n; i++)
      {
       out += String.valueOf(hive[n-i][i] - hive[n-i-1][i+1]);
       if (i < n-1) out += ", ";
      }
     out += ")";
     g.drawString(out, w, size().height-36);
     
     out = "B = (";
     for (i = 0; i < n; i++)
      {
       out += String.valueOf(hive[0][n-i] - hive[0][n-i-1]);
       if (i < n-1) out += ", ";
      }
     out += ")";
     g.drawString(out, w, size().height-24);


     out = "C = (";
     for (i = 0; i < n; i++)
      {
       out += String.valueOf(hive[i][0] - hive[i+1][0]);
       if (i < n-1) out += ", ";
      }
     out += ")";
     g.drawString(out, w, size().height-12);

     if (showHiveNumbers)
       {
	 if (drawSurface)
	   {
	     g.setColor(Color.red);
	     for (j = 0; j <= n; j++)
	       for (i = 0; i <= n-j; i++)
		 {
		   drawRhombusBar(g,w, i,j, i+1,j+1, i,j+1,   i+1,j);   //ENE
		   drawRhombusBar(g,w, i,j, i-1,j+2, i-1,j+1, i,j+1);  //N
		   drawRhombusBar(g,w, i,j, i+2,j-1, i+1,j-1, i+1,j);  //ESE
		 }
	     
	     for (i=0; i<n; i++)
	       {
		 g.drawLine(w + 30*i, size().height-72
			    - zeroedHive(0,i)/vertscale,
			    w + 30*(i+1), size().height-72
			    - zeroedHive(0,i+1)/vertscale);
		 g.drawLine(w + 15*i, size().height-72-i*yaspect 
			    - zeroedHive(i,0)/vertscale,
			    w + 15*(i+1), size().height-72-(i+1)*yaspect
			    - zeroedHive(i+1,0)/vertscale);
		 g.drawLine(w + 30*i + 15*(n-i), size().height-72-(n-i)*yaspect
			    - zeroedHive(n-i,i)/vertscale,
			    w+30*(i+1)+15*(n-1-i), size().height-72-(n-i-1)*yaspect
			    - zeroedHive(n-i-1,i+1)/vertscale);
				//            03
				//          02  12
				//        01  11  21
				//      00  10  20  30

	       }
	   }

	 if (drawRGBtriangles)
	 for (j = n-1; j >= 0; j--)
	   for (i = 0; i < n-j; i++)
	     {
	       g.setColor(new Color(
	        1-shine(zeroedHive(i,j),zeroedHive(i+1,j),zeroedHive(i,j+1)),
		1-shine(zeroedHive(i+1,j),zeroedHive(i,j+1),zeroedHive(i,j)),
		1-shine(zeroedHive(i,j+1),zeroedHive(i,j),zeroedHive(i+1,j))
		));
	       Polygon tri = new Polygon();

	       tri.addPoint(w + 30*j + 15*i, size().height-72-i*yaspect
			    - zeroedHive(i,j)/vertscale);
	       tri.addPoint(w + 30*(j+1) + 15*i, size().height-72-i*yaspect
			    - zeroedHive(i,j+1)/vertscale);
	       tri.addPoint(w + 30*j + 15*(i+1), size().height-72-(i+1)*yaspect
			    - zeroedHive(i+1,j)/vertscale);

	       g.fillPolygon(tri);

             if (j>0) {
	      g.setColor(new Color(
	       shine(zeroedHive(i,j),zeroedHive(i+1,j),zeroedHive(i+1,j-1)),
	       shine(zeroedHive(i+1,j-1),zeroedHive(i,j),zeroedHive(i+1,j)),
	       shine(zeroedHive(i+1,j),zeroedHive(i+1,j-1),zeroedHive(i,j))
	       ));

	       tri = new Polygon();

	       tri.addPoint(w + 30*j + 15*i, size().height-72-i*yaspect
			    - zeroedHive(i,j)/vertscale);
	       tri.addPoint(w + 30*j + 15*(i+1), size().height-72-(i+1)*yaspect
			    - zeroedHive(i+1,j)/vertscale);
	       tri.addPoint(w + 30*(j-1) + 15*(i+1),size().height-72-(i+1)*yaspect
			    - zeroedHive(i+1,j-1)/vertscale);

	       g.fillPolygon(tri);
	       }

	     }

	 g.setColor(lineColor);

	 for (j = 0; j <= n; j++)
	   for (i = 0; i <= n-j; i++)
	     g.drawString(String.valueOf(trueHive(j,i)), 
			  w + 30*i + 15*j, 
			  size().height-72-j*yaspect);
	       

       }
   }

  void drawRhombusBar(Graphics g, int w,
			     int i1,int j1,int i4,int j4,
			     int i3,int j3,int i2,int j2)
    {
      if ((i1>=0) && (i2>=0) && (i3>=0) && (i4>=0) &&
	  (j1>=0) && (j2>=0) && (j3>=0) && (j4>=0) &&
	  (i1+j1<=n) &&(i2+j2<=n) &&(i3+j3<=n) && (i4+j4<=n))
       {
	if (hive[j2][i2]+hive[j3][i3] > hive[j1][i1]+hive[j4][i4])
          g.setColor(Color.red);
	else if (hive[j2][i2]+hive[j3][i3] < hive[j1][i1]+hive[j4][i4])
          g.setColor(lineColor);
        else return;
	
        g.drawLine(w + 30*i2 + 15*j2, size().height-72-j2*yaspect 
		     - zeroedHive(j2,i2)/vertscale,
		     w + 30*i3 + 15*j3, size().height-72-j3*yaspect
		     - zeroedHive(j3,i3)/vertscale);
       }
    }

  int findcoord(int i, int j, int whichcoord, boolean up)
  // Allen addition for drawing constant coordinates on the edges,
   {
    Point p = getCoords(i,j,up);

    if (whichcoord == 1) return p.x;     // N-S co-ord
    if (whichcoord == 2) return -p.y;     // NW-SE co-ord
    if (whichcoord == 3) return p.y-p.x;   // NE-SW co-ord
    return 0;
   }

  public Point getCoords(int i, int j, boolean up)
// This gives the uncalibrated co-ordinates of a triangle in hive.
// every triangle in hive (up or down-pointing) corresponds to a point
// on the honeycomb.
// i, j are the co-ordinates of the leftmost corner of the triangle.
// Some semiinfinite triangles are included.
   {
    int a=0, b=0;

    if (j== -1)
     {
      a = hive[0][i] - hive[0][i-1];
      b = hive[1][i-1] - hive[0][i-1] - infty;
     }
    else if (i==0 && !up)
     {
      a = hive[j][i+1] - hive[j][i] - infty;
      b = hive[j+1][i] - hive[j][i];
     }
    else if (i+j == n)
     {
      a = hive[j][i] - hive[j][i-1] + infty; 
      b = hive[j+1][i-1] - hive[j][i-1] + infty;
     }
    else if (up)
     {
      a = hive[j][i+1] - hive[j][i];
      b = hive[j+1][i] - hive[j][i];
     }
    else
     {
      a = hive[j+1][i] - hive[j+1][i-1];
      b = hive[j+1][i] - hive[j][i];
     }
 
    return new Point(a,b);
   }

  public int getn()
   {
    return n;
   }

  public void initHive(int n)	// standard regular honeycomb of order n
   {
    this.n = n;
    for (int j = 0; j <= n; j++)
      for (int i = 0; i <= n-j; i++)
       {
        backup_hive[j][i] = hive[j][i] = (i*i + i*j + j*j - i - j)*10;
        selected[j][i] = false;
       }

    xscale = ((float)20)/n;	// setting scaling here
    yscale = ((float)20)/n;
   }

  boolean legalHive() // returns true if the hive is currently legal
   {
    for (int j = 0; j < n - 1; j++)
      for (int i = 0; i < n-1-j; i++)
       {
        if (hive[j+1][i] + hive[j+1][i+1] + virtual >
               hive[j][i+1] + hive[j+2][i])
        return false;

        if (hive[j+1][i] + hive[j][i+1] + virtual >
                hive[j][i] + hive[j+1][i+1])
          return false;

        if (hive[j][i+1] + hive[j+1][i+1] + virtual >
                hive[j+1][i] + hive[j][i+2])
          return false;
       }
    return true;
   }

  void locateHex(int x, int y)
// locates the hexagon(I,J) which contains (x,y).  
   {
    double a = (x-x0)/xscale;
    double b = ((y0-y)/yscale * sqrt3 + a)/2;

    I=J=0;
    for (int j = 0; j < n; j++)
      for (int i = 0; i < n-j; i++)
       {
        if (hive[j][i+1]-hive[j][i] < a)
          if (hive[j][i+1]-hive[j+1][i] < a-b)
           {
            if (j == 0) I++;
            else if (hive[j-1][i+1] - hive[j][i] > a-b) I++;
           }
        if (hive[j+1][i]-hive[j][i] < b)
          if (hive[j][i+1]-hive[j+1][i] > a-b)
           {
            if (i == 0) J++;
            else if (hive[j][i] - hive[j+1][i-1] < a-b) J++;
           }
       } 
   }

  public boolean mouseDown(Event e, int x, int y)
   {

    int i,j;

    if (mode == SHRINK_MODE || mode == ENLARGE_MODE)
      for (j = 0; j <= n; j++)
        for (i = 0; i <= n-j; i++)
          backup_hive[j][i] = hive[j][i];

    I=J=0;
    locateHex(x,y);
 
    if (mode == SHRINK_MODE || mode == ENLARGE_MODE)
     {
      if (mode == SHRINK_MODE ^ e.metaDown()) hive[J][I]++; else hive[J][I]--;

      if (!legalHive())
       {
          for (j = 0; j <= n; j++)
            for (i = 0; i <= n-j; i++)
              hive[j][i] = backup_hive[j][i];
        return false;
       }
     }
    else
     {
      selected[J][I] = !selected[J][I];
     }

    drawHoneycomb(offGraphics);
    paint(this.getGraphics());

    return true;
   }
 
  public void paint(Graphics g)
  {
    if (offImage == null) 
     {
      offImage = createImage(size().width, size().height);
      offGraphics = offImage.getGraphics();
      xscale = ((float)20)/n;	// setting scaling and origin here
      yscale = ((float)20)/n;
      x0 = 100;
      y0 = size().height/2 + 90;
      
      if (yscale > xscale) yscale = xscale;
      if (xscale > yscale) xscale = yscale;

      drawHoneycomb(offGraphics);
     }
    g.drawImage(offImage,0,0,this);
  }

  Point point(int i, int j, boolean up)
   {
    Point p = getCoords(i,j,up);

    double x = x0 + p.x*xscale;
    double y = y0 - (2*p.y-p.x)/sqrt3 * yscale;
    return new Point((int) x, (int) y);
   }

  public void repaint()
   {
    drawHoneycomb(offGraphics);
    this.getGraphics().drawImage(offImage,0,0,this);
   }

  void setHiveDisplay(String hiveDisplayChoice)
    {
      this.showHiveNumbers = !(hiveDisplayChoice.equals("Hide Hive"));
      this.drawRGBtriangles = hiveDisplayChoice.equals("Hive mound w/colors");
      this.drawSurface = hiveDisplayChoice.equals("Hive flatspaces");
      if (this.drawSurface) this.vertscale = 100000; else this.vertscale = -2;
      repaint();
   }
 
  public void setShrink(String shrinkChoice)
   {
    if (shrinkChoice.equals("shrink hexagons")) mode = SHRINK_MODE;
    if (shrinkChoice.equals("enlarge hexagons")) mode = ENLARGE_MODE;
    if (shrinkChoice.equals("select hexagons")) mode = SELECT_MODE;
   }

  public void setVirtual(String virtualChoice)
   {
    if (virtualChoice.equals("Non-negative edges")) virtual = NONNEGATIVE;
    if (virtualChoice.equals("Positive edges")) virtual = POSITIVE;
    if (virtualChoice.equals("Virtual edges")) virtual = VIRTUAL;
   }

  float shine(int firstlow, int secondlow, int high)
    {
      int shinescale = 50;
      int diff = shinescale/2 + high - (firstlow + secondlow)/2;

      if (diff<0) return 0;
      if (diff>shinescale) return 1;
      return ((float)diff)/shinescale;
    }

  public boolean shrinkSelected(boolean shrink) 
                       // true if shrinking, false if enlarging
   {
    int i,j;

    for (j = 0; j <= n; j++)
      for (i = 0; i <= n-j; i++)
       {
        backup_hive[j][i] = hive[j][i];
        if (selected[j][i])
          if (shrink) hive[j][i]++; else hive[j][i]--;
       }

    if (!legalHive())
     {
      for (j = 0; j <= n; j++)
        for (i = 0; i <= n-j; i++)
          hive[j][i] = backup_hive[j][i];
      return false;
     }

    drawHoneycomb(offGraphics);
    paint(this.getGraphics());
    
    return true;
   }

  public boolean shrinkallSelected(boolean shrink) 
                       // true if shrinking, false if enlarging
   {
    int i,j;

    for (j = 0; j <= n; j++)
      for (i = 0; i <= n-j; i++)
       {
        backup_hive[j][i] = hive[j][i];
        if (shrink) hive[j][i]-= i*i + i*j + j*j - i - j; 
        else        hive[j][i]+= i*i + i*j + j*j - i - j;
       }

    if (!legalHive())
     {
      for (j = 0; j <= n; j++)
        for (i = 0; i <= n-j; i++)
          hive[j][i] = backup_hive[j][i];
      return false;
     }

    drawHoneycomb(offGraphics);
    paint(this.getGraphics());
    
    return true;
   }


  public boolean shrinkmaxSelected(boolean shrink) 
                       // true if shrinking, false if enlarging
   {
    int i,j;
    boolean modified = false, badloop = false;

    for (j = 0; j <= n; j++)
      for (i = 0; i <= n-j; i++)
       {
        backup_hive[j][i] = hive[j][i];
        hive[j][i] = selected[j][i] ? (shrink ? 1 : -1) : 0;
       }

    badloop = legalHive(); 

    for (j = 0; j <= n; j++)
      for (i = 0; i <= n-j; i++)
        hive[j][i] = backup_hive[j][i];

    if (badloop) return false; // prevent an infinite loop

    while(shrinkSelected(shrink)) modified = true;

    return modified;
   }


  public int trueHive(int j,int i)  // a simple fix to the hive sign problem
    {
      return (n-1)*n*10 - hive[j][i];
    }

  public void undo()
   {
    int tmp;

    for (int j = 0; j <= n; j++)
      for (int i = 0; i <= n-j; i++)
       {
        tmp = backup_hive[j][i];
        backup_hive[j][i] = hive[j][i];
        hive[j][i] = tmp;
       }

    repaint();
   }

  public int zeroedHive(int i,int j)
    {
      return (hive[i][j] - hive[0][0] 
	      - (i*(hive[n][0]-hive[0][0]))/n
	      - (j*(hive[0][n]-hive[0][0]))/n);
    }
}


