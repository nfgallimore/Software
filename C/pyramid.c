/*********************************************************
 ** mario.c                                             **
 ** Nicholas Gallimore <nick@virtualtheologies.com>     **
 **                                                     **
 ** Builds a pyramid for Mario                          **
 **                                                     **
 *********************************************************/

#include <stdio.h>
#include <cs50.h>

int main (void)
{
    
    /* intilizes variables */
    int hash,base,space;
    hash = 0;
    
    /* prompts for user input */
    printf("\n How tall is your pyramid? ");
    int height = GetInt();
    
    /* value must not be larger than 23 */
    do
    {
        printf("\nThat number is greater than 23, please retry. ");
        height = GetInt();
    }
    while ((height > 23) && (height < 0));
        
    for (space=base-hash; space<=0; space++)
        for(height=0;height < space;hash++)
            printf(" ");
            
	while (height<1);
	
    for (hash = 1; hash <= height; hash++)
	{
        printf("\n");
        
        for(base = 0; line < hash; line++)
        {
            printf(" ");
        }
        for(line = 0; line < space; line++)
        {
            printf("#");
        }
	}

}
