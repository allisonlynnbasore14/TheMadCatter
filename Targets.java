
import java.lang.*;

// This is a class for targets, which move like helpers and obstacles but when the player colideds with them, they win the game.
public abstract class Targets extends Entity{

	public static final float DEFAULT_SPEED = 2.0f;
	protected float xMove, yMove;
	public float speed;
	private WinState winState;

	public Targets(Handler handler, float x, float y, int width, int height, String type){
		super(handler, x, y, width, height, type);
		yMove = 0;
		speed = DEFAULT_SPEED;
		winState = new WinState(handler);

	}

	public void move(){
		// If the player collides with the target, the player wins
		if(collisionDetection(x, y)){
			State.setState(winState);
		}
		// Only moving in the x dIrection
		moveX();
	}


	public void moveX(){
		if((int) x > 300){
			x = 0;
		}else{
			// Moving left to right
			x += speed;
		}
		
	}

	protected boolean collisionDetection(float a, float b){
		float xpos = handler.getGame().getGameState().getPlayer().getX();
		float ypos = handler.getGame().getGameState().getPlayer().getY();;
		int playerSize = handler.getGame().getGameState().getPlayer().PLAYER_SIZE;
		int obsSize = handler.getGame().getGameState().getPlayer().PLAYER_SIZE; 
		// This obsSize variable is the size of the target/obstacle/helper
		boolean xConflict = (Math.abs(xpos + playerSize - (a + obsSize))<playerSize - 10);
		boolean yConflict = (Math.abs(ypos + playerSize - (b + obsSize))<playerSize- 10);

		if(xConflict && yConflict){
			return true;
		}else{
			return false;
		}
	}

}

