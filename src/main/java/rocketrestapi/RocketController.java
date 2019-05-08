package rocketrestapi;

public class RocketController {
	private RocketStatus status;
	private RocketCountdown countdown;
	
	public RocketController(RocketCountdown countdown) {
		Application.logger.info("Creating new instance of RocketController.");
		Application.logger.debug(String.format("Creating new instance of RocketController.\ncountdown: %s", countdown));

		this.status = RocketStatus.WAITING;
		this.countdown = countdown;
	}
	
	public void start() throws RocketCountdownAlreadyStartedException {
		Application.logger.info("Running RocketController.start().");

		this.countdown.start();
		this.status = RocketStatus.COUNTDOWN;
	}
	
	public void reset() throws RocketCountdownNotStartedException {
		Application.logger.info("Running RocketController.reset().");

		this.countdown.reset();
		this.status = RocketStatus.WAITING;
	}
	
	public RocketStatus getStatus() {
		Application.logger.info("Running RocketController.getStatus().");

		if (this.status == RocketStatus.COUNTDOWN && this.countdown.getRemainingTime() <= 0) {
			this.status = RocketStatus.SHOOTED;
		}
		
		Application.logger.debug(String.format("Returning %s in RocketController.getStatus().", this.status));
		return this.status;
	}
	
	public RocketCountdown getCountdown() {
		Application.logger.info("Running RocketController.getCountdown().");
		Application.logger.debug("Returning %s in RocketController.getCountdown().", this.countdown);
		return this.countdown;
	}
}
