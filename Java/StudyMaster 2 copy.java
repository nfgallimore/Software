import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class StudyMaster
{
	public static void main(String argv[])
	{
        HashMap<String,ArrayList<String>> myHashMap = new HashMap<String,ArrayList<String>>();
        ArrayList<String> correctAnswer = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext())
        {
            String question = scan.nextLine();
            String line = scan.nextLine();
            ArrayList<String> answers = new ArrayList<String>();
            
            while(line.length() != 0)
            { 
                answers.add(line);
                line = scan.nextLine();
            }
            myHashMap.put(question,answers);  // we put all the question with the answers in HashMap
        }
    }
}