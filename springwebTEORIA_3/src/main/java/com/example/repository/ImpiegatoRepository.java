package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Impiegato;

public interface ImpiegatoRepository extends JpaRepository<Impiegato, Long>{

	// nuovi metodi dedicato all'entity Impiegato
//	List<Impiegato> findBySede(String sede);
	
	Impiegato findByMatricola(String matricola);
	
	Page<Impiegato> findBySede(String citta, Pageable paginazione);
}
