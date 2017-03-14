// Fib1.java

/**  
 *  This program calculates and prints out the first 20 Fibonacci numbers
 *  STRATEGY IS TO USE A TEMPORARY VARIABLE
 *
 *  @author   Dr. Henry H. Leitner
 *  @version  Last_modified June 28, 2013
 */


class Fib1 
{
    public static void main (String [] args)  
    {   
        int highest  = 1;          // highest Fibonacci yet calculated 
        int nextHighest = 0;       //  the next-to last one 

        System.out.println ("HERE ARE THE FIRST 20 FIBONACCI NUMBERS");

        // initialize and print the first two numbers
         
        System.out.printf ("f0  %9d\n", nextHighest );
        System.out.printf ("f1  %9d\n", highest );

        //  now compute the remaining 18 

	    for (int count = 2; count < 20; count++)
	    {
               int tempSum  = highest   + nextHighest;
               nextHighest = highest ;
               highest  = tempSum;
                // having used the former value, we can change it!
               System.out.printf ("f%-2d %9d\n", count, highest );
        }
    }
}
