import exp4.*;
public class bitch
{
	public static void main(String[] args)
	{
		int varX, varY;
		Calculable calc = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)")
       			.withVariable("x", varX)
                .withVariable("y", varY)
       	        .build();
       	 double result1=calc.calculate();
	}
}
