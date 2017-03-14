import java.util.*;

class June28b
{
     public static void main (String [] args)
     {
            final int N = 5;

            double sum = 0.0;
 
            Scanner keyboard = new Scanner (System.in);
          
            for (int i = 1; i <= N; i++)
            {
                 System.out.print ("Please input one grade point average: ");
                 sum += keyboard.nextDouble();
            }
  
            System.out.println ("Your average GPA = "  +   (sum / N)  );
    }
}
