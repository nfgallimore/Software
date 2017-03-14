// File E.java
/* Demonstrate how to create and use one's
 * own "exceptions"
 * @author  Henry Leitner
 * @version Last modified July 12, 2013
 */

import java.io.*;
import java.util.*;

public class E
{
  public static void main (String [] args)
  {
       int total = 0;
       Scanner keyboard = new Scanner(System.in);
       try
       {
          for (int i = 0; i < 5; i++)
          {
             System.out.print("Type a baseball score: ");
             int score = keyboard.nextInt();
             if (score < 0) 
                   throw (new IllegalBaseballScoreException
                            ("You input an illegal score < 0!"));
             total += score;
          }
       }
       catch( IllegalBaseballScoreException e)
       { 
          System.out.println (e.getMessage() );  
       }
       System.out.println ("Sum of all input scores is ... " + total);
   }
}


class IllegalBaseballScoreException extends Exception
{
   public IllegalBaseballScoreException (String s)
   {  
     super (s); 
   }
}
