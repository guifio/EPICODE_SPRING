package com.example.model;

import org.springframework.http.HttpStatus;

public class ApiError {

	private String message;
	private HttpStatus status;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public ApiError(String messaggio, HttpStatus stato) {
		this.message=messaggio;
		this.status=stato;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
	
}
