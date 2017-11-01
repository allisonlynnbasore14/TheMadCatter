
public abstract class Helpers extends Entity{

	public static final float DEFAULT_SPEED = 1.0f;
	protected float xMove, yMove;
	public float speed;
	private int numWater;
	private int[] waterStartingPositions;
	private int[] logStartingPositions;
	private int numLogs;

	public Helpers(Handler handler, float x, float y, int width, int height, String type){
		super(handler, x, y, width, height, type);
		xMove = 0;
		yMove = 0;
		speed = DEFAULT_SPEED;
	}

	public void move(){
		int numWater = handler.getGame().getGameState().getWorld().getNumWater();
		int numLogs = handler.getGame().getGameState().getNumLogs();
		int[] waterStartingPositions = handler.getGame().getGameState().getWorld().getWaterStartingPositions();
		logStartingPositions = new int[waterStartingPositions.length];
		for( int i = 0; i < waterStartingPositions.length; i = i + 2){
			logStartingPositions[i] = waterStartingPositions[i];
			logStartingPositions[i+1] = waterStartingPositions[i] + 25;
		}
		moveX(numWater,logStartingPositions, numLogs);
		moveY();

		handler.getGame().setYLog(y);
		handler.getGame().setXLog(x);
	}



	public void moveX(int numWater, int[] logStartingPositions, int numLogs){
		float xpos = handler.getGame().getGameState().getPlayer().getX();
		float ypos = handler.getGame().getGameState().getPlayer().getY();



		if (collisionDetection( x, y, xpos, ypos)){
			for(int l = 0; l < numLogs; l++){
				if( (int) ypos  > logStartingPositions[l] -20 && (int) ypos  < logStartingPositions[l] +20 ){
					handler.getGame().setOnLog(true, l);
				}
			}
		}else{
			for(int l = 0; l < numLogs; l++){
				if( (int) ypos  > logStartingPositions[l] -5 && (int) ypos  < logStartingPositions[l] +5 ){
					handler.getGame().setOnLog(false, l);
				}
			}
		}

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
		// RIGHT NOW THE OBSTACLES ARE THE SAME SIZE AS THE PLAYERS
		
		boolean xConflict = (Math.abs(xpos + playerSize - (a + obsSize))<playerSize + 20);
		boolean yConflict = (Math.abs(ypos + playerSize - (b + obsSize))<playerSize + 20);

		if(xConflict && yConflict){
			return true;
		}else{
			return false;
		}
	}

	public void moveY(){
	
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

