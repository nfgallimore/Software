/* Created by Nick Gallimore
 * Fall 2013
 */

package breakout;

import org.lwjgl.input.Keyboard;

public class KeyEvents
{
	public void start()
	{		
		// waits for next keyboard event
		while (Keyboard.next())
		{
			// if key is currently being pressed is:
			if (Keyboard.getEventKeyState())
			{
				switch(Keyboard.getEventKey())
				{
					// A
					case Keyboard.KEY_A:
					{
						System.out.println("A Key Pressed");
					}
					// S
					case Keyboard.KEY_S:
					{
						System.out.println("S Key Pressed");
					}
					// D
					case Keyboard.KEY_D:
					{
						System.out.println("D Key Pressed");
					}
					// W
					case Keyboard.KEY_W:
					{
						System.out.println("W Key Pressed");
					}
				};
			}
			// if the key no longer being pressed is:
			else 
			{
				switch(Keyboard.getEventKey())
				{
					// A
					case Keyboard.KEY_A:
					{
						System.out.println("A Key Released");
					}
					// S
					case Keyboard.KEY_S:
					{
						System.out.println("S Key Released");
					}
					// D
					case Keyboard.KEY_D:
					{
						System.out.println("D Key Released");
					}
					// W
					case Keyboard.KEY_W:
					{
						System.out.println("W Key Released");
					}
				};
			}
		}
	}

}

