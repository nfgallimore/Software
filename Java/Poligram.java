package tsd.crypto.algoritm;

public class Poligram {
	
	private static String[] subst;
	private static String acceptedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
	private static boolean firstTime = true;
	
	private static char charAtIndex(int i)
	{
		return acceptedChars.charAt(i);
	}
	
	private static int indexOfChar(char c)
	{
		return acceptedChars.indexOf(c);
	}
	//initializeaza matricea de substituie. Va fi sub forma de vector iar indicii ii prelucram pentru a mapa vectorul in matrice
	private static void initTabel(){
		subst = new String[729];
		//generam toate combinatiile de 2 caractere posibile
		for(int i = 0;i<27;i++){
			for(int j = 0;j<27;j++){
				subst[i*27+j] = charAtIndex(i) +""+charAtIndex(j);
			}
		}
		//dam valorile peste cap dupa o formula aiurea cu ajutorul vectorului permut ce indica indicii de interschimbare
		//il dam peste cam de mai multe ori pt a arata cat mai random
		String aux;
		int[] permut = {0,31,1,29,2,27,3,20,4,15};
		int count = 1;
		while(count <10){
			for(int i = 0;i<subst.length-32;i+=16){
				for(int j = 0;j<permut.length;j+=2)
				{
					aux = new String(subst[(i+permut[j]*count)%subst.length]);
					subst[(i+permut[j]*count)%subst.length] =new String(subst[((subst.length-i-32)+permut[j+1]*count)%subst.length]);
					subst[((subst.length-i-32)+permut[j+1]*count)%subst.length] =aux;
					
				}
			}
			count++;
		}

	}
	
	
	public static String encrypt(String plain)
	{
		if(firstTime) {initTabel();firstTime = false;}
		plain = plain.toUpperCase();
		char[] clar = plain.toCharArray();
		int x,y;
		String tmp = "";
		for(int i=0;i<clar.length;i+=2)
		{
			//calculam indicii din tabel pt a afla grupul corepsunzator
			x = indexOfChar(clar[i]);
			y = indexOfChar(clar[i+1]);
			tmp += subst[x*27+y];
			
		}
		
		return tmp;
		
		
	}
	
	public static String decrypt(String cipher)
	{
		if(firstTime) {initTabel();firstTime = false;}
		cipher = cipher.toUpperCase();
		
		char[] crypt = cipher.toCharArray();
		String tmp="";
		String plain = "";
		int x,y,pos = 0;
		for(int i=0;i<crypt.length;i+=2)
		{
			//luam doua cate doua
			tmp = crypt[i]+""+crypt[i+1];
			//cautam in matricea de substitutie(vector vazut ca matrice mai exact)
			for(int j=0;j<subst.length;j++)
			{
				if(subst[j].equals(tmp))
				{
					pos = j;
					break;
				}
			}
			//calculam linia si coloana in matrice
			x = pos/27;
			y = pos%27;
			//grupul descifrat
			plain += charAtIndex(x)+""+charAtIndex(y);
		}
		
		return plain;
	}
}
