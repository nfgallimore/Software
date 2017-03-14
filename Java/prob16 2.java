class prob16
{
    public static void main(String [] args)
    {
	writeStars(0);
	writeStars(1);
	writeStars(5);
    }

    public static void writeStars(int n)
    {
	if (n==0) { System.out.println(); }
	else
	{
	    System.out.print("*");
	    writeStars(n - 1);
	}
    }
}
