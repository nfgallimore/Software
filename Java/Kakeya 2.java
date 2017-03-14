import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Kakeya extends Canvas
{
  public float area_est;
  float alpha;
  int n;
  boolean needs_update;
  Image offImage;
  Graphics offGraphics;

  Kakeya()
  {
    alpha = 1;
    n = 0;
    needs_update = true;
    offImage = null;
    area_est = 0;
  }


  public void draw(Graphics g)
   {
    int[] X = new int[3];
    int[] Y = new int[3];
    int height = size().height - 10;
    int width = size().width - 10;

    int min;
 
    if (height < width) min = height; else min = width;

    g.clearRect(0, 0, size().width, size().height);

    g.setColor(Color.black);
    g.drawRect(0, 0, size().width-1, size().height-1);

    Y[0] = 5;
    Y[1] = min+5;    
    Y[2] = min+5;    

    int n2 = 1;
    float alpha_n = 1;

    for (int i = 0; i < n; i++)
     {
      n2 *= 2;
      alpha_n *= alpha;
     }

    for (int k = 0; k < n2; k++)
     {
      float x = 0;
      int m = k;
      int j2 = n2/2;
      float alpha_j = 1;
      int col;

      if (n2 > 128) col = k / (n2 / 128); else col = k * (128 / n2);
 
      g.setColor(new Color(col,col,col));

      for (int j = 0; j<n; j++)
       { 
        x += (alpha_j / j2) * (float) ((m+1)/2);
        alpha_j *= alpha;
        j2 /= 2;
        m /= 2;
       }
      x *= alpha-1;
      x += 1 - alpha_n;
      X[0] = (int) (x * min) + 5;
      x += (float)k / n2;
      X[1] = (int) (x * min) + 5;
      x += (float)1 / n2;
      X[2] = (int) (x * min) + 5;
      g.fillPolygon(X,Y,3);
     } 

    area_est = alpha_n*alpha_n + (1-alpha);
   }

  public void paint(Graphics g)
  {
//    System.out.println("Ahem.");
    if (offImage == null) 
     {
      offImage = createImage(size().width, size().height);
      offGraphics = offImage.getGraphics();
     }

    if (needs_update)
     {
      draw(offGraphics);
      needs_update = false;
     }
    g.drawImage(offImage,0,0,this);

//    g.drawString("Hello",5,4);
  }

  public void redraw(float alpha, int n)
   {
//    System.out.println("Hello?");
    this.alpha = alpha;
    this.n = n;
    needs_update = true;
    paint(getGraphics());
   }
}
