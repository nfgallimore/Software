//  Movie2.java
/** Illustration of simple inheritance
  * based on example in "On to Java", by P. H. Winston
  * @author  Henry H. Leitner
  * @version Last modified on July 14, 2013
  */

public class Movie2  extends Attraction
{
    // First, define instance variables:
    protected int 
           script = 5, 
           acting = 5, 
        directing = 5;

    Movie2 ( )  
    {  
       System.out.println ("Inside zero-parameter Movie constructor!");
    }
      
    Movie2 (String movieName)
    {
       System.out.println ("Inside one-parameter Movie constructor!");
       name = movieName;
    }

    Movie2 (String movieName, int s, int a, int d, int m)
    { 
        System.out.println ("Inside 5-parameter Movie constructor!");
        name      = movieName; 
        script    = s; 
        acting    = a; 
        directing = d; 
        timeInMinutes   = m;
    }
 
    Movie2 (String name, int s, int a, int d)
    {
      System.out.println ("Inside 4-parameter Movie constructor!");
      script = s;
      acting = a;
      directing = d;
      this.name = name;
    }

   // Define an overloaded public method named rating:
   public int rating (double scaleFactor) 
   {
       return (int) (scaleFactor * 
                     (script + acting + directing));
   }  
   
   public int rating()
   {
     return script + acting + directing;
   }  
   
   public boolean equals (Object o)
   {
       if (o instanceof Movie2)
       {
           Movie2 other = (Movie2) o;
           return other.getName().equalsIgnoreCase (this.getName());
       }
       else return false;
   }
}

