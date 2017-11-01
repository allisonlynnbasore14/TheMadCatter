
import java.lang.*;

public abstract class Targets extends Entity{

	public static final float DEFAULT_SPEED = 2.0f;
	protected float xMove, yMove;
	public float speed;
	//public float[] currentXpos;
	private WinState winState;

	public Targets(Handler handler, float x, float y, int width, int height, String type){
		super(handler, x, y, width, height, type);
		// xMove = 0;
		yMove = 0;
		speed = DEFAULT_SPEED;
		winState = new WinState(handler);

	}

	public void move(){
		if(collisionDetection(x, y)){
			State.setState(winState);
		}
		moveX();
	}


	public void moveX(){
		if((int) x > 300){
			x = 0;
		}else{
			x += speed;
		}
		
	}

	protected boolean collisionDetection(float a, float b){
		float xpos = handler.getGame().getGameState().getPlayer().getX();
		float ypos = handler.getGame().getGameState().getPlayer().getY();;
		int playerSize = handler.getGame().getGameState().getPlayer().PLAYER_SIZE;
		int obsSize = handler.getGame().getGameState().getPlayer().PLAYER_SIZE; 
		// // RIGHT NOW THE OBSTACLES ARE THE SAME SIZE AS THE PLAYERS
		
		boolean xConflict = (Math.abs(xpos + playerSize - (a + obsSize))<playerSize - 10);
		boolean yConflict = (Math.abs(ypos + playerSize - (b + obsSize))<playerSize- 10);

		if(xConflict && yConflict){
			return true;
		}else{
			return false;
		}
	}

	public void moveY(){
		//boolean notCollided = !collisionDetection( x, y, type);
		
		//y += speed/10.0;
	}


	// public float getSpeed(){
	// 	return speed;
	// }

	// public void setSpeed(float speed){
	// 	this.speed = speed;
	// }


	// public float getxMove() {
	// 	return xMove;
	// }

	// public void setxMove(float xMove) {
	// 	this.xMove = xMove;
	// }

	// public float getyMove() {
	// 	return yMove;
	// }

	// public void setyMove(float yMove) {
	// 	this.yMove = yMove;
	// }


}

