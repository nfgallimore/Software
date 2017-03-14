#include <cs50.h>
#include <stdio.h>

int main(int argc, string argv[])
{
    /* user input */
    float i;
    int coin = 0;
    do
    {   
        printf("How much change is owed?\n");
		i = GetFloat();
	}
    while (i < 0);
    
    /* Quarters */
    for (int q = 0; i > .2499; q++)
    {
        i = i - .25;
        coin++;
    }
    
    /* Dimes */
    if (i < .25)
    {
    	for (int d = 0; i > .0999; d++)
    	{
        	i = i - .10;
       		coin++;
   		} 
    }
    
    /* Nickels */
    if (i < .10)
    {
    for (int n = 0; i > .0499; n++)
    	{
        	i = i - .05;
        	coin++;
    	}   
    }
    
    /* Pennies */
    if (i < .05)
    {
    	for (int p = 0; i > .0099; p++)
    	{
        	i = i - .01;
        	coin++;
    	}
    }
    printf("\nTotal amount of coins used: %d\n", coin);
 	return 0;
}