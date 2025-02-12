package com.example.model;

public class WebServiceException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private String message;
	private Object classe;
	private Exception ex;
	
	public WebServiceException(String message, Class c, Exception e) {
		this.message=message;
		this.classe=c;
		ex=e;
	}
	
	
}
