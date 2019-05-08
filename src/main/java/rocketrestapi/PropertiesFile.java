package rocketrestapi;

public class PropertiesFile {
	private static String propsFileName = "application.properties";
	private static String countdownTimePropName = "countdownTime";
	
	public static String getPropsFileName() {
		return PropertiesFile.propsFileName;
	}
	
	public static String getCountdownTimePropName() {
		return PropertiesFile.countdownTimePropName;
	}
}
