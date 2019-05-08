package rocketrestapi;

import java.util.Date;

public class Response<T> {
	private final Date timestamp;
	private final int status;
	private final String error;
	private final T message;
	private final String path;

	public Response(T message, String path, int status) {
		Application.logger.info("Creating new instance of Response.");
		Application.logger.debug(String.format("Creating new instance of Response.\nmessage: %s\npath: %s\nstatus: %s", message, path, status));
		
		this.message = message;
		this.path = path;
		this.status = status;
		this.error = "OK";
		this.timestamp = new Date();
	}
	
	public Response(T message, String path) {
		this(message, path, 200);
	}
	
	public Date getTimestamp() {
		Application.logger.info("Running Response.getTimestamp().");
		Application.logger.debug(String.format("Returning %s in Response.getTimestamp().", this.timestamp));
		return this.timestamp;
	}
	
	public int getStatus() {
		Application.logger.info("Running Response.getStatus().");
		Application.logger.debug(String.format("Returning %s in Response.getStatus().", this.status));
		return this.status;
	}
	
	public String getError() {
		Application.logger.info("Running Response.getError().");
		Application.logger.debug(String.format("Returning %s in Response.getError().", this.error));
		return this.error;
	}
	
	public T getMessage() {
		Application.logger.info("Running Response.getMessage().");
		Application.logger.debug(String.format("Returning %s in Response.getMessage().", this.message));
		return this.message;
	}
	
	public String getPath() {
		Application.logger.info("Running Response.getPath().");
		Application.logger.debug(String.format("Returning %s in Response.getPath().", this.path));
		return this.path;
	}
}
