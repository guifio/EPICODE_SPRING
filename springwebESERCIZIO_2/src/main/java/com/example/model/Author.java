package com.example.model;

import java.time.LocalDate;
import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="id")
public class Author {

	private int id;
	private String nome;
	private String cognome;
	private String email;
	private LocalDate dataNascita;
	private String avatar;
	
}
