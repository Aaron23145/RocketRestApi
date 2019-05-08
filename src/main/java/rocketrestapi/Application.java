package rocketrestapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static final Logger logger = LoggerFactory.getLogger(Application.class);
	public static RocketController rocketController;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		Application.initializeRocketController();
	}

	public static void initializeRocketController() {
		Application.logger.info("Running Application.initializeRocketController().");
		
		String propsFileName = PropertiesFile.getPropsFileName();
		String countdownTimePropName = PropertiesFile.getCountdownTimePropName();
		
		Application.logger.debug(String.format("Data loaded from PropertiesFile:\npropsFileName: %s\ncountdownTimePropName: %s", propsFileName, countdownTimePropName));
		
		try {
			PropertiesReader propertiesReader = new PropertiesReader(propsFileName);
			String countdownTime = propertiesReader.getProperty(countdownTimePropName);
			Application.logger.debug(String.format("%s property is set to %s.", countdownTimePropName, countdownTime));
			RocketCountdown countdown =  new RocketCountdown(Integer.parseInt(countdownTime));
			Application.logger.debug(String.format("%s property value is valid.", countdownTimePropName));
			Application.rocketController = new RocketController(countdown);
		} catch (PropertiesFileDoesNotExistException e) {
			Application.logger.error(String.format("The properties file does not exist. Aborting.\nPlease, check that you have a file named %s in src/main/resources.\nDetailed error exception: %s", propsFileName, e));
			System.exit(1);
		} catch (NumberFormatException e) {
			Application.logger.error(String.format("Could not get a valid value from the %s property in the properties file. Aborting.\nPlease, check that you have setted it and has a valid numeric value.\nDetailed error exception: %s", countdownTimePropName, e));
			System.exit(1);
		} catch (Exception e) {
			Application.logger.error("An Exception has ocurred. Aborting.\nDetailed error exception: " + e);
			System.exit(1);
		}
		
		Application.logger.info("RocketController initialized successfully.");
	}
}
