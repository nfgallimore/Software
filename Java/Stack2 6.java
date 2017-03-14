// File Stack2.java
// Henry H. Leitner
// This example implements a "Stack" using
//  "inheritance" -- i.e., we EXTEND the 
//  Vector class in package java.util  

import java.util.Vector;

class Stack2 extends Vector<Object>
{
   public Object push (Object item)
   {
      add (item);
      return item;
   }


   public Object peek()
   {
      return get (size() - 1);
   }


   public Object pop ()
   {
     Object obj = peek ();
     remove (size() - 1);
     return obj;
   }
}
