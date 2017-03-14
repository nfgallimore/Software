class q15
{
  public static void main(String args [])
  {
       int num=5;
       int i=3,j=2;

       int maxX=10,maxY=10;

       /*Part b)*/ 
 
       TwoDArray myArray = new TwoDArray(maxX, maxY);      

       myArray.setItem(num, i,j);
 
       /*Part c*/

       num = 0;

       num = (Integer)myArray.getItem(i,j);

       System.out.println("num = " + num);
  }
}
