
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;


public class Tile {

	//public static Tile[] tiles = new Tile[10];
	// public Tile roadTile = getTileTexture(0);
	// public Tile grassTile = getTileTexture(1);
	// public Tile waterTile = getTileTexture(2);

	public static final int TILEWIDTH = 50, TILEHEIGHT = 50;

	protected BufferedImage texture;
	protected final int id;
	private BufferedImage img;
	private String tileString;


	public Tile( BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
	}

	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	public void tick(){
		
	}

	public boolean isSolid(){
		return false;
	}

	public int getId(){
		return id;
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
}

// Right now I need to figure out how to store these tiles inteh getTile texture mehtod into the tiles array