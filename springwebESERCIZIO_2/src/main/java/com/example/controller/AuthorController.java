package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.Author;
import com.example.model.Blog;


@RestController
@RequestMapping("/authors")
public class AuthorController {
		
	@Autowired
	Blog blog;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public String inserisciAutore(@RequestBody Author autoreNuovo) {
		int id = blog.nuovoAutore(autoreNuovo);
		return "L'autore con identificativo " +id+ " è stato inserito con successo";
	}
	
		
	@GetMapping()
	public List<Author> vediAutori(){
		 List<Author> lista = blog.vediAutori();
		 if(lista.size()>=0) {
			 return lista;
		 }else {
			 throw new RuntimeException("La lista degli autori è momentaneamente vuota");
		 }
	}
	
	@GetMapping("/{id}")
	public Author recuperaAutore(@PathVariable int id) {
		return blog.recuperaAutore(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String modificaAutore(@RequestBody Author autoreModificato, @PathVariable int id) {
		if(blog.modificaAutore(autoreModificato, id)) {
			return "Modifica avvenuta con successo";
		}else {
			return "Impossibile effettuare la modifica. Autore non presente nel sistema";
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String cancellaAutore(@PathVariable int id) {
		return blog.cancellaPost(id);
	}



}
