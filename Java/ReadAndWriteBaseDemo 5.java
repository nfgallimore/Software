/** ReadAndWriteBaseDemo.java
 * Allows the user to input, from the keyboard, positive ints
 *  using any "base" from binary through decimal (2-10),
 *  and permits the output of positive ints using these bases
 *
 *  @author:  Dr. Henry H. Leitner
 *  @version: Last Modified July 10, 2013
 */

import java.util.*;
import java.io.*;

class ReadAndWriteBaseDemo
{
    static final int BINARY = 2;
    static final int OCTAL = 8;
    static final int DECIMAL = 10;


    /** readBase allows the user to input an int value
     *  from the keyboard.  The value is typed as a 
     *  sequence of chars in some base between binary
     *  and decimal.  Returns the equivalent _decimal_ int.
     *  Does not deal with negative numbers at all.
     */
    static int readBase (int base) 
    {
        char ch;
        Scanner keyboard = new Scanner(System.in);
        keyboard.useDelimiter("");

        do                                      // Ignore white space and
        {
            ch = keyboard.next().charAt(0); 	// non-base chars
        }
        while (  (ch < '0' ||  ch -'0' >= base ));
        int n = 0;
        do
         {       // This loop "builds" an int
            n = (base * n) + (ch -'0');
            ch = keyboard.next().charAt(0);
         } while ((ch >= '0') && (ch < '0' + base));
		
		// Now toss the rest of the line (may be empty):

         if (ch != '\n') keyboard.nextLine();
         return n;
    }

    /** writeBase is a recursive routine to display
     *  a _decimal_ integer arg 'n' as a sequence of
     *   chars, in some base 'base', between binary and decimal.
     */
    static void writeBase (int n, int base)
    {
        if (n >= base)  writeBase( n / base, base);
      
        //  print a single digit character between '0' and '9' 
        char [] digits = {'0', '1', '2', '3', '4', '5', '6', '7',
                              '8', '9'};
        System.out.print (digits [n % base] );
     }

    public static void main (String[] args)
    {
       int foo;
       System.out.print ("Input a positive DECIMAL integer: ");
       foo = readBase (DECIMAL);
       System.out.print("In DECIMAL, that equals: ");
       writeBase(foo, DECIMAL);  System.out.println();
       System.out.print("In OCTAL, that equals: ");
       writeBase(foo, OCTAL);    System.out.println();
       System.out.print("In BINARY, that equals: ");
       writeBase(foo, BINARY);   System.out.println();
       System.out.println();
       System.out.print ("Input a positive BINARY integer: ");
       foo = readBase (BINARY);
       System.out.print("In DECIMAL, that equals: ");
       writeBase(foo, DECIMAL);  System.out.println();
       System.out.print("In OCTAL, that equals: ");
       writeBase(foo, OCTAL);    System.out.println();
       System.out.print("In BINARY, that equals: ");
       writeBase(foo, BINARY);   System.out.println();
       System.out.println("\n\nThat's all, folks!");
    }
}
