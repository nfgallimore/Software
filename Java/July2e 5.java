class July2e
{
    public static void main (String [] args)
    {

           System.out.println (  randomInt (-11, 10) );

           System.out.println (  randomInt (4, 32) );
    }



    static int randomInt (int a, int b)
    {
         return   (int)  (Math.random() * (b-a+1))  + a;
    }
 }
