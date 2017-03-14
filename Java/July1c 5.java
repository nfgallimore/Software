// Squares.java 

/**
 *  This program prints a pattern of nested squares
 *  User selects size
 *
 *  @author   Dr. Henry H. Leitner
 *  @version  Last_modified June 3, 2013
 */

import java.util.*;

class July1c 
{

    static void printTheCorrectCharacter (int r, int column, int outerLength)
    {
        int distance = r - 1;                // distance from top edge
        if (distance > outerLength - r)      // distance from bottom edge
              distance = outerLength - r;
        if (distance > column - 1)           // from left edge
               distance = column - 1;
        if (distance > outerLength - column) // from right edge
               distance = outerLength - column;
        if (distance % 2 == 0)               // even number of squares
                System.out.print (" *");     // from closest edge
        else  System.out.print (" .");
    }

    public static void main (String [] args) 
    {
        Scanner keyboard = new Scanner (System.in);
        System.out.print ("How long is the outermost side? ");
        int outerLength = keyboard.nextInt();
        
        for ( int row = 1; row <= outerLength; row++) 
        {
            for ( int column = 1; column <= outerLength; column++)
            {
                printTheCorrectCharacter(row, column, outerLength);
            }
            System.out.println ();
        }
    }
}
