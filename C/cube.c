/* Return.c */

#include <stdio.h>

// function prototype
int cube(int a);

int main(void)
{
	int x = 2;
	printf("x is now %d\n", x);
	printf("Cubing...\n");
	x = cube (x);
	printf("Cubed!\n");
	printf("x i snow %d\n", x);
	return 0;
}

/**
 * Cubes argument.
 */

int cube(int a)
{
	return a * a * a;
}