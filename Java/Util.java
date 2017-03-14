import java.awt.*;

public class Util extends Object
 {
  static GridBagConstraints DEFAULT,CENTER,ENDL,VFILL;

  static
   {
    DEFAULT = new GridBagConstraints(); 
    DEFAULT.fill = GridBagConstraints.BOTH;
    DEFAULT.weightx = 1.0;

    CENTER = new GridBagConstraints(); 
    CENTER.fill = 1;
    CENTER.gridwidth = GridBagConstraints.REMAINDER;

    ENDL = new GridBagConstraints(); 
    ENDL.fill = GridBagConstraints.BOTH;
    ENDL.gridwidth = GridBagConstraints.REMAINDER;
    ENDL.weightx = 1.0;

    VFILL = new GridBagConstraints();
    VFILL.gridheight = GridBagConstraints.REMAINDER;
    VFILL.weighty = 1.0;
   }

  public static void add(Container container, Component component, 
                         GridBagConstraints constraint)
   {
    container.add(component);
    ((GridBagLayout)container.getLayout()).setConstraints(component,constraint);
   } 

  public static Choice colorChoice()
   {
    Choice choice = new Choice();
    choice.addItem("Black");
    choice.addItem("Blue");
    choice.addItem("Cyan");
    choice.addItem("Dark Gray");
    choice.addItem("Gray");
    choice.addItem("Green");
    choice.addItem("Light Gray");
    choice.addItem("Magenta");
    choice.addItem("Orange");
    choice.addItem("Pink");
    choice.addItem("Red");
    choice.addItem("White");
    choice.addItem("Yellow");
    return choice;
   }
  
  public static Color colorOf(Choice c)
   {
    String s = c.getSelectedItem();

    if (s == "Red") return Color.red;
    if (s == "Blue") return Color.blue;
    if (s == "Cyan") return Color.cyan;
    if (s == "Dark Gray") return Color.darkGray;
    if (s == "Gray") return Color.gray;
    if (s == "Light Gray") return Color.lightGray;
    if (s == "Black") return Color.black;
    if (s == "Magenta") return Color.magenta;
    if (s == "Orange") return Color.orange;
    if (s == "Pink") return Color.pink;
    if (s == "Green") return Color.green;
    if (s == "Yellow") return Color.yellow;
    if (s == "White") return Color.white;

    return Color.black;
   }  

  public static double doubleValue(TextField t) throws NumberFormatException
   {
    return Double.valueOf(t.getText()).doubleValue();
   }
 }
