package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Message;
import com.example.model.User;


public interface MessageRepository extends JpaRepository<Message, Long>{

	List<Message> findByTitleContainingIgnoreCase(String title);
	
	List<Message> findByFromUser(User fromUser);	
	
	List<Message> findByToUser(User toUser);
	
	@Query("SELECT m FROM messages m WHERE lower(m.title) LIKE %:findText% OR lower(m.content) LIKE %:findText%")
	List<Message> findByTitleOrContentContainingIgnoreCase(String findText);
}
