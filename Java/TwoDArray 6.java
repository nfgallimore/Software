public class TwoDArray
{
   private /*blank1*/ Object data[];
   private int xDimensions;
   private int yDimensions;

   public TwoDArray(int xDimensions, int yDimensions)
   {
          data = new /*blank2*/ Object [xDimensions*yDimensions]; /*blank 3*/

          /*blank4*/ this.xDimensions = xDimensions;
          /*blank5*/ this.yDimensions = yDimensions;
   }

   public Object getItem(int x, int y)
   {
         return data[y*xDimensions + x]; /*blank6*/
   }

   public void setItem(/*blank7*/ Object elt, int x, int y)
   {
        data[y*xDimensions + x] = elt; /*blank8*/
   }
}
