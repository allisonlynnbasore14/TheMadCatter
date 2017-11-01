
public class Handler {
	
	private Game game;
	private World world;


	
	public Handler(Game game){
		this.game = game;
		
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}



	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public int testing(){
		return 2;
	}


}


// public class Handler{
// 	private Game game;
// 	private World world;

// 	public Handler(Game game){
// 		this.game = game;
// 	}


// 	// Getters

// 	public Game getGame(){
// 		return game;
// 	}

// 	public int getWidth(){
// 		return game.getWidth();
// 	}

// 	public int getHeight(){
// 		return game.getHeight();
// 	}


// 	public World getWorld(){
// 		return world;
// 	}

// 	// Setters

// 	public void setGame(Game game){
// 		this.game = game;
// 	}

// 	public void setWorld(World world){
// 		this.world = world;
// 	}


// 	// public String getKeyManager(){
// 	// 	System.out.println("Made it to KYE");
// 	// 	return "TEST";//game.getKeyManager();
// 	// }


// }