/* 	Let N = 453504209. Write a program to extract a square root of 105592908 in Z*_N using brute-force search. 
	How long does it take to find the square root? (You might need to run your program multiple times and take an average.)
*/
import java.util.*;
import java.lang.Math;

public class GCD
{
	public static void main(String[] args)
	{
		int n = 453504209;
		int p = 105592908;
		elementsOfZ(n, p);
	}
	public static void elementsOfZ(int n, int p)
	{
		System.out.println(euclideanAlgorithm(n, p));
	}

}

/*
import java.io.*;
import java.util.*;

class GCD 
{
    public static void main (String args[])  // entry point from OS
    {
        int a=0, b=0, d;
        int sign_a = 1, sign_b = 1;   //  These are used to handle cases when a < 0
                                      //  and/or b < 0, as the extendedEuclid function
                                      //  assumes that a and b are nonnegative.  
        int[] ans = new int[3];

        if (args.length < 2)  {
               System.out.println("\n   Please input two arguments!\n");
               System.exit(1);
           }
        else if (args.length > 0)  {
           try {
                   a = Integer.parseInt(args[0]);
               }
           catch (NumberFormatException e) {
                 System.err.println("\n   Arguments must be integers!\n");
                 System.exit(1);
           }
           try {
                   b = Integer.parseInt(args[1]);
               }
           catch (NumberFormatException e) {
                 System.err.println("\n  Arguments must be integers!\n");
                 System.exit(1);
           }
       }  
       if ((a == 0) && (b == 0)) {
          System.out.println("\n  Oops, both arguments are zero!  No GCD of zero!\n");
          System.exit(1);
       }
       if ( a < 0 )
            {
               sign_a = -1;
               a = Math.abs(a);
            }       
       if ( b < 0 )
            {
               sign_b = -1;
               b = Math.abs(b);
            }       
       ans = ExtendedEuclid(a,b);
       System.out.println("\n   gcd(" + a*sign_a + "," + b*sign_b +") = " + ans[0]);
       System.out.print("   " + ans[0] + " = (" + sign_a*ans[1] + ") (" + sign_a*a +")");
       System.out.println(" + (" + sign_b*ans[2] + ") (" + sign_b*b + ")\n");

    }


    public static int[] ExtendedEuclid(int a, int b)
    { 
        int[] ans = new int[3];
        int q;

        if (b == 0)  { 
            ans[0] = a;
            ans[1] = 1;
            ans[2] = 0;
        }
        else
            {   
               q = a/b;
               ans = ExtendedEuclid (b, a % b);
               int temp = ans[1] - ans[2]*q;
               ans[1] = ans[2];
               ans[2] = temp;
            }

        return ans;
    }
 
}  /*  end of "main" program  */

