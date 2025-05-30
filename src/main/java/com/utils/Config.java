package com.utils;

public class Config {
	public String readProperty(String Key) {
		String baseDir = System.getProperty("user.dir");
		String FilePath = baseDir + "/src/main/resources/config.properties";

		PropUtil pu = new PropUtil(FilePath);
		return pu.readPropertiesFile(FilePath, Key);

	}
	
	public String openBrowser() {
		return readProperty("browser");

	}
	
	public String launchUrl() {
		return readProperty("baseUrl");

	}
	
	public String searchTerm() {
		return readProperty("searchProduct");
	}

	public String sortType() {
		return readProperty("sortType");
	}

	public int pageLimit() {
		String value = readProperty("pageLimit");
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Invalid number for pageLimit in config.properties: " + value);
		}
	}

}
