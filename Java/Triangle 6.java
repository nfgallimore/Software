public class Triangle extends Object
 {
  public int i,j;
  public boolean up;
//          /\
//         /31\
//        /____\
//       /\ 21 /\
//      /21\  /22\		triangle locations using i-j notation
//     /____\/____\
//    /\ 11 /\ 12 /\
//   /11\  /12\  /13\
//  /____\/____\/____\

//          /\
//         /8 \
//        /____\
//       /\ 7  /\			triangle locations enumerated (internal use only)
//      /3 \  /6 \
//     /____\/____\
//    /\ 2  /\ 5  /\
//   /0 \  /1 \  /4 \
//  /____\/____\/____\


  public Triangle(int i, int j, boolean up)
   {
    this.i = i;
    this.j = j;
    this.up = up;
   }

  public Triangle()
   {
    this(1,1,true);
   }

  public Triangle(int enum)
   {
    int s = 1;

    while (enum >= s*s) s++;		// s is the level of the triangle

    i = (enum - (s-1)*(s-1))/2 + 1;
    up = (((enum - (s-1)*(s-1)) % 2) == 0);
    if (up)
     j = s+1 - i;
    else
     j = s - i;
   }

  public int level()
   {
    if (up) return i+j-1;		// varies from 1 to n
    else return i+j;
   }

  public int enum()
   {
    int s = level();
    if (up) return (s-1)*(s-1) + 2*i-2;
    else return (s-1)*(s-1) + 2*i-1;
   }

  public boolean inside(int n) // is this triangle inside the n-puzzle?
   {
    return (i > 0) && (j > 0) && (level() <= n);
   }
 }
