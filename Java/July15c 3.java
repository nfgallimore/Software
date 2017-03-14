import java.util.*;

class July15c
{
    public static void reverse (Scanner input)
    {
	if (input.hasNextLine())
	{
	    String line = input.nextLine();
	    reverse (input);
	    System.out.println (line);
	}
    }

    public static void main (String [] args)
    {
	Scanner keyboard = new Scanner (System.in);
	reverse (keyboard);
    }
}
