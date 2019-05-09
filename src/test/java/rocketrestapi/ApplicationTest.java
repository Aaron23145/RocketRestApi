package rocketrestapi;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testWaitingResponses() throws IOException {
    	Application.initializeRocketController();
    	String baseUri = "http://localhost:" + port;
    	
    	ResponseEntity<String> statusResponse = this.restTemplate.getForEntity(baseUri + "/status/", String.class);
    	assertThat(statusResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode statusRoot = mapper.readTree(statusResponse.getBody());

    	JsonNode statusStatus = statusRoot.path("status");
    	JsonNode statusError = statusRoot.path("error");
    	JsonNode statusMessage = statusRoot.path("message");
    	JsonNode statusPath = statusRoot.path("path");
    	
    	assertThat(statusStatus.asInt()).isEqualTo(200);
    	assertThat(statusError.asText()).isEqualTo("OK");
    	assertThat(statusMessage.asText()).isEqualTo(String.valueOf(RocketStatus.WAITING));
    	assertThat(statusPath.asText()).isEqualTo("/status/");
    	
    	ResponseEntity<String> countdownResponse = this.restTemplate.getForEntity(baseUri + "/countdown/", String.class);
    	assertThat(countdownResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    	
    	JsonNode countdownRoot = mapper.readTree(countdownResponse.getBody());
    	
    	JsonNode countdownStatus = countdownRoot.path("status");
    	JsonNode countdownError = countdownRoot.path("error");
    	JsonNode countdownPath = countdownRoot.path("path");
    	
    	assertThat(countdownStatus.asInt()).isEqualTo(200);
    	assertThat(countdownError.asText()).isEqualTo("OK");
    	assertThat(countdownPath.asText()).isEqualTo("/countdown/");
    }
    
    @Test
    public void testCountdownResponses() throws IOException {
    	Application.initializeRocketController();
    	String baseUri = "http://localhost:" + port;
    	
    	ResponseEntity<String> startResponse = this.restTemplate.postForEntity(baseUri + "/start/", null, String.class);
    	assertThat(startResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode startRoot = mapper.readTree(startResponse.getBody());
    	
    	JsonNode startStatus = startRoot.path("status");
    	JsonNode startError = startRoot.path("error");
    	JsonNode startMessage = startRoot.path("message");
    	JsonNode startPath = startRoot.path("path");
    	
    	assertThat(startStatus.asInt()).isEqualTo(200);
    	assertThat(startError.asText()).isEqualTo("OK");
    	assertThat(startMessage.asText()).isEqualTo("Countdown started.");
    	assertThat(startPath.asText()).isEqualTo("/start/");
    	
    	ResponseEntity<String> statusResponse = this.restTemplate.getForEntity(baseUri + "/status/", String.class);
    	assertThat(statusResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    	
    	JsonNode statusRoot = mapper.readTree(statusResponse.getBody());
    	
    	JsonNode statusStatus = statusRoot.path("status");
    	JsonNode statusError = statusRoot.path("error");
    	JsonNode statusMessage = statusRoot.path("message");
    	JsonNode statusPath = statusRoot.path("path");
    	
    	assertThat(statusStatus.asInt()).isEqualTo(200);
    	assertThat(statusError.asText()).isEqualTo("OK");
    	assertThat(statusMessage.asText()).isEqualTo(String.valueOf(RocketStatus.COUNTDOWN));
    	assertThat(statusPath.asText()).isEqualTo("/status/");
    	
    	ResponseEntity<String> countdownResponse = this.restTemplate.getForEntity(baseUri + "/countdown/", String.class);
    	assertThat(countdownResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    	
    	JsonNode countdownRoot = mapper.readTree(countdownResponse.getBody());
    	
    	JsonNode countdownStatus = countdownRoot.path("status");
    	JsonNode countdownError = countdownRoot.path("error");
    	JsonNode countdownPath = countdownRoot.path("path");
    	
    	assertThat(countdownStatus.asInt()).isEqualTo(200);
    	assertThat(countdownError.asText()).isEqualTo("OK");
    	assertThat(countdownPath.asText()).isEqualTo("/countdown/");
    }
    
    @Test
    public void testResetWhenCountdownHasNotStarted() throws IOException {
    	Application.initializeRocketController();
    	String baseUri = "http://localhost:" + port;
    	
    	ResponseEntity<String> resetResponse = this.restTemplate.postForEntity(baseUri + "/reset/", null, String.class);
    	assertThat(resetResponse.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode resetRoot = mapper.readTree(resetResponse.getBody());
    	
    	JsonNode resetStatus = resetRoot.path("status");
    	JsonNode resetError = resetRoot.path("error");
    	JsonNode resetMessage = resetRoot.path("message");
    	JsonNode resetPath = resetRoot.path("path");
    	
    	assertThat(resetStatus.asInt()).isEqualTo(409);
    	assertThat(resetError.asText()).isEqualTo("Conflict");
    	assertThat(resetMessage.asText()).isEqualTo("Rocket countdown hasn't started, so a reset can't be performed.");
    	assertThat(resetPath.asText()).isEqualTo("/reset/");
    }
    
    @Test
    public void testStartWhenCountdownHasAlreadyBeenStarted() throws IOException {
    	Application.initializeRocketController();
    	String baseUri = "http://localhost:" + port;
    	
    	ResponseEntity<String> firstResponse = this.restTemplate.postForEntity(baseUri + "/start/", null, String.class);
    	assertThat(firstResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    	
    	ResponseEntity<String> secondResponse = this.restTemplate.postForEntity(baseUri + "/start/", null, String.class);
    	assertThat(secondResponse.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode secondRoot = mapper.readTree(secondResponse.getBody());
    	
    	JsonNode secondStatus = secondRoot.path("status");
    	JsonNode secondError = secondRoot.path("error");
    	JsonNode secondMessage = secondRoot.path("message");
    	JsonNode secondPath = secondRoot.path("path");
    	
    	assertThat(secondStatus.asInt()).isEqualTo(409);
    	assertThat(secondError.asText()).isEqualTo("Conflict");
    	assertThat(secondMessage.asText()).isEqualTo("Rocket countdown is already started. Reset it first.");
    	assertThat(secondPath.asText()).isEqualTo("/start/");
    }
}
