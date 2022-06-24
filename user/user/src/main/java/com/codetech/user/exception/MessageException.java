package com.codetech.user.exception;

public class MessageException extends RuntimeException {

	private static final long serialVersionUID = -9199092646157789340L;
	private final String error;
		
	public MessageException(String error, String message) {
        super(message);
        this.error = error;
    }
	
	public String getError() {
        return error;
    }
}
