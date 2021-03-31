package com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import com.constants.UIConstants;

public class PropertyReader {
	
	private Properties prop;
	
	public PropertyReader() {
		String defaultConfigFile = UIConstants.DEFAULT_CONFIG_FILE;
		prop = new Properties();
		loadProperties(defaultConfigFile);
	}
	
	public PropertyReader(String fileName) {
		prop = new Properties();
		loadProperties(fileName);
	}
	
	public void loadProperties(String fileName) {
		try {
			String fileRoot = System.getProperty("user.dir");
			File file = new File(fileRoot + fileName);
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);

			FileReader reader = new FileReader(fileRoot + fileName);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String  getProperty(String key) {
		return (String) prop.get(key);
	}
	
	public HashMap<String, String> getProperties(){
		HashMap<String, String> propertiesMap = new HashMap<String, String>();
		Set<Object> keys = prop.keySet();
		String value; 
		for(Object obj: keys) {
			String key = (String) obj;
			value = getProperty(key);
			propertiesMap.put(key, value);
		}
		return propertiesMap;
	}
}
