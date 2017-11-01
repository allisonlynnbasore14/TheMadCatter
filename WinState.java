

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

// A state for when the player reaches the target
public class WinState extends State{

	public static BufferedImage img;
	public int gameHeight;
	public int gameWidth;
	public int titleHeight;
	public int titleWidth;

	public WinState(Handler handler){
		super(handler);
	}

	public void tick(){
		// checking for input from the user to start the game over
		getInput();
	}

	public void render(Graphics g){
	
		// WinScreen1 is an image I made to tell the user to restart
		try {
		    img = ImageIO.read(new File("winScreen1.png"));
		} catch (IOException e) {
			
		}

		int titleHeight = 700;
		int titleWidth = 700;
		
		// Drawing the image to be centered around the words
		g.drawImage(img, -200, -200, titleWidth, titleHeight, null);
	}

	public void getInput(){
		
		if(handler.getKeyManager().aUp){
			// Resets the players position and sets the state to start state if the player hits the up key
			handler.getGame().getGameState().getPlayer().setX(150);
			handler.getGame().getGameState().getPlayer().setY(280);
			State.setState(handler.getGame().getStartState());
		}
	}

}
