package rocketrestapi;

public class Response<T> {
	private final boolean success;
	private final T result;

	public Response(T result, boolean success) {
		this.success = success;
		this.result = result;
	}

	public Response(T result) {
		this(result, true);
	}

	public boolean getSuccess() {
		return this.success;
	}

	public T getResult() {
		return this.result;
	}
}
