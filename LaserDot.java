
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class LaserDot extends Targets{


	public static BufferedImage img;

	public LaserDot(Handler handler, float x, float y, String type){
		super(handler, x, y, 20, 20, type);

	}

	public void tick(){
		move();

	}

	public void render(Graphics g){
		try {
		    img = ImageIO.read(new File("laserDot.png"));
		} catch (IOException e) {
			
		}
		g.drawImage(img, (int) x, (int) y, 20, 20, null);
	}
	

}
