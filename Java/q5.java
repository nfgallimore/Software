class q5
{
   static int prob5(int k)
   {
       int tmp;

       if (k==1) 
       {
         System.out.println("prob5(1) is returning 0");
         return 0;
       }
       else
       {
         System.out.println("Calling prob5(" + k/2 + ")");

         tmp = 1 + prob5(k/2);

         System.out.println("Call to prob5(" + k + ") returning 1+prob5(" + k/2 + ")=" + tmp);
         return tmp;
       }
   }
   public static void main(String args[])
   {
       System.out.println("Calling prob5(16)");
       prob5(16);
      
   }
}
