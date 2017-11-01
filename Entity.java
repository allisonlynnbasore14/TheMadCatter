import java.awt.Graphics;

public abstract class Entity{

	// the abstract means you cannot make an instance of an Enity, instead it is a template for subclasses
	protected Handler handler;
	protected float x,y;
	protected int width, height;
	protected String type;
	// protected Rectangle bounds;

	public Entity(Handler handler, float x, float y, int width, int height, String type){
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.type = type;
	
	}

	public abstract void tick();

	public abstract void render(Graphics g);

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