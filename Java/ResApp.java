import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import ResGrid;
import DoubleField;
import ColorChoice;

public class ResApp extends Applet
 {
  ResGrid grid;
  ColorChoice xChoice, yChoice;
  Button clearButton;
  Checkbox box[];
  DoubleField xPos[], yPos[], xRes[], yRes[];
  public TextField IntegField;

  boolean totalUpdate()
    {
     try
     {
     for (int i = 0; i < 4; i++)
      {
       grid.on[i] = box[i].getState();
       grid.x[i] = (float) xPos[i].toDouble();
       grid.y[i] = (float) yPos[i].toDouble();
       grid.xRes[i] = (float) xRes[i].toDouble();
       grid.yRes[i] = (float) yRes[i].toDouble();
      }
      grid.setFunction();
     }
     catch (NumberFormatException e)
      {
       return false;
      }
     return true;
    }

   public boolean action(Event evt, Object what)
    {
     if (evt.target==clearButton) 
      {
       totalUpdate();
       return true;
      }

     if (evt.target==xChoice)
      {
       grid.xcolor = yChoice.getSelectedColor();
       return true;
      }

     if (evt.target==yChoice)
      {
       grid.xcolor = yChoice.getSelectedColor();
       return true;
      }

     for (int i = 0; i < 4; i++)
      {
       if (evt.target==box[i] || evt.target==xPos[i] || evt.target==yPos[i] || evt.target==xRes[i] || evt.target==yRes[i])
        {
         return totalUpdate();
        }
      }

     return false;
    }

  Panel LowerPanel()
   {
    Panel controls = new Panel();

    controls.add(new Label("Integral = 2 pi *"));
    controls.add(IntegField = new TextField(35));
    IntegField.setEditable(false);
 
    return controls;
   }

  public void init()
   {
    setLayout(new BorderLayout(15,15));
    add("Center", grid = new ResGrid(this));
    add("North", UpperPanel());
    add("South", LowerPanel());
   }

  Panel UpperPanel()
   {
    Panel fullControls = new Panel();
    Panel controls = new Panel();

    fullControls.setLayout(new GridLayout(5,1));

    controls.add(new Label(" f(z) is"));
    controls.add(xChoice = new ColorChoice());
    xChoice.select("Green");

    controls.add(new Label(" i f(z) is"));
    controls.add(yChoice = new ColorChoice());
    yChoice.select("Cyan");

    controls.add(clearButton = new Button("Clear"));

    fullControls.add(controls);

    int i;

    box = new Checkbox[4];
    xPos = new DoubleField[4];
    yPos = new DoubleField[4];
    xRes = new DoubleField[4];
    yRes = new DoubleField[4];


    for (i = 0; i < 4; i++)
     {
      controls = new Panel();
      controls.add(box[i] = new Checkbox("Pole " + String.valueOf(i+1)));
      controls.add(new Label("at"));
      controls.add(xPos[i] = new DoubleField());
      controls.add(new Label("+i"));
      controls.add(yPos[i] = new DoubleField());
      controls.add(new Label("w. residue"));
      controls.add(xRes[i] = new DoubleField());
      controls.add(new Label("+i"));
      controls.add(yRes[i] = new DoubleField());
      fullControls.add(controls);
     }

    box[0].setState(true);
    xRes[0].setDouble(1.0);   
    xRes[1].setDouble(2.0);   xPos[1].setDouble(4.0);
    xRes[2].setDouble(1.0);   yPos[2].setDouble(4.0);
    xRes[3].setDouble(-1.0);  xPos[3].setDouble(-4.0);  yPos[3].setDouble(-4.0);

    totalUpdate();

    return fullControls;
   }

 }
