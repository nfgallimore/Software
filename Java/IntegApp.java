import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import IntegGrid;

public class IntegApp extends Applet
 {
  IntegGrid grid;
  Choice functionChoice, Xchoice, Ychoice;
  Button clearButton;


   public boolean action(Event evt, Object what)
    {
     if (evt.target==functionChoice) 
      {
       String functionString = functionChoice.getSelectedItem();

       if (functionString.equals("1")) grid.setFunction(grid.ONE);
       if (functionString.equals("1+i")) grid.setFunction(grid.ONEI);
       if (functionString.equals("z")) grid.setFunction(grid.IDENT);
       if (functionString.equals("1/z")) grid.setFunction(grid.INVERSE);
       if (functionString.equals("exp(z)")) grid.setFunction(grid.EXP);
       if (functionString.equals("zbar")) grid.setFunction(grid.ZBAR);

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
    add("Center", grid = new IntegGrid());
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
    functionChoice.addItem("1");
    functionChoice.addItem("1+i");
    functionChoice.addItem("z");
    functionChoice.addItem("zbar");
    functionChoice.addItem("1/z");
    functionChoice.addItem("exp(z)");

    controls.add(new Label(" f(z) is"));
    colorize(Xchoice = new Choice());
    Xchoice.select("Green");
    controls.add(Xchoice);

    controls.add(new Label(" i f(z) is"));
    colorize(Ychoice = new Choice());
    Ychoice.select("Cyan");
    controls.add(Ychoice);

    controls.add(clearButton = new Button("Clear"));

    return controls;
   }
 }
