public class gcf
{
	public static void main(String[] args)
	{
		int a = 16;
		int b = 20;
		int[] factorsOfA = new int[a];
		int[] factorsOfB = new int[b];

		factorsOfA[0] = 1;
		factorsOfA[1] = a;

		for (int i = 2; i < a; i += 2)
		{
			factorsOfA[i] = a / i;
			factorsOfA[i+1] = i;
		}
		for (int i = 2; i < a; i += 2)
		{
			factorsOfB[i] = a / i;
			factorsOfB[i+1] = i;
		}
	}
}