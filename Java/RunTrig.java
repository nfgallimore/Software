import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.math.*;

class RuntTrig
{
	public static void main(String[] args) throws IOException {

		Trig tr = new Trig();
		Triangle t = tr.init();
		
		while (true) {
			t.print();
			Scanner kb = new Scanner(System.in);
			if (!kb.hasNextDouble()) {
				String str = kb.next();
				if (str.equals("clear")) {
					new File(tr.strFname(tr.fversion())).createNewFile();
					t = new Triangle();
				}
				else if (str.equals("q")) System.exit(0);
			} 
			else { 
				double d = kb.nextDouble();
				t.sides.add(d);
				new DataOutputStream(new FileOutputStream(new File(tr.fname()),true)).writeDouble(d);
			}
		}
	}

}