import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Gridgame;
import Util;

public class GridgameApp extends Applet 
{
  Gridgame g;
  public TextField score, free,occupied,target;
  Choice squareChoice, nChoice;
  int n;

  public void init()
   {
    setLayout(new BorderLayout());
    n = 9;
    add(g = new Gridgame(n,this), BorderLayout.CENTER);

    Panel p = new Panel();
    add(p, BorderLayout.EAST);
    p.setLayout(new GridBagLayout());

    Util.add(p,new Label("Board size n:"),Util.DEFAULT);

    Util.add(p,nChoice = new Choice(),Util.ENDL);

    for (int i = 3; i < 13; i++) nChoice.add(String.valueOf(i));

    nChoice.select(String.valueOf(n));
    nChoice.addItemListener(new ItemListener()
     {
      public void itemStateChanged(ItemEvent e)
       {
        try
         {
          n = Integer.parseInt(nChoice.getSelectedItem());
          g.setn(n);
          g.paint(g.getGraphics());
          setSquareChoice();
         }
        catch (NumberFormatException error) {return;}
       }
     });
    
    Util.add(p,new Label("Square type: "),Util.DEFAULT);
    Util.add(p,squareChoice = new Choice(),Util.ENDL);
    squareChoice.addItemListener(new ItemListener() 
     {
      public void itemStateChanged(ItemEvent e)
       {
        g.setkey(squareChoice.getSelectedIndex());
       }
     });
    setSquareChoice();

    Util.add(p,new Label("Current score"),Util.DEFAULT);
    Util.add(p,score = new TextField("N/A", 10),Util.ENDL);
    score.setEditable(false);

//    Util.add(p,new Label("Squares already occupied:"),Util.DEFAULT);
//    Util.add(p,occupied = new TextField("0",10),Util.ENDL);
//    occupied.setEditable(false);

    Util.add(p,new Label("Squares needed to win:"),Util.DEFAULT);
    Util.add(p,target = new TextField(g.getTarget(),10),Util.ENDL);
    target.setEditable(false);

    Util.add(p,new Label(""), Util.VFILL);
   }

  void setSquareChoice()
   {
    squareChoice.removeAll();
    squareChoice.add("None");
    for (int i = 1; i <=n;  i++) squareChoice.add(String.valueOf(i));
    squareChoice.select("1");
   }

}
