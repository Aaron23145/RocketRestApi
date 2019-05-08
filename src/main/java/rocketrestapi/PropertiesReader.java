package rocketrestapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * Reader that will extract and return the properties values in the configuration file.
 */
public class PropertiesReader {
	private final InputStream inputStream;
	private Properties props;

	/*
	 * Initializes the inputStream to be able to read the content of the properties file.
	 * 
	 * @param propsFileName The name of the properties file to read.
	 * @throws PropertiesFileDoesNotExistException If the file is not found.
	 * @throws IOException If an IO error occurs.
	 */
	public PropertiesReader(String propsFileName) throws PropertiesFileDoesNotExistException, IOException {
		Application.logger.info("Creating new instance of PropertiesReader.");
		Application.logger.debug(String.format("Creating new instance of PropertiesReader.\npropsFileName value: %s", propsFileName));

		this.inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);

		if (this.inputStream == null) {
			Application.logger.debug("Properties file read from PropertiesFile doesn't exist. Throwing PropertiesFileDoesNotExistException.");
			throw new PropertiesFileDoesNotExistException(String.format("%s file does not exist.", propsFileName));
		} else {
			this.props = new Properties();
			this.props.load(this.inputStream);
			Application.logger.debug("Properties file read successfully and properties loaded.");
		}
	}

	/*
	 * Gets a property from the properties file loaded.
	 * 
	 * @param propertyName The name of the property to get.
	 * @throws PropertiesReaderUninitializedException If the PropertiesReader was not initialized successfully and you try to
	 * 	       get a property.
	 */
	public String getProperty(String propertyName) throws PropertiesReaderUninitializedException {
		Application.logger.info("Running PropertiesReader.getProperty().");
		Application.logger.debug(String.format("Running PropertiesReader.getProperty. propertyName: %s", propertyName));

		if (this.inputStream == null) {
			Application.logger.warn("Tried to get a property with an uninitialized PropertiesReader. Throwing PropertiesReaderUninitializedException.");
			throw new PropertiesReaderUninitializedException("PropertiesReader was not properly initialized. invalid inputStream.");
		} else {
			String propertyValue = this.props.getProperty(propertyName);
			Application.logger.debug(String.format("Read %s property and obtained %s value.", propertyName, propertyValue));
			return propertyValue;
		}
	}
}
