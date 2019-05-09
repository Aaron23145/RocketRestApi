package rocketrestapi;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * The Rest Controller that will manage and map all requests.
 */
@RestController
public class RestApiController {
	/*
	 * Starts the rocket countdown. If it has been started it displays an error message.
	 * 
	 * @throws ResponseStatusException If the countdown was already started
	 */
	@PostMapping("/start")
	@ResponseStatus(code = HttpStatus.OK, reason = "Countdown started.")
	public void startCountdown() {
		Application.logger.info("POST Request received in /start. Running RestApiController.startCountdown().");

		try {
			Application.rocketController.start();
			Application.logger.info("Rocket Countdown started.");
		} catch (RocketCountdownAlreadyStartedException e) {
			Application.logger.warn("Tried to start an already started Rocket Countdown.\nDetailed exception: " + e);
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Rocket countdown is already started. Reset it first.");
		}
	}
	
	/*
	 * Resets the rocket countdown. If it hasn't been started it displays an error message.
	 * 
	 * @throws ResponseStatusException If the countdown wasn't already started
	 */
	@PostMapping("/reset")
	@ResponseStatus(code = HttpStatus.OK, reason = "Countdown resetted successfully.")
	public void resetCountdown() {
		Application.logger.info("POST Request received in /reset. Running RestApiController.resetCountdown().");

		try {
			Application.rocketController.reset();
			Application.logger.info("Rocked Countdown resetted.");
		} catch (RocketCountdownNotStartedException e) {
			Application.logger.warn("Tried to reset the Rocket Countdown that was not started.\nDetailed exception: " + e);
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Rocket countdown hasn't started, so a reset can't be performed.");
		}
	}

	@GetMapping("/status")
	public Response<RocketStatus> getRocketStatus() {
		Application.logger.info("GET Request received in /status. Running RestApiController.getRocketStatus().");
		return new Response<>(Application.rocketController.getStatus(), "/status/");
	}

	@GetMapping("/countdown")
	public Response<RocketCountdown> getCountdown() {
		Application.logger.info("GET Request received in /countdown. Running RestApiController.getCountdown().");
		return new Response<>(Application.rocketController.getCountdown(), "/countdown/");
	}
}
