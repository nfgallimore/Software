class July8b
{
    public static void main (String [] args)
    {
	Duck d = new Duck (18);
	d.quack();

        Movie argo = new Movie(9, 10, 8);
        Movie lincoln = new Movie(8, 9, 7);
        Movie silverLinings = new Movie();

        System.out.println ("rating of Argo = " +  argo.rating());
        System.out.println ("rating of Lincoln = " +  lincoln.rating());
        System.out.println ("rating of Silver Linings Playbook = " +  
                            silverLinings.rating());
    }
}
