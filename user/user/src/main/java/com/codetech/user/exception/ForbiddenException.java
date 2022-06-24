package com.codetech.user.exception;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = -4559286328343640371L;
    private final String error;
	
	public ForbiddenException(String error, String message) {
        super(message);
        this.error = error;
    }
	
	public String getError() {
        return error;
    }
}