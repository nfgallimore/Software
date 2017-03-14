enum Grade { A, B, C, D, F}

public class Enumerated
{
  public static void main(String [] args)
  {  
     Grade myGrade = Grade.A;
     if (myGrade == Grade.F) System.out.println("You fail");
     else System.out.println("You pass");

     System.out.println("myGrade: " + myGrade);
     System.out.println("myGrade.ordinal(): " +
			 myGrade.ordinal());
     System.out.println("myGrade.name(): " +
			 myGrade.name());
     Grade [] x = Grade.values();
     for (int i = 0; i < x.length; i++) 
       System.out.println (x[i]);
     System.out.println (x[0] == Grade.A);
     System.out.println ("A" == myGrade.name());
  }
}

