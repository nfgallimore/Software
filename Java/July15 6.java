class July15
{
    public static void main (String [] args)
    { 
	Rational [] ratList = {
	                        new Rational (3, 7),
			        new Rational (5),
			        new Rational (9, 12),
			        new Rational ()
	};

	for (Rational r : ratList) System.out.println (r);
    }
}
