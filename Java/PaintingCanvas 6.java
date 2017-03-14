// File PaintingCanvas.java  

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class PaintingCanvas extends JPanel
{
    private int xValue = -10, yValue = -10;
    
    public PaintingCanvas()
    {
	addMouseMotionListener (
	       new MouseMotionAdapter()
               {
		   public void mouseDragged (MouseEvent e)
		   {
		       xValue = e.getX();
		       yValue = e.getY();
		       repaint();
		   }
	       }         
                               );
    }
    
    public void paintComponent (Graphics g)
    {
        //super.paintComponent (g);
        g.fillOval (xValue, yValue, 7, 7);
    }
}

