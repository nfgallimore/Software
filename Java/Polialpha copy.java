package tsd.crypto.algoritm;

public class Polialpha {
	
	private static String acceptedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
	
	private static char charAtIndex(int i)
	{
		return acceptedChars.charAt(i);
	}
	
	private static int indexOfChar(char c)
	{
		return acceptedChars.indexOf(c);
	}
	
	public static String encrypt(String key,String plainTxt)
	{
		int chIndex;
		String cipherText = "";
		char[] plain = plainTxt.toCharArray();
		char[] k = key.toCharArray();
		int kIndex = 0;
		for(int i = 0;i<plain.length;i++)
		{
			chIndex = (indexOfChar(plain[i]) + indexOfChar(k[kIndex]))%27;
			cipherText += charAtIndex(chIndex);
			kIndex = (kIndex+1)%k.length;
		}
		return cipherText;
	}
	
	public static String decrypt(String key,String cipherTxt)
	{
		int chIndex;
		String cipherText = "";
		char[] cipher = cipherTxt.toCharArray();
		char[] k = key.toCharArray();
		int kIndex = 0;
		for(int i = 0;i<cipher.length;i++)
		{
			chIndex = (indexOfChar(cipher[i]) - indexOfChar(k[kIndex]));
			if(chIndex<0) chIndex +=27;
			cipherText += charAtIndex(chIndex);
			kIndex = (kIndex+1)%k.length;
		}
		return cipherText;
	}
}
