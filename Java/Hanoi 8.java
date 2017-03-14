import java.util.*;
public class Hanoi
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner (System.in);
		System.out.println("How many disks do you want to play with? ");
		int numDisks = keyboard.nextInt();
		towers('s', 'd', 'i', numDisks);
	}
	static void towers ( char origin, char dest, char intermed, int numDisks)
	{
		if (numDisks == 1) // base case
		{	
			System.out.println ("Move disk #1 from "
								+ origin + " to " + dest );
			return;
		}
		else
		{
			towers(origin, intermed, dest, numDisks - 1);
			System.out.println("Move disk #"
								+ numDisks + " from " + 
								origin + " to " + dest);
			towers(intermed, dest, origin, numDisks - 1);
		}
	}
}
