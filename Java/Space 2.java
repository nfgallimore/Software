import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import Exponent;
import Regularity;

public class Space extends Object
 {
  public int space;
  public final int R_N = 0;
  public final int Z_N = 1;
  public final int T_N = 2;

  public int support;
  public final int NO_SUPPORT = 0;
  public final int DECAY = 1;
  public final int COMPACT = 2;

  public int freq;
  public final int ANY_FREQ = 0;
  public final int BALL_FREQ = 1;
  public final int ANNULUS_FREQ = 2;

  public boolean homog;

  public int norm;
  public final int LEBESGUE = 1;
  public final int LORENTZ = 2;
  public final int BMO = 3;
  public final int HARDY = 4;
  public final int SOBOLEV_2 = 5;
  public final int SOBOLEV_p = 6;
  public final int TRIEBEL = 7;
  public final int BESOV = 8;
  public final int CONTINUOUS = 9;

  int n;
  Exponent p,q;
  Regularity k,s;
  
  Space()
   {
    space = R_N; 
    support = NO_SUPPORT;
    freq = ANY_FREQ;
    norm = LEBESGUE;
    p = new Exponent(2);
    q = new Exponent(2);
    k = new Regularity(0);
    s = new Regularity(0);
   }

 }
