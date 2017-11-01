import java.awt.Graphics;

public abstract class Entity{

	// an abstract class that is a template for the creature class
	protected Handler handler;
	protected float x,y;
	protected int width, height;
	protected String type;

	public Entity(Handler handler, float x, float y, int width, int height, String type){
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.type = type;
		// setting requrired attributes
	}

	public abstract void tick();
	// Requring a tick method

	public abstract void render(Graphics g);
	// requring arender method

	// Getters

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}




	// Setters

	public void setWidth(int width){
		this.width = width;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public void setX(float x){
		this.x = x;
	}

	public void setY(float y){
		this.y = y;
	}


}
