import java.awt.*;

public class DoubleField extends TextField
 {
  public DoubleField()
   {
    super("0.0",10);
   }

  public DoubleField(double x)
   {
    super(String.valueOf(x),10);
   }

  public void setDouble(double x)
   {
    setText(String.valueOf(x));
   }

  public double toDouble() throws NumberFormatException
   {
    return Double.valueOf(getText()).doubleValue();
   }
 }
