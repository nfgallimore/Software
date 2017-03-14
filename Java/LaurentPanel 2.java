import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/* import Complex; */

public class LaurentPanel extends Panel
{
 TextField z0Field;
 TextField taylor, laurent1, laurent2;
 TextField taylorConv, laurent1Conv, laurent2Conv;

 public LaurentPanel()
  {
   Label l;

   setLayout(new GridLayout(14,1));

   add(new Label("z0 = "));
   add(z0Field = new TextField());
   z0Field.setEditable(false);

   l = new Label("Taylor series");
   l.setForeground(Color.red);
   add(l);   
   add(taylor = new TextField());
   taylor.setEditable(false);

   l = new Label("Region of convergence:");
   l.setForeground(Color.red);
   add(l);
   add(taylorConv = new TextField());
   taylorConv.setEditable(false);

   l = new Label("Laurent series 1");
   l.setForeground(Color.green);
   add(l);   
   add(laurent1 = new TextField());
   laurent1.setEditable(false);

   l = new Label("Region of convergence:");
   l.setForeground(Color.green);
   add(l);
   add(laurent1Conv = new TextField());
   laurent1Conv.setEditable(false);

   l = new Label("Laurent series 2");
   l.setForeground(Color.cyan);
   add(l);   
   add(laurent2 = new TextField());
   laurent2.setEditable(false);

   l = new Label("Region of convergence:");
   l.setForeground(Color.cyan);
   add(l);
   add(laurent2Conv = new TextField());
   laurent2Conv.setEditable(false);

   setZ0(new Complex(0,0));
  }


 String term(Complex a, int k, boolean signed)
  {
   String s;

   if (k == 0 || Math.abs(a.x) < .0001 || Math.abs(a.y) < 0.001) s = a.toString(signed);
   else
     if (!signed) s = "(" + a.toString(false) + ")";
     else
       if (a.x > 0) s = "+(" + a.toString(false) + ")";
       else         s = "-(" + a.minus().toString(false) + ")";

   if (k ==  0) return s;
   if (k ==  1) return s + "(z-z0)";
   if (k >   1) return s + "(z-z0)^"+String.valueOf(k);
   if (k == -1) return s + "/(z-z0)";
   return s + "/(z-z0)^"+String.valueOf(-k);
  }

 Complex coeff(Complex pole, int k)
  {
   int i;
   Complex z = new Complex(1,0);

   if (k >= 0)
    {
     for (i = 0; i <= k; i++) z = z.over(pole);
     return z;
    }
   else
    {
     for (i = 0; i < -k-1; i++) z = z.times(pole);
     return z.minus();
    }
  }
     
 public void setZ0(Complex z0)
  {
   int i;

   String zString = "|z - z0|";
   String s;

   Complex z1 = new Complex(1,0);
   Complex z2 = new Complex(2,0);
   Complex zNear, zFar;

   float a = Complex.dist(z0, z1);
   float b = Complex.dist(z0, z2);
   float r1, r2;

   if (a < b)
    { r1 = a; r2 = b; zNear = z1.minus(z0); zFar = z2.minus(z0); }
   else
    { r1 = b; r2 = a; zNear = z2.minus(z0); zFar = z1.minus(z0); }


   z0Field.setText(z0.toString());

   taylorConv.setText(zString + " < " + String.valueOf(r1));

   s = new String();
   for (i = 0; i < 3; i++)
     s += term(coeff(zNear,i).minus(coeff(zFar,i)), i,i > 0);
   taylor.setText(s+"+...");

   laurent1Conv.setText(String.valueOf(r1) + " < " + zString + " < " + String.valueOf(r2));

   s = new String();
   for (i = 0; i < 3; i++)
     s += term(coeff(zFar,i).minus(), i,i > 0);
   s+= "+...";
   for (i = -1; i >= -3; i--)
     s += term(coeff(zNear,i), i,true);
   laurent1.setText(s+"+...");

   laurent2Conv.setText(String.valueOf(r2) + " < " + zString); 

   s = new String();
   for (i = -1; i >= -3; i--)
     s += term(coeff(zNear,i).minus(coeff(zFar,i)), i,i < -1);
   laurent2.setText(s+"+...");

  }
}
