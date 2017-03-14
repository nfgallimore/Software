import java.util.*;
import java.io.*;

class q17b
{
     public static void main(String args [])
     {
          Scanner input = null;
          
          try
          {
             input = new Scanner(new File("readme.txt"));
          }
          catch(FileNotFoundException e)
          {
             System.out.println(e);         
          }

          int count = 0;


          while(input.hasNextLine())
          {
              System.out.println( "Input : " + input.nextLine());
              count++;
          }

          System.out.println(count + " total");
     }
}
