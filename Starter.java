

public class Starter{
	public static void main( String[] args){
		// This is the file that is first called
		// It makes a game, title, size, and starts it
		Game game = new Game("The Mad Catter", 300, 300);
		game.start();
	}
}
