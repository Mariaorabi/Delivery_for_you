import java.io.*;
public class SaveFiles {
	BufferedReader reader;
	BufferedWriter writer;
	public SaveFiles(String s){
		try{
			reader = new BufferedReader(new FileReader(s));
			writer = new BufferedWriter(new FileWriter(s,true));
		}
		catch(FileNotFoundException e){}
		catch(IOException e){}
	}
}