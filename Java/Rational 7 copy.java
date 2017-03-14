class Rational
{
    // first defining the instance variabls
    private int numerator;
    private int denominator;

    // Step 2: define constructors
    public Rational (int a, int b)
    {
	/* Euclid's Algorithm for Greatest Common Divisor
           Given m, and n ...
           Step 1:  Divide first number by second number
                     In other words, divide m by n
           Step 2:  If there's a remainder of 0, all done
                     because n is the GCD
           Step 3:  Otherwise replace m by the value of n
                     replace n by the remainder of previous
                     division.
                    Now GO BACK TO STEP #1
        */
          int  m = a;
          int  n = b;
          
          int remainder = m % n;
          while ( remainder != 0)
          { 
                m = n;
                n = remainder;
                remainder = m % n;
          }

         numerator = a / n;
         denominator = b / n;
    }

    public Rational (int n)
    {
	numerator = n;
	denominator = 1;
    }

    public Rational (Rational r)
    {
	numerator = r.numerator;
	denominator = r.denominator;
    }

    public Rational ()
    {
	numerator = 0;
	denominator = 1;
    }

    public Rational mulRat (Rational multiplier)
    {
	int top = this.numerator * multiplier.numerator;
	int bottom = this.denominator * multiplier.denominator;
	return new Rational (top, bottom);
    }

    public Rational addRat (Rational addend)
    {
	int bottom = denominator * addend.denominator;
	int top = numerator * addend.denominator +
	          denominator * addend.numerator;
	return new Rational (top, bottom);
    }

    public String toString()
    {
         return numerator + " / " + denominator;
    }
}
