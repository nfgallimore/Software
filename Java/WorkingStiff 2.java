class WorkingStiff extends Person
{
    protected String employer;
    protected double yearlySalary;
    protected int yearsOfEducation;
    
    public WorkingStiff(String t, String fN, String lN, String a, String c,
			String s, String z, boolean sex, int birthYear,
			String emp, double yS, int yOE)
    {
	super(t, fN, lN, a, c, s, z, sex, birthYear);
	employer = emp;
	yearlySalary = yS;
	yearsOfEducation = yOE;
    }

}
