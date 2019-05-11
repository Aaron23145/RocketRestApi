# Rocket Rest API

Rest Api that manages a Rocket Countdown and allows you to control when to start or reset it. Programmed with Java and Spring. You can complement it with its front-end: [Rocket Front-end Angular](https://github.com/Aaron23145/rocket-front-end-angular).

## Features

- Logging system implemented with Spring Boot.
- Configuration file to configure variable values without modifying the source code.
- Unit and Integration Tests.

## Endpoints

| Endpoint | Method | Use |
|----------|--------|-----|
|/status|GET|Obtain Rocket Status. If it's in WAITING, COUNTDOWN or SHOOTED mode.|
|/countdown|GET|Obtain the remaining time until the countdown ends.|
|/start|POST|Starts the Rocket Countdown.|
|/reset|POST|Resets the Rocket Countdown and sets the Rocket in WAITING (initial) state.|

## Project Structure

| Folder | Content |
|--------|---------|
|.mvn/wrapper|Maven wrapper to let you run the app without needing Maven installed.|
|src/main/java/rocketrestapi|Main app source code.|
|src/main/resources|Configuration files and additional resources.|
|src/test/java/rocketrestapi|Unit and integration tests.|
|logs|App logs. It doesn't exist before the first app execution.|

## Running App

To run the app you'll need JDK 8 installed. You may also need to define the JAVA_HOME environment variable pointing to your JDK installation.

Just execute the following command on Linux while being in the root folder:

```sh
./mvnw spring-boot:run
```

or if you're on Windows:

```sh
mvnw.cmd sprint-boot:run
```

Then the app will start in http://localhost:8080. Take a look at the endpoints section to find out which paths you can make requests.

## Running Tests

To run the tests you'll need JDK 8 installed. You may also need to define the JAVA_HOME environment variable pointing to your JDK installation.

Just execute the following command on Linux while being in the root folder:

```sh
./mvnw clean test
```

or if you're on Windows:

```sh
mvnw.cmd clean test
```
