package rocketrestapi;

import java.util.Date;

/*
 * Class that will manage the rocket countdown and calculate the remaining time.
 */
public class RocketCountdown {
	private Date startingDate;
	private Date expectedFinishingDate;
	private final int countdownTime;

	/*
	 * Initializes the rocket countdown but doesn't start it.
	 * 
	 * @param countdownTime The time in miliseconds which will take the countdown.
	 */
	public RocketCountdown(int countdownTime) {
		Application.logger.info("Creating new instance of RocketCountdown.");
		Application.logger.debug(String.format("creating new instance of RocketCountdown.\ncountdownTime: %s", countdownTime));
		this.countdownTime = countdownTime;
	}

	/*
	 * Starts the countdown.
	 * 
	 * @throws RocketCountdownAlreadyStartedException If the countdown was already started.
	 */
	public void start() throws RocketCountdownAlreadyStartedException {
		Application.logger.info("Running RocketCountdown.start().");

		if (this.startingDate != null) {
			Application.logger.info("startingDate already defined, throwing RocketCountdownAlreadyStartedException.");
			throw new RocketCountdownAlreadyStartedException();
		}

		this.startingDate = new Date();
		this.expectedFinishingDate = new Date(this.startingDate.getTime() + this.countdownTime);
	}

	/*
	 * Resets an started countdown to its initial state.
	 * 
	 * @throws RocketCountdownNotStartedException If the countdown hasn't started.
	 */
	public void reset() throws RocketCountdownNotStartedException {
		Application.logger.info("Running RocketCountdown.reset().");

		if (this.startingDate == null) {
			Application.logger.info("startingDate not defined, throwing RocketCountdownNotStartedException.");
			throw new RocketCountdownNotStartedException();
		}

		this.startingDate = null;
		this.expectedFinishingDate = null;
	}

	public int getCountdownTime() {
		Application.logger.info("Running RocketCountdown.getCountdownTime().");
		Application.logger.debug(String.format("Returning %s in RocketCountdown.getCountdownTime().", this.countdownTime));
		return this.countdownTime;
	}

	/*
	 * Calculates the time remaining until the countdown ends. 0 if it has finished.
	 * 
	 * @return The remaining time.
	 */
	public double getRemainingTime() {
		Application.logger.info("Running RocketCountdown.getRemainingTime().");

		if (this.startingDate == null) {
			double remainingTime = (double) this.countdownTime / 1000;
			Application.logger.debug(String.format("Returning %s in RocketCountdown.getRemainingTime().", remainingTime));
			return remainingTime;
		}

		Date currentTime = new Date();
		long remainingTimeInMs = this.expectedFinishingDate.getTime() - currentTime.getTime();
		double remainingTime = (double) remainingTimeInMs / 1000;

		if (remainingTime < 0) remainingTime = 0;

		Application.logger.debug(String.format("Returning %s in RocketCountdown.getRemainingTime().", remainingTime));
		return remainingTime;
	}
}
