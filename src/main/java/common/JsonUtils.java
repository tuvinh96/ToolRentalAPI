package common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	public boolean isCheckJsonArray(String jsonBody) {
		boolean result = false;
		JSONParser parser = new JSONParser();
		Object jsonObject;
		try {
			jsonObject = parser.parse(jsonBody);
			if (jsonObject instanceof JSONArray) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<String> getDataByKey(String json, String key) {
		ArrayList<String> results = new ArrayList<>();
		JSONParser parser = new JSONParser();
		if (isCheckJsonArray(json)) {
			try {
				JSONArray jsonArray = (JSONArray) parser.parse(json);
				for (Object obj : jsonArray) {
					JSONObject jsonObject = (JSONObject) obj;
					if (jsonObject.containsKey(key)) {
					results.add(jsonObject.get(key).toString());
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			try {
				JSONObject jsonObject = (JSONObject) parser.parse(json);
				if (jsonObject.containsKey(key)) {
					results.add(jsonObject.get(key).toString());
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
	
}
