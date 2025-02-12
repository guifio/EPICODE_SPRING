package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.model.User;
import com.example.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public Optional<User> getById(Long id){
		// JPArepository
		return (userRepo.findById(id));
	}
	
	public Optional<User> getByUsername(String username) {
		// UserRepository
		return userRepo.findByUsername(username);
	}
	
	public List<User> getByActive(Boolean active){
		// UserRepository
		return userRepo.findByActive(active);
	}
	
	public List<User> getByFirstName(String name){
		// UserRepository
		return userRepo.findByFirstnameContaining(name);
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public User createUser(User nuovoUtente) {
		return userRepo.save(nuovoUtente);
	}
	
	public Optional<User> updateUser(User utenteModificato, Long id) {
		Optional<User> utenteTrovato = userRepo.findById(id);
		
		if(utenteTrovato.isPresent()) {
			utenteTrovato.get().setUsername(utenteModificato.getUsername());
			utenteTrovato.get().setFirstname(utenteModificato.getFirstname());
			utenteTrovato.get().setLastname(utenteModificato.getLastname());
			utenteTrovato.get().setBirthdate(utenteModificato.getBirthdate());
			utenteTrovato.get().setActive(utenteModificato.getActive());
			userRepo.save(utenteTrovato.get());
			return utenteTrovato;
		}else {
			throw new RuntimeException();
		}
		
		
	}
	
	public void deleteUser(Long id) {
		Optional<User> utenteTrovato = userRepo.findById(id);
		
		if(utenteTrovato.isPresent()) {
			userRepo.deleteById(id);
		}else {
			throw new RuntimeException();
		}
		
	}
	
	
	
	
}
