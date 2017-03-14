import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends Applet 
 {

   public void paint(Graphics g)
    {
     int N = 10;
     int i,j,ii,jj,k,kk,x,t,y,z;

     g.setColor(Color.black);

     for (i = 5; i < 10; i++)
       for (j = 1; j <= i; j++)
         for (ii = 5; ii < 10; ii++)
           for (jj = 1; jj < ii; jj++)
             for (k = j; k < 500; k+= i)
               for (kk = jj; kk < 500; kk += ii)
                 g.drawLine(0, k, 500, kk);

     g.setColor(Color.red);

     for (i = 5; i < 10; i++)
       for (j = 1; j <= i; j++)
         for (ii = 5; ii < 10; ii++)
           for (jj = 1; jj < ii; jj++)
            {
             x = 500 * i / (i + ii);
             t = 1; 
             do
              {
               y = (j * ii + jj*i + t * i * ii) / (i + ii);
               g.drawLine(x,y,x,y);
               t++;
              }
             while (y < 500);
            }

   }
 }
