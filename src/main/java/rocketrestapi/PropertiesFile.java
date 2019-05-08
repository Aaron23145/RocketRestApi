package rocketrestapi;

/*
 * Stores the properties file name and its properties that will be needed. It will be used by the PropertiesReader.
 * @see PropertiesReader
 */
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
