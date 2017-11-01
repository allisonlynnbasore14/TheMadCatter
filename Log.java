
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Log extends Helpers{


	public static BufferedImage img;

	public Log(Handler handler, float x, float y, String type){
		super(handler, x, y, 20, 40, type);

	}

	public void tick(){
		move();

	}

	public void render(Graphics g){
		try {
		    img = ImageIO.read(new File("log.png"));
		} catch (IOException e) {
			
		}
		g.drawImage(img, (int) x, (int) y, 20, 40, null);
	}
	

}
