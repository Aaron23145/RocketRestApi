package rocketrestapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	private final InputStream inputStream;
	private Properties props;
	
	public PropertiesReader(String propsFileName) throws PropertiesFileDoesNotExistException, IOException {
		this.inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);

		if (this.inputStream == null) {
			throw new PropertiesFileDoesNotExistException(String.format("%s file does not exist.", propsFileName));
		} else {
			this.props = new Properties();
			this.props.load(inputStream);
		}
	}
	
	public String getProperty(String propertyName) throws PropertiesReaderUninitializedException {
		if (this.inputStream == null) {
			throw new PropertiesReaderUninitializedException("PropertiesReader was not properly initialized. invalid inputStream.");
		} else {
			return this.props.getProperty(propertyName);
		}
	}
}
