import java.awt.*;

public class ColorChoice extends Choice
 {
  public ColorChoice()
   {
    add("Black");
    add("Blue");
    add("Cyan");
    add("Dark Gray");
    add("Gray");
    add("Green");
    add("Light Gray");
    add("Magenta");
    add("Orange");
    add("Pink");
    add("Red");
    add("White");
    add("Yellow");
   }

  public Color getSelectedColor()
   {
    String s = getSelectedItem();

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
 }