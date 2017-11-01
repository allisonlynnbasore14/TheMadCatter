
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;

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