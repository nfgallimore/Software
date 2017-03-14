public class Problem
{
	// stores quesiton information
	public String text;

	public final int LENGTH = 4;

	public Grid grid = new Grid();

	// stores question text
	public String print = text + grid.displayGrid();

	public class Grid
	{
		// access key with solution.key
		private Answer solution;
		private Answer[] table;

		private boolean genDone = false;

		// generates key grid
		private void genKeyGrid()
		{
			// instance variables
			int length = LENGTH;
			this.table = new Answer[length - 1];

			for (int i = 0; i < length; i++)
			{
				if (!this.table[i].letter.equals(this.solution.letter))
				{
					this.table[i] = new Answer();
				}
			}
			java.util.Random rand = new java.util.Random();

			for (int i = 0; i < length; i++)
			{
				int tmp = rand.nextInt();
				for (int j = 0; j < length; j++)
				{
					if (this.table[j].key == tmp && this.table[j].key != this.solution.key)
					{
						tmp = rand.nextInt(4);
						j = 0;
					}
				}
				this.table[i].key = tmp;
			}
			genDone = true;
		}


		private String displayGrid()
		{
			if (!genDone) 
			{
				return null;
			}

			int size = LENGTH;
			String str = null;

			for (int counter = 0; counter != size; counter++)
			{
				for (int i = 0; i < size; i++)
				{
					if (counter == 0 && this.table[i].letter.equals("a"))
					{
						str += "a)\n" + this.table[i].letter;
					}
					else if (counter == 1 && this.table[i].letter.equals("b"))
					{
						str += "b)\n" + this.table[i].letter;
					}
					else if (counter == 2 && this.table[i].letter.equals("c"))
					{
						str += "c)\n" + this.table[i].letter;
					}
					else if (counter == 3 && this.table[i].letter.equals("d"))
					{
						str += "d)\n" + this.table[i].letter;
					}
				}
			}
			return str;
		}

		// generates key
		private String genKey(int key)
		{
			String letter = null;
			switch(key)
			{
				case 1:
				{
					letter = "a";
					break;
				}
				case 2:
				{
					letter = "b";
					break;
				}
				case 3:
				{
					letter = "c";
					break;
				}
				case 4:
				{
					letter = "d";
					break;
				}
			}
			return letter;
		}

		// generates answer
		private void genSolution()
		{
			this.solution.letter = genKey((int)(new java.util.Random().nextInt() * 4 + 1));
		}

		// checks inputted answer
		private boolean check(Answer input)
		{
			return (input.key == solution.key);
		}
		public void create()
		{
			this.genSolution();
			this.genKeyGrid();
		}
	}
}