package rocketrestapi;

import java.util.Date;
import java.io.InputStream;


public class RocketCountdown {
	private Date startingDate;
	private Date expectedFinishingDate;
	private final int countdownTime;
	
	InputStream inputStream;

	public RocketCountdown(int countdownTime) {
		Application.logger.info("Creating new instance of RocketCountdown.");
		Application.logger.debug(String.format("creating new instance of RocketCountdown.\ncountdownTime: %s", countdownTime));
		this.countdownTime = countdownTime;
	}
	
	public void start() throws RocketCountdownAlreadyStartedException {
		Application.logger.info("Running RocketCountdown.start().");
		
		if (this.startingDate != null) {
			Application.logger.info("startingDate already defined, throwing RocketCountdownAlreadyStartedException.");
			throw new RocketCountdownAlreadyStartedException();
		}
		
		this.startingDate = new Date();
		this.expectedFinishingDate = new Date(this.startingDate.getTime() + this.countdownTime);
	}
	
	public void reset() throws RocketCountdownNotStartedException {
		Application.logger.info("Running RocketCountdown.reset().");
		
		if (this.startingDate == null) {
			Application.logger.info("startingDate not defined, throwing RocketCountdownNotStartedException.");
			throw new RocketCountdownNotStartedException();
		}
		
		this.startingDate = null;
		this.expectedFinishingDate = null;
	}

	public Date getStartingDate() {
		Application.logger.info("Running RocketCountdown.getStartingDate().");
		Application.logger.debug(String.format("Returning %s in RocketCountdown.getStartingDate().", this.startingDate));
		return this.startingDate;
	}

	public Date getExpectedFinishingDate() {
		Application.logger.info("Running RocketCountdown.getExpectedFinishingDate().");
		Application.logger.debug(String.format("Returning %s in RocketCountdown.getExpectedFinishingDate().", this.expectedFinishingDate));
		return this.expectedFinishingDate;
	}

	public int getCountdownTime() {
		Application.logger.info("Running RocketCountdown.getCountdownTime().");
		Application.logger.debug(String.format("Returning %s in RocketCountdown.getCountdownTime().", this.countdownTime));
		return this.countdownTime;
	}
	
	public double getRemainingTime() {
		Application.logger.info("Running RocketCountdown.getRemainingTime().");
		
		if (this.startingDate == null) {
			int remainingTime = this.countdownTime;
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
