
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Log extends Helpers{
	// Log is the current only extension of Helpers
	public static BufferedImage img;
	// Image from http://www.clker.com/clipart-wood-sign-only.html
	// Since the log is now taking up the whole water tile it is 40 by 20 instead of 20 by 20
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
