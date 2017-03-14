// EyeDemo.java
// Create animated eyes that follow the cursor.  

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

 class EyePanel extends JPanel implements MouseMotionListener
 {
   PairOfEyes e1, e2; 
   Point cursor;
  
   public EyePanel() 
   { 
     	e1 = new PairOfEyes (new Point (130, 150)); 	// center of one pair of eyes 
    	e2 = new PairOfEyes (new Point (540, 150));	// center of the other pair
     	cursor = new Point (250, 2000);    	        // initial cursor 
     	addMouseMotionListener (this);
    }
    
    public void mouseMoved (MouseEvent e) 
    {
        cursor = new Point (e.getX(), e.getY()); 
        repaint();	    // this call is critical!
    }
  
    public void mouseDragged (MouseEvent e) { } 
          
           
    public void paintComponent (Graphics g)
    {  
       super.paintComponent (g); 
       e1.stare (g, cursor);   
       e2.stare (g, cursor);
    } 
    
}			 // Of EyePanel class

   
public class EyeDemo  
{ 
  public static void main(String args[]) 
   {
       JFrame frame = new JFrame ("Follow the cursor, Man!");
       EyePanel eyePanel = new EyePanel ();
    
       eyePanel.setBackground (Color.BLUE);
       frame.setContentPane (eyePanel);
       frame.setSize (700,700);
       frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
       frame.setVisible (true);
   }
}
