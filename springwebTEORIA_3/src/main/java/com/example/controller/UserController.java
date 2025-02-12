package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.EpicodeException;
import com.example.model.User;
import com.example.model.WebServiceException;
import com.example.service.UserService;

//@RestController
//@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		
		Optional<User> userFound = service.getById(id);
		
		if(userFound.isPresent()) {
			return new ResponseEntity<>(userFound.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(){
		
		List<User> utenti= service.getAllUsers();
		
		if(utenti.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(utenti, HttpStatus.OK);
		}
		
	}
		
	@PostMapping("/addUser")
	public ResponseEntity<User> createUser(@RequestBody User utente) throws WebServiceException{
		
		try {
			User userSaved = service.createUser(utente);
			return new ResponseEntity<>(userSaved, HttpStatus.CREATED);

		}catch(Exception e) {
			throw new WebServiceException("User not saved",User.class, e);
		}
		
	}
		
	@PostMapping("/addUsers")
	@ResponseStatus(HttpStatus.CREATED)
	public String createUsers(@RequestBody List<User> listaUtente) throws WebServiceException{
		
		try {
			
			for(User utente : listaUtente) {
				service.createUser(utente);
			}
			
			return "La lista degli utenti è stata inserita correttamente";

		}catch(Exception e) {
			return "La lista non è stata inserita nel database";
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userUpdated) throws WebServiceException{
		
		try {
			service.updateUser(userUpdated, id);
			return new ResponseEntity<User>(HttpStatus.OK);
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
	
	@GetMapping("/getUserByUsername/{username}")
	public ResponseEntity<User> getById(@PathVariable String username) {
		
		Optional<User> userFound = service.getByUsername(username);
		
		if(userFound.isPresent()) {
			return new ResponseEntity<>(userFound.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getUserByFirstname/{firstName}")
	public ResponseEntity<List<User>> getByFirstName(@PathVariable String firstName) {
		
		List<User> usersFound = service.getByFirstName(firstName);
		
		if(usersFound.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(usersFound, HttpStatus.OK);
		}
	}
	
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
