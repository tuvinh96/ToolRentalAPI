package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	String configFilePath;
	public Config(String configFilePath) {
		this.configFilePath = configFilePath;
	}
	public String getValueByKey(String key) {
		String result = "";
		Properties properties = new Properties();
		File configFile = new File(configFilePath);
		try {
			InputStream inputStream = new FileInputStream(configFile);
			properties.load(inputStream);
			result = properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
