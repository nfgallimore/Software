/*
 * SimpleDate.java
 *
 * Modified by:     <your name>, <your e-mail address>
 * TF:              <your TF's name>
 * Date modified:   <current date>
 */

class SimpleDate {
    /* instance variables */
    private int month;
    private int day;

    /* array containing the number of days in each month */
    public static final int[] DAYS_IN_MONTH = 
      {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /** constructor for SimpleDate objects */
    public SimpleDate(int m, int d) {
	month = m;
	day = d;
    }

    /* 
     * static helper method for computing the number of days in the year
     * that come before the specified month.
     */
    private static int daysBefore(int month) {
	int numDays = 0;
	
	for (int m = 1; m <= month; m++)
	    numDays = numDays + DAYS_IN_MONTH[m]; 

	return numDays;
    }

    /**
     * instance method for computing the number of days from the date
     * represented by the calling object to the date represented by the
     * other parameter.
     */
    public int daysUntil(SimpleDate otherDate) {
	int dayInYear = daysBefore(this.month) + this.day;
	int otherDayInYear = daysBefore(otherDate.month) + otherDate.day;

	return (otherDayInYear - dayInYear);
    }
}
