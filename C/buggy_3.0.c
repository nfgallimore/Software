/* buggy3.c */

#include <stdio.h>

// function prototype
void swap(int a, int b);

int main(void)
{
	int x = 1;
	int y = 2;
	printf("x is %d", x);
	printf("y is %d", y);
	printf("Swapping...\n");
	swap (x, y);
	printf("Swapped!");
	printf("x is %d", x);
	printf("y is %d", y);

return 0;

}

/**
 * Swap arguments' values.
 */
void swap (int a, int b)
{
	/* introduces new bug when line 29 deleted */
	int tmp = a; 
	a = b;
	b = tmp;
}
