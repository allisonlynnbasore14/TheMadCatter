
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class GrassTile extends Tile{

	private BufferedImage img;

	public GrassTile( int id){

		super{try {img = ImageIO.read(new File("grass.png"))} catch (IOException e) {}, id);
	}

}