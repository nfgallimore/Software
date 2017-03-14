#include <stdio.h>

void swap (int* a, int* b)
{
	printf("%p, %d, %p, %d\n\n", a, *a, b, *b);

	int tmp = *a;
	printf("%p, %d, %p, %d, %d\n\n", a, *a, b, *b, tmp);

	*a = *b;
		printf("%p, %d, %p, %d, %d\n\n", a, *a, b, *b, tmp);

	*b = tmp;
	printf("%p, %d, %p, %d, %d\n\n", a, *a, b, *b, tmp);
}
int main (void)
{
	int x = 1;
	int y = 2;
	swap(&x, &y);
	printf("%d %d", x, y);
}