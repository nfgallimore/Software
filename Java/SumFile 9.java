/*  File SumFile.java 
 *  Reads a text file of integers, prints the sum of the ints found.
 *  @author  Dr. Henry H. Leitner
 *  @version Last Modified:  July 10, 2013
*/

import java.io.*;
import java.util.*;

class SumFile 
{
   public static void main (String [] args) 
   {
      int      count = 0;      // the number of numbers input
      int      sum = 0;        // the sum of the numbers

      try 
      {
          Scanner keyboard = new Scanner(System.in);
          System.out.print ("Type the name of an input file containing integers: ");
          String fname = keyboard.nextLine();
          Scanner in = new Scanner (new File(fname));

          while (in.hasNextInt() ) 
          {
             int x = in.nextInt();
             count++;
             System.out.println ("Value #" + count + " is " + x);
             sum += x;
          } 
          System.out.println("Sum of " + count + " numbers is " + sum);
	  in.close();
       }   
       catch (FileNotFoundException e)
       {
          System.out.print ("Trouble opening or reading the file");
          System.out.println (" ... maybe it was misspelled???");
          e.printStackTrace();
       }
   }
}
