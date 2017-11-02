
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;

// Class for making the Road tile
// Road image from https://www.freecreatives.com/textures/road-texture.html
public class RoadTile extends Tile{

	private BufferedImage img;

	public RoadTile( int id){
		try {
		    img = ImageIO.read(new File("road.png"));
		} catch (IOException e) {
			
		}

		super(img, id);
	}

}
