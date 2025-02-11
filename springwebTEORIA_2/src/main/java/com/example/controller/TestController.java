package com.example.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Result;
import com.example.service.TestService;

@RestController
@RequestMapping("/api")
public class TestController {

	static final Logger log = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("/testGet")
	public String testGet() {
		return "Calling Get /testGet";
	}
	
	@PostMapping("/testPost")
	public String testPost() {
		return "Calling Post /testPost";
	}
	
	@PutMapping("/testPut")
	public String testPut() {
		return "Calling Put /testPut";
	}
	
	@PatchMapping("/testPatch")
	public String testPatch() {
		return "Calling Patch /testPatch";
	}
	
	// ALTRI ESEMPI
	@GetMapping("/testQueryString")
	public String testQueryString(@RequestParam String param1, String param2) {
		return "Parametri : " +param1+ " - " +param2;
	}
	
	@GetMapping("/testPathParam/{param}")
	public String testParameters(@PathVariable String param) {
		return "Parametri : " +param;
	}
	
	@PostMapping("/testBodyString")
	public String testBodyString(@RequestBody String body) {
		return "Parametri dal body : " +body;
	}
	
	// TEST STATUS CODE
	@GetMapping("/testGetStatus")
	@ResponseStatus(HttpStatus.CREATED)
	public String testGetStatus() {
		return "Test approvato con esito Created";
	}
	
	@GetMapping("/testNonImplementato")
	@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
	public String testNonImplementato() {
		return "Work in progress";
	}
	
	
	// TEST RESPONSE ENTITY
	@GetMapping("/testResponseEntity")
	public ResponseEntity<String> getResponseEntity(){
		HttpHeaders head = new HttpHeaders();
		head.add("customHead", "Valore personalizzato");
		String body = "Test della Reponse Entity";
		
		ResponseEntity<String> responseTOclient = new ResponseEntity<String>(body, head, HttpStatus.OK);
		return responseTOclient;
	}
	
	
	// TEST RESULT POJO --> default JSON
	@GetMapping("/testResultPojo")
	public Result testResultPojo() {
		Result risultato = new Result();
		risultato.setMessage("Edizione Corso Back End");
		risultato.setValue(1);
		return risultato;		
	}
	
	// TEST RESULT POJO impostato esplicitamente formato JSON
	@GetMapping(value="/testResultPojoExplicit", produces=MediaType.APPLICATION_JSON_VALUE)
	public Result testResultPojoExplicit() {
		Result risultato = new Result();
		risultato.setMessage("Edizione Corso Back End");
		risultato.setValue(1);
		return risultato;		
	}
	
	// TEST BASE CHIAMATA SERVICE
	@Autowired
	TestService service;
	
	@GetMapping("/testService/{orario}")
	public String testService(@PathVariable int orario) {
		String saluto = service.salutoUser(orario);
		return saluto;
	}
	
}
