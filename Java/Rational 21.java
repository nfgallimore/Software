class Rational
{
    // first, define instance variables
    private int numerator;
    private int denominator;

    // second, define constructors

    public Rational (int a, int b)
    {
       // will construct a Rational number in LOWEST TERMS
       // by using Euclid's algorithm for GCD
       int m = a;
       int n = b;
       // you write the rest of the code!!!
       int remainder = m % n;
       while (remainder != 0)
       {
          m = n;
          n = remainder;
          remainder = m % n;
       }
      // we just found n is the GCD 
       numerator =  a / n; 
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

    // third, define instance methods
    public Rational mulRat (Rational multiplier)
    {
        int top = this.numerator * multiplier.numerator;
        int bottom = this.denominator * multiplier.denominator;
        return new Rational (top, bottom);
    }

    public Rational addRat (Rational addend)
    {
        int top = numerator * addend.denominator +
                  denominator * addend.numerator;
        int bottom = denominator * addend.denominator;
        return new Rational (top, bottom);
    }
    // last, but not least: define toString

    public String toString ()
    {
        return numerator + " / " + denominator;
    }    
}    


