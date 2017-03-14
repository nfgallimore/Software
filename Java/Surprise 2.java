// Suprise.java
// Fills a window with a big yellow circle 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Surprise
{
   int diameter = 0;
   static int SIZE = 800; 
   int panelHeight, panelWidth; 


   public static void main (String [] args)
   {
      Surprise s = new Surprise();
      s.drawIt ();
   }

   public void drawIt ()
   {
      JFrame window = new JFrame ("Surprise!");
      SurprisePanel sp = new SurprisePanel ();
      sp.setBackground (Color.BLUE);
      window.setSize (SIZE, SIZE);
      window.add (sp);
      window.setVisible (true);
      window.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      panelHeight = sp.getSize().height;
      panelWidth = sp.getSize().width;
      while (diameter < Math.min (panelHeight, panelWidth))
      {
         diameter++;
         try {Thread.sleep (15); }               // sleep for 15 msec
         catch (InterruptedException e) { };
         sp.repaint();
      }
      Graphics g = sp.getGraphics();
      g.setColor (Color.RED);
      g.setFont (new Font ("Courier", Font.BOLD, 36));
      FontMetrics metrics = g.getFontMetrics ();
      try { Thread.sleep (1000); }               // pause for 1 sec
      catch (InterruptedException e) { };
      g.drawString ("Surprise!", (panelWidth-metrics.stringWidth ("Surprise"))/2,
                    (panelHeight + metrics.getHeight()) / 2);

    }

    class SurprisePanel extends JPanel 
    {   // an inner class so we can use the diameter, 
        // panelHeight, and panelWidth variables

       public void paintComponent (Graphics g)
       {
          super.paintComponent (g);
          g.setColor (Color.YELLOW);
          g.fillOval ((panelWidth-diameter)/2, (panelHeight-diameter)/2,
                       diameter, diameter);
       }
    }
}
