
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


// A state for the user to see before starting the game

public class StartState extends State{

	public static BufferedImage img;
	public int gameHeight;
	public int gameWidth;
	public int titleHeight;
	public int titleWidth;
	private World world;

	public StartState(Handler handler){
		super(handler);
		world = new World(handler, "WorldsToUse/WorldD.txt");
		// Using World D, other options are in the Folder WorldsToUse
	}

	public void tick(){
		getInput();
		// Checks the input from the user for switching to the gameState
	}

	public void render(Graphics g){
		// titleScreen1 is an image I made to direct the user to start the game
		try {
		    img = ImageIO.read(new File("titleScreen1.png"));
		} catch (IOException e) {
			
		}

		int gameHeight = handler.getGame().getHeight();
		int gameWidth = handler.getGame().getWidth();
		int titleHeight = 180;
		int titleWidth = 250;
		
		// Rendering the world in the background without any obstacles or helpers. This is just the tiles for visual affect
		world.render(g);
		
		// Drawing the title screen and centering it
		g.drawImage(img, (int) (gameWidth-titleWidth)/2, (int)(gameHeight-titleHeight)/2, titleWidth, titleHeight, null);
	}

	public void getInput(){
		
		if(handler.getKeyManager().aDown){
			// Checks if the user has hit the down arrow
			State.setState(handler.getGame().getGameState());
		}
	}

}
