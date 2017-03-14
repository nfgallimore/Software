import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import FtocGrid;

public class FtocApp extends Applet
 {
  FtocGrid grid;
  Choice functionChoice, primChoice, Xchoice, Ychoice;
  Button clearButton;

  void setFunction(int num)
   {
    functionChoice.select(num);
    primChoice.select(num);

    switch (num)
     {
      case 0: grid.setFunction(grid.ONE); return;
      case 1: grid.setFunction(grid.ONEI); return;
      case 2: grid.setFunction(grid.IDENT); return;
      case 3: grid.setFunction(grid.INVERSE); return;
      case 4: grid.setFunction(grid.INVERSE2); return;
      case 5: grid.setFunction(grid.EXP); return;
     }
   }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==functionChoice) 
      {
       setFunction(functionChoice.getSelectedIndex());
       return true;
      }

     if (evt.target==primChoice) 
      {
       setFunction(primChoice.getSelectedIndex());
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
    add("Center", grid = new FtocGrid());
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
    controls.add(new Label("F(z) = "));
    controls.add(primChoice = new Choice());

    functionChoice.addItem("1");		    primChoice.addItem("z");
    functionChoice.addItem("1+i");		    primChoice.addItem("(1+i)z");
    functionChoice.addItem("z");		    primChoice.addItem("z^2/2");
    functionChoice.addItem("1/z");		    primChoice.addItem("Log(z)");
    functionChoice.addItem("1/z");		    primChoice.addItem("Log_(0,2pi](z)");
    functionChoice.addItem("exp(z)");	    primChoice.addItem("exp(z)");

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
