import java.io.*;
import java.util.*;

class July15f
{
    public static void main (String [] args) throws FileNotFoundException
    {

	// open weather.txt file and output the change
	// in temperature on consecutive days
	
	Scanner input = new Scanner ( new File ( args [0] ));


	double prev = input.nextDouble();
	while (input.hasNextDouble())
	{
	    double next = input.nextDouble();
	    System.out.println (prev + " to " + next +
		                 ", change = " + (next-prev));
	    prev = next;
	}
    }
}


