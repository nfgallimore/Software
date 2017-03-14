import java.io.*;

class scores {
    
    public static void parseScores (BufferedReader in,
				    PrintWriter out) 
		  throws IOException {
	int sum = 0;

	for (int i = 0; i < 3; i++) {

	    String sscore = in.readLine();
	    int score = Integer.parseInt(sscore);
	    out.println("Score " + (i+1) + " is " + score);
	    sum += score;
	}

	out.println("Average is " + (sum/3.0));
    }

	
    

    public static void main(String[] args) throws IOException{

	BufferedReader file = new BufferedReader
				    (new FileReader(args[0]));
	PrintWriter output = new PrintWriter
				    (new FileWriter(args[1]));
	String name = new String();

	while ((name = file.readLine()) != null) {
	    output.println("Name: " + name);
	    parseScores(file, output);
	}

	file.close();
	output.close();

    }

}
