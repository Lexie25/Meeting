package com.br.Meeting.exceptions;

import org.springframework.http.HttpStatus;

public class ApiError {
	
	private String message;
	private HttpStatus status;
	
	public ApiError(String message, HttpStatus status) {
		this.status = status;
		this.message = message;
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
