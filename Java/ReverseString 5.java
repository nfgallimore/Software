import java.util.*;

class ReverseString
{
	public static void reverse (Scanner input)
	{
		if (input.hasNextLine())
		{
			String line = input.nextLine();
			reverse(input);
			System.out.println(line);
		}
	}
	public static void main(String[] args)
	{
		System.out.println("Type a series of strings and I will reverse them for you!");
		Scanner keyboard = new Scanner (System.in);
		reverse(keyboard);
	}
}