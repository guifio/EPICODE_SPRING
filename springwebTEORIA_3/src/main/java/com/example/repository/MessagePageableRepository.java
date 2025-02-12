package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Message;
import com.example.model.User;


public interface MessagePageableRepository extends JpaRepository<Message, Long>{

	Page<Message> findByTitleContainingIgnoreCase(String title, Pageable page);
	
	Page<Message> findByFromUser(User fromUser, Pageable page);	
	
	Page<Message> findByToUser(User toUser, Pageable page);
	
	@Query("SELECT m FROM messages m WHERE lower(m.title) LIKE %:findText% OR lower(m.content) LIKE %:findText%")
	Page<Message> findByTitleOrContentContainingIgnoreCase(String findText, Pageable page);
}
