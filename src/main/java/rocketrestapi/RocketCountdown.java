package rocketrestapi;

import java.util.Date;
import java.io.InputStream;


public class RocketCountdown {
	private Date startingDate;
	private Date expectedFinishingDate;
	private final int countdownTime;
	
	InputStream inputStream;

	public RocketCountdown(int countdownTime) {
		this.countdownTime = countdownTime;
	}
	
	public void start() throws RocketCountdownAlreadyStartedException {
		if (this.startingDate != null) {
			throw new RocketCountdownAlreadyStartedException();
		}
		
		this.startingDate = new Date();
		this.expectedFinishingDate = new Date(this.startingDate.getTime() + this.countdownTime);
	}
	
	public void reset() throws RocketCountdownNotStartedException {
		if (this.startingDate == null) {
			throw new RocketCountdownNotStartedException();
		}
		
		this.startingDate = null;
		this.expectedFinishingDate = null;
	}

	public Date getStartingDate() {
		return this.startingDate;
	}

	public Date getExpectedFinishingDate() {
		return this.expectedFinishingDate;
	}

	public int getCountdownTime() {
		return this.countdownTime;
	}
	
	public double getRemainingTime() {
		if (this.startingDate == null) return this.countdownTime;
		
		Date currentTime = new Date();
		long remainingTimeInMs = this.expectedFinishingDate.getTime() - currentTime.getTime();
		double remainingTime = (double) remainingTimeInMs / 1000;
		
		if (remainingTime < 0) remainingTime = 0;

		return remainingTime;
	}
}
