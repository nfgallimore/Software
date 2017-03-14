import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import DiffGrid;

public class DiffApp extends Applet
 {
  DiffGrid grid;
  Choice functionChoice, Xchoice, Ychoice;
  Button clearButton;


   public boolean action(Event evt, Object what)
    {
     if (evt.target==functionChoice) 
      {
       String functionString = functionChoice.getSelectedItem();

       if (functionString.equals("z")) grid.setFunction(grid.IDENT);
       if (functionString.equals("(1+i)z-i")) grid.setFunction(grid.AFFINE);
       if (functionString.equals("z^2/4")) grid.setFunction(grid.SQUARE);
       if (functionString.equals("x+y+yi")) grid.setFunction(grid.XYYI);
       if (functionString.equals("5/z")) grid.setFunction(grid.INVERSE);
       if (functionString.equals("exp(z)")) grid.setFunction(grid.EXP);
       if (functionString.equals("zbar")) grid.setFunction(grid.ZBAR);
       if (functionString.equals("x+y^2+yi")) grid.setFunction(grid.XY2YI);

       return true;
      }

     if (evt.target==clearButton) { grid.setFunction(grid.getFunction()); return true;}
    
     if (evt.target==Xchoice || evt.target==Ychoice)
      {
       grid.setColor(((Choice)evt.target).getSelectedItem(), evt.target == Xchoice);
       return true;
      }

     return false;
    }

  public void init()
   {
    setLayout(new BorderLayout(15,15));
    add("North", UpperPanel());
    add("Center", grid = new DiffGrid());
   }

  void colorize(Choice choice)
   {
    choice.addItem("Red");
    choice.addItem("Blue");
    choice.addItem("Cyan");
    choice.addItem("Dark Gray");
    choice.addItem("Light Gray");
    choice.addItem("Black");
    choice.addItem("Magenta");
    choice.addItem("Orange");
    choice.addItem("Pink");
    choice.addItem("Green");
    choice.addItem("Yellow");
    choice.addItem("White");
   }

  Panel UpperPanel()
   {
    Panel controls = new Panel();

    controls.add(new Label("f(z) = "));
    controls.add(functionChoice = new Choice());
    functionChoice.addItem("z");
    functionChoice.addItem("(1+i)z-i");
    functionChoice.addItem("z^2/4");
    functionChoice.addItem("5/z");
    functionChoice.addItem("x+y+yi");
    functionChoice.addItem("x+y^2+yi");
    functionChoice.addItem("exp(z)");
    functionChoice.addItem("zbar");

    controls.add(new Label(" df/dx is"));
    colorize(Xchoice = new Choice());
    Xchoice.select("Green");
    controls.add(Xchoice);

    controls.add(new Label(" df/dy is"));
    colorize(Ychoice = new Choice());
    Ychoice.select("Cyan");
    controls.add(Ychoice);

    controls.add(clearButton = new Button("Clear"));

    return controls;
   }
 }
