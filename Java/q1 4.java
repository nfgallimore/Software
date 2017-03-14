class q1
{
    public static void main(String args [])
    {
        int a1,b1;
        boolean a,b;

        System.out.println("a \t b \t     !(a && b)\t      (a || b)\t !(a && b) && (a || b)");
       
        for(a1=0;a1<=1;a1++)
           for(b1=0;b1<=1;b1++)
           {
                if (a1==1)
                    a = true;
                else
                    a = false;

                if (b1==1)
                    b = true;
                else
                    b = false;
               
                System.out.println(a + "\t" + b + "\t\t" + (!(a && b)) + "\t\t" + (a || b) + "\t\t" + (!(a && b) && (a || 
b)));  
           }
    }
}
