// C.java
// Demonstrate use of super and this
// Author: Henry H. Leitner
// Last Modified: July 8, 2013

public class C extends B
{
   public C () 
   { 
     super (3); 
     System.out.println ("In 0-arg constructor C");  
   }
}

class B extends A
{
   public B ()      { System.out.println ("In 0-arg constructor B");  }
   public B (int x) { System.out.println ("In 1-arg constructor B "+ x); }
}

class A
{
   public A () 
   { 
      this (44);
      System.out.println ("In 0-arg constructor A");
   }
   
   public A (int x) 
   {
      System.out.println ("In 1-arg constructor A "+ x); 
   }
}

   
