// Honeycomb Wrapper class 1.3
// October 1998
// Terence Tao and Allen Knutson

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Honeycomb;

public class HoneycombApp extends Applet
 {
  Honeycomb honeycomb;
  Choice nChoice, shrinkChoice, hiveDisplayChoice, virtualChoice;
  Button undoButton, shrinkButton, enlargeButton, shrinkallButton, enlargeallButton;
  Button shrinkmaxButton, enlargemaxButton, resetButton, numbersButton;

   public boolean action(Event evt, Object what)
    {
     if (evt.target==shrinkChoice)
      {
       honeycomb.setShrink(shrinkChoice.getSelectedItem());
       return true;
      }

     if (evt.target==hiveDisplayChoice)
      {
       honeycomb.setHiveDisplay(hiveDisplayChoice.getSelectedItem());
       return true;
      }

     if (evt.target==nChoice) 
      {
       honeycomb.initHive(Integer.valueOf(nChoice.getSelectedItem()).intValue());
       honeycomb.repaint();
       return true;
      }
 
     if (evt.target==resetButton)
      {
       honeycomb.initHive(honeycomb.getn());
       honeycomb.repaint();
       return true;
      }

     if (evt.target==undoButton)
      {
       honeycomb.undo();
       return true;
      }

     if (evt.target==virtualChoice)
      {
       honeycomb.setVirtual(virtualChoice.getSelectedItem());
       return true;
      }

     if (evt.target==numbersButton)
      {
       honeycomb.numbers = !honeycomb.numbers;
       honeycomb.repaint();
       return true;
      }

     if (evt.target==shrinkButton)
      {
       return honeycomb.shrinkSelected(true);
      }

     if (evt.target==shrinkmaxButton)
      {
       return honeycomb.shrinkmaxSelected(true);
      }

     if (evt.target==shrinkallButton)
      {
       return honeycomb.shrinkallSelected(true);
      }

     if (evt.target==enlargeButton)
      {
       return honeycomb.shrinkSelected(false);
      }

     if (evt.target==enlargemaxButton)
      {
       return honeycomb.shrinkmaxSelected(false);
      }

     if (evt.target==enlargeallButton)
      {
       return honeycomb.shrinkallSelected(false);
      }
     
     return false;
    }

  public void init()
   {
    setLayout(new BorderLayout(15,15));
    add("North", UpperPanel());
    add("Center", honeycomb = new Honeycomb());
   }

  Panel UpperPanel()
   {
    Panel fullControls = new Panel();
    Panel controls;

    fullControls.setLayout(new GridLayout(3,1));

    controls = new Panel();
    controls.setLayout(new FlowLayout());

    controls.add(new Label("n ="));
    controls.add(nChoice = new Choice());

    for (int i = 1; i <= 9; i++)
      nChoice.addItem(String.valueOf(i));
    nChoice.select("5");

    controls.add(new Label(" Mouse clicks"));
    controls.add(shrinkChoice = new Choice());
    shrinkChoice.addItem("shrink hexagons");
    shrinkChoice.addItem("enlarge hexagons");
    shrinkChoice.addItem("select hexagons");
  
    controls.add(hiveDisplayChoice = new Choice());
    hiveDisplayChoice.addItem("Hive flatspaces");
    hiveDisplayChoice.addItem("Hive mound w/colors");
    hiveDisplayChoice.addItem("Hide Hive");
    hiveDisplayChoice.select("Hive mound w/colors"); 

    controls.add(virtualChoice = new Choice());
    virtualChoice.addItem("Non-negative edges");
    virtualChoice.addItem("Positive edges");
    virtualChoice.addItem("Virtual edges");

    fullControls.add(controls);

    controls = new Panel();

    controls.add(shrinkButton = new Button("Shrink"));
    controls.add(shrinkmaxButton = new Button("Shrink Max"));
    controls.add(shrinkallButton = new Button("Shrink All"));
    controls.add(enlargeButton = new Button("Enlarge"));
    controls.add(enlargemaxButton = new Button("Enlarge Max"));
    controls.add(enlargeallButton = new Button("Enlarge All"));

    fullControls.add(controls);

    controls = new Panel();
    controls.add(numbersButton = new Button("Toggle numbers"));
    controls.add(resetButton = new Button("Reset"));
    controls.add(undoButton = new Button("Undo"));

    fullControls.add(controls);
    return fullControls;
   }
 }


