package br.com.app.api.exception;

public class ApplicationException extends RuntimeException {

	
	private static final long serialVersionUID = -1773317842525777288L;

	public ApplicationException(String mensage, Throwable cause){
		super(mensage,cause);
	}

	public ApplicationException(String mensage) {
		super(mensage);
	}

}

