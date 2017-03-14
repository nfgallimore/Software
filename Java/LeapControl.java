/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.FingerList;
import static com.leapmotion.leap.Gesture.Type.TYPE_SWIPE;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class LeapListener extends Listener
{
    int lastSwipeID = 0;
    Polarizer polarizer;
    
    public LeapListener(Polarizer t)
    {
        polarizer = t;
        t.mouse = false;
        
    }

    @Override
    public void onInit(Controller controller)
    {
        System.out.println("Initialized");
    }

    @Override
    public void onConnect(Controller controller)
    {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        
        /* PROBLEM COULD'VE BEEN HERE */
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    }

    @Override
    public void onDisconnect(Controller controller)
    {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    @Override
    public void onExit(Controller controller)
    {
        System.out.println("Exited");
    }
    int counter = 0, count = 0;

    @Override
    public void onFrame(Controller controller)
    {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        
        if (!frame.hands().isEmpty())
        {
            // Get the first hand
            Hand hand = frame.hands().get(0);

            // Check if the hand has any fingers
            FingerList fingers = hand.fingers();
            if (!fingers.isEmpty())
            {
            }
          
            Vector normal = hand.palmNormal();
            Vector direction = hand.direction();
            
        }
               GestureList gestures = frame.gestures();
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);
            
            /* You don't need a switch here unless you want to handle more than one gesture type! */
            if (gesture.type() == TYPE_SWIPE) {

                    // if we're processing the same gesture again
                	if(lastSwipeID == gesture.id()){
                		return;
                	}

                    // creating second instance of swipegesture
                    SwipeGesture swipe = new SwipeGesture(gesture);

                    // pretty much the delta or the distance of swipe from start to current
                    Vector v = swipe.position().minus(swipe.startPosition());
                    
                    // sets the length of vector v to 1 (i.e. if v.getX() > 0.75f is same as 75%)
                    v = v.normalized();


                    /* NOTE maybe make these instead of "else if's" to 
                     (consider the X and Y both move!) */

                     /* if X value has changed */
                    if (v.getX() > 0.75f) {
                    	System.out.println("right: " +v.getX());
                    	lastSwipeID = gesture.id();
                    	//dx = 1; 
                    }
                    else (v.getX() < -0.75f) {
                    	System.out.println("left: " +v.getX());
                    	lastSwipeID = gesture.id();
                    	//dx = -1;
                    }



                    /* if Y value has changed */
                    if (v.getY() > 0.75f) {
                    	System.out.println("up: " +v.getX());
                    	lastSwipeID = gesture.id();
                    	//dy = -1; 
                    }
                    else (v.getY() < -0.75f) {
                    	System.out.println("down: " +v.getX());
                    	lastSwipeID = gesture.id();
                    	//dy = 1;
                    }



                    // Prints logs to terminal
                    System.out.println("Swipe id: " + swipe.id()
                               + ", " + swipe.state()
                               + ", position: " + swipe.position()
                               + ", direction: " + swipe.direction()
                               + ", speed: " + swipe.speed()+" vec dir " + v);
                    break;
                        }
                    }
                }
            }
        



class LeapControl
{

    public static void main(String[] args)
    {
        LeapListener listener = new LeapListener(new Polarizer());
        Controller controller = new Controller();
        controller.addListener(listener);
        
        
        System.out.println("Press enter to quit...");
        
        try
        {
            System.in.read();
        } catch (IOException ex)
        {
            Logger.getLogger(LeapControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        controller.removeListener(listener);

    }
}
