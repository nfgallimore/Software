// Given an array of String values, write a method that removes
//  all instances of a particular String value from the list

import java.util.*;

class ArrayListDemo
{
    public static void main (String [] args)
    {
        String [] data1 = {"a", "b", "c", "a", "b", "c", "a", "b", "c"};
        test (data1, "a");     	// returns {"b", "c", "b", "c", "b", "c"}
        test (data1, "b");      // returns {"a", "c", "a", "c", "a", "c"}
        test (data1, "c");      // returns {"a", "b", "a", "b", "a", "b"}

        String [] data2 = {"a", "a", "a", "a", "a"};
        test (data2, "b");    	// returns {"a", "a", "a", "a", "a"}
	test (data2, "a");      // should return with an empty arrayList 

        String [] data3 = {}; 
        test (data3, "a"); 	// returns {}
    }

    public static void test (String [] data, String target)
    {
       ArrayList<String> list = new ArrayList<String>();
       for (String word : data) list.add(word);
       System.out.println("Testing " + list);
       System.out.println("   removing " + target);
       removeAll (list, target);
       System.out.println("   result = " + list);
       System.out.println();
    }

    static void removeAll (ArrayList<String> a, String s)
    {
	for (int i = a.size() -1; i >= 0; i--)
	{
	    if (a.get(i).equals(s)) 
	    {
		a.remove(i);
	    }
	}
    }
}
