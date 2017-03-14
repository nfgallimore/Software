class July2g
{
     // roll a pair of dice until you throw a "double", which
     //  means you get the same number on both (e.g., 3 and 3)

     public static void main (String [] args)
     { 
         // Solve this using a while loop
        
         int die1 = 0;
         int die2 = 1;
  
         while ( die1 != die2)
         {
            die1 = (int) (Math.random() * 6) + 1;
            die2 = (int) (Math.random() * 6) + 1;
 
            System.out.println ("You just rolled a " + die1 +
                                  " and a " + die2);
         }
         System.out.println ("Hey, you just rolled a DOUBLE!");
    }
}
