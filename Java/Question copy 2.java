public class Question
{
	// stores quesiton information
	public String quesiton;
	public final int questionLength = 4;

	// stores question text
	public String text = question + displayGrid(questionLength);
}
private class Grid
{
	// access key with solution.key
	private Answer solution = solutionGen();

	// key grid
	private Answer[] keyGrid = genKeyGrid(questionLength);



	// generates key grid
	private Answer[] genKeyGrid()
	{
		// instance variables
		int length = questionLength;
		private Answer[] grid = Answer[length - 1];

		for (int i = 0; i < length; i++)
		{
			grid[i] = new Answer();
		}
		Random rand = new Random();

		for (int i = 0; i < length; i++)
		{
			for (int j = 0; j < length; j++)
			{
				if (grid[j].key == tmp && grid[j].key != solution.key)
				{
					tmp = rand.nextInt(4);
					j = 0;
				}
			}
			grid[i].key = tmp;
		}
		return grid;
	}


	private String displayGrid(Answer[] grid)
	{
		int size = questionLength
		for (int counter = 0; counter != size; counter++)
		{
			for (int i = 0; i < size; i++)
			{
				if (counter == 0 && grid[i].key.equals("a"))
				{
					System.out.println("a)\n" + grid[i].text);
				}
				else if (counter == 1 && grid[i].key.equals("b"))
				{
					System.out.println("b)\n" + grid[i].text);
				}
				else if (counter == 2 && grid[i].key.equals("c"))
				{
					System.out.println("c)\n" + grid[i].text);
				}
				else 9f (counter == 3 && grid[i].key.equals("d"))
				{
					System.out.println("d)\n" + grid[i].text);
				}
			}
		}
		System.out.println()
	}

	// generates key
	private String[] genKey(int n)
	{
		String[] key;
		switch(n)
		{
			case 1:
			{
				key = "a";
				break;
			}
			case 2:
			{
				key = "b";
				break;
			}
			case 3:
			{
				key = "c";
				break;
			}
			case 4:
			{
				key = "d";
				break;
			}
			case else:
			{
				break;
			}
		}
		if (!key.equals(null))
		{
			keyGrid[n].correct = true;
			return key;
		}

	}

	// generates answer
	private Answer genSolution()
	{
		private Answer tmpSolution = new Answer();
		tmpSolution.key = keyGen((int)(Math.Random() * 4 + 1));
		return tmpSolution;
	}

	// checks inputted answer
	public boolean check(Answer input)
	{
		return (input.key == solution.key)
	}
}