
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

// An obstacle that looks like a shark
// Image from http://pngimg.com/download/18832

public class Shark extends Obstacles{


	public static BufferedImage img;

	public Shark(Handler handler, float x, float y, String type){
		super(handler, x, y, 20, 20, type);

	}

	public void tick(){
		move();

	}

	public void render(Graphics g){
		try {
		    img = ImageIO.read(new File("shark.png"));
		} catch (IOException e) {
			
		}
		g.drawImage(img, (int) x, (int) y, 50, 20, null);
	}
	

}
