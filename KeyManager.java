import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyManager implements KeyListener{
// This class is for getting the ketbaord input from the user

	private boolean[] keys;
	// A boolean array that has a spot for each key
	// If a key is pressed the boolean for the key will switch to true
	public boolean up, down, left, right;
	public boolean aUp, aDown, aLeft, aRight, space;

	public KeyManager(){
		// Number of keys
		keys = new boolean[256];
	}

	public void tick(){
		
		// the WSAD set of controls
		// Not used currently
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];

		// The Arrow set of keys
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];

		// The Space bar
		space = keys[KeyEvent.VK_SPACE];
	}

	public void keyPressed(KeyEvent e){
		// When it is pressed, change the value in the array to true
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e){
		// When it is released, change the value in the array to false
		keys[e.getKeyCode()] = false;
		
	}

	public void keyTyped(KeyEvent e){
		// Placeholder
	}

}
