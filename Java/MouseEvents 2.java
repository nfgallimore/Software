/* Created by Nick Gallimore
 * Fall 2013
 */

package breakout;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

// listens for mouse events
public class MouseEvents
{
	// polls mouse events
	public void start()
	{
		// x coordinate
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		// booleans for right and left mouse buttons clicked
		boolean leftButtonDown = Mouse.isButtonDown(0);
		boolean rightButtonDown = Mouse.isButtonDown(1);
		
		// if either left or right click was received
		if (leftButtonDown || rightButtonDown)
		{
			System.out.println("X: " + x + " Y: " + y);
		}
		// if the user presses space
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			System.out.println("Space Key Pressed");
		}
	}
}