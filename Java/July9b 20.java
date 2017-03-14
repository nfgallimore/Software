import java.util.*;

class July9b
{
      public static void main (String [] args)
      {
          int [] iq = {126, 167, 95};
          update (iq);
          System.out.println  ( Arrays.toString (iq) ); 
      }

      static void update ( int [] a)
      {
           for (int i = 0; i < a.length; i++)
           { 
                a[i] = a[i] *2;
           }
      }
}


