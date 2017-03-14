class July2
{
    public static void main (String [] args)
    {
          tableOfSqrts (10, 21, 1);
          tableOfSqrts (5, 33, 3);
        
    }

    static void tableOfSqrts (int start, int finish, int increment)
    {
          System.out.println ("Here is a table of Square Roots\n");
          for (int n = start; n <= finish; n += increment)
          {
              System.out.println (n + "\t\t" + Math.sqrt (n) );
          }
          System.out.println ("\n\n");
    }
}
