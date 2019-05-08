package rocketrestapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.io.IOException;

import org.junit.Test;

import rocketrestapi.PropertiesFileDoesNotExistException;
import rocketrestapi.PropertiesReader;

public class PropertiesReaderTest {

	@Test
	public void testReadingValidPropertiesFile() throws IOException {
		String fileName = "application.properties";
		String propertyName = "countdownTime";
		
		PropertiesReader reader = new PropertiesReader(fileName);
		String propertyValue = reader.getProperty(propertyName);
		
		assertThat(reader).isNotNull();
		assertThat(propertyValue).isNotNull();
	}

	@Test
	public void testReadingUnexistingPropertiesFile() {
		String fileName = "unexistingfile.properties";
		
		Throwable thrown = catchThrowable(() -> {
			new PropertiesReader(fileName);
		});
		
		assertThat(thrown).isInstanceOf(PropertiesFileDoesNotExistException.class);
	}
}
