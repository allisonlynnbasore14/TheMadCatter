
public abstract class Creature extends Entity{

	protected float speed;
	protected float xMove, yMove;
	private float prevX;
	private float prevY;

	public Creature(Handler handler, float x, float y, int width, int height, String type){
		super(handler, x, y, width, height, type);
		speed = 1.0f;
		xMove = 0;
		yMove = 0;

	}

	public void move(){

		int gameWidth = handler.getGame().getWidth();
		int gameHeight = handler.getGame().getHeight();
		
		if(xMove + x + width > gameWidth || xMove + x < 0){
			xMove = 0;
		}
		if(yMove + y + height > gameWidth || yMove + y < 0){
			yMove = 0;
		}

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

		// if(xMove != 0.0f){
		// 	x += xMove + 5;
		// }else{
			
		// }
		// float delta = 1.0f;
		// if(prevX < 0.0){
		// 	if(xMove >= -3.0f && prevX != 0.0f && prevX < - delta){
		// 		x += prevX +  delta;
		// 		prevX = prevX  +  delta;
		// 		System.out.println("negatory");
		// 	}else{
		// 		x+= xMove;
		// 		prevX = xMove;
		// 	}
		// }else{

		// 	if(xMove <= 3.0f && prevX != 0.0f && prevX >  delta){
		// 		x += prevX -  delta;
		// 		prevX = prevX -  delta;
		// 		System.out.println("posatory");
		// 	}else{
		// 		x+= xMove;
		// 		prevX = xMove;
		// 	}
		// }

	}

	public void moveY(){
			y += yMove;


		// float delta = 1.0f;
		// if(prevY < 0.0){
		// 	if(yMove >= -3.0f && prevY != 0.0f && prevY < - delta){
		// 		y += prevY +  delta;
		// 		prevY = prevY  +  delta;
		// 		System.out.println("negatory");
		// 	}else{
		// 		y+= yMove;
		// 		prevY = yMove;
		// 	}
		// }else{

		// 	if(xMove <= 3.0f && prevY != 0.0f && prevY >  delta){
		// 		y += prevY -  delta;
		// 		prevY = prevY -  delta;
		// 		System.out.println("posatory");
		// 	}else{
		// 		y+= yMove;
		// 		prevY = yMove;
		// 	}
		// }
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