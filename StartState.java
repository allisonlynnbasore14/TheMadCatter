
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class StartState extends State{

	public static BufferedImage img;
	public int gameHeight;
	public int gameWidth;
	public int titleHeight;
	public int titleWidth;
	private World world;
	//private GameState gameState;

	public StartState(Handler handler){
		super(handler); // getting it from the state class
		world = new World(handler, "WorldsToUse/WorldD.txt");
		//gameState = new GameState(handler);
	}

	public void tick(){
		getInput();
		
	}

	public void render(Graphics g){
			
		// g.setColor(Color.black);
		// g.fillRect(10, 40, 20, 40); // x,y,width, height
		// g.drawString("Ready to Play?", 50, 50);
		// g.drawString("Press Down to Begin.", 100, 100);
		try {
		    img = ImageIO.read(new File("titleScreen1.png"));
		} catch (IOException e) {
			
		}

		int gameHeight = handler.getGame().getHeight();
		int gameWidth = handler.getGame().getWidth();
		int titleHeight = 180;
		int titleWidth = 250;

		world.render(g);
		g.drawImage(img, (int) (gameWidth-titleWidth)/2, (int)(gameHeight-titleHeight)/2, titleWidth, titleHeight, null);
		//g.drawImage(img, 100,100, 200, 200, null);
	}

	public void getInput(){
		
		if(handler.getKeyManager().aDown){
			
			State.setState(handler.getGame().getGameState());
		}
	}

 	// public GameState getGameState(){
	 // 	return gameState;
	 // }


}