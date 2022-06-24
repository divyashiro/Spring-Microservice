package com.codetech.user.exception;

public class ValidateDataException extends RuntimeException {

	private static final long serialVersionUID = 8999395823477394986L;
	private final String error;
	
	public ValidateDataException(String error, String message) {
        super(message);
        this.error = error;
    }
	
	public String getError() {
        return error;
    }
}
