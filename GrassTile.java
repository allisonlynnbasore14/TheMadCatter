
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

// Class for making the grass tile
// grass image from http://www.powerpointhintergrund.com/ppt-image/green-grass-texture-hd-wallpaper-wallpaper-list-5424.html

public class GrassTile extends Tile{

	private BufferedImage img;

	public GrassTile( int id){

		super{try {img = ImageIO.read(new File("grass.png"))} catch (IOException e) {}, id);
	}

}
