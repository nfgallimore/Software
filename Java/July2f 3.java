import java.util.Scanner;

class July2f
{
     public static void main (String [] args)
     {  
        // Estimate the value of PI by throwing chalk!
        int hits = 0;
       
        Scanner keyboard = new Scanner (System.in);
        System.out.print ("How many pieces of chalk should I throw? ");
        int n = keyboard.nextInt();

        for (int i = 1; i <= n; i++)
        {
            // throw one piece of chalk
            double x = Math.random();
            double y = Math.random();

            if (x*x + y*y <= 1.0) hits++ ;
        }
        System.out.println ("An approximation for PI is ... "  +
                                (double) hits / n  * 4 );
     }
}
