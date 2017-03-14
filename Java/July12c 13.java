import java.util.Scanner;

class July12c
{
    static int fib (int n)
    {
	if (n == 0) return 0;
	else if (n == 1) return 1;
	else return fib(n-1) + fib(n-2);
    }

    static int fact (int n)
    {
	if (n == 0) return 1;
	else return n * fact(n-1);
    }

    static double power (double x, int n)
    {
	if (n == 0) return 1.0;
	else if (n > 0) return x * power (x, n-1);
	else return 1.0 / power (x, -n);
    }

    public static void main (String [] args)
    {
	//System.out.println ( fact (-17) );
	for (int i = 3; i <= 12; i++)
	{
	    System.out.println (i + " factorial = " + fact (i));
	}
	System.out.println ( power (7, 3) );
	System.out.println ( power (7, -3) );

	Scanner keyboard = new Scanner (System.in);
	System.out.print ("Calculate which Fibonacci #? ");
	int x = keyboard.nextInt();
	System.out.println ("Fib answer = "  +  fib (x) );
    }

}
