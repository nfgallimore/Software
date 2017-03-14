/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.com.badlogic.gdxinvaders.screens;

import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.State;
import static com.leapmotion.leap.Gesture.Type.TYPE_CIRCLE;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.KeyTapGesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.ScreenTapGesture;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;

import java.io.IOException;


/**
 *
 * @author nickgallimore
 */
class LeapListener extends Listener
{

    public int numFingers, numHands, avgPos, numTools, numGestures;
    public long time, frameid;
    public boolean motionDetected;
    
    
    @Override
    public void onInit(Controller c)
    {
        System.out.println("Leap Initialized");
    }

    @Override
    public void onConnect(Controller c)
    {
        System.out.println("Connected");
        c.enableGesture(Gesture.Type.TYPE_SWIPE);
        c.enableGesture(Gesture.Type.TYPE_CIRCLE);
        c.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        c.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    @Override
    public void onDisconnect(Controller c)
    {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    
    @Override
    public void onExit(Controller c)
    {
        System.out.println("Exited");
    }
    
    
    public boolean handDetected(Controller c)
    {
        motionDetected = !c.frame().hands().isEmpty();
        return motionDetected;
    }
    
    
    public void update(Controller c)
    {
        // Get the most recent frame and report some basic information
        frameid = c.frame().id();
        time = c.frame().timestamp();
        numHands = c.frame().hands().count();
        numFingers = c.frame().fingers().count();
        
    }
    
    
    public boolean fingerDetected(Controller c)
    {
        return (handDetected(c) && !c.frame().hands().get(0).fingers().isEmpty());
    }

    public Vector fingerPos(Controller c)
    {
        Vector pos = Vector.zero();
        if (fingerDetected(c))
        {
            update(c);
            for (Finger finger : c.frame().hands().get(0).fingers())
            {
                pos = pos.plus(finger.tipPosition());
            }
            pos = pos.divide(c.frame().hands().get(0).fingers().count());
        }
        return pos.divide(numFingers).plus(pos).divide(2.0f);
    }
    
    public float radius(Controller c)
    {
        return c.frame().hands().get(0).sphereRadius();
    }
    
    
    public Vector palmPos(Controller c)
    {
        return c.frame().hands().get(0).palmPosition();
    }
    
    
    public Vector normal(Controller c)
    {
        return c.frame().hands().get(0).palmNormal();
    }
    
    
    public Vector direction(Controller c)
    {
        return c.frame().hands().get(0).direction();
    }
    
    
    public double pitch(Controller c)
    {
        return Math.toDegrees(direction(c).pitch());
    }
    
    
    public double roll(Controller c)
    {
        return Math.toDegrees(normal(c).roll());
    }
    
    
    public double yaw(Controller c)
    {
        return Math.toDegrees(direction(c).yaw());
    }
    
    
    public CircleGesture circle (Controller c)
    {
        CircleGesture circle = new CircleGesture();
        for (int i = 0; i < c.frame().gestures().count(); i++)
        {
            Gesture g = c.frame().gestures().get(i);
            if (c.frame().gestures().get(i).type() == TYPE_CIRCLE)
            {
                circle = (CircleGesture) c.frame().gestures().get(i);

                // Calculate clock direction using the angle between circle normal and pointable
                boolean clockwise;
                clockwise = circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 4;

                // Calculate angle swept since last frame
                double sweptAngle = 0;
                if (circle.state() != State.STATE_START)
                {
                    CircleGesture previousUpdate = new CircleGesture(c.frame(1).gesture(circle.id()));
                }
            }
        }
        return circle;
    }
    
    public void OnGesture(Controller c)
    {
        Frame frame = c.frame();
        GestureList gestures = frame.gestures();
        for (int i = 0; i < gestures.count(); i++)
        {
            Gesture gesture = gestures.get(i);

            switch (gesture.type())
            {
                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    System.out.println("Swipe id: " + swipe.id()
                            + ", " + swipe.state()
                            + ", position: " + swipe.position()
                            + ", direction: " + swipe.direction()
                            + ", speed: " + swipe.speed());
                    break;
                case TYPE_SCREEN_TAP:
                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                    System.out.println("Screen Tap id: " + screenTap.id()
                            + ", " + screenTap.state()
                            + ", position: " + screenTap.position()
                            + ", direction: " + screenTap.direction());
                    break;
                case TYPE_KEY_TAP:
                {
                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
                    System.out.println("Key Tap id: " + keyTap.id() 
                            + ", " + keyTap.state() 
                            + ", position: " + keyTap.position() 
                            + ", direction: " + keyTap.direction());
                    break;
                }
                default:
                {
                    System.out.println("Unknown gesture type.");
                    break;
                }
            }
        }

        if (!frame.hands().isEmpty() || !gestures.isEmpty())
        {
            System.out.println();
        }
    }
}
