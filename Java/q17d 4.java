import java.util.*;
import java.io.*;

class q17d
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


          while(input.hasNextInt())
          {
              System.out.println( "Input : " + input.nextInt());
              count++;
          }

          System.out.println(count + " total");
     }
}
