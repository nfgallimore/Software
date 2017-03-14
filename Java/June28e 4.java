// Output all 3-digit integers that are equal to the sum of the cube of their
// digits

class June28e
{
    public static void main (String [] args)
    { 
          // solution using one for
          for (int n = 100; n < 1000; n++ )
          {
                int h = n / 100;
                int u = n % 10;
                int t = (n/10) % 10;
               
                if (h*h*h + t*t*t + u*u*u == n)
                    System.out.println (n);
          }
          System.out.println ();


           // another solution
           for (int h = 1; h <= 9; h++)
           {
               for (int t = 0; t <= 9; t++)
               {
                    for (int u = 0; u <= 9; u++)
                    {
                           if (h*h*h + t*t*t + u*u*u == 
                                  100*h + 10*t + u)
                            System.out.println  ("" +  h + t + u);
                    }
                }
           }
     }
}                   
