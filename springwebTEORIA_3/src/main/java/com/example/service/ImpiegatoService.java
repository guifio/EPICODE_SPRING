package com.example.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dto.ImpiegatoDTO;
import com.example.model.Impiegato;
import com.example.repository.ImpiegatoRepository;

@Service
public class ImpiegatoService {

	@Autowired
	ImpiegatoRepository repo;
	
	/**
	 * Metodo che inserisce un nuovo impiegato.
	 * @param imp : DTO proveniente dalla controller
	 * @return : id assegnato in automatico dal database
	 */
	public Long inserisciImpiegato(ImpiegatoDTO impDTO) {
		
		// travaso DTO -> ENTITY
		Impiegato impEntity = fromDTOtoEntity(impDTO);
		
		// chiamare un metodo di repository
		Impiegato impInserito = repo.save(impEntity);
		
		return impInserito.getId();
	}
	
	
	public ImpiegatoDTO vediImpiegatoById(Long id) {
		Optional<Impiegato> risultato = repo.findById(id);
		
		if(risultato.isPresent()) {
			// devo tornare impDTO al controller --> client
			ImpiegatoDTO impDTO = fromEntityTOdto(risultato.get());
			return impDTO;
		}else {
			throw new RuntimeException("L'impiegato non è presente nel sistema");
		}
	}
	
	
	public Page<Impiegato> getAllImpiegatiSede(int page,int size,String sortBy,String citta){
		Pageable paginazione = (Pageable) PageRequest.of(page, size, Sort.by(sortBy));
		return repo.findBySede(citta,paginazione);
	}
	
	
	/**
	 * Trasformo l'oggetto DTO in un ENTITY a disposizione del DATABASE
	 * @param impDTO : oggetto proveniente dal client
	 * @return un entity Impiegato
	 */
	public Impiegato fromDTOtoEntity(ImpiegatoDTO impDTO) {
		Impiegato imp = new Impiegato();
		imp.setCognome(impDTO.getCognome());
		imp.setNome(impDTO.getNome());
		imp.setDataAssunzione(impDTO.getDataAssunzione());
		imp.setMatricola(impDTO.getMatricola());
		imp.setSalario(impDTO.getSalario());
		imp.setSede(impDTO.getSede());
		return imp;
	}
	
	/**
	 * Metodo che trasfroma un entity in un dto pronto per la response al client
	 * @param imp : oggetto entity per la comunicazione con il DB
	 * @return impDTO per la comunicazione con il client
	 */
	public ImpiegatoDTO fromEntityTOdto(Impiegato imp) {
		ImpiegatoDTO dto = new ImpiegatoDTO();
		dto.setCognome(imp.getCognome());
		dto.setNome(imp.getNome());
		dto.setDataAssunzione(imp.getDataAssunzione());
		dto.setMatricola(imp.getMatricola());
		dto.setSalario(imp.getSalario());
		dto.setSede(imp.getSede());
		return dto;
	}
	
	
	
}
