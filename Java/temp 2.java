import java.awt.*;
import WizCard;

public class ObjectCard extends WizCard
 {
  public ObjectCard(Wizard w)
   {
    super(w);
    isFinal = true;
    add(new Label("Object screen"));
   }

  public void doNext()
   {
    w.reset();
   }
 }
