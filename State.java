
import java.awt.Graphics;

public abstract class State {
	// This is an abstract class for the state files
	// It sets a template for its childrent classes

	protected Handler handler;

	public State(Handler handler){
		this.handler = handler;
	}

	private static State currentState = null;

	public abstract void tick();

	public abstract void render(Graphics g);


	// Setters 

	public static void setState(State state){
		currentState = state;
	}


	// Getters

	public static State getState(){
		return currentState;
	}

}
