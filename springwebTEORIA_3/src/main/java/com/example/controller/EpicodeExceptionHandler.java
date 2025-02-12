package com.example.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.model.ApiError;
import com.example.model.EpicodeException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class EpicodeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EpicodeException.class)
	protected ResponseEntity<Object> gestioneErrori(EpicodeException ex){
		ApiError error = new ApiError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		// chiamiamo il metodo private che genere la ResponseEntity di ritorno
		return buildResponseEntity(error);
	}
	
	// Metodo che genera una Response Entity con Oggetto dell'errore e stato.
	private ResponseEntity<Object> buildResponseEntity(ApiError api){
		return new ResponseEntity<>(api, api.getStatus());
	}
	
	
}
