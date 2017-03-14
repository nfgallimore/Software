// File PaintTest.java
// Uses class MouseMotionAdapter
// which is implemented by class PaintingCanvas
// A very simple "painting" program. 

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PaintTest 
{
    public static void main (String args [])
    {
	PaintingCanvas pc = new PaintingCanvas();
	JLabel jl = new JLabel ("DRAG the mouse in order to paint!");
        jl.setForeground (Color.RED.darker());
	jl.setFont (new Font ("Helvetica", Font.BOLD, 24));

        JFrame jf = new JFrame ("Painting Example");
        jf.setSize (500, 500);
        jf.add (jl, BorderLayout.SOUTH);
        jf.add (pc, BorderLayout.CENTER);
        jf.getContentPane().setBackground (Color.YELLOW);
        jf.setVisible(true);

        jf.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }
}
