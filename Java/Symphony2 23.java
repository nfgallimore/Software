//  Symphony2.java
/** Illustration of simple inheritance
  *  based on an example in "On to Java", by P. H. Winston
  * @author Henry H. leitner
  * @version Last modified on July 11, 2013
  */

public class Symphony2 extends Attraction
{ 
    // 3 instance variable declarations follow
    private int music, playing, conducting;

    // 3 class variable (constant) declarations follow
    private static final int wMusic = 4, 
                             wPlaying = 4, 
                             wConducting = 2;

    // 1 instance method follows
    public int rating () 
    {
         return wMusic*music + 
                wPlaying*playing + 
                wConducting*conducting;
    }
    
    public double rating (double scaleFactor) 
    {
         return (wMusic*music + 
                  wPlaying*playing + 
                  wConducting*conducting) * scaleFactor;
    }

   // 2 constructor methods follow
   public Symphony2 (String symphonyName)  
   {
       name = symphonyName;
       music = playing = conducting = 5;
       System.out.println ("Calling one-parameter Symphony constructor!");
   }

   public Symphony2 (String symphonyName, int m, int p, int c)
   {
       System.out.println ("Calling four-parameter Symphony constructor!");
       name = symphonyName; 
       music = m; 
       playing = p; 
       conducting = c;
   } 

   public String getCategory() {return "Symphony";}

}

