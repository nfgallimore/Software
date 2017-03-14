import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import ArgumentGrid;
import DoubleField;
import ColorChoice;

public class ArgumentApp extends Applet
 {
  ArgumentGrid grid;
  Button aLeft, aRight, bLeft, bRight, cLeft, cRight, dLeft, dRight;
  DoubleField aField, bField, cField, dField;

  boolean totalUpdate()
    {
     try
     {
       grid.a = (float) aField.toDouble();
       grid.b = (float) bField.toDouble();
       grid.c = (float) cField.toDouble();
       grid.d = (float) dField.toDouble();
       grid.resetGrid();
     }
     catch (NumberFormatException e)
      {
       return false;
      }
     return true;
    }


   boolean decrement(DoubleField f)
    {
     f.setDouble(f.toDouble() - 0.1);
     return totalUpdate();
    }

   boolean increment(DoubleField f)
    {
     f.setDouble(f.toDouble() + 0.1);
     return totalUpdate();
    }

   public boolean action(Event evt, Object what)
    {
     if (evt.target == aField || evt.target == bField || evt.target == cField || evt.target == dField)
      {
       return totalUpdate();
      }

     if (evt.target == aLeft) return decrement(aField);
     if (evt.target == bLeft) return decrement(bField);
     if (evt.target == cLeft) return decrement(cField);
     if (evt.target == dLeft) return decrement(dField);

     if (evt.target == aRight) return increment(aField);
     if (evt.target == bRight) return increment(bField);
     if (evt.target == cRight) return increment(cField);
     if (evt.target == dRight) return increment(dField);

     return false;
    }

  public void init()
   {
    setLayout(new BorderLayout(15,15));
    add("Center", grid = new ArgumentGrid(this));
    add("North", UpperPanel());
   }

  Panel UpperPanel()
   {
    Panel fullcontrols = new Panel();
    Panel controls;

    fullcontrols.setLayout(new GridLayout(3, 1));

    controls = new Panel();
    controls.add(new Label("(z^2 +"));
    controls.add(aLeft = new Button("<"));
    controls.add(aField = new DoubleField((double) 1.0));
    controls.add(aRight = new Button(">"));
    controls.add(new Label("z +"));
    controls.add(bLeft = new Button("<"));
    controls.add(bField = new DoubleField((double) 5.0));
    controls.add(bRight = new Button(">"));
    controls.add(new Label(")"));
    fullcontrols.add(controls);

    controls = new Panel();
    controls.add(new 
       Label("---------------------------------------------------------------------------------------------"));
    fullcontrols.add(controls);

    controls = new Panel();
    controls.add(new Label("(z^2 +"));
    controls.add(cLeft = new Button("<"));
    controls.add(cField = new DoubleField((double) -1.0));
    controls.add(cRight = new Button(">"));
    controls.add(new Label("z +"));
    controls.add(dLeft = new Button("<"));
    controls.add(dField = new DoubleField((double) 2.0));
    controls.add(dRight = new Button(">"));
    controls.add(new Label(")"));
    fullcontrols.add(controls);

    totalUpdate();

    return fullcontrols;
   }

 }
