
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


// An obstacle that is looks like a truck
// Image from http://carpng.com/tag/small-truck/

public class Truck extends Obstacles{


	public static BufferedImage img;

	public Truck(Handler handler, float x, float y, String type){
		super(handler, x, y, 20, 20, type);

	}

	public void tick(){
		move();

	}

	public void render(Graphics g){
		try {
		    img = ImageIO.read(new File("truck.png"));
		} catch (IOException e) {
			
		}
		g.drawImage(img, (int) x, (int) y, 20, 20, null);
	}
	

}
