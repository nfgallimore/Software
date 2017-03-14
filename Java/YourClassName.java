import java.util.Scanner 
import java.util.ArrayList;
import java.util.HashMap;
 
class YourClassName
{
    public static void main(String args[])
    {
        // you read the file
         
        /* Now i believe that HashMap will be a really good choice because we have a connection
         * in Questions and the answers and the HashMap will have Strings and an ArrayList of Strings
         * in key What is the Capital of British Columbia the values will be ["Victoria ","Coquitlam","Richmond",...]
         */
 
        HashMap<String,ArrayList<String>> myHashMap = new HashMap<String,ArrayList<String>>();
        ArrayList<String> correctAnswer = new ArrayList<String>(); // will keeps the correct answer of each question
        
        while (scan.hasNext())
        {
            String question = scan.nextLine();
            String line = scan.nextLine();
            ArrayList<String> answers = new ArrayList<String>();
            
            while(line.length != 0)
            { 
                answers.add(line);
                line = scan.nextLine();
            }
            myHashMap.put(question,answers);  // we put all the question with the answers in HashMap
        }
 
 
        /* when all this ends that will mean we have read all the file and we have save all the data in
         * a HashMap. Keys are the Questions and values of the HasHap are the answers 
         * now if you know the correct answers of each questions save them in correctAnswer ArrayList just to have them saved ;)
         * and now do whatever you want to do with HashMap
         */
    }
}