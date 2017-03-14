import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class SpaceInit extends Panel
 {
  SpaceApp applet;
  Choice orderChoice, spaceChoice, supportChoice, freqChoice, homogChoice,
         normChoice;
  Button button;

  TextField nText, pText, qText, sText, kText;

  SpaceInit(SpaceApp applet)
   {
    this.applet = applet;
    init();
   }

  public boolean action(Event ev, Object arg) 
   {
    if (ev.target instanceof Button) 
     {
      Exponent e = Exponent.valueOf(pText.getText());
      System.out.println(e.toString());
 //     applet.NewMain();
      return true;
     }
    return false;
   } 
   
  public void init()
   {
    Panel controls;

    setLayout(new GridLayout(8,1));


    controls = new Panel();
    controls.add(new Label("f is a function in"));
    controls.add(spaceChoice = new Choice());
    spaceChoice.addItem("R^n");
    spaceChoice.addItem("T^n");
    spaceChoice.addItem("Z^n");
    controls.add(new Label("with"));
    controls.add(supportChoice = new Choice());
    supportChoice.addItem("No support assumptions");
    supportChoice.addItem("Decay at infinity");
    supportChoice.addItem("Compact physical support");

    add(controls);
    controls = new Panel();

    controls.add(new Label("and frequency support"));
    controls.add(freqChoice = new Choice());
    freqChoice.addItem("arbitrary");
    freqChoice.addItem("on a ball");
    freqChoice.addItem("on an annulus");

    add(controls);
    controls = new Panel();

    controls.add(orderChoice = new Choice());
    orderChoice.addItem("Assume");
    orderChoice.addItem("Show");
    controls.add(new Label("f has k "));
    controls.add(homogChoice = new Choice());
    homogChoice.addItem("homogeneous");
    homogChoice.addItem("inhomogeneous");
    controls.add(new Label("derivatives in the "));
    controls.add(normChoice = new Choice());
    normChoice.addItem("Lebesgue space L^p");
    normChoice.addItem("Lorentz space L^{p,q}");
    normChoice.addItem("Hardy space H^p");
    normChoice.addItem("space BMO");
    normChoice.addItem("Sobolev space L^p_s");
    normChoice.addItem("Besov space B^{p,q}_s");
    normChoice.addItem("Triebel-Lizorkin space F^{p,q}_s");
    normChoice.addItem("space C^s");

    add(controls);
    controls = new Panel();

    controls.add(new Label("where"));

    add(controls);
    controls = new Panel();
    
    controls.add(new Label("n = "));
    controls.add(nText = new TextField("1",4));

    controls.add(new Label("k = "));
    controls.add(kText = new TextField("0",6));
    
    controls.add(new Label("s = "));
    controls.add(sText = new TextField("0",6));

    add(controls);
    controls = new Panel();

    controls.add(new Label("p = "));
    controls.add(pText = new TextField("2",4));

    controls.add(new Label("q = "));
    controls.add(qText = new TextField("2",6));
    
    add(controls);
    add(button = new Button("Start"));
    button.resize(6,1);
   }

 }
