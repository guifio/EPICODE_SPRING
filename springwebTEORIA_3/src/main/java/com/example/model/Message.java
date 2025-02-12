package com.example.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name="messages")
@Data
public class Message {

//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_generator")
//	@SequenceGenerator(name="message_generator", sequenceName = "message_seq", allocationSize = 10)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// COLLEGAMENTI ESTERNI
	@ManyToOne(optional = false)
	private User fromUser;
	
	@ManyToOne(optional = false)
	private User toUser;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	private LocalDate sendDate;
	
}
