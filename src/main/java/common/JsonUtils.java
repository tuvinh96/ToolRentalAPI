package common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {
	public String readJsonFile(String fileName) {
		String jsonContent = "";
		try {
			String projectPath = System.getProperty("user.dir") + "/testcase/TestData/";
			String sourceFilePath = projectPath + fileName;
			String destinationFilePath = projectPath + fileName.replace(".json", "_Temp.json");
			jsonContent = copyJsonFile(new File(sourceFilePath), new File(destinationFilePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonContent;
	}
	
	public String copyJsonFile(File sourceFile, File destinationFile) {
		String jsonContent = "";
		if (destinationFile.exists()) {
			destinationFile.delete();
		}
		try {
			Files.copy(sourceFile.toPath(), destinationFile.toPath());
			jsonContent = new String(Files.readAllBytes(destinationFile.toPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonContent;
	}
	
	
}
