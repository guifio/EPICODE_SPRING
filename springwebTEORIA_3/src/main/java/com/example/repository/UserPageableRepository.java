package com.example.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.example.model.User;

// PagingAndSortingRepository : sottointerfaccia JpaRepository
public interface UserPageableRepository extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long>{
	
	// Page incapsula i dati impaginati per la risposta al client
	// Pageable permette al client di inviare dati per la impaginazione
	Page<User> findByActive(Boolean active, Pageable page);
	
	User findByUsername(String username);
	
	Page<User> findByFirstnameContaining(String firstname, Pageable page);
	
	Optional<User> findById(Long id);
	
}
