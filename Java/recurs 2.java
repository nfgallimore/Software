class recurs {

    public static void printReverse(String s) {

	if (s.length() == 1) {
	    System.out.print(s.charAt(0));
	} else {
	    printReverse(s.substring(1));
	    System.out.print(s.charAt(0));
	}

    }


	
    
    public static void main (String[] args) {

	String s = "abcde";

	printReverse(s);
	System.out.println("\n" + s);

    }

}
