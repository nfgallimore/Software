// ArrayExpansionDemo.java
/** Illustrates how to use an Exception
  * in order to make arrays grow "dynamically" -- i.e.,
  * at execution time
  * @author: Henry Leitner
  * @version: Last modified on July 13, 2013
  */

import java.util.*;

public class ArrayExpansionDemo
{

   public static void main( String args[] ) 
   {
      Scanner keyboard = new Scanner( System.in );
      String [] list = new String [4];
      int count = 0;
      final int ARRAY_INCREMENT = 1;
      
      System.out.print ("Input a string (or just newline to quit): ");
      String s = keyboard.nextLine();
      
      while (s.length() != 0)
      {  // append a bunch of strings onto end of list
         
  
         /* Note that if an ArrayIndexOutOfBoundsException occurs, the
          *  catch clause extends the size of the array by a small amount
          *  before inserting the string. This is rather inefficient!
          */
         try 
         {
             list[count] = s;
         }
         catch (ArrayIndexOutOfBoundsException e) 
         {
             String newList[] =
                new String[ list.length + ARRAY_INCREMENT ]; // Create a new array
             for (int k = 0; k < list.length ; k++)          // Copy old to new
                newList[k] = list[k];
             newList[count] = s;                             // Insert item into new
             list = newList;                                 // Make old point to new
             System.out.println ("Array just got expanded!");
         }
         finally
         {
             count++;  // Array got bigger, regardles of exception being thrown or not
         }
         System.out.print ("Input a string (or just newline to quit): ");
         s = keyboard.nextLine ();
      } 
      System.out.println ("Here are the String values you typed ... ");
      for (String str : list) System.out.println (str);
      
    }

}
