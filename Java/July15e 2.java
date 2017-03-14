import java.io.*;

class July15e
{
    public static void main (String [] args)
    {
	/*String [] a = {"Now", "is the", "time", "for-each!"};

	for (String s : a) 
	{
	    System.out.println (s);
	}
	*/

	File f = new File (args[0]);
	if (! f.exists() ) System.out.println ("No such file/directory!");
	else recursivelyPrint (f, 0);
    }

    public static void recursivelyPrint (File f, int level)
    {
	for (int i = 0; i < level; i++)  System.out.print ("   ");
	System.out.println (f.getName());
	if (f.isDirectory() && f.canRead())
	{
	    for (File subF : f.listFiles())
	    {
		recursivelyPrint (subF, level+1);
	    }
	}
    }
}
