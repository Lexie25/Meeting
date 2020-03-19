package com.br.Meeting.exceptions;

import org.springframework.http.HttpStatus;

public class ApiErrorRequest extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus status;
	public ApiErrorRequest (String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
	
}
