package rocketrestapi;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RocketCountdownTest {

	@Test
	public void testInstantiatingRocketCountdown() {
		int countdownTime = 5000;
		double expectedRemainingTime = (double) countdownTime / 1000;
		
		RocketCountdown countdown = new RocketCountdown(countdownTime);
		
		assertThat(countdown).isNotNull();
		assertThat(countdown.getCountdownTime()).isEqualTo(countdownTime);
		assertThat(countdown.getRemainingTime()).isEqualTo(expectedRemainingTime);
	}
	
	@Test
	public void testStartingAndRestartingRocketCountdown() throws RocketCountdownAlreadyStartedException, RocketCountdownNotStartedException, InterruptedException {
		int countdownTime = 5000;
		double expectedCountdownTime = (double) countdownTime / 1000;
		
		RocketCountdown countdown = new RocketCountdown(countdownTime);
		countdown.start();
		TimeUnit.MILLISECONDS.sleep(5);
		
		assertThat(countdown.getRemainingTime()).isLessThanOrEqualTo(expectedCountdownTime);
		
		countdown.reset();
		
		assertThat(countdown.getRemainingTime()).isEqualTo(expectedCountdownTime);
	}
	
	@Test
	public void testFinishedRocketCountdown() throws RocketCountdownAlreadyStartedException, InterruptedException {
		int countdownTime = 5;
		float expectedRemainingTime = 0;
		
		RocketCountdown countdown = new RocketCountdown(countdownTime);
		countdown.start();
		TimeUnit.MILLISECONDS.sleep(10);
		
		assertThat(countdown.getRemainingTime()).isEqualTo(expectedRemainingTime);
	}
}
