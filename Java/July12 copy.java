class July12
{
    public static void main (String [] args)
    {
       float [] [] payScaleTable = {
	                             {10.5f, 12.0f, 14.5f, 16.75f, 18.0f},
				     {20.5f, 22.25f, 24.0f},
				     {34.0f, 36.5f},
				     {50.0f}
                                   };
       // Print the pay difference between the lowest and highest
       //  steps for each grade level in payScaleTable
       float diff;
       for (int i = 0; i < payScaleTable.length; i++)
       {
	   diff = payScaleTable[i] [payScaleTable[i].length-1] -
	               payScaleTable [i][0];
	   System.out.println ("Difference in Grade " + i + " = " + diff);
       }


       // Calculate and print the average pay for the Grade 2 programmers
       float sum = 0.0f;
      
       for (int i = 0; i < payScaleTable[2].length; i++)
       {
           sum += payScaleTable [2][i];
       }
       System.out.println ("Avg pay for Grade 2 programmers = " 
                             + sum /payScaleTable[2].length);

       for (int i = 0; i < payScaleTable.length; i++ )
       {
	  for (int j = 0; j < payScaleTable[i].length; j++)
	  {
	      payScaleTable [i][j] += 1.50f;
	      System.out.printf ("  $%5.2f", payScaleTable [i][j]);
	  }
	  System.out.println();
       }
    }
  }
