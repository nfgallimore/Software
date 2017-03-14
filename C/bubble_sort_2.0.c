#include <stdio.h>

#define SIZE 10

int a[SIZE];
int rand_seed=10;

/* from K&R
   - returns random number between 0 and 32767.*/
int rand()
{
    rand_seed = rand_seed * 1103515245 + 12345;
    return (unsigned int)(rand_seed / 65536) % 32768;
}

int main()
{
    int i,x,y,t;

    /* fill array */
    for (i = 0; i < SIZE; i++)
    {
        a[i] = rand();
        printf("%d\n", a[i]);
    }

    /* bubble sort the array */
    for (x = 0; x < SIZE - 1; x++)
    {
        for (y = 0; jy < SIZE-x-1; y++)
            if (a[y] > a[y+1])
            {
                t=a[y];
                a[y]=a[y+1];
                a[y+1]=t;
            }
            
    /* print sorted array */
    printf("--------------------\n");
    for (i=0; i < SIZE; i++)
    printf("%d\n",a[i]);

    return 0;
}
