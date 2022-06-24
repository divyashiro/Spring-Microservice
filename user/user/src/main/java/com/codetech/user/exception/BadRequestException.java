package com.codetech.user.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 8999395823477394986L;
	private final String error;
	
	public BadRequestException(String error, String message) {
        super(message);
        this.error = error;
    }
	
	public String getError() {
        return error;
    }
}
