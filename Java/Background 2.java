/* Created by Nick Gallimore
 * Fall 2013
 */

package breakout;

// in case image isn't found
import java.io.IOException;

// lightweight java graphics library (lwjgl)
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

// slick library
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

// creates a new texture or image
public class Background
{
	// creates a new Image called image
	private Texture bg;
	
	public void create()
	{
		background(1280, 800);
		image("menu");
		
		while (true)
		{
			glClear(GL_COLOR_BUFFER_BIT);
			render();
			
			Display.update();
			Display.sync(100);
			
			if (Display.isCloseRequested())
			{
				Display.destroy();
				System.exit(0);
			}
		}
		
	}
// 
	private void background(int width, int height) 
	{
		// try's to create a new display
		try
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Display.setVSyncEnabled(true);
		}
		// catches exception
		catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		
		// enables 2d texture property
		glEnable(GL_TEXTURE_2D);
		
		// sets it to transparent
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			
		// enables blend
			glEnable(GL_BLEND);
			
			// function for blend
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			
			// view port
			glViewport(0, 0, width, height);
		
		// changes model view to matrix mode
		glMatrixMode(GL_MODELVIEW);
		
		//changes projection to matrix mode
		glMatrixMode(GL_PROJECTION);
		
		// loads the texture properties
		glLoadIdentity();
		
		// look up later ***************/
		glOrtho(0, width, height, 0, 1, -1);
		
		// does this again?
		glMatrixMode(GL_MODELVIEW);
	}
	// 
	public void image(String img)
	{
		try
		{	
			bg = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + img + ".png"));
			System.out.println("Texture Loaded: " + bg);
			System.out.println(">> Image Width: " + bg.getImageWidth());
			System.out.println(">> Image Height: " + bg.getImageHeight());
			System.out.println(">> Texture Width: " + bg.getWidth());
			System.out.println(">> Texture Height: " + bg.getHeight());
			System.out.println(">> Texture ID: " + bg.getTextureID());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void render()
	{
		Color.white.bind();
		bg.bind();
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(0, 0);
			glTexCoord2f(1, 0);
			glVertex2f(bg.getTextureWidth(), 0);
			glTexCoord2f(1, 1);
			glVertex2f(bg.getTextureWidth(), bg.getTextureHeight());
			glTexCoord2f(0, 1);
			glVertex2f(0, bg.getTextureHeight());
		glEnd();
			
	}
}

