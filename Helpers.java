
public abstract class Helpers extends Entity{

	public static final float DEFAULT_SPEED = 1.0f;
	protected float xMove, yMove;
	public float speed;
	private int numWater;
	private int[] waterStartingPositions;
	private int[] logStartingPositions;
	private int numLogs;

	// Helpers are like Obstacles in their movement but they help the player
	// The only helper is the log that moves acorss the water tiles
	public Helpers(Handler handler, float x, float y, int width, int height, String type){
		super(handler, x, y, width, height, type);
		xMove = 0;
		yMove = 0;
		speed = DEFAULT_SPEED;
	}

	public void move(){
		int numWater = handler.getGame().getGameState().getWorld().getNumWater();
		int numLogs = handler.getGame().getGameState().getNumLogs();
		// Makeing a class copy of the waterStartingPositions for log placement
		int[] waterStartingPositions = handler.getGame().getGameState().getWorld().getWaterStartingPositions();
		logStartingPositions = new int[waterStartingPositions.length];
		// The orginial plan was for there to be two logs instead of one
		// This is code for placeing one log at the start of the water and a second on the bottom half of the water
		for( int i = 0; i < waterStartingPositions.length; i = i + 2){
			logStartingPositions[i] = waterStartingPositions[i];
			logStartingPositions[i+1] = waterStartingPositions[i] + 25;
		}
		moveX(numWater,logStartingPositions, numLogs);
		moveY();
		// Setting the log positions in a game variable for the player to use
		handler.getGame().setYLog(y);
		handler.getGame().setXLog(x);
	}

	public void moveX(int numWater, int[] logStartingPositions, int numLogs){
		float xpos = handler.getGame().getGameState().getPlayer().getX();
		float ypos = handler.getGame().getGameState().getPlayer().getY();
		// getting the player's positions
		
		if (collisionDetection( x, y, xpos, ypos)){
			for(int l = 0; l < numLogs; l++){
				// setting the log positions boolean variable for the player to use
				if( (int) ypos  > logStartingPositions[l] -20 && (int) ypos  < logStartingPositions[l] +20 ){
					handler.getGame().setOnLog(true, l);
				}
			}
		}else{
			for(int l = 0; l < numLogs; l++){
				// if there is not a collision, setting the boolean of the player collision to false
				if( (int) ypos  > logStartingPositions[l] -5 && (int) ypos  < logStartingPositions[l] +5 ){
					handler.getGame().setOnLog(false, l);
				}
			}
		}
		// this was for when there were logs of multiple names
		// One long would go left to right and the other right to left
		if(type == "Log1"){
			if((int) x < 0){
				x = 300;
			}else{
				x -= speed;
			}
		}else{
			if((int) x > 300){
				x = 0;
			}else{
				x += speed;
			}
		}
		
	}

	protected boolean collisionDetection(float a, float b, float xpos, float ypos){
		int playerSize = handler.getGame().getGameState().getPlayer().PLAYER_SIZE;
		int obsSize = handler.getGame().getGameState().getPlayer().PLAYER_SIZE; 
		// Using the player size as the same size of helpers
		
		// finding the position collision of the helper and the player with a buffer of 20 pixels (i.e. if the player is within 20 pixels of the log, it will automatically go to it)
		boolean xConflict = (Math.abs(xpos + playerSize - (a + obsSize))<playerSize + 20);
		boolean yConflict = (Math.abs(ypos + playerSize - (b + obsSize))<playerSize + 20);

		if(xConflict && yConflict){
			return true;
		}else{
			return false;
		}
	}

	public void moveY(){
		// No movement in the y direction
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

