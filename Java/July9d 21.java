import java.util.*;

class July9d
{
    public static void main (String [] args)
    {
	int [] a = {4, 7, 11, -9, 42};
	int [] b = {-2, 5, 103, 12};

	int [] c = merge (a, b);

	System.out.println ( Arrays.toString (c) );

        reverse (c);
        System.out.println ( "In reverse, array = " + Arrays.toString (c) );
    }

    static int [] merge (int [] array1, int [] array2)
    {
	int [] answer = new int [array1.length + array2.length];
        // first copy all of the elements of array1 into the answer	
        for (int i = 0; i < array1.length; i++)
	{
	    answer [i] = array1[i];
	}
        // now copy all of the elements of array2 into the answer
        for (int i = 0; i < array2.length; i++)
        {
             answer [ i + array1.length ]    =  array2[i];
        }
        return answer;
    }

    static void reverse (int [] list)
    {
          for (int i = 0; i < list.length/2; i++)
          {
                int save = list[i];
                
                list [i] =  list [ list.length-i-1];
                list [ list.length-i-1] = save;
          }
     }

}


