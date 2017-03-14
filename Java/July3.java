class July3
{
    public static void main (String [] args)
    {
	for (int i = 0; i <= 127; i++)
	{
	    System.out.println (i + "\t\t" + (char) i );
	}

        for (char ch = 'W'; ch <= 'c'; ch++ )
        {
             System.out.println (  (int) ch + "\t\t" + ch);
        }

        for (int i = 0; i <= 127; i++)
        {
             System.out.printf ("%5o  %5d %4x  %3c \n", i, i, i, i);
        }
        System.out.println ("Hi, I am a BETA ... \u03B2 !");
    }
}
