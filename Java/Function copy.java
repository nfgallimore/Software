public class Function
{
	private double input = 0;

	public Function(double x)
	{
		input = x;
	}
	public double output()
	{
		return compute(input);
	}
	private double compute(double in)
	{
		return in;
	}
	double x = 2;
	double newX = 2.001;
	double f = 10;
	double fPrime = -7;
	double g = -7;
	double gPrime = 8;

	double deltaX = newX - x;

	public double methodOne
	{
		method1(x, deltaX, f, fPrime, g, gPrime);
	}
	public double methodTwo
	{
		return method2(x, deltaX, f, fPrime, g, gPrime);
	}
	private static double method1(double x, double deltaX, double f, double fPrime, double g, double gPrime)
	{
		return (fPrime * deltaX + f) * (gPrime * deltaX + g);
	}
	private static double method2(double x, double deltaX, double f, double fPrime, double g, double gPrime)
	{
		return (f * g + (f * gPrime + fPrime * g) * deltaX);
	}
	private static double fixedAbscissaProduct(double x, double f, double fPrime, double g, double gPrime)
	{
		return (f * gPrime + fPrime * gPrime);
	}
		private static double fixedAbscissaQuotient(double x, double f, double fPrime, double g, double gPrime)
	{
		return (fPrime * g - f * gPrime)
	}
	public static double function
}
}