package br.com.app.api.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.app.api.util.messages.Message;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends ApplicationException {

	private static final long serialVersionUID = -5644356299117363243L;
	private static final String EXCEPTION_MESSAGE = "Validation exception";

	private String errorHeader;

	private List<Message> errors;

	@Deprecated
	public ValidationException(String message, Throwable cause,
			String... errors) {
		super(message, cause);

		this.errors = getLesErros(Arrays.asList(errors));
	}
@Deprecated
	public ValidationException(Throwable cause, String... errors) {
		super(EXCEPTION_MESSAGE, cause);

		this.errors = getLesErros(Arrays.asList(errors));
	}

	@Deprecated
	public ValidationException(String message, String... errors) {
		super(message);

		this.errors = getLesErros(Arrays.asList(errors));
	}

	public ValidationException(String message, Message... errors) {
		super(message);

		this.errors = Arrays.asList(errors);
	}

	public ValidationException(String message, List<Message> errors) {
		super(message);

		this.errors = errors;
	}

	@Deprecated
	public ValidationException(String errors) {
		super(EXCEPTION_MESSAGE);

		this.errors = getLesErros(Arrays.asList(errors));
	}

	public ValidationException(Message... errors) {
		super(EXCEPTION_MESSAGE);

		this.errors = Arrays.asList(errors);
	}

	public ValidationException(List<Message> errors) {
		super(EXCEPTION_MESSAGE);

		this.errors = errors;
	}

	public ValidationException(String message, String errorHeader, List<Message> errors) {
		super(message);

		this.errorHeader = errorHeader;
		this.errors = errors;
	}

	public List<Message> getErrors() {
		return errors;
	}

	private List<Message> getLesErros(List<String> errors) {
		return errors.stream().map(s -> new Message(s))
				.collect(Collectors.toList());
	}

	public String getErrorHeader() {
		return errorHeader;
	}

	public void setErrorHeader(String errorHeader) {
		this.errorHeader = errorHeader;
	}

}
