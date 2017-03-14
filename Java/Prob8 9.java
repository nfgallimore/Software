//Do not modify this class
class Prob13
{
    public static void main (String [] args)
    {
	Circle c1 = new Circle();
	Circle c2 = new Circle();
	Rectangle r1 = new Rectangle();
	Rectangle r2 = new Rectangle();
	c1.radius = 3.0;
	c2.radius = 5.2;
	r1.width = 3.0;
	r1.height = 4.2;
	r2.width = 10.0;
	r2.height = 6.6;
	System.out.println("Circle #1 has an area of " +
				c1.area());
	System.out.println(" and circle #2 has an area of "
				+ c2.area());
	System.out.println("\nRectangle #1 has an area of "
				+ r1.area());
	System.out.println(" and rectangle #2 has an area of "
				+ r2.area());
    }
}
