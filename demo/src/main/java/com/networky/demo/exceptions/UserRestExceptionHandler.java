package com.networky.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {


	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleUserException(UserNotFoundException userNotFoundException) {

		ErrorResponse errorResponse = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				userNotFoundException.getMessage(),
				System.currentTimeMillis()
				);

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);



	}

//	@ExceptionHandler
//	public ResponseEntity<ErrorResponse> handleAnyException(Exception exception) {
//
//		ErrorResponse errorResponse = new ErrorResponse();
//
//		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//		errorResponse.setMessage(exception.getMessage());
//		errorResponse.setTimeStamp(System.currentTimeMillis());
//		
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}

}
