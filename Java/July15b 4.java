import java.util.*;
class July15b
{
	public static void main(String [] args)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Type some integers, one per line.. ");

		int sum = 0;
		int count = 0;
		System.out.print ("Please type an int: ");
		while (keyboard.hasNextInt())
		{
			int n = keyboard.nextInt();
			sum += n;
			count++;
			System.out.print ("Please type an int: ");
		}
		System.out.println("You typed " + count + " integer values");
		System.out.println("and their sum = " + sum);

	}
}