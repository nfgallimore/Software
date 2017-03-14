import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import Exponent;
import Regularity;
import SpaceInit;
import SpaceMain;

public class SpaceApp extends Applet
 {
  SpaceInit space_init;
  SpaceMain space_main;

  public void init()
   {
    add(space_init = new SpaceInit(this));
    space_main = new SpaceMain(this);
   }

  public void NewMain()
   {
    removeAll();
    add(space_main);
    repaint();
    space_main.repaint();
    validate();
   }

  public void NewInit()
   {
    removeAll();
    add(space_init);
    repaint();
   }
 }
