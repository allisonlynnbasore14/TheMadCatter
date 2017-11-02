
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class World {

	// This class placecs the obstaclecs and loads the tiles according to the World text file
	
	private int width, height;
	private int[][] tiles;
	private Handler handler;
	private Player player;
	private int[] roadPositions;
	private int[] waterPositions;
	private int numWater;
	
	// This variable is for finding if the player is in the water or not
	private int[] waterStartingPositions;

	// The game screen is 300 by 300 with a 6 by 6 grid of 50 by 50 tiles
	public static final int TILEWIDTH = 50, TILEHEIGHT = 50;

	protected BufferedImage texture;
	private int id;
	private BufferedImage img;
	private String tileString;

	// The path variable is the path to the World text file that is being used
	public World(Handler handler, String path){

		this.handler = handler;
		numWater = 0;
		loadWorld(path);

	
	}

	public void tick(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				// Goes through all the tiles
				// If the tile is a water tile (i.e. 1) then it checks if the player is in the water
				if(tiles[x][y] == 1){
					float ypos = handler.getGame().getGameState().getPlayer().getY();
					int playerSize = handler.getGame().getGameState().getPlayer().PLAYER_SIZE;
					int tileY = y* Tile.TILEHEIGHT;

					float yDiff = ypos + playerSize - (tileY + Tile.TILEHEIGHT);
					boolean enterBottom = (yDiff < playerSize && yDiff > 0 );
					boolean enterTop = ( yDiff < playerSize && tileY - (ypos + playerSize) < playerSize);
					boolean inside = (yDiff < 0 && ypos > tileY && ypos + playerSize < tileY + Tile.TILEHEIGHT);
					boolean yConflict = (inside || enterBottom || enterTop);

					if(yConflict){
						for(int l = 0; l < numWater; l++){
							// If the player is in the water set the water and id to true in the game class
							if(y * Tile.TILEHEIGHT == waterStartingPositions[l]){
								handler.getGame().setInWater(true, l);
							}
						}
					}else{
						for(int l = 0; l < numWater; l++){
							// If the player is not in the water, set the boolean and id to false in the game state
							if(y * Tile.TILEHEIGHT == waterStartingPositions[l]){
								handler.getGame().setInWater(false, l );
							}
						}
					}
				}
			}

		}
	}

	public void render(Graphics g){
		// for all the tiles, render the graphic to the screen
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				g.drawImage(getTile(x,y), x*Tile.TILEWIDTH, y* Tile.TILEHEIGHT, null);
			}
		}
	}



	public BufferedImage getTileTexture( int id){
		// Coding the images to ids
		if(id == 0){

			tileString = "road.png";
		}else if(id == 1){
			tileString = "water.png";
		}else{
			tileString = "grass.png";
		}

		try {
		    img = ImageIO.read(new File(tileString));
		} catch (IOException e) {

		}
		return img;
	}

	public void loadWorld(String path){
		// This is just parsing the text file according to its custom format
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+"); // Looking for whitespaces
		width = Utils.parseInt(tokens[0]); // The first element of the file
		height = Utils.parseInt(tokens[1]); // The second element of the file
		tiles = new int[width][height]; // makes the tiles array
		roadPositions = new int[height];
		waterPositions = new int[height];
		for(int y=0; y < height; y++){
			for(int x=0; x< width; x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
				// Makes the tiles array from all the elements in the file
				// PareInt takes a string and returns an integer
			}
		}
		for(int m = 0; m < height; m++){
			// for all the rows, finding the water and road positions for obstacle reference
			if (tiles[1][m] == 0){
				roadPositions[m] = m;
			}else if(tiles[1][m]==1){
				waterPositions[m] = m;
				numWater = numWater + 1;
			}else{
				roadPositions[m] = 0;
				waterPositions[m] = 0;
			}
		}



		waterStartingPositions =  new int[numWater*2];
		// Makes the starting water positions array for log placement
		
		// The below code runs through the waterPositions array which is the size of height
		// Therefore most of the value in waterPositions is 0
		// This is for finding when the starting position is not 0 and using that to find the waterstarting positions
		int count = 0;
		for(int m = 0; m < numWater; m++){
			boolean out = false;
			while(!out)
				if (waterPositions[count] != 0){
					waterStartingPositions[m] = count * Tile.TILEHEIGHT;
					count = count + 1;
					out = true;
				}else{
					count = count + 1;
				}
		}
	}

	public BufferedImage getTile( int x, int y){
		
		// Getting the tile images and parsing the tiles array
		
		int tileNum = tiles[x][y];
		if(tileNum == 0){
			tileString = "road.png";
		}else if(tileNum == 1){
			tileString = "water.png";
		}else{
			tileString = "grass.png";
		}

		try {
		    img = ImageIO.read(new File(tileString));
		} catch (IOException e) {
			
		}
		return img;
	}
	
	// Getters

	public int[] getRoadPosition(){
		return roadPositions;
	}

	public int[] getWaterPosition(){
		return waterPositions;
	}

	public int getHeight(){
		return height;
	}

	public int[] getWaterStartingPositions(){
		return waterStartingPositions;
	}

	public int getNumWater(){
		return numWater;
	}
}
