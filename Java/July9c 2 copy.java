class July9c
{
  public static void main (String [] foobar)
  {
     int n = rint (5, 12);
     int [] harvard = new int [n];
     int [] yale = new int [n];

     // initialize the two arrays of basketball scores
     for (int i = 0; i < harvard.length; i++)
     {
         harvard [i] = rint (40, 100);
           yale [i] = rint (38, 95);

         System.out.println ("In game #" + (i+1) + " Harvard: " +
                               harvard[i] + ", Yale: " + yale[i] );
     }
     System.out.println ("Avg number of points scored by Harvard = " + 
                            average (harvard) );
     System.out.println ("Avg number of points scored by Yale = " + 
                            average (yale) );
     System.out.println ("Most points scored by Harvard = "  +  maxPts (harvard));
     System.out.println ("Most points scored by Yale = "  +  maxPts (yale));
   }

   static int maxPts ( int [] team)
   {
       int largest = -1;
       for (int i = 0; i < team.length; i++)
       {
             if  (team [i] > largest) largest = team[i];
       }
       return largest;
   }


   static double average ( int [] team)
   {
       int sum = 0;
       for (int i = 0; i < team.length; i++)
       {
           sum += team [i];
       }
       return  (double) sum / team.length;
   }


   static int rint (int a, int b)
   {
      return (int) (Math.random() * (b-a+1)) + a;
   }
}
