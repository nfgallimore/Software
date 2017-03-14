public class Problem
{
	// stores quesiton information
	public String text;

	public final int LENGTH = 4;

	public Grid grid = new Grid();

	// stores question text
	public void print()
	{
		System.out.println(text +"\n"+ grid.displayGrid() + "\n\n" + grid.sol());
	}
	public void create()
	{
		grid.init();
	}
	public void add(String[] answers)
	{
		grid.add(answers);
	}
	public class Grid
	{
		public String sol()
		{
			return ""; //(int)this.solution.letter + "\n" + this.solution.value;
		}
		public void add(String[] answers)
		{
			this.solution.value = answers[0];
			for (int i = 0; i < LENGTH; i++)
			{
				if (!this.solution.value.equals(this.table[i].value))
				{
					this.table[i].value = answers[i];
				}
			}
			genSolution();
			genKeyGrid();
		}
		// access key with solution.key
		private Answer solution = new Answer();
		private Answer[] table;

		private boolean genDone = false;

		// generates key grid
		private void genKeyGrid()
		{
			java.util.Random rand = new java.util.Random();

			for (int i = 0; i < LENGTH; i++)
			{
				int tmp = rand.nextInt();
				for (int j = 0; j < LENGTH; j++)
				{
					if (this.table[j].key == tmp && this.table[j].key != this.solution.key)
					{
						tmp = rand.nextInt(LENGTH);
						j = 0;
					}
				}
				this.table[i].key = tmp;
			}
			genDone = true;
		}

		private void initGrid()
		{
			// instance variables
			this.table = new Answer[LENGTH];

			for (int i = 0; i < LENGTH; i++)
			{
				this.table[i] = new Answer();
				this.table[i].letter = (char)('a' + i);
			}
		}
		private String gridHelper(int index, int key, char letter)
		{
			if (index == key && solved == false)
			{
				solved = true;
				if (this.solution.key == index) System.out.println("YES");
				else System.out.println("NO");
				return this.solution.value;
			}
			String str = "";
			for (int i = 0; i < LENGTH; i++)
			{
				str += ("\nSOLUTION VALUE: " + this.table[i].value + " INDEX:" + index + " KEY:" + key);
			}
			return str;
			// for (int i = 0; i < LENGTH; i++)
			// {
			// 	if (this.table[i].key == index)
			// 	{
			// 		return this.table[i].value;
			// 	}
			// }
			// return null;
		}
		private String display()
		{
			ArrayList<String
		}
		private boolean solved = false;
		
		private String displayGrid()
		{
			if (!genDone) 
			{
				return null;
			}
			String str = "";
			int key = this.solution.key;
			int index = 0;

			while (index != LENGTH)
			{
				switch (index)
				{
					case 0:
					{
						str += "\na) " + gridHelper(index, key, 'a');
						index++;
						break;
					}					
					case 1:
					{
						str += "\nb) " + gridHelper(index, key, 'b');
						index++;
						break;
					}
					case 2:
					{
						str += "\nc) " + gridHelper(index, key, 'c');
						index++;
						break;
					}
					case 3:
					{
						str += "\nd) " + gridHelper(index, key, 'd');
						index++;
						break;
					}
				}
			}
			return str;
		}

		// generates key
		private char genKey(int key)
		{
			//System.out.println("Key: " + key);
			return (char)(key - 1 + 'a');
		}

		// generates answer
		private void genSolution()
		{
			this.solution.letter = genKey(((int)(Math.random() * LENGTH + 1)));
		}

		// checks inputted answer
		private boolean check(Answer input)
		{
			return (input.key == solution.key);
		}
		private void init()
		{
			initGrid();
			this.genSolution();
			this.genKeyGrid();
		}
	}
}