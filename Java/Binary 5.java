import java.io.*;

class Binary
{
   public static void main (String [] args) throws IOException
   {
         DataOutputStream binaryFile = 
                  new DataOutputStream(new FileOutputStream ("binary.dat"));
         PrintWriter textFile = new PrintWriter ("text.dat");
         for (int i = 1; i <= 1000; i++)
         {
             double n = Math.random ();
             binaryFile.writeDouble (n);
             textFile.println (n);
         }
         textFile.close();
         binaryFile.close();
   }
}
