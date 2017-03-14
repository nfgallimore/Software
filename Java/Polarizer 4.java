import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * @author Elliot Dib
 * 
 */
public class Polarizer extends JFrame {
    public static boolean mouse;

        public Polarizer() {
                add(new GamePanel());

                setTitle("Polarizer");
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setSize(Gui.WIDTH, Gui.HEIGHT);
                setBackground(Color.white);
                setLocationRelativeTo(null);
                setVisible(true);
                setResizable(false);
        }

        public static void main (String[] args) {
                new Polarizer();
               // polarizer.();
        }

}
