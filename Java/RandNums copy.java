package utils;

import java.util.Random;

/* RANDNUMS.JAVA - a simple random-number utility to adapt java
 * Random.methods() to e50a/b C++ random number interface.
 *
 *      Chris Morrison, Dr. Henry H. Leitner
 *      Last Modified September 1, 1999
 */

/** A simple random-number utility, created to shorten random-number
 *  function calls in programs
 */
public class RandNums {
	static private Random rnj = new Random();

	/** "seed" the Random Number Generator (RNJ) with 'arg'.  You can
	 *  make the RNJ repeatedly produce _exactly_ the same sequence 
	 *  of pseudorandom numbers by seeding and reseeding it with the
	 *  same value - useful for debugging!
	 */
	public static void setSeed (long seed) {
		rnj.setSeed(seed);
	}

	/** return random number n, 
	 *  such that 0 <= n < Integer.MAX_VALUE.
	 */
	public static int rand() {
		return (int)(Math.floor(rnj.nextDouble() * Integer.MAX_VALUE));
	}
	

	/** return random number n, such that  low <= n <= high, 
	 *  even if low > high.
	 */
	public static int randIntRange(int low, int high) {

		if (low > high) {		//swap if low > high
			int temp = low;
			low = high;
			high = temp;
		}
		return rand() % (high - low + 1) + low;
	}


	/* ...for testing the class only */
	/* public */   		// keep javadoc from showing in pub interface
	static void main(String[] args) {
		int low = -5;
		int high = 5;
		long seed = 5432;
		
		System.out.println("setting seed to " + seed);
		RandNums.setSeed(seed);
		for (int i = 0; i < 20; i++) {
			System.out.println ("here's a rand #: " + rand());
			System.out.println ("and one >= " + low
					+ " and <= " + high + ": " 
					+ randIntRange(low, high));
			System.out.println();
		}
		System.out.println("resetting seed to " + seed);
		RandNums.setSeed(seed);
		for (int i = 0; i < 20; i++) {
			System.out.println ("here's a rand #: " + rand());
			System.out.println ("and one >= " + low
					+ " and <= " + high + ": " 
					+ randIntRange(low, high));
			System.out.println();
		}
	}
}
