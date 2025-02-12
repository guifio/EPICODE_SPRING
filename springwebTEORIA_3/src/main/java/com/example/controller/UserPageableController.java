package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.EpicodeException;
import com.example.model.User;
import com.example.model.WebServiceException;
import com.example.service.UserPageableService;

@RestController
@RequestMapping("/users")
public class UserPageableController {

	@Autowired
	UserPageableService service;
	
	
	@PostMapping("/addUser")
	public ResponseEntity<User> createUser(@RequestBody User utente) throws WebServiceException{
		
		try {
			User userSaved = service.createUser(utente);
			return new ResponseEntity<>(userSaved, HttpStatus.CREATED);

		}catch(Exception e) {
			throw new WebServiceException("User not saved",User.class, e);
		}
		
	}

	
	@PostMapping("/addAllUsers")
	public ResponseEntity<String> createUser(@RequestBody List<User> listaUtenti) throws WebServiceException{
		
		try {
			
			for(User utente : listaUtenti) {
				service.createUser(utente);
			}
			return new ResponseEntity<>(listaUtenti.size()+" contatti inseriti correttamente.", HttpStatus.CREATED);

		}catch(Exception e) {
			throw new WebServiceException("User not saved",User.class, e);
		}
		
	}
	
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		
		Optional<User> userFound = service.getById(id);
		
		if(userFound.isPresent()) {
			return new ResponseEntity<>(userFound.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/getAllUsersPag")
	public ResponseEntity<Page<User>> getAllUsers(Pageable pageable){
		
		Page<User> utenti= service.getAllUsers(pageable);
		
		if(utenti.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(utenti, HttpStatus.OK);
		}
		
	}
	
	// Per il valore Boolean è sottointeso @RequestParam
	@GetMapping("getAllUsersPag/active")
	public ResponseEntity<Page<User>> getUserByActive(Boolean value, Pageable pageable) throws WebServiceException{
		try {
			Page<User> listaUtenti = service.getByActive(value, pageable);
			if(listaUtenti.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(listaUtenti, HttpStatus.OK);
			}
		}catch(Exception e) {
			throw new WebServiceException("Error during search", User.class, e);
		}
	}
	
	
	
		
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userUpdated) throws WebServiceException{
		
		try {
			Optional<User> utenteModificato = service.updateUser(userUpdated, id);
			return new ResponseEntity<User>(utenteModificato.get() ,HttpStatus.OK);
		}catch(Exception e){
			throw new WebServiceException("User not updated",User.class, e);
		}
		
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) throws WebServiceException{
		
		try {
			service.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	// Gestione centralizzata delle Exception
		@GetMapping("/getUserException/{id}")
		public ResponseEntity<User> getByIdException(@PathVariable Long id) {
			
			Optional<User> userFound = service.getById(id);
			
			if(userFound.isPresent()) {
				return new ResponseEntity<>(userFound.get(), HttpStatus.OK);
			}else {
				throw new EpicodeException("Utente non presente");
			}
		}
	
	
}
