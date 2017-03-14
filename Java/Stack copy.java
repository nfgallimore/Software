// File Stack.java
// Henry H. Leitner
// This example implements a "Stack" using
//  "composition" -- i.e., a Vector containing
//  the stack items is an instance variable

import java.util.Vector;

class Stack
{
  private Vector<Object> theData;

  public Stack()
  {
     theData = new Vector<Object>(); 
  }

  public boolean isEmpty()
  {
     return theData.isEmpty();
  }

  public Object push (Object item)
  {
     theData.add (item);
     return item;
  }

  public Object peek()
  {
     return theData.get(theData.size()-1);
  }

  public Object pop ()
  {
     Object result = theData.lastElement();
     theData.remove(theData.size()-1);
     return result;
  }
}
