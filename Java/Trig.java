import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.math.*;

public class Trig
{
	public static Triangle init() throws IOException {

		Triangle t = new Triangle();
		// Loads previous triangle data
		new File(fname()).createNewFile();
		DataInputStream in = (new DataInputStream(new FileInputStream(new File(fname()))));
		while(in.available() > 0)
			t.sides.add(in.readDouble());
		return t;
	}


	public static void main(String[] args) throws IOException {

		Triangle t = init();
		while (true)
		{
			t.print();
			Scanner kb = new Scanner(System.in);
			if (!kb.hasNextDouble()) {
				String str = kb.next();
				if (str.equals("clear")) {
					new File(strFname(fversion())).createNewFile();
					t = new Triangle();
				}
				else if (str.equals("q")) System.exit(0);
			} 
			else { 
				double d = kb.nextDouble();
				t.sides.add(d);
				if (!kb.hasNextDouble())
					if (kb.next().equals("+"))
						if (kb.hasNextDouble()) 
							t.sides.add(d + kb.nextDouble());
				new DataOutputStream(new FileOutputStream(new File(fname()),true)).writeDouble(d);
			}
		}
	}


	public static String strFname(int i) {

		return "trigdat/trig_1.1." + (i) + ".dat";
	}


	public static int fversion() throws IOException {

		int fVer = 0;
		while (new File(strFname(fVer)).exists()) fVer++;
		return fVer; 
	}


	public static String fname() throws IOException {

		return strFname(fversion()-1);
	}
}