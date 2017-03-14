import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class SpaceMain extends Panel
 {
  SpaceApp applet;

  SpaceMain(SpaceApp applet)
   {
    this.applet = applet;
    init();
   }

  public boolean action(Event ev, Object arg) 
   {
    if (ev.target instanceof Button) 
     {
      applet.NewInit();
      return true;
     }
    return false;
   } 
   
  public void init()
   {
    Panel controls;

    setLayout(new GridLayout(6,1));

//    controls = new Panel();
//    add(controls);

    add(new Button("Stop"));
   }

 }
