import java.util.*;

class June28c
{
     public static void main (String [] args)
     {
            
            Scanner keyboard = new Scanner (System.in);
          
            System.out.print ("Type your grade as an integer: ");
            int grade = keyboard.nextInt();

            if (grade >= 90) System.out.println ("A");
            else if (grade >= 80) System.out.println ("A-");
            else if (grade >= 70) System.out.println ("B+");
            else System.out.println ("That does not compute!");
    }
}
