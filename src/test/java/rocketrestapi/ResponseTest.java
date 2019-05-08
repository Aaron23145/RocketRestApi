package rocketrestapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import rocketrestapi.Response;

public class ResponseTest {

	@Test
	public void testInstantiatingResponseWithMainConstructor() {
		String message = "ARandomMessage";
		String path = "/32492";
		int status = 400;
		
		String expectedError = "OK";
		
		Response<String> response = new Response<>(message, path, status);
		
		assertThat(response).isNotNull();
		assertThat(response.getMessage()).isEqualTo(message);
		assertThat(response.getPath()).isEqualTo(path);
		assertThat(response.getStatus()).isEqualTo(status);
		assertThat(response.getError()).isEqualTo(expectedError);
	}
	
	@Test
	public void testInstantiatingResponseWithoutStatus() {
		String message = "sfopkpfkopqkWEFWEF";
		String path = "/status";
		
		int expectedStatus = 200;
		String expectedError = "OK";
		
		Response<String> response = new Response<>(message, path);
		
		assertThat(response).isNotNull();
		assertThat(response.getMessage()).isEqualTo(message);
		assertThat(response.getPath()).isEqualTo(path);
		assertThat(response.getStatus()).isEqualTo(expectedStatus);
		assertThat(response.getError()).isEqualTo(expectedError);
	}
}
