public class MindtheGap
{
	public static void main(String[] args)
	{
		int[] array = {1, 3, 6, 7, 12};
		System.out.println(minGap(array));

	}
	static int minGap(int[] arr)
	{
		if (arr.length < 2)
			return 0;
		int i = 0;
		int gap = arr[i+1] - arr[i];

		for (int length = arr.length; i < length; i++)
			if (i < length - 1 && arr[i+1] - arr[i] < gap)
				gap = arr[i+1] - arr[i];
		return gap;
	}
}