
import java.lang.*;
import java.util.Random;

public abstract class Obstacles extends Entity{

	public static final float DEFAULT_SPEED = 1.7f;
	protected float xMove, yMove;
	public float speed;
	public float[] currentXpos;
	private EndState endState;
	private Random rand = new Random();

	public Obstacles(Handler handler, float x, float y, int width, int height, String type){
		super(handler, x, y, width, height, type);
		xMove = 0;
		yMove = 0;
		speed = DEFAULT_SPEED;
		endState = new EndState(handler);
		
	}

	public void move(){
		moveX();
		moveY();

	}


	public void moveX(){
		int n = rand.nextInt(500) + 300; // 300 is the minimum and 500 is the max
		int n2 = rand.nextInt(200) + 1; 
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
		// RIGHT NOW THE OBSTACLES ARE THE SAME SIZE AS THE PLAYERS
		
		boolean xConflict = (Math.abs(xpos + playerSize - (a + obsSize))<playerSize - 10);
		boolean yConflict = (Math.abs(ypos + playerSize - (b + obsSize))<playerSize- 10);

		if(xConflict && yConflict && handler.getGame().getOnLog() == -1){
			State.setState(endState);
			return true;
		}else{
			return false;
		}
	}

	public void moveY(){
		//boolean notCollided = !collisionDetection( x, y, type);
		
		//y += speed/10.0;
	}


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

