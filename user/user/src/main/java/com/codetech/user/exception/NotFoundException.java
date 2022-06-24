package com.codetech.user.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9199092646157789340L;
	private final String error;
		
	public NotFoundException(String error, String message) {
        super(message);
        this.error = error;
    }
	
	public String getError() {
        return error;
    }
}
