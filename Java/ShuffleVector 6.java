import java.util.*;

public class ShuffleVector extends Vector
{  
  int shuffle[];
  boolean doShuffle;
  boolean isShuffled;

////////////////////// Constructors ///////////////////////
  public ShuffleVector()
   {
    this(false);
   } 

  public ShuffleVector(boolean doShuffle) 
   {
    super();
    this.doShuffle = doShuffle;
    isShuffled = false;
    shuffle = new int[0];
   }

////////////////////// Methods ///////////////////////

  public void unshuffle()
   {
    doShuffle = false;
    isShuffled = false;
   }

  public void shuffle()
// Note: actual shuffling is delayed until the first getShuffled() is called
   { 
    doShuffle = true;    
   }

////////////////////// Private Routines ///////////////////////

  private void shuffleIt()
   {
    int i,j,temp;
    Random r = new Random();

    if (!doShuffle) return;
   
    isShuffled = true;
    shuffle = new int[size()];

    for (i = 0; i < size(); i++)  shuffle[i] = i;

    for (i = 1; i < size(); i++)
     {
      j = r.nextInt(i+1);
      temp = shuffle[i];
      shuffle[i] = shuffle[j];
      shuffle[j] = temp;
     }    
   }

////////////////////// Queries ///////////////////////

  public Object getShuffled(int index)  
   {
    if (!doShuffle) 
      return get(index);
    else
     {
      if (!isShuffled) shuffleIt();
      return get(shuffle[index]);
     }
   }

 }