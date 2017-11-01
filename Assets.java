
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Assets{


	public static BufferedImage kitten = null;
	public static BufferedImage img;

	public static void init(){
		// try{
			
		// 	kitten = ImageIO.read(new File("cat.PNG"));
		// 	System.out.println(kitten);
				
		// }catch(IOException e){
		// 	e.printStackTrace();
			
		// }

		try {
		    img = ImageIO.read(new File("Darcy.gif"));
		} catch (IOException e) {
			
		}

		System.out.println(img);
		
	}

}