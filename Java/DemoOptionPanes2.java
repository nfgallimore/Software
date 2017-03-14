// Illustrates the use of Integer.parseInt and Double.parseDouble
import  javax.swing.*;

public class DemoOptionPanes2 
{
    public static void main (String [] args) 
    {
        String ageText = JOptionPane.showInputDialog (null, "How old are you?");
        int age = Integer.parseInt(ageText);
        
        String moneyText = JOptionPane.showInputDialog (null, "How much $ do you have?");
        double money = Double.parseDouble (moneyText);
        
        JOptionPane.showMessageDialog (null, "If you can double your money each year\n" +
                            "You'll have " + (money*32) + " dollars at age " +
                             (age+5) + "!!!");
     }
}
