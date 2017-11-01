
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;

public class WaterTile extends Tile{

	private BufferedImage img;

	public WaterTile( int id){
		try {
		    img = ImageIO.read(new File("water.png"));
		} catch (IOException e) {
			
		}

		super(img, id);
	}

}