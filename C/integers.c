/**
 * integers.c
 * Nicholas Gallimore <nick@virtualtheologies.com>
 * 
 * Converts fractions into decimels!
 *
 */
 
#include <stdio.h>
#include <cs50.h>

int main(void)
{
    //initiate floats
    float n,d,p;
    
	// prompts user to enter non-negative numerator
	{
		printf("Please enter a non-negative numerator: ");
	}
	
	// sets n to users numerator
	n = GetFloat();
    
	// if number is negative have user try again
	if (n <= 0)
	{
		printf("\nThat number is incorrect, please try again: ");
		n = GetFloat();
	}
   
   	// prompts user to enter positive denominator
	{
		printf("Please enter a positive denominator: ");
	}
	
	// sets d to users denominator
	d = GetFloat();
    
	// if number is positive have user try again
	if (d <= 0)
	{
		printf("\nThat number is incorrect, please try again: ");
		d = GetFloat();
	}
    
    //calculates percentage
    {
	    p = n/d*100;
	}
	
	{
		printf("%4.2f%% \n", p);
    }
}
