package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Persona;

@RestController
@RequestMapping("/lombok")
public class TestLombok {

	
	@GetMapping("/test")
	public void testLombok() {
		Persona p = new Persona();
		p.setNome("Guido");
	}
	
}
