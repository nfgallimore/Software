// DoWhile2.java

/**
 *  Illustrate use of sentinel input value & do-while loop
 *
 *  @author   Dr. Henry H. Leitner
 *  @version  Last_modified June 23, 2013
 */
 
import java.util.*;

class DoWhile2 
{
    public static void main (String [] args) 
    {
        Scanner keyboard = new Scanner (System.in);
        final int SENTINEL = -1;
        int count = 0;
        int sum = 0;
        int n;

        System.out.println ("TYPE ZERO OR MORE INTEGERS, FOLLOWED BY ");
        System.out.println (SENTINEL + ", and the numbers will be summed up!\n");

        System.out.print ("Input an integer: ");
        n = keyboard.nextInt();
        do 
        {   
            count++;
            sum += n;
            System.out.print ("Input an integer: ");
            n = keyboard.nextInt ();
        } while (n != SENTINEL);
        
        System.out.println ("SUM OF " + count + " NUMBERS WAS " + sum);
    }
}
