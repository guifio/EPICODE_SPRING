package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ImpiegatoDTO;
import com.example.model.Impiegato;
import com.example.service.ImpiegatoService;

@RestController
@RequestMapping("/imp")
public class ImpiegatoController {

	@Autowired
	ImpiegatoService service;
	
	@PostMapping("/nuovoImpiegato")
	@ResponseStatus(HttpStatus.CREATED)
	public String nuovoImpiegato(@RequestBody ImpiegatoDTO impDTO) {
		
		Long idGenerato = service.inserisciImpiegato(impDTO);
		return "L'impiegato " +impDTO.getCognome()+ " è stato inserito correttamente nel sistema (id:" +idGenerato+")" ;
	}
	
	
	@GetMapping("/recuperaImpiegato/{id}")
	public ImpiegatoDTO getImpiegatoID(@PathVariable Long id) {
		return service.vediImpiegatoById(id);
	}
	
	
	@GetMapping("/impSede/{citta}")
	public Page<Impiegato> impiegatiSede(@RequestParam int page, 
										 @RequestParam int size, 
										 @RequestParam String sortBy, @PathVariable String citta){
		
		return service.getAllImpiegatiSede(page,size,sortBy, citta);
		
	}
	
	
	
}
