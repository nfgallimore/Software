import utils.SavitchIn;

class DateSubtracter {
    public static void main (String[] args) {
	SimpleDate july4 = new SimpleDate(7, 4);
	SimpleDate christmas = new SimpleDate(12, 25);
	int numDays = july4.daysUntil(christmas);

	System.out.println("The number of days from July 4 to Christmas is " +
	  numDays);
	if (numDays != 174)
	    System.out.println("  *** ERROR: we should get 174 days");

	SimpleDate valentines = new SimpleDate(2, 14);
	SimpleDate st_patricks = new SimpleDate(3, 17);
	numDays = valentines.daysUntil(st_patricks);

	System.out.println("The number of days from Valentine's Day " +
	  "to St. Patrick's Day is " + numDays);
	if (numDays != 31)
	    System.out.println("  *** ERROR: we should get 31 days");
    }
}
