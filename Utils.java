
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Utils{

// Not sure why this does not need its own instalization

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

	public static int parseInt(String num){
		try{
			return Integer.parseInt(num);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}


}