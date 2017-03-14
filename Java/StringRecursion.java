public class StringRecursion {
    public static String removeVowels(String str) {
	if (str == null || str.equals("")) {
	    return str;
	}

	String removedFromRest = removeVowels(str.substring(1));

	char first = str.toLowerCase().charAt(0);
	if (first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u')
	    return removedFromRest;
	else 
	    return first + removedFromRest;
    }
}