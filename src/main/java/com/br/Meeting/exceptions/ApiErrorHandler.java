package com.br.Meeting.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ApiErrorRequest.class)
	public ResponseEntity<?> handleApiRequestError (ApiErrorRequest ex) {
		ApiError apiError = new ApiError(ex.getMessage(), ex.getStatus());
		return new ResponseEntity<ApiError>(apiError, ex.getStatus());
	}
}
