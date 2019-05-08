package rocketrestapi;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class RestApiController {
	private RocketController rocketController;
	
	public RestApiController() {
		Application.logger.info("Initializing the RestApiController.");
		
		String propsFileName = PropertiesFile.getPropsFileName();
		String countdownTimePropName = PropertiesFile.getCountdownTimePropName();
		
		Application.logger.debug(String.format("Data loaded from PropertiesFile:\nProperties File Name: %s\nCountdown Time Property Name: %s", propsFileName, countdownTimePropName));
		
		try {
			PropertiesReader propertiesReader = new PropertiesReader(propsFileName);
			String countdownTime = propertiesReader.getProperty(countdownTimePropName);
			Application.logger.debug(String.format("countdownTime property is set to %s.", countdownTime));
			RocketCountdown countdown =  new RocketCountdown(Integer.parseInt(countdownTime));
			this.rocketController = new RocketController(countdown);
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
		
		Application.logger.info("RestApiController initialized successfully.");
	}
	
	@PostMapping("/start")
	public Response<String> startCountdown() {
		Application.logger.info("POST Request received in /start.");
		
		try {
			this.rocketController.start();
			Application.logger.info("Rocket Countdown started.");
			return new Response<>("Countdown started!");
		} catch (RocketCountdownAlreadyStartedException e) {
			Application.logger.warn("Tried to start an already started Rocket Countdown.\nDetailed exception: " + e);
			return new Response<>("Rocket countdown is already started! Reset it first.", false);
		}
	}
	
	@PostMapping("/reset")
	public Response<String> resetCountdown() {
		Application.logger.info("POST Request received in /reset.");
		
		try {
			this.rocketController.reset();
			Application.logger.info("Rocked Countdown resetted.");
			return new Response<>("Countdown resetted successfully.");
		} catch (RocketCountdownNotStartedException e) {
			Application.logger.warn("Tried to reset the Rocket Countdown that was not started.\nDetailed exception: " + e);
			return new Response<>("Rocket countdown hasn't started, so a reset can't be performed.", false);
		}
	}

	@GetMapping("/status")
	public Response<RocketStatus> getRocketStatus() {
		Application.logger.info("GET Request received in /status.");
		return new Response<>(this.rocketController.getStatus());
	}

	@GetMapping("/countdown")
	public Response<RocketCountdown> getCountdown() {
		Application.logger.info("GET Request received in /countdown.");
		return new Response<>(this.rocketController.getCountdown());
	}
}
