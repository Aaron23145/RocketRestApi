package rocketrestapi;

import java.io.FileNotFoundException;

public class PropertiesFileDoesNotExistException extends FileNotFoundException {
	private static final long serialVersionUID = 1L;

	public PropertiesFileDoesNotExistException(String message) {
		super(message);
	}
}
