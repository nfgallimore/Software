import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import MultiGrid;

public class MultiApp extends Applet
 {
  MultiGrid grid;
  Choice functionChoice, showChoice;
  Button clearButton;


   public boolean action(Event evt, Object what)
    {
     if (evt.target==functionChoice) 
      {
       String functionString = functionChoice.getSelectedItem();

       if (functionString.equals("+/- z")) grid.setFunction(grid.PM);
       if (functionString.equals("z^{1/2}")) grid.setFunction(grid.SQRT);
       if (functionString.equals("z^{1/3}")) grid.setFunction(grid.CUBERT);
       if (functionString.equals("z^{1/4}")) grid.setFunction(grid.FOURTHRT);
       if (functionString.equals("z^{1/5}")) grid.setFunction(grid.FIFTHRT);
       if (functionString.equals("z^{1/6}")) grid.setFunction(grid.SIXTHRT);
       if (functionString.equals("Log(z)")) grid.setFunction(grid.LOG);

       return true;
      }

     if (evt.target==clearButton) grid.setFunction(grid.getFunction());

     if (evt.target==showChoice) grid.setShow(showChoice.getSelectedItem().equals("Show all branches"));
     return false;
    }

  public void init()
   {
    setLayout(new BorderLayout(15,15));
    add("North", UpperPanel());
    add("Center", grid = new MultiGrid());
   }

  Panel UpperPanel()
   {
    Panel controls = new Panel();

    controls.add(new Label("f(z) = "));
    controls.add(functionChoice = new Choice());
    functionChoice.addItem("+/- z");
    functionChoice.addItem("z^{1/2}");
    functionChoice.addItem("z^{1/3}");
    functionChoice.addItem("z^{1/4}");
    functionChoice.addItem("z^{1/5}");
    functionChoice.addItem("z^{1/6}");
    functionChoice.addItem("Log(z)");

    controls.add(clearButton = new Button("Clear"));

    controls.add(showChoice = new Choice());
    showChoice.addItem("Show all branches");
    showChoice.addItem("Principal branch only");

    return controls;
   }
 }
