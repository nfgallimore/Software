public class Person
{
    protected String title;
    protected String firstName, lastName;
    protected String streetAddress;
    protected String city, state, zipCode;
    protected boolean isMale;
    protected int yearOfBirth;
    
    public Person() 
    {  
    }

    public Person(String t, String fN, String lN, 
		  String sA, String c, String s, String zC,
		  boolean iM, int yOB)
    {
	title = t; 
	firstName = fN;  
	lastName = lN;
	streetAddress = sA; 
	city = c; 
	state = s; 
	zipCode = zC;
	isMale = iM; 
	yearOfBirth = yOB;
    }
    
    public String toString()
    {
	return title + " " + firstName + " " + lastName + "\n" + 
	    streetAddress + "\n" +
	    city + ", " + state + "  " + zipCode;
  }

    public abstract void printLetter();   // gets overridden in subclasses
}

