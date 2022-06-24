package com.codetech.user.exception;

public class InternalException extends RuntimeException {

	private static final long serialVersionUID = -3404549279956874173L;
    private final String error;
	
	public InternalException(String error, String message) {
        super(message);
        this.error = error;
    }
	
	public String getError() {
        return error;
    }
}