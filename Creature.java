
public abstract class Creature extends Entity{
	
	// The creature class controls the player. If there were multiple players or other non-obstacle entities
	// Creatures would also control them.

	protected float speed;
	protected float xMove, yMove;
	private float prevX;
	private float prevY;
	
	

	public Creature(Handler handler, float x, float y, int width, int height, String type){
		super(handler, x, y, width, height, type);
		speed = 1.0f;
		xMove = 0;
		yMove = 0;
		// Setting initial variables
		// The speed is set low (usually 3.0f) because the game is designed for the player to jump with the space bar
		// The speed is the default if the player "walks" with the arrow keys instead of jumping
	}

	public void move(){
		
		
		int gameWidth = handler.getGame().getWidth();
		int gameHeight = handler.getGame().getHeight();
		
		// Checks to make sure the player is not going off the screen, else the player does not move
		
		if(xMove + x + width > gameWidth || xMove + x < 0){
			xMove = 0;
		}
		if(yMove + y + height > gameWidth || yMove + y < 0){
			yMove = 0;
		}
		
		// Calls for a move in each direction according to input from the Keys
		moveX();
		moveY();
	}


	// Getterss

	public float getSpeed(){
		return speed;
	}

	public float getxMove(){
		return xMove;
	}

	public float getyMove(){
		return yMove;
	}

	public void moveX(){
		x += xMove;
	}

	public void moveY(){
		y += yMove;
	}


	// Setters

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public void setxMoving(float x){
		this.xMove = x;
	}

	public void setyMoving(float y ){
		this.yMove = y;
	}


}
