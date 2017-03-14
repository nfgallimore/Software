import java.awt.*;

public class NumberChoice extends Choice
 {
  public NumberChoice()
   {
    this(9);
   }

  public NumberChoice(int n)
   {
    super();
    for (int i = 1; i <= n; i++)
      addItem(String.valueOf(i));
   }

  public void selectNumber(int i)
   {
    select(String.valueOf(i));
   }

  public int getSelectedNumber()
   {
    try
     {
      return Integer.parseInt(getSelectedItem());
     }
    catch (NumberFormatException e)
     {
      return 1;
     }
   }
 }