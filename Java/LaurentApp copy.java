import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/* import Complex;
import LaurentGrid;
import LaurentPanel; */

public class LaurentApp extends Applet
 {
  LaurentGrid grid;
  LaurentPanel panel;
  Choice functionChoice, Xchoice, Ychoice;
  Button clearButton;

  public void init()
   {
    setLayout(new GridLayout(1,2));
    add(grid = new LaurentGrid());
    add(panel = new LaurentPanel());

    grid.addMouseListener(new MouseAdapter() {
       public void mouseClicked(MouseEvent e)
        {
         Complex z = grid.toComplex(e.getPoint());
         grid.setZ0(z);
         panel.setZ0(z);
        }
     });
   }
 }
