// Puzzle Wrapper class 1.0
// August 2001
// Terence Tao and Allen Knutson

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import PuzzleBoard;
import NumberChoice;

public class PuzzleApp extends Applet
 {
  PuzzleBoard board;
  NumberChoice onesChoice, zeroesChoice;

  public void init()
   {
    setLayout(new BorderLayout(15,15));
    add("Center", board = new PuzzleBoard(5,5));
    add("North", UpperPanel());
   }

  Panel UpperPanel()
   {
    Panel controls = new Panel();
    controls.setLayout(new FlowLayout());

    controls.add(new Label("k ="));
    controls.add(onesChoice = new NumberChoice());
    onesChoice.selectNumber(5);

    controls.add(new Label("n-k ="));
    controls.add(zeroesChoice = new NumberChoice());
    zeroesChoice.selectNumber(5);

    onesChoice.addItemListener(new ItemListener()
     {
      public void itemStateChanged(ItemEvent e)
       {
        board.reset(onesChoice.getSelectedNumber(), zeroesChoice.getSelectedNumber()); 
       }
     });

    zeroesChoice.addItemListener(new ItemListener()
     {
      public void itemStateChanged(ItemEvent e)
       {
        board.reset(onesChoice.getSelectedNumber(), zeroesChoice.getSelectedNumber()); 
       }
     });

    return controls;
   }
 }


