// File StackDemo.java

// Henry H. Leitner
import java.util.*;

class StackDemo
{
  public static void main (String [] args)
  {
     java.util.Stack<Object> s = new java.util.Stack<Object> ();

     s.push ("foo");
     s.push ("bar");
     s.push ( 34 );

     System.out.println( s.pop() );
     System.out.println( s.pop() );  
     System.out.println( s.pop() ); 
  }
}
