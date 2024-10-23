package com.assessment.task.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("message", ex.getMessage());
		response.put("status", "NOT_FOUND");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<Map<String, String>> handleInternalServer(InternalServerErrorException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("message", ex.getMessage());
		response.put("status", "INTERNAL_SERVER_ERROR");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	  @ExceptionHandler(InvalidRequestException.class)
	    public ResponseEntity<?> handleInvalidRequestException(InvalidRequestException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }


	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("message", ex.getMessage());
		response.put("status", "BAD_REQUEST");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
		Map<String, String> response = new HashMap<>();
		response.put("message", "An unexpected error occurred");
		response.put("details", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> response = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			response.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
