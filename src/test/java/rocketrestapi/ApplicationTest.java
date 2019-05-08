package rocketrestapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import rocketrestapi.Application;

public class ApplicationTest {

	@Test
	public void testInitializeRocketController() {
		Application.initializeRocketController();
		assertThat(Application.rocketController).isNotNull();
	}

}