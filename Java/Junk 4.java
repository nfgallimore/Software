class Junk
{
  public static void main (String args [])
  {
      final int SIZE = 3;
      Person [] p;
      p = new Person [SIZE];
      
      p[0] = new Student();
      p[1] = new WorkingStiff("Ms.", "Karel", "Robo", "3 Cyber Lane",
			      "Yucko", "CA", "92344", true, 1955,
			      "Foul Industries, Inc.", 45600.23, 18);
      
      System.out.println(p[1] );
      System.out.println(p[0]);
  }
}
