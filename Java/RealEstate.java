import java.util.Scanner;
import java.lang.Character;

public class RealEstate
{
    public static void main(String[] args)
    {

        /*
        // testing code 
        String test = "Desirable unfurnished flat in quiet residental area";
        int i = 0;

        if ( vowel (test.charAt(i)) ) 
        {
            System.out.print(test.charAt(i));
            i = 1;
        }
        if ( test.length() > 1) {
            for (int length = test.length(); i < length; i++)
                if ( !vowel(test.charAt(i)) || test.charAt(i - 1) == ' ')
                    System.out.print(test.charAt(i));
            System.out.println(); 
        }
        */

        Scanner input = new Scanner(System.in);
        System.out.println("Enter a property description: ");
        String s = input.nextLine();
        int j = 0;
        if ( vowel (s.charAt(j)) ) 
        {
            System.out.print(s.charAt(j));
            j = 1;
        }
        if (s.length() > 1) 
        {
            for (int length = s.length(); j < length; j++)
                if ( !vowel(s.charAt(j)) || s.charAt(j - 1) == ' ')
                    System.out.print(s.charAt(j));
            System.out.println();
        } 
    }

    // determines if char is a vowel
    public static boolean vowel(char c)
    {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') return true;
        else return false;
    }
}