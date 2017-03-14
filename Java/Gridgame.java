import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Gridgame extends Canvas
{
  int n;
  int key;
  boolean fast;
  int A[][];
  GridgameApp app;

  public Gridgame(int N, GridgameApp theApp)
  {
    app = theApp;
    n = N;
    setn(9);
    fast = true;
    addMouseListener(new MouseAdapter()
     {
      public void mousePressed(MouseEvent e)
       {
        int i = (e.getX()-3)/20;
        int j = (e.getY()-3)/20;
        if (i < 0 || i >= n || j < 0 || j >= n) return;
        if (key != 0)
         {
          if (shaded(i,j)) return;
          if (A[i][j] == key) 
           {
            A[i][j] = 0; paint(getGraphics()); return;
           }
          if (illegal(i,j)) return;
         }
        A[i][j] = key;
        paint(getGraphics());
       }
     });

    addKeyListener(new KeyAdapter()
     {
      public void keyTyped(KeyEvent e)
       {
        int a = -1;
        switch (e.getKeyChar())
         {
          case ' ': setn(n); break; 
          case 'a': a = 10; break;
          case 'b': a = 11; break;
          case 'c': a = 12; break;
          case 'f': fast = !fast; break;
          case 'S':
            System.out.println(String.valueOf(n));
            for (int i = 0; i < n; i++)
             {
              for (int j = 0; j < n; j++)
                System.out.print(String.valueOf(A[i][j]) + " ");
              System.out.println("");
             }
            return; 
          default:
           String s = String.valueOf(e.getKeyChar());
           try
            {
             a = Integer.parseInt(s);
            }
           catch(NumberFormatException event) {return; }
          }
        setkey(a);
        paint(getGraphics());
       }
     }); 
  }

  public void setkey(int a)
   {
    if (a >= 0 && a <= n)
      app.squareChoice.select(key = a);
   }

  int countShaded()
   {
    int i, j, sum = 0;
 
    for (i = 0; i < n; i++)
      for (j = 0; j < n; j++)
        if (shaded(i,j)) sum++;
    return sum;
   }

 
  public void setn(int n)
   {
    this.n = n;
    key = 1;
    A = new int[n][n];

    int i,j;
    for (i = 0; i < n; i++)
      for (j = 0; j < n; j++)
        A[i][j] = 0;
   }

  public boolean shaded(int i, int j)
   {
    int a, b;

    if (A[i][j] != 0) return false;

    for (a = 0; a < n; a++)
      for (b = 0; b < n; b++)
        if (a != i && b != j)
          if (A[a][j] == A[i][b] && A[i][b] != 0)
            return true;
    return false;
   }

  public boolean illegal(int i, int j)
   {
    if (key == 0) return false;

    if (A[i][j] != 0) return true;
    int a,b;
    for (a = 0; a < n; a++)
      if (A[i][a] == key || A[a][j] == key) return true;
    for (a = 0; a < n; a++)
      for (b = 0; b < n; b++)
       {
        if (A[a][b] == key)
          if (A[a][j] != 0 || A[i][b] != 0) return true;
       }
    return false;
   }

  int numOccupied()
   {
    int i, j, sum = 0;
 
    for (i = 0; i < n; i++)
      for (j = 0; j < n; j++)
        if (A[i][j] > 0) sum++;
    return sum;
   }

  String getTarget()
   {
    int a = (int)(Math.exp(Math.log(n) * Math.log(6) / Math.log(3)) + 1.001);
    int o = numOccupied();

    return String.valueOf(a-o) + " (" + String.valueOf(n*n - countShaded() - o)
           + " free)";
   }

  String getScore()
   {
    int sum = numOccupied();

    if (sum == 0) return "N/A";

    double x = Math.log(sum) / Math.log(n);

    return String.valueOf(x);
   }

  public void paint(Graphics g)
   {
    g.clearRect(0, 0, getSize().width, getSize().height);

    int i,j;

    g.setColor(Color.black);

    for (i = 0; i <= n; i++)
     {
      g.drawLine(i*20 + 3, 3, i*20 + 3, n*20+3);
      g.drawLine(3,i*20 + 3, n*20+3, i*20 + 3);
     }

    int s = countShaded(),t;

    for (i = 0; i < n; i++)
      for (j = 0; j < n; j++)
       {
        if (A[i][j] > 0) 
         {
          g.setColor(Color.black);
          g.drawString(String.valueOf(A[i][j]), i*20+5, j*20+18);
          continue;
         }
        else if (shaded(i,j)) g.setColor(Color.black);
        else if (illegal(i,j)) g.setColor(Color.red);
        else if (key == 0 || fast) g.setColor(Color.white);
        else
         {
          A[i][j] = key;
          t = 255 - 5 * (countShaded() - s);
          g.setColor(new Color(t,t,t));
          A[i][j] = 0;
         }
        g.fillRect(i*20+4,j*20+4,19,19);
       }
    app.score.setText(getScore());
//    app.free.setText(String.valueOf(n*n - countShaded() - numOccupied()));
//    app.occupied.setText(String.valueOf(numOccupied()));
    app.target.setText(getTarget());
   }
}
