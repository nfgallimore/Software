/* Created by Nick Gallimore
 * Fall 2013
 */

package breakout;

// lightweight java graphics library
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;


public class Game
{

	// statistics
	float x, y;
	float rotation = 0;
	private long lastFrame;
	private int FPS;
	@SuppressWarnings("unused")
	private long lastFPS;
	private int delta;
	
	// settings
	boolean vsync, fullscreen = false;
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	private final double SPEED = 100;
	
	// starts game
	public void start()
	{
		init();
		run();
		exit();
	}
	
	// main methods
	public void init()
	{
		create();
		center();
		delta();
		initGL();
	}
	public void run()
	{
		while (!Display.isCloseRequested())
		{
			rotate();
			move();
			render();
			refresh();
		}
	}
	public void exit()
	{
		Display.destroy();
	}
	
	// run methods
	public void rotate()
	{
		delta();
		rotation += 0.15f * delta;
	}
	public void move()
	{
		// moves forward
		if (Keyboard.isKeyDown(Keyboard.KEY_W) ||
			Keyboard.isKeyDown(Keyboard.KEY_UP)) 
			y += (SPEED / 100) * 0.35f * delta;
		
		// moves left
		if (Keyboard.isKeyDown(Keyboard.KEY_A) ||
				Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 
			x -= (SPEED / 100) * 0.35f * delta;
		
		// moves back
		if (Keyboard.isKeyDown(Keyboard.KEY_S) ||
			Keyboard.isKeyDown(Keyboard.KEY_BACK)) 
			y -= (SPEED / 100) * 0.35f * delta;
		
		// moves right
		if (Keyboard.isKeyDown(Keyboard.KEY_D) ||
			Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) 
			x += (SPEED / 100) * 0.35f * delta;
		
		// waits for setting keys
		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				// if user hits F for full screen
				if (Keyboard.getEventKey() ==  Keyboard.KEY_F)
				{
					if (fullscreen) fullscreen = false;
					else fullscreen = true;
				}
				// if user hits V for vertical sync
				else if (Keyboard.getEventKey() == Keyboard.KEY_V)
				{
					if (vsync) vsync = false;
					else vsync = true;
				}
			}
		}
		// if out of bounds
		if (x < 0) x = 0;
		if (x > WIDTH) x = WIDTH;
		if (y < 0) y = 0;
		if (y > HEIGHT) y = HEIGHT;
		
		fps();
	}		
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glColor3f(0.5f, 0.5f, 1.0f);
		glPushMatrix();
			glTranslatef(x, y, 0);
			glRotatef(rotation, 0f, 0f, 1f);
			glTranslatef(-x, -y, 0);
			
			glBegin(GL_QUADS);
				glVertex2f(x - 50, y - 50);
				glVertex2f(x + 50, y - 50);
				glVertex2f(x + 50, y + 50);
				glVertex2f(x - 50, y + 50);
			glEnd();
		glPopMatrix();
		
		Display.update();
		Display.sync(60);
	}
	public void refresh()
	{
		if (error()) return;
		
		try
		{
			if (fullscreen) Display.setDisplayMode(fullscreen());
			else Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			if (fullscreen() == null) return;
			Display.setFullscreen(fullscreen);
		} 
		catch (LWJGLException e)
		{
			System.out.println("Unable to setup mode " + WIDTH + "x" + HEIGHT + " fullscreen = " + fullscreen + e);
		}
			
	}

	// display methods
	public void initGL()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, 0, HEIGHT, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	public boolean error()
	{
		return ((Display.getDisplayMode().getWidth() == WIDTH) && 
				(Display.getDisplayMode().getHeight() == HEIGHT) &&
				(Display.isFullscreen() == fullscreen));
	}
	public void create()
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
		center();
		delta();
	}
	public DisplayMode fullscreen()
	{
		DisplayMode targetDisplayMode = null;
		DisplayMode[] modes;
		try {
			modes = Display.getAvailableDisplayModes();
			int freq = 0;		
			for (int i = 0; i < modes.length; i++)
			{
				DisplayMode current = modes[i];
				if ((current.getWidth() == WIDTH) && current.getHeight() == HEIGHT)
				{
					if ((targetDisplayMode == null) || (current.getFrequency() >= freq))
					{
						if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel()))
						{
							targetDisplayMode = current;
							freq = targetDisplayMode.getFrequency();
						}
					}
					if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
							(current.getFrequency() == Display.getDesktopDisplayMode().getFrequency()))
					 {
						targetDisplayMode = current;
						break;
					}
				}
			}
		} 
		catch (LWJGLException e) {
			e.printStackTrace();
		}
		return targetDisplayMode;
	}
	
	// timing methods
	public void fps()
	{
		if (getTime() - lastFrame > 1000)
		{
			Display.setTitle("FPS: " + FPS);
			FPS = 0;
			lastFPS += 1000;
		}
		FPS++;
	}
	public void delta()
	{
		long time = getTime();
		delta = (int)(time - lastFrame);
		lastFrame = time;
	}
	public long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	public void center()
	{
		x = WIDTH / 2;
		y = HEIGHT / 2;
	}
}
