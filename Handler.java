
public class Handler {
	// This class is for handleing the interactions between other classes
	// For example, if the player class needs to know where the obstacles are, it references this to get the game etc..
	
	private Game game;
	private World world;
	
	public Handler(Game game){
		this.game = game;
	}
	
	// Getters
	
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
