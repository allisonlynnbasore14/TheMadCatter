
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Utils{
	// This class if for more of the backend
	// This is for loading files and parseing
	
	// LoadFileAsString load the worlds
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();

		try{
			BufferedReader buffR = new BufferedReader(new FileReader(path));
			String l;
			while((l = buffR.readLine()) != null){
				builder.append(l + "\n");
			}
			buffR.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return builder.toString();
	}

	// Parse Init turns the txt file info into the world code
	public static int parseInt(String num){
		try{
			return Integer.parseInt(num);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}


}
