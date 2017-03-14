import java.util.*;

class array {

    public static void main(String[] args) {

	// generate integer array containing the integers one through ten
	// copy array one at a time into a vector

	int[] array = new int[10];
	Vector vector = new Vector();

	// fill array
	for (int i = 0; i < array.length; i++) {

	    array[i] = i + 1;

	}

	// copy to vector
	for (int i = 0; i < array.length; i++) {

	    Integer num = new Integer(array[i]);
	    vector.add(num);

	}

	System.out.println(array);
	System.out.println(vector);
	System.out.println("Vector has " + vector.size() + "elements");
    }
}

	
