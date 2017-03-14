import java.util.*;

public class Lec
{
	public static void main(String[] args)
	{
		double a = 1000;
		double n = 6;
		double k = 365;

		double r = 1 + .006 / k;

		Scanner kb = new Scanner(System.in);

		
		//System.out.println(1000.00 * (Math.pow(1.10, 7)));



		double y = 1000000.00;

		// y /= 700,000 * Math.pow(1.015, n);
		n = Math.log(1.42857) / Math.log(1.015);
		System.out.println(n);
	}
}