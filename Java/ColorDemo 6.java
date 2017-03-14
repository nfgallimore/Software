//  ColorDemo.java
/** Rapidly change the content pane's
  * background color (using all RGB combinations)
  * @author: Henry Leitner
  * @version: Last modified on July 6, 2013
  */

import javax.swing.*;
import java.awt.*;

public class ColorDemo 
{
  public static void main(String[] args) 
  {
     JFrame jf = new JFrame("Lotsa colors!");
     jf.setSize (800, 600);
     jf.setVisible (true);
    
     Container c = jf.getContentPane();
     for (int red = 0; red <= 255; red++)
     {
        for (int green = 0; green <= 255; green++)
        {
             for (int blue = 0; blue <= 255; blue++ )
	     {
		 Color color = new Color (red, green, blue);
		 c.setBackground (color);
	     }
        }
     }
  }
}

