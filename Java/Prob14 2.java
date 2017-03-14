class Fee
{
    public Fee()
    {  System.out.println("Fee"); }

}

class Fie extends Fee
{
    public Fie()
    {  System.out.println("Fie"); }
}

class Foe extends Fie
{
    public Foe()
    {  System.out.println("Foe"); }
   
    public Foe (int x)
    {  System.out.println("Foe " + x); }
}

class Fum extends Foe
{
    public Fum()
    {
	super(3);
	System.out.println("Fum");
    }
}

public class Prob14
{
    public static void main(String[] args)
    {
	Fum foo = new Fum();
	System.out.println("Hello");
    }
}
    
