
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;


public class Game implements Runnable{

	private boolean running = false;
	public Thread thread;
	private Display display;
	private Handler hand;
	private int width, height;
	public String title;

	// public boolean inWater1;
	// public boolean onLog1;

	public boolean[] inWater;
	public boolean[] onLog;

	// public boolean inWater2;
	// public boolean onLog2;

	private BufferStrategy buffStrat;
	private Graphics g;
	private KeyManager keyManager;
	private GameState gameState;
	//private EndState endState;
	private StartState startState;
	//private WinState winState;
	private Handler handler; 

	public float yLog;
	public float xLog;

	// public varibales can be manipulated by all classes
	// private variabels can be manipulated and accessed by only this class
	// protected variables can be manipulated and access by only this class and any sub class

	// If something is private but you want the share option, use getters and setters

	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;

	}

	public void init(){

		handler = new Handler(this);
		display = new Display(title, width, height);
		keyManager = new KeyManager();

		yLog = 0;
		xLog = 0;

		inWater = new boolean[2];
		onLog = new boolean[2];

		for(int i=0; i<inWater.length;i++){
			inWater[i] = false;
			onLog[i] = false;
		}
		// inWater1 = false;
		// onLog1 = false;

		// inWater2 = false;
		// onLog2 = false;

		startState = new StartState(handler);
		gameState = new GameState(handler);
		//endState = new EndState(handler);
		//winState = new WinState(handler);
		
		//State.setState(endState);
		State.setState(startState);
		display.getFrame().addKeyListener(keyManager);

	}

	public void tick(){
		if (State.getState() != null ){
			State.getState().tick();
		}

		keyManager.tick();

	}

	public void render(){
		buffStrat = display.getCanvas().getBufferStrategy();
		// a buffer strategy: how to tell the computer to draw, a buffer is a hidden computer screen inside your computer
		// without this it would glitch like old games did
		// Basically it loads a several frames in advance and roates the path
		// You can only do this with enough memory to hold three version of a large display object
		if(buffStrat == null){
			display.getCanvas().createBufferStrategy(2);
			// generally this is 2 or 3
			return;
		}

		g = buffStrat.getDrawGraphics(); // Gets the graphics object to paint on the canvas with
		g.clearRect(0,0, width, height); // must be called before anything is drawn

		if(State.getState() != null){
			State.getState().render(g); // calls the rendering of the state with the graphics object
		}

		// Temporary testing block
		// g.setColor(Color.blue);
		// g.fillRect(30,100,40,100);

		buffStrat.show();
		g.dispose();
	}

	public void run(){
		init();

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
				// System.out.println("Ticks " + ticks);
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

	// GETTERS

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

