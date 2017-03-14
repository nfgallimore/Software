// DemonstrateInheritance.java
/** Illustrates how class hierarchies naturally reflect
  * subclass-superclass relations among classes
  * Based on example in "On to Java", by P. H. Winston
  * @author Henry H. Leitner
  * @version Last modified by HH Leitner on July 12, 2013
  */
  

public class DemonstrateInheritance 
{
    public static void main (String [] args)
    {
         Attraction [] attractions = { new Movie2 ("Bedtime for Bonzo", 5,6, 3, 93),
                                       new Movie2 ("Lord of the Ringdings" ),
                                       new Symphony2 ("Beethoven's Nth", 7, 2, 2),
                                       new Symphony2 ("Leitner's Lost Symphony" )
                                     };

         // Attraction a = new Attraction();   // is this legal or not?
         System.out.println (attractions[0]);
         int sum = 0;
         for (int counter = 0; counter < attractions.length; ++counter)
         { 
             sum += attractions[counter].rating(); 
             System.out.println(attractions[counter].getName() + " is a " +
                     attractions[counter].getClass().getName());
         }
         System.out.print ("The average rating of the " + attractions.length);
         System.out.println (" attractions is ... " + sum/attractions.length);
      }
}
