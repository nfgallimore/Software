import java.util.*;

class July8
{
     public static void main (String [] args)
     {
          Scanner keyboard = new Scanner (System.in);
          System.out.print ("Type a string & I'll determine if it's a palindrome: ");
          String s = keyboard.nextLine();

          s = s.toUpperCase();

          int left = 0;
          int right = s.length() - 1;

          while (left < right)
          {
              char lc = s.charAt (left);
              char rc = s.charAt (right);
  
              if (lc < 'A' || lc > 'Z') 
              {  
                 left++ ;
                 continue;
              }
              // we know that lc is an upper-case alphabetic
              // now consider rc
              if (rc < 'A' || rc > 'Z') 
              {  
                 right--;
                 continue;
              }

              // both lc and rc contain an uppercase alphabetic character
              if ( lc != rc )
              {
                   System.out.println ("Sorry, NOT a palindrome!");
                   return;
              }
              left++;
              right--;
          }
          System.out.println ("YES, we do have a palindrome!");
      }
}
