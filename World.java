
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;


// THi FILE IS NOT DONE YET!!!

public class World {

	private int width, height;
	private int[][] tiles;
	private Handler handler;
	private Player player;
	private int[] roadPositions;
	private int[] waterPositions;
	private int numWater;
	private int[] waterStartingPositions;

	public static final int TILEWIDTH = 50, TILEHEIGHT = 50;

	protected BufferedImage texture;
	private int id;
	private BufferedImage img;
	private String tileString;

	public World(Handler handler, String path){

		this.handler = handler;
		//endState = new EndState(handler);
		numWater = 0;
		loadWorld(path);

	
	}

	public void tick(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(tiles[x][y] == 1){
					//float xpos = handler.getGame().getGameState().getPlayer().getX();
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
							if(y * Tile.TILEHEIGHT == waterStartingPositions[l]){
								// System.out.println(l);
								// System.out.println("ONLY ONE L SHOULD MAKE IT HERE");
								handler.getGame().setInWater(true, l);
							}
						}
					}else{
						for(int l = 0; l < numWater; l++){
							if(y * Tile.TILEHEIGHT == waterStartingPositions[l]){
								// System.out.println(l);
								// System.out.println("THE OTHER ONE SHOUDL MAKE IT HERE");

								handler.getGame().setInWater(false, l );
							}
						}
					}
				}
			}

		}
	}

	public void render(Graphics g){
		//player.render(g);

		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				g.drawImage(getTile(x,y), x*Tile.TILEWIDTH, y* Tile.TILEHEIGHT, null);
			}

		}
	}



	public BufferedImage getTileTexture( int id){

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
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+"); // Looking for whitespaces
		width = Utils.parseInt(tokens[0]); // The first element of the file
		height = Utils.parseInt(tokens[1]); // The second
		tiles = new int[width][height];
		roadPositions = new int[height];
		waterPositions = new int[height];
		for(int y=0; y < height; y++){
			for(int x=0; x< width; x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
			}
		}
		for(int m = 0; m < height; m++){
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
		// handler.getGame().getGameState().getNumLogs()
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
		int tileNum = tiles[x][y];
		// making a list of the road positions

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