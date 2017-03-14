import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PairOfEyes 
 {
   // Animated eyes that follow the cursor.  

   Point left, right, leftPupil, rightPupil;
   final int 
      EYE_RADIUS = 60,
    PUPIL_RADIUS = 25;
  
   public PairOfEyes (Point c) 
   {
      left = new Point (c.x - EYE_RADIUS-3, c.y); 
     right = new Point (c.x + EYE_RADIUS+3, c.y);
   }
  
   private void fillCircle (Graphics g,  Point center, int radius) 
   {
  	// Utility method: an abbreviated 
  	// way to draw a filled circle.
 
	g.fillOval((int)center.x-radius, (int)center.y-radius, 2*radius, 2*radius);
   }
  
   public void stare (Graphics g, Point cursor) 
   {
     // Draw the "white" of the eyes
     g.setColor(Color.YELLOW);
     fillCircle(g, left, EYE_RADIUS);
     fillCircle(g, right, EYE_RADIUS);
  
     // Draw the pupils
     g.setColor (Color.RED);
     leftPupil = compute (cursor, left);
     fillCircle (g, leftPupil, PUPIL_RADIUS);
     rightPupil = compute (cursor, right);
     fillCircle (g, rightPupil, PUPIL_RADIUS);
   }
  
   private Point compute (Point cursor, Point eye) 
   {
  	// Compute the location of the pupil, given the
 	// locations of the eye and the cursor.
     double d = Math.sqrt ( (cursor.getX()-eye.getX()) * (cursor.getX() - eye.getX())
                          + (cursor.getY() -eye.getY()) * (cursor.getY() - eye.getY()));
     int r = EYE_RADIUS - PUPIL_RADIUS;
     return new Point (eye.x + (int)((cursor.x - eye.x) * r/d),
                          eye.y + (int)((cursor.y -eye.y) * r/d));
   }
}

