#include <stdio.h>
#include <stdbool.h>
 
#define N 9
#define P 295075153U
 
static unsigned long outputs[] =
{
    154012769, 230061617, 1248063, 226721520, 108518981, 158467836, 96934365, 153344519, 66017501
};
 
void generate_values(unsigned long x, unsigned long y, long n)
{
    for (int i=0; i<n; i++) {
        x = (2*x + 5) % P;
        y = (3*y + 7) % P;
        
        printf("Output #%d: %lu*%lu = %lu\n", i+1, x, y, (x*y) % P);
    }
}
 
// Modular division by two
unsigned long modular_div2(unsigned long x)
{
    if (x % 2 == 0) return x/2;
    else return (x + P)/2;
}
 
// Use Euclid’s algorithm to find the multiplicative inverse of a modulo P.
unsigned long modular_inverse(long a)
{
    long x = 0, lastx = 1, b = P, t;
    while (b != 0)
    {
        long quotient = a / b;
        t = b; b = a % b; a = t;
        t = x; x = lastx - quotient*x; lastx = t;
    }
    while (lastx < 0) lastx += P;
    return lastx % P;
}
 
// General modular division: x/y modulo P
unsigned long modular_div(unsigned long x, unsigned long y)
{
    return (x * modular_inverse(y)) % P;
}
 
int main()
{
    unsigned long y_values[N];
    unsigned long putative_x_values[N];
    
    for (unsigned long y=0; y<P; y++)
    {
        if (y % 10000000 == 0)
        {
            printf("%.2f%% done\n", (double) (y+1) / P * 100);
        }
        
        // Initialise y_values and putative_x_values
        unsigned long yy = y;
        bool matched = true;
        for (int n=0; n<N; n++)
        {
            yy = (3*yy + 7) % P;
            y_values[n] = yy;
            putative_x_values[n] = modular_div(outputs[n], yy);
            if (n > 0 && (putative_x_values[n-1] * 2 + 5) % P != putative_x_values[n])
            {
                matched = false;
                break;
            }
        }
        
        if (matched)
        {
            // We have a match!
            unsigned long x = modular_div2(putative_x_values[0] - 5) % P;
            printf("x=%lu, y=%lu\n", x, y);
            
            generate_values(x, y, 15);
            return 0;
        }
    }
    
    // No match
    return 1;
}
