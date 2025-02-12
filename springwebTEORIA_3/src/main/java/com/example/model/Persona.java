package com.example.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public @Data class Persona {
	
	@Setter @Getter
	private Long idPersona;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private String codiceFiscale;
	private double altezza;
	private double peso;
	




}
