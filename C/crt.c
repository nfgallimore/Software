#include <stdio.h>
#include <stdlib.h>

typedef struct remainder { int divisor; int residual; } remainder;

int main ( void )
{
    
    void getData ( remainder * arr, int const * const num );

    int cRT ( remainder * arr, int const * const num );

    int num;

    remainder * arr = NULL;

    /* Begin Infinite Loop */

    for (  ;  ;  )
    {

        /* Get amount of data to compute */

        printf ("Enter the quantity of data to be computed ");

        scanf ("%i", &num);

        /* Check to see if we should exit */

        if ( num <= 0 )
        {

            return 0;

        }

        /* Allocate Memory */

        arr = (remainder*)calloc (num, sizeof(remainder));

        /* Get Actual Data */

        getData(arr, &num);

        /* Check to see if data is sane */

        /* Sanity check goes here; assume all data to be sane for now */

        /* Solve for the original value and print solution */

        printf ("\nThe solution is %i.\n\n", cRT(arr, &num));

        free(arr);

    }

}

void getData ( remainder * arr, int const * const num )
{

    register int i;

    for ( i = 0; i < *num; i++ )
    {

        printf ("\nEnter divisor and residual for Chinese Remainder Theorem ");

        scanf ("%i %i", &arr[i].divisor, &arr[i].residual);

    }

}

int cRT ( remainder * arr, int const * const num )
{

    register int i, add = arr->divisor, result = arr->residual;

    int lcm ( int a, int b );

    for ( i = 1 ; i < *num ; add = lcm(add, arr[i++].divisor) )
    {

        for (  ; result % arr[i].divisor != arr[i].residual ; result += add );

    }

    return result;

}

int lcm ( int a, int b )
{

    int gcd ( int a, int b );

    return a * b / gcd ( a , b );

}

int gcd ( register int a, register int b )
{

    register int temp;

    while (b != 0)
    {

        temp = b;

        b = a % b;

        a = temp;

    }

    return a;

    /* Recursive Algorithm */
    /* return ( b == 0 ) ? a : gcd ( b, a % b ); */

}