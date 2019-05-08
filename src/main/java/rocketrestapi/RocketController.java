package rocketrestapi;

public class RocketController {
	private RocketStatus status;
	private RocketCountdown countdown;
	
	public RocketController(RocketCountdown countdown) {
		this.status = RocketStatus.WAITING;
		this.countdown = countdown;
	}
	
	public void start() throws RocketCountdownAlreadyStartedException {
		this.countdown.start();
		this.status = RocketStatus.COUNTDOWN;
	}
	
	public void reset() throws RocketCountdownNotStartedException {
		this.countdown.reset();
		this.status = RocketStatus.WAITING;
	}
	
	public RocketStatus getStatus() {
		return this.status;
	}
	
	public RocketCountdown getCountdown() {
		return this.countdown;
	}
}
