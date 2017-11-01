
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;

	// A class for running the game's functions
public class Game implements Runnable{
	// Having Game impliment Runnable alows us to create a thread
	private boolean running = false;
	public Thread thread;
	private Display display;
	private Handler hand;
	private int width, height;
	public String title;

	// variables for determining if the player is in the water and on a log
	public boolean[] inWater;
	public boolean[] onLog;
	
	private BufferStrategy buffStrat;
	private Graphics g;
	private KeyManager keyManager;
	private GameState gameState;
	private StartState startState;
	private Handler handler; 

	public float yLog;
	public float xLog;

	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		// initlalizing the game settings
	}

	public void init(){

		handler = new Handler(this);
		display = new Display(title, width, height);
		keyManager = new KeyManager();
		// creating the handler, display, and keyManager
		yLog = 0;
		xLog = 0;

		inWater = new boolean[2];
		onLog = new boolean[2];
		// createing the arrays for inWater and onLog
		// Setting inital values to be false
		for(int i=0; i<inWater.length;i++){
			inWater[i] = false;
			onLog[i] = false;
		}

		startState = new StartState(handler);
		gameState = new GameState(handler);
		
		// setting the inital state to be the start state
		State.setState(startState);
		
		// setting up the frame's key manager
		display.getFrame().addKeyListener(keyManager);

	}

	public void tick(){
		
		// calling the tick function of whatever state the user is in
		
		if (State.getState() != null ){
			State.getState().tick();
		}
		// calling tick for the keyManager
		keyManager.tick();

	}

	public void render(){
		buffStrat = display.getCanvas().getBufferStrategy();
		// Using a buffer strategy to tell the computer to draw, the buffer is a hidden computer screen inside your computer
		// Basically it is loading several frames in advance and routes the path
		// You can only do this with enough memory to hold three version of a large display object
		if(buffStrat == null){
			display.getCanvas().createBufferStrategy(2); // generally this is 2 or 3
			return;
		}

		g = buffStrat.getDrawGraphics(); // Gets the graphics object to paint on the canvas with
		g.clearRect(0,0, width, height); // must be called before anything is drawn

		if(State.getState() != null){
			State.getState().render(g); // calls the rendering of the state with the graphics object
		}

		buffStrat.show();
		g.dispose();
	}

	public void run(){
		init();
		// Setting the speed of the game display
		int fps = 60; // frames per second
		double timePerTic = 1000000000/ fps; // converting
		double delta = 0; // change in time variable
		long now; // time variable as a long variable
		long lastTime = System.nanoTime(); // last time variable
		long timer = 0;
		int ticks = 0;

		while(running){
			now = System.nanoTime();  // gets the time
			delta += (now-lastTime)/timePerTic; // calcuates time difference
			timer += (now - lastTime); // sets timer 
			lastTime = now; // resets lastime

			if(delta >= 1){
				tick();
				render(); // runs next frame and resets
				ticks ++;
				delta--;
			}

			if(timer >= 1000000000){
				ticks = 0;
				timer = 0; // resets
			}

		}

		stop();
		// stops the game when it is running is false

	}

	public synchronized void start(){ 
		// The synchronized part means that this can only be executed for one trhead at a time. 
		// Also other synchronized things can only do thier thing after this one is done
		if(running){
			return; // We dont want to do anything if this thread is already running
		}
		running = true;
		thread = new Thread(this); // starts new thread
		thread.start();

	}

	public synchronized void stop(){
		if(running){
			running = false;
			try{
				thread.join();
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}else{
			return;
		}
	}

	// Getters and Setters

	public int getWidth(){
		return width;
	}

	public KeyManager getKeyManager(){
		return keyManager;
	}

	public int getHeight(){
		return height;
	}

	public GameState getGameState(){
		return gameState;
	}


	public StartState getStartState(){
		return startState;
	}

	public void setInWater(boolean set, int id){
		inWater[id] = set;
	}

	public boolean getInWater(int id){
		return inWater[id];
	}

	public void setOnLog(boolean set, int id){
		onLog[id] = set;
	}

	public int getOnLog(){
		// returns -1 if the player is not on a log
		for(int i = 0; i< onLog.length; i ++){
			if(onLog[i] == true){
				System.out.println(i);
				return i;
			}
		}
		return -1;
		
	}

	public float getYLog(){
		return yLog;
	}

	public float getXLog(){
		return xLog;
	}

	public void setYLog(float y){
		yLog = y;
	}

	public void setXLog( float x){
		xLog = x;
	}

}

