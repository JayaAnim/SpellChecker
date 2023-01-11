package UWF.Project2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

public class FileManager {
	private File fileObj = null;
	private Scanner scanner = null;
	private FileWriter writer = null;
	public FileManager(String fileName) throws Exception {
		fileObj = new File(fileName);
		fileObj.createNewFile();
	}
	
	public String readFile() throws Exception {
		String returnString = "";
		if (fileObj.exists() && !fileObj.isDirectory()) { // checks if the file object already exists before reading from it
			scanner = new Scanner(fileObj);
			while (scanner.hasNextLine()) {
				returnString += scanner.nextLine();
			}
			scanner.close();
		}

		return returnString;
	}
	
	public void editFile(String newContent) throws Exception {
		if (fileObj.exists() && !fileObj.isDirectory()) {
			writer = new FileWriter(fileObj, false);
			writer.write(newContent);
			writer.close();
		}
	}

}
