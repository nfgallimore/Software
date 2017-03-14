/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.com.badlogic.gdxinvaders.screens;

import com.leapmotion.leap.Controller;
import java.io.IOException;

/**
 *
 * @author nickgallimore
 */
public class Game
{
    /**
     *
     * @param args
     * @param listener
     */
    public static void main(String[] args)
    {
        
        LeapListener listener = new LeapListener();
        Controller c = new Controller();
        
        // Create a sample listener and assign it to a controller to receive events
        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e)
        {
            // Throws Error
        }
        //The controller must be disposed of before the listener
        c.delete();
        listener.delete();
    }
}