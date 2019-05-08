package rocketrestapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class RocketControllerTest {

	@Test
	public void testInstantiatingRocketController() {
		int countdownTime = 5000;
		RocketStatus expectedStatus = RocketStatus.WAITING;
		RocketCountdown countdown = new RocketCountdown(countdownTime);
		RocketController controller = new RocketController(countdown);
		
		assertThat(controller).isNotNull();
		assertThat(controller.getCountdown()).isEqualTo(countdown);
		assertThat(controller.getStatus()).isEqualTo(expectedStatus);
	}
	
	@Test
	public void testStartingAndResetingRocketController() throws RocketCountdownAlreadyStartedException, InterruptedException, RocketCountdownNotStartedException {
		int countdownTime = 5000;
		RocketStatus initialStatus = RocketStatus.WAITING;
		RocketStatus expectedStatus = RocketStatus.COUNTDOWN;
		RocketCountdown countdown = new RocketCountdown(countdownTime);
		RocketController controller = new RocketController(countdown);
		
		controller.start();
		TimeUnit.MILLISECONDS.sleep(5);
		
		assertThat(controller.getCountdown().getCountdownTime()).isLessThanOrEqualTo(countdownTime);
		assertThat(controller.getStatus()).isEqualTo(expectedStatus);
		
		controller.reset();
		
		assertThat(controller.getCountdown().getCountdownTime()).isEqualTo(countdownTime);
		assertThat(controller.getStatus()).isEqualTo(initialStatus);
	}
	
	@Test
	public void testFinishedRocketController() throws RocketCountdownAlreadyStartedException, InterruptedException {
		int countdownTime = 5;
		double expectedRemainingTime = 0;
		RocketStatus expectedStatus = RocketStatus.SHOOTED;
		RocketCountdown countdown = new RocketCountdown(countdownTime);
		RocketController controller = new RocketController(countdown);
		
		controller.start();
		TimeUnit.MILLISECONDS.sleep(10);
		
		assertThat(controller.getCountdown().getRemainingTime()).isEqualTo(expectedRemainingTime);
		assertThat(controller.getStatus()).isEqualTo(expectedStatus);
	}
}
