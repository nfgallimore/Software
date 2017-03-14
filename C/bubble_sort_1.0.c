#include <stdio.h>
#include <stdbool.h>

void bubbleSort (int array[], int length);
void printArray (int array[]);

int main(void)
{
	// initializes an unsorted array
	int array[] = {4, 15, 16, 50, 8, 23, 42, 108};
	
	// prints unsorted array
	printf("\nUnsorted array:\n");
	printArray(array);

	// size of array
	int length = (sizeof(array) / (sizeof(array[0])) );
	
	// sorts an array using bubble sort
	bubbleSort(&array[0], length);
}

// bubblesort, runs in O(n^2)
void bubbleSort (int* pointerArray, int length)
{
	// counts passes
	int pass = 0;

	// counts number of swaps
	int swap = 0;

	// make new array
	int array[length-1];

	// fills in the values
	for (int i = 0; i < length; i++)
	{
		array[i] = pointerArray[i];
	}
	
	// determines if array is sorted
	bool sorted;
	do
	{
		// assume array is sorted
		sorted = true;;

		// keeps track of swaps per pass
		int swapsPer = 0;
	
		// for every element in array
		for (int i = 0; i < length; i++)
		{
			// test to see if current element is greater than the next element
			if (array[i] > array[i+1])
			{
				// if true, then swap the elements
				int tmp = array[i];
				array[i] = array[i+1];
				array[i+1] = tmp;

				// acknowledge that array isn't fully sorted
				sorted = false;

				// update number of swaps
				swap++;
				swapsPer++;
			}
		}

		// update pass count
		pass++;

		// prints the number of passes thus far
		printf("Pass # %d made %d swaps:\n", pass, swapsPer);

		// prints array
		printArray(array);

	// repeat this while the array is unsorted	
	} while (sorted == false);

	// prints number of swaps
	printf("Total number of swaps: %d\n", swap);
	printf("Total number of passes: %d\n", pass);
}
void selectionSort(int array[])

// function to print ever element in an array
void printArray(int array[])
{
	// length of array
	int length = 8;

	// prints every element in array
	for (int i = 0; i < length; i++)
	{
		printf("%d ", array[i]);
	}
	printf("\n\n");
}
