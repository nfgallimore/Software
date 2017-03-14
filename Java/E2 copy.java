// File E2.java
/** Alternative program to E.java
  * in which we correct the exceptional problem
  * @author: Henry H. Leitner
  * @version: Last modified on July 13, 2013
  */

import java.io.*;
import java.util.*;

public class E2
{
  public static void main (String args[])
  {
     int total = 0;

     Scanner keyboard = new Scanner(System.in);

     for (int i = 0; i < 5; i++)
     {
        try
        {
           System.out.print("Type a baseball score: ");

           int score = keyboard.nextInt();
           if (score < 0) 
               throw (new IllegalBaseballScoreException ("You typed a score less than zero!"));
           total += score;
        }
        catch ( IllegalBaseballScoreException e)
        { 
           i--;       // Change the for loop control variable! 
           System.out.println (e.getMessage() );  
        }
     }
     System.out.println ("Sum of all scores is " + total);
   }
}


class IllegalBaseballScoreException extends Exception
{
   public IllegalBaseballScoreException (String s)
   {  
       super (s); 
   }
}

