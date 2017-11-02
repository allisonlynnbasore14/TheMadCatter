
import java.lang.*;
import java.util.Random;

public abstract class Obstacles extends Entity{
	
	public static final float DEFAULT_SPEED = 1.7f;
	protected float xMove, yMove;
	public float speed;
	public float[] currentXpos;
	private EndState endState;
	// Making a randodm value
	private Random rand = new Random();

	public Obstacles(Handler handler, float x, float y, int width, int height, String type){
		super(handler, x, y, width, height, type);
		xMove = 0;
		yMove = 0;
		speed = DEFAULT_SPEED;
		endState = new EndState(handler);
		
	}
	// 
	public void move(){
		moveX();
		moveY();
	}


	public void moveX(){
		int n = rand.nextInt(500) + 300; // 300 is the minimum and 500 is the max
		int n2 = rand.nextInt(200) + 1; // 1 is the minimum and 200 is the max (this number is multiplied by -1 below)
		// The variables n and n2 are for the random placement of the obstalces off the screen
		// This makes it so the obstacle appear at random times to the user
		// n is for when the player is going right to left
		// n2 is for when the obstacle is going left to right
		boolean notCollided = !collisionDetection( x, y, type);
		if(type == "Shark"){
			if((int) x < 0 && notCollided){
				x = n;
			}else{
				x -= speed;
			}
		}else{
			if((int) x > 300 && notCollided){
				x = n2 * -1;
			}else{
				x += speed;
			}
		}
		
		
	}

	protected boolean collisionDetection(float a, float b, String type){
		float xpos = handler.getGame().getGameState().getPlayer().getX();
		float ypos = handler.getGame().getGameState().getPlayer().getY();;
		int playerSize = handler.getGame().getGameState().getPlayer().PLAYER_SIZE;
		int obsSize = handler.getGame().getGameState().getPlayer().PLAYER_SIZE; 
		// Assuming the obstacle is the same size of the player
		
		boolean xConflict = (Math.abs(xpos + playerSize - (a + obsSize))<playerSize - 10);
		boolean yConflict = (Math.abs(ypos + playerSize - (b + obsSize))<playerSize- 10);
		// Checking if the player is conflicting with an obstacle and not on a log
		// If the player is on a log it cannot be attacked by the shark obstacle
		if(xConflict && yConflict && handler.getGame().getOnLog() == -1){
			State.setState(endState);
			return true;
		}else{
			return false;
		}
	}

	public void moveY(){
		//The obstacles do not move in the y directionn
	}
	
	// Getters and Setters

	public float getSpeed(){
		return speed;
	}

	public void setSpeed(float speed){
		this.speed = speed;
	}


	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}


}

