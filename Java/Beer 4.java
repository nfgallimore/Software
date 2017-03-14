// Beer.java   

/**  
 *  Illustrates a for-loop that counts DOWN
 *      and prints the lyrics to a rather annoying song
 *  Slightly ungrammatical in the 1-bottle(s) case...
 *  In final println:  (n-1) can't be n-1... compile error!
 *      an operator precedence problem.
 *
 * @author  Dr. Henry H. Leitner
 * @version Last_Modified June 9, 2013
 */

class Beer 
{
    public static void main (String [] args) 
    {
        for (int n = 99; n >= 1; n--)  
        {
            System.out.println (n + " bottles of beer on the wall.");
            System.out.println (n + " bottles of beer!");
            System.out.println 
                ("If one of those bottles should happen to fall, ");
            System.out.println 
                ("There'd be " + (n-1) + " bottles of beer on the wall!\n");
        }     // end of "for loop" statement
    }
}
