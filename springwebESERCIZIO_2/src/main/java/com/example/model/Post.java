package com.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="id")
public class Post {

	private int id;
	private String categoria;
	private String titolo;
	private String cover;
	private String contenuto;
	private int tempoLettura;
	
	private Author autore;
	
}
