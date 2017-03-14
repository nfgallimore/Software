import java.util.Scanner;

class June28
{
    public static void main (String [] args)
    {
        Scanner keyboard = new Scanner (System.in);

        System.out.print ("Please input an integer: ");
        
        int n = keyboard.nextInt();

        System.out.print ("Now type a double: ");

        double d = keyboard.nextDouble();
   
        System.out.println ("The sum of the 2 #'s equals ... " +  (n+d) );
    }
}

