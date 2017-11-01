
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Player extends Creature{

	public static final int PLAYER_SIZE = 20;

	public static BufferedImage img;
	private EndState endState;

	public int spaceCount;

	public Player(Handler handler, float x, float y, String type){
		super(handler, x, y, PLAYER_SIZE, PLAYER_SIZE, type);
		endState = new EndState(handler);
		spaceCount = 0;


		// Bounds stuff goes here
	}

	public void tick(){

		spaceCount = spaceCount + 1;
		
		if(spaceCount > 7){
			spaceCount = 0;
		}
		
		int numWater = handler.getWorld().getNumWater();
		int[] waterStartingPositions = handler.getWorld().getWaterStartingPositions();
		getInput( numWater);
		int logPlayerisOn = handler.getGame().getOnLog();


		if(handler.getKeyManager().space){
			
			if(spaceCount == 0){
				if(handler.getGame().getOnLog() == -1){
					y = y - 25;
				}
				else{
					y = y - 50;
				}
				// By hard coding this 25, it prevents the cat from jumping backwards
			}
		}

		if(logPlayerisOn != -1){
			for(int i = 0; i < numWater; i ++){
				if(!handler.getKeyManager().space){
					y = handler.getGame().getYLog();
					x = handler.getGame().getXLog();
				}else{
					handler.getGame().setOnLog(false, logPlayerisOn);
				}
			}
		}



		move();
		
		// GAME CAMERA Center!
		// handler.getGameCamera().centerOnEntity(this);
	}

	public void render(Graphics g){

		//g.setColor(Color.green);
		//g.fillRect((int) x, (int) y, 20, s40); // x,y,width, height
		//g.fillRect((int) (x-handler.getGameCamera().getxOffset()), (int) (y-handler.getGameCamera().getyOffset()), 20, 40); // x,y,width, height
		//g.fillRect(30,30, 40,40); // x y width height
		try {
		    img = ImageIO.read(new File("Cat.png"));
		} catch (IOException e) {
			
		}


		g.drawImage(img, (int) x, (int) y, PLAYER_SIZE, PLAYER_SIZE, null);
		
	}

	// The dealth funciton goes here

	public void getInput(int numWater){


		xMove = 0;
		yMove = 0;

		// System.out.println("WATER 1");
		// System.out.println(handler.getGame().getInWater(0));
		// System.out.println(handler.getGame().getOnLog(0));
		// System.out.println("WATER 2");
		// System.out.println(handler.getGame().getInWater(1));
		// System.out.println(handler.getGame().getOnLog(1));

		int logPlayerisOn = handler.getGame().getOnLog();
		
		for(int i = 0; i < numWater; i ++){
			if(handler.getGame().getInWater(i) && logPlayerisOn == -1){
				//System.out.println("DEAD 111111111111111");
				State.setState(endState);
			}
		}

		if(handler.getKeyManager().aUp){
			yMove = -speed; // Speed is in the creature classs
		}
		if(handler.getKeyManager().aDown){
			yMove = speed; 

		}
		if(handler.getKeyManager().aRight){
			xMove = speed; 
		}
		if(handler.getKeyManager().aLeft){
			xMove = -speed; 
		}
		
	}
	
}