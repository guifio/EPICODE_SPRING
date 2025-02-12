package com.example.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.model.User;
import com.example.repository.UserPageableRepository;

@Service
public class UserPageableService {

	@Autowired
	UserPageableRepository userRepo;

	public Optional<User> getById(Long id){
		// JPArepository
		return userRepo.findById(id);
	}

	public Optional<User> getByUsername(String username) {
		// UserRepository
		return Optional.of(userRepo.findByUsername(username));
	}

	public Page<User> getByActive(Boolean active, Pageable page){
		// UserRepository
		return userRepo.findByActive(active, page);
	}

	public Page<User> getByFirstName(String name, Pageable page){
		// UserRepository
		return userRepo.findByFirstnameContaining(name, page);
	}

	public Page<User> getAllUsers(Pageable page){
		Page<User> result = userRepo.findAll(page);
		return result;
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
