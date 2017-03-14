// File SliderTest.java
// Use "red", "green", "blue" slider
// controls to change the window background color
// Last modified on July 5, 2013

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SliderTest
{  
   public static void main(String[] args)
   { 
      SliderFrame frame = new SliderFrame (600, 600);
      frame.setTitle ("SliderTest");
      frame.setVisible (true);
   }
}

class SliderFrame extends JFrame
{  
   private  JPanel colorPanel;
   private JSlider redSlider;
   private JSlider greenSlider;
   private JSlider blueSlider;
   
   public SliderFrame (int width, int height)
   {  
      setSize(width, height);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // construct components

      colorPanel = new JPanel();
      ColorListener listener = new ColorListener();

      redSlider = new JSlider(0, 100, 5);
      redSlider.addChangeListener (listener);

      greenSlider = new JSlider (0, 100, 70);
      greenSlider.addChangeListener (listener);

      blueSlider = new JSlider(0, 100, 70);
      blueSlider.addChangeListener(listener);
      
      // fill content pane

      JPanel southPanel = new JPanel();
      JLabel red = new JLabel ("Red", SwingConstants.RIGHT);
      JLabel green = new JLabel ("Green", SwingConstants.RIGHT);
      JLabel blue = new JLabel ("Blue", SwingConstants.RIGHT);
      Font f = new Font ("Helvetica", Font.BOLD, 18);
      red.setFont(f);
      green.setFont(f);
      blue.setFont(f);
      southPanel.setLayout (new GridLayout(3, 2));
      southPanel.add (red);
      southPanel.add (redSlider);
      southPanel.add (green);
      southPanel.add (greenSlider);
      southPanel.add (blue);
      southPanel.add (blueSlider);

      add (colorPanel, BorderLayout.CENTER);
      add (southPanel, BorderLayout.SOUTH);
   }
   
   /**
      Reads the slider values and sets the panel to
      the selected color.
   */

   private class ColorListener implements ChangeListener
   {  
      public void stateChanged(ChangeEvent event)
      {  
         // read slider values
   
         float red = 0.01F * redSlider.getValue();
         float green = 0.01F * greenSlider.getValue();
         float blue = 0.01F * blueSlider.getValue();

         // now set panel background to selected color
         colorPanel.setBackground (new Color (red, green, blue));
         colorPanel.repaint();
      }
   }   
}
