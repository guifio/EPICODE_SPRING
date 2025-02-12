package com.example.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Data
@Table(name="users")
public class User {

//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
//	@SequenceGenerator(name = "users_seq", sequenceName = "users_seq")
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String firstname;
	
	@Column(nullable = false)
	private String lastname;
	private LocalDate birthdate;
	private Boolean active;
	
}
