/* buggy4.c v2 */

#include <stdio.h>

// function prototype
int increment(int);

int main(void)
{
	int x = 1;
	printf("x is now %d\n", x);
	printf("Incrementing...\n");
	x = increment (x);
	printf("Incremented!\n");
	printf("x is now %d\n", x);
	return 0;
}

/**
 * Tries to increment x.
 */
int increment(int number)
{
	return number + 1;
}