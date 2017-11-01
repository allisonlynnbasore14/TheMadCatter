
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class EndState extends State{
	
	// A State that the user goes to when they run into an obstacle or fall into the water

	public static BufferedImage img;
	public static BufferedImage img2;
	public int gameHeight;
	public int gameWidth;
	public int titleHeight;
	public int titleWidth;

	private StartState startState;

	public EndState(Handler handler){
		super(handler); 
	}

	public void tick(){
		// Gets the input of the user to switch screen to the restart screen.
		
		getInput();
	}

	public void render(Graphics g){

		// endScreen1 is an image I made that asks the player to restart
		try {
		    img = ImageIO.read(new File("endScreen1.png"));
		} catch (IOException e) {
			
		}

		// sad cat is an image from https://giphy.com/gifs/cat-cry-catnip-3o7qEciAHeS
		try {
		    img2 = ImageIO.read(new File("sadCat.gif"));
		} catch (IOException e) {
			
		}

		int gameHeight = handler.getGame().getHeight();
		int gameWidth = handler.getGame().getWidth();
		int titleHeight = 400;
		int titleWidth = 500;

		g.drawImage(img, -100, -100, titleWidth, titleHeight, null);
		g.drawImage(img2, 40, 150, 150, 150, null);
		// arranging the cat (img2) to be in the bottom left corner of the screen
	}

	public void getInput(){
		// If the user hits the up key, the state changes to the start screen
		if(handler.getKeyManager().aUp){
			handler.getGame().getGameState().getPlayer().setX(150);
			handler.getGame().getGameState().getPlayer().setY(280);
			State.setState(handler.getGame().getStartState());
		}
	}


}
