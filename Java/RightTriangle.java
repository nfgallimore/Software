import java.math.*;
public class RightTriangle
{
	public static double alpha = 61;
	public static double beta = 54;
	public static double gamma;
	public static double a;
	public static double b = 24;
	public static double c;

	public static void main(String[] args)
	{

		// a/cos(alpha) = b/cos(beta) = c/cos(gamma);
		// a/cos(61) = 24/cos(54) = c/cos(gamma);
		
		a();
		b();
		c();
	}
	public static double a()
	{
		a = (b/cos(beta)) * cos(alpha);
		System.out.println(a);
		return a;
	}
	public static double b()
	{
		b = (a/cos(alpha)) * cos(beta);
		System.out.println(b);	
		return b;
	}
	public static double c()
	{
		c = (b/cos(beta)) * cos(gamma);
		System.out.println(c);	
		return c;
	}
	public static double cos(double x)
	{
		return Math.cos(x);
	}
	public static double sin(double x)
	{
		return Math.sin(x);
	}

}