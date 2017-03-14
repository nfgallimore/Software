import java.util.*;
import java.io.*;

class q17
{
     public static void main(String args [])
     {
          Scanner input = new Scanner(new File("readme.txt"));
          int count = 0;


          while(input.hasNextLine())
          {
              System.out.println( "Input : " + input.nextLine());
              count++;
          }

          System.out.println(count + " total");
     }
}
