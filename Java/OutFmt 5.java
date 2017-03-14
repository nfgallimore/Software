package utils;

import java.text.*;

/* provide basic number formatting for printing: 
 *	field widths, leading 0's, etc. 
 *
 *	Chris Morrison, for CSCI E-50A and E-50B. 
 *  Last modified:  9/2/99
 *
 *  Uses non-localized format patterns - default locale is good enuf...
 *  Assumes monospaced fonts. Behavior w/ prop'l fonts unknown.
 *	Default format is:  two decimal places, with trailing 0's, 
 *						minimum number of integer places, including - sign
 *
 *  USAGE:  to format a floating-pt number using curently set number of
 *          decimal places:  OutFmt.fmt(num);
 *
 *          to change the number of decimal places to use:
 *          OutFmt.setDecimalPlaces(decPlcs); 
 *          
 *          to format int n in cols columns:
 *          OutFmt.RJust (OutFmt.fmt(n), cols);  // or LJust()
 *
 *			to format double n in cols total columns (including decimal
 *          point), with decPlcs digits to the right of the decimal point:
 *
 *          OutFmt.setDecimalPlaces(decPlcs);  // if current setting n.g.
 *          OutFmt.RJust(OutFmt.fmt(n), cols);
 *
 *  NOTE: RJust(), LJust() work on arbitrary Strings, not just numeric Strs.
 *
 *  BUGS:  -rounding default can cause weirdities:
 *			with 2 decimal places set, 1234.999 formats as 1235.0
 *		   		I DON'T YET HAVE A FIX FOR THIS
 *		   - Doesn't handle int args well. (not that it's s'posed to)
 *         - RJust weird?  unit3/Gold.java used:
 *
 *	NOTES: 6/27: I'm not sure why this works.  format(double) is a 
 *	NumberFormat method, which doesn't refer to a format pattern. Yet
 *	this code clearly does use the pattern.  (DecimalFormat doesnt'
 *	overload format(double)).  Code was based on code examples in
 *  Chan, Lee, Kramer, "The Java Class Libraries, 2nd ed, vol 1):
 *		class DecimalFormat (pgs 626ff)
 *		class NumberFormat  (pg 1196)
 *	Current Final Theory:  there's something missing in the doc,
 *   OR I'm missing someething in the doc
 *
 *	----------------------------------------------------------------------
 *  LEFT TO BE DONE:
 *	- static setLeadingZeroes (on/off);  	// for existing format 
 *										// pattern (0's to left of .)
 *
 *  - for fmt(double) if # dec. places is 0, we get 123.   Better as 123  ?
 */



/** provide basic number formatting for printing: 
 *	field widths, leading 0's, etc. 
 *
 *  <p>USAGE:  to format int n in cols columns:
 *  <p>        OutFmt.RJust (OutFmt.fmt(n), cols);  // or LJust()
 *
 *	<p>		to format double n in cols total columns (including decimal
 *          point), with decPlcs digits to the right of the decimal point:
 *
 *    <p>      OutFmt.setDecimalPlaces(decPlcs); // if current setting n.g. 
 *          OutFmt.RJust(OutFmt.fmt(n), cols);
 *
 *  <p>NOTE: RJust(), LJust() work on arbitrary Strings, not just numeric Strs.
 *
 *	<p>Default format is:  two decimal places, with trailing 0's, 
 *						minimum number of integer places, including - sign
 */

public class OutFmt {
	private static final String defaultFormatPattern = "#0.00";
	private static DecimalFormat df 
						= new DecimalFormat (defaultFormatPattern);


	//=============================================================
	/** return double n as a String, using number of decimal places
	*   currently set (default 2, changed w/ setDecimalPlaces())
	*
	*	@param n;  the double to be formatted (ints get promoted to double,
	*				so don't come out like ints.
	*   @see #setDecimalPlaces(int)
	*/
	public static String fmt (double n) {
		return df.format(n);			// this is NumberFormat.format()
	}


	//=============================================================
	/** return int n as a String 
	*
	*	@param n;  the int to be formatted
	*/
	public static String fmt (int n) {
		return (String.valueOf(n));
	}


	//=============================================================
	/** set the number of decimal places to be printed, w/ trailing 0's	
	 *  in subsequent printing operations.	The new format will remain 
	 *  in force till explicitly changed.
	 *
	 *	@param howMany:  the number of decimal places to be set
	 */
	public static void setDecimalPlaces (int howMany) {
		String formatPattern = df.toPattern ();

		int dotIndex = formatPattern.indexOf ('.');	
		
		// get "###." part:
		formatPattern = formatPattern.substring (0, dotIndex + 1);	
		for (int i = 0; i < howMany; i++) {		// add 0's at end
			formatPattern += "0";
		}
		df.applyPattern (formatPattern);		// set new format
	}

	//=============================================================
	/** return str as a String, LEFT-justified in col total columns;
	 *	will not truncate Strings whose length is > col.
	 */
	public static String LJust (String str, int col) {
		StringBuffer sBuf = new StringBuffer (str);

		for (int p = sBuf.length(); p < col; p++)
			sBuf.append (' ');

		return sBuf.toString();
	}


	//=============================================================
	/** return str as a String, RIGHT-justified in col total columns;
	 *	will not truncate Strings whose length is > col.
	 */
	public static String RJust (String str, int col) {
		StringBuffer sBuf = new StringBuffer (str);

		for (int p = sBuf.length(); p < col; p++)
			sBuf.insert (0, ' ');

		return sBuf.toString();
	}
} // end of class
