package com.jiuzhou.exception;

public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 3944156971920024617L;

	protected int retCode;
	
	protected String message;
	
	public ServiceException(int retCode, String message){
		super(message);
		this.retCode = retCode;
		this.message = message;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
