package tsd.crypto.algoritm;

import java.nio.charset.Charset;

public class Test
{

    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");


    public static void main(String[] args)
    {
        try
        {
            System.out.println("------------------");

            String message = "This is encryption using AES!";
            String key = "1a25s8fe5dsg65ad";
            
            byte[] k = key.getBytes();
            byte[] m = message.getBytes();
            byte[] c = AES.encrypt(m, k);
            
            c = AES.encrypt(m, k);
            m = AES.decrypt(c, k);
            
            String cipherText = new String(c);
            message = new String(m);

            System.out.println("Message (m) =  " + message);
            System.out.println("Enc (m, k) = " + cipherText);
            System.out.println("Dec (c, k) = " + message);
            System.out.println("------------------");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
