class July2d
{
  
    static void foobar (int a, double d)
    {
         System.out.println ("Hello!");
    }

    static void foobar (double d, int a)
    {
         System.out.println ("World!");
    }

    public static void main (String [] args)
    {

           foobar (3, 17.2);
           foobar (3.14, 999);

           foobar (4, 5);
    }
}


