import java.util.ArrayList;
import java.math.*;

public class Triangle
{
	public double a,b,c,alpha,beta,gamma;

	public ArrayList angles = new ArrayList();
	public ArrayList sides = new ArrayList<Double>();

	public void print()
	{

		System.out.println(sides);
		System.out.println("Add more to list or type 'clear', 'q' :   ");
	}
}
