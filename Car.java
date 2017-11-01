
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


// An obstacle that is looks like a car
// Image from https://openclipart.org/detail/202079/car-ds4

public class Car extends Obstacles{


	public static BufferedImage img;

	public Car(Handler handler, float x, float y, String type){
		super(handler, x, y, 20, 20, type);

	}

	public void tick(){
		move();

	}

	public void render(Graphics g){
		try {
		    img = ImageIO.read(new File("car.png"));
		} catch (IOException e) {
			
		}
		g.drawImage(img, (int) x, (int) y, 20, 20, null);
	}
	

}
