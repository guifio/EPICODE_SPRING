package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByActive(Boolean active);
	
	Optional<User> findByUsername(String username);
	
	List<User> findByFirstnameContaining(String firstname);
	
	Optional<User> findById(Long id);
	
}
