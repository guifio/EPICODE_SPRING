package com.example.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="impiegati")
@Data
public class Impiegato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(unique = true)
	private String matricola;
	private double salario;
	private LocalDate dataAssunzione;
	private String sede;
		
}
