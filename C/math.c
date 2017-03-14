#include <stdio.h>
#include <math.h>
#include <cs50.h>
int main()
{
	int x = GetInt();
    int result;
    int f = 0;
    int function = f * x;
    function = 5 * x - 2;
    
	function = 5 * x - 2;
	result = function*(4*x)+ 2*function*(x);
	printf("x = %d, result = %d function = %d\n", x, result, function);
	int a = 30 * x + 6;
	int b = 30 * x - 6;
	int c = 30 * x - 13;
	int d = 60 * x - 6;
	int e = 59 * x - 6;
	printf("A = %d, B = %d, C = %d, D = %d, E = %d\n",a,b,c,d,e);
return 0;
}
