import java.io.*;
import java.util.*;

class July15d
{
    public static void main (String [] args) throws IOException
    {
	File f = new File (args[0]);

	if (f.exists() && f.canRead())
	{
	    System.out.println ("length of file = " + f.length());
	    Scanner input = new Scanner (f);
	    input.useDelimiter("[^a-zA-Z']+");
	    while (input.hasNext() )
	    {
		String text = input.next();
		System.out.println (text);
	    }
	}
    }
}
