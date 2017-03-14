/* 
 * ArrayReverse.java
 * 
 * Computer Science E-119, Harvard University
 */

/**
 * Demonstrates how recursion can be used to reverse the contents
 * of an array of integers.
 */
public class ArrayReverse {
    /**
     * reverse - reverses the elements from arr[left] to arr[right] in
     * the specified array by reversing the two "edges" (arr[left] and
     * arr[right]) and then making a recursive call to reverse the
     * elements from arr[left+1] to arr[right-1].  Returns if there
     * are 0 or 1 elements left (i.e., if left >= right).
     */
    public static void reverse(int[] arr, int left, int right) {
	if (left >= right)
	    return;         // base case

	// Swap the "edges": arr[left] and arr[right].
	int tmp = arr[left];
	arr[left] = arr[right];
	arr[right] = tmp;

        // Print the current contents of the array.
	System.out.print("\t\t");
	printArray(arr);

	// Reverse the "middle" -- i.e., everything but the edges.
	reverse(arr, left + 1, right - 1);
    }

    /**
     * reverseArray - this "wrapper" method calls reverse() with the
     * correct initial parameters to reverse the entire array.
     */
    public static void reverseArray(int[] arr) {
	if (arr == null)
	    return;
	reverse(arr, 0, arr.length - 1);
    }

    /**
     * printArray - prints the contents of the specified array.
     */
    private static void printArray(int[] arr) {
	System.out.print("  [ ");
	for (int i = 0; i < arr.length; i++)
		System.out.print(arr[i] + " ");
	System.out.println("]");
    }

    public static void main(String[] args) {
	int[] values = {25, 7, 11, 6, 35, 21, 19, 6};

	System.out.print("\n*** original:\t");
	printArray(values);
	System.out.println();

	reverseArray(values);

	System.out.print("\n*** final:\t");
	printArray(values);
	System.out.println();
    }
}
