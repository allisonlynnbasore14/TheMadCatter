
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class EndState extends State{

	public static BufferedImage img;
	public static BufferedImage img2;
	public int gameHeight;
	public int gameWidth;
	public int titleHeight;
	public int titleWidth;

	private StartState startState;

	public EndState(Handler handler){
		super(handler); 

		//startState = new StartState(handler);
	}

	public void tick(){

		getInput();
	}

	public void render(Graphics g){
		// g.setColor(Color.black);
		// g.fillRect(10, 40, 20, 40); // x,y,width, height
		// g.drawString("GAME OVER YOU LOSE", 50, 50);

		try {
		    img = ImageIO.read(new File("endScreen1.png"));
		} catch (IOException e) {
			
		}

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
	}

	public void getInput(){
		
		if(handler.getKeyManager().aUp){
			handler.getGame().getGameState().getPlayer().setX(150);
			handler.getGame().getGameState().getPlayer().setY(280);
			State.setState(handler.getGame().getStartState());
		}
	}


}