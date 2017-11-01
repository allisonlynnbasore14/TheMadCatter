

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;



public class WinState extends State{

	public static BufferedImage img;
	public int gameHeight;
	public int gameWidth;
	public int titleHeight;
	public int titleWidth;
	//private StartState startState;

	public WinState(Handler handler){
		super(handler); // getting it from the state class

		//startState = new StartState(handler);
	}

	public void tick(){

		getInput();

		
	}

	public void render(Graphics g){
		// g.setColor(Color.red);
		// g.fillRect(10, 40, 20, 40); // x,y,width, height
		// g.drawString("WINNER WINNER!", 50, 50);

		try {
		    img = ImageIO.read(new File("winScreen1.png"));
		} catch (IOException e) {
			
		}


		int gameHeight = handler.getGame().getHeight();
		int gameWidth = handler.getGame().getWidth();
		int titleHeight = 700;
		int titleWidth = 700;

		g.drawImage(img, -200, -200, titleWidth, titleHeight, null);
	}

	public void getInput(){
		
		if(handler.getKeyManager().aUp){
			handler.getGame().getGameState().getPlayer().setX(150);
			handler.getGame().getGameState().getPlayer().setY(280);
			State.setState(handler.getGame().getStartState());
		}
	}

}