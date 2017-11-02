
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Player extends Creature{
	// Class for handleing the player which is the current sole version of Creature
	public static final int PLAYER_SIZE = 20;

	public static BufferedImage img;
	private EndState endState;

	public int spaceCount;

	public Player(Handler handler, float x, float y, String type){
		super(handler, x, y, PLAYER_SIZE, PLAYER_SIZE, type);
		endState = new EndState(handler);
		spaceCount = 0;
	}

	public void tick(){

		spaceCount = spaceCount + 1;
		// This is a debouncing method of only jumping once if the player holds down the space bar
		// Since the space bar is touchy, it is nearly impossible to only click it once.
		// 7 is the threashold
		if(spaceCount > 7){
			spaceCount = 0;
		}
		
		// Finding if the player is in water and which water it is in
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
				// Only allowing the player to jump forwards
				// The 50 value is for getting off of the logs without the water collision
				// Otherwise, the player jumps by 25 pixels each time
			}
		}

		if(logPlayerisOn != -1){
			// If logPlayerisOn is -1, then they are not on a log
			for(int i = 0; i < numWater; i ++){
				// If the player is on a log, the player moves with the log
				if(!handler.getKeyManager().space){
					y = handler.getGame().getYLog();
					x = handler.getGame().getXLog();
				}else{
					handler.getGame().setOnLog(false, logPlayerisOn);
				}
			}
		}



		move();
	}

	public void render(Graphics g){
	// Image from https://play.google.com/store/apps/details?id=com.phonyGames.platypus.android
		
		try {
		    img = ImageIO.read(new File("Cat.png"));
		} catch (IOException e) {
			
		}
		// The player is placed at x and y at size Player_size
		g.drawImage(img, (int) x, (int) y, PLAYER_SIZE, PLAYER_SIZE, null);
		
	}


	public void getInput(int numWater){


		xMove = 0;
		yMove = 0;

		int logPlayerisOn = handler.getGame().getOnLog();
		
		for(int i = 0; i < numWater; i ++){
			if(handler.getGame().getInWater(i) && logPlayerisOn == -1){
				// If the player is in the water and not on a log, then transition to endState
				State.setState(endState);
			}
		}

		if(handler.getKeyManager().aUp){
			yMove = -speed;
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
