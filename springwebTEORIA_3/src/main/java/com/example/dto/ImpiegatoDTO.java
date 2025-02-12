package com.example.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ImpiegatoDTO {
	
	private String nome;
	private String cognome;
	private String matricola;
	private double salario;
	private LocalDate dataAssunzione;
	private String sede;
	
}
