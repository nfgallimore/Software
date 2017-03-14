#include <stdio.h>
#include <cs50.h>

int main()
{
    ///c - f, or f - c
    int choice, c, f;
    printf("\n1. Celcius to Farenheight \n2. Farenheight to Celcius \n \nSelect one of the following options: ");
    choice = GetInt();
    
        // if choice is celcius to farenheight
        if (choice == 1)
        {
            printf("What is the temperature in Celcius? ");
            c = GetInt();
            f = c * 9 / 05 + 32;
            printf("The temperature in farenheight is %d \n \n",f);
            return 0;
        }       
        
        // if choice is farenheight to celcius
        if (choice == 2)
        {
            printf("What is the temperature in Farenheight? ");
            f = GetInt();
            c = (f - 32) * 5 / 9;
            printf("The temperature in Celcius is %d \n \n",c);
            return 0;
        }
}
