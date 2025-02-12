package com.example.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.model.Message;
import com.example.model.MessageDTO;
import com.example.model.User;
import com.example.repository.MessagePageableRepository;
import com.example.repository.MessageRepository;
import com.example.repository.UserPageableRepository;
import com.example.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class MessagePageableService {
	
	@Autowired
	MessagePageableRepository repo;
	
	@Autowired
	UserPageableRepository repoUser;
	
	
	
	public void nuovoMessaggio(Message nuovoMessaggio, Long fromUser, Long toUser) {
		// ricerca mittente e destinatario
		Optional<User> mittente = repoUser.findById(fromUser);
		Optional<User> destinatario = repoUser.findById(toUser);
		
		if(mittente.isPresent() && destinatario.isPresent()) {
			nuovoMessaggio.setFromUser(mittente.get());
			nuovoMessaggio.setToUser(destinatario.get());
			repo.save(nuovoMessaggio);
		}

	}
	
	
	public void nuovoMessaggioDTO(MessageDTO nuovoMessaggio) {
		// ricerca mittente e destinatario
		Optional<User> mittente = repoUser.findById(nuovoMessaggio.getFromUser());
		Optional<User> destinatario = repoUser.findById(nuovoMessaggio.getToUser());
		
		if(mittente.isPresent() && destinatario.isPresent()) {
			Message messaggio = new Message();
			messaggio.setContent(nuovoMessaggio.getContent());
			messaggio.setTitle(nuovoMessaggio.getTitle());
			messaggio.setSendDate(nuovoMessaggio.getSendDate());
			messaggio.setFromUser(mittente.get());
			messaggio.setToUser(destinatario.get());
			repo.save(messaggio);
		}

	}
	
	
	public Optional<Message> getMessaggio(Long id) {
		
		Optional<Message> messaggio = repo.findById(id);
	
		return messaggio;
	}
	
	public Page<Message> getAllMessages(Pageable pageable){
		Page<Message> listaMessaggi = repo.findAll(pageable);
		
		if(listaMessaggi.isEmpty()) {
			throw new RuntimeException();
		}else {
			return listaMessaggi;
		}
	}
	
	public List<Message> getAllMessages(){
		List<Message> listaMessaggi = repo.findAll();
		
		if(listaMessaggi.isEmpty()) {
			throw new RuntimeException();
		}else {
			return listaMessaggi;
		}
	}
	
	
	public void updateMessage(Message messaggioModificato, Long id) {
		
		Optional<Message> messaggioTrovato = repo.findById(id);
		
		if(messaggioTrovato.isEmpty()) {
			throw new RuntimeException();
		}else {
			
			messaggioTrovato.get().setContent(messaggioModificato.getContent());
			messaggioTrovato.get().setTitle(messaggioModificato.getTitle());
			messaggioTrovato.get().setSendDate(messaggioModificato.getSendDate());

		}
	}
	
	
	public void deleteMessage(Long id) {
		Optional<Message> messaggioTrovato = repo.findById(id);
		
		if(messaggioTrovato.isPresent()) {
			repo.delete(messaggioTrovato.get());
		}
	}
	
	
	public Page<Message> cercaDaTitolo(String title, Pageable page){
		return repo.findByTitleContainingIgnoreCase(title, page);
	}
	
	public Page<Message> cercaMessaggiMittente(User mittente, Pageable page){
		return repo.findByFromUser(mittente, page);
	}
	
	public Page<Message> cercaMessaggiDestinatario(User destinatario, Pageable page){
		return repo.findByToUser(destinatario, page);
	}
	
	public Page<Message> cercaMessaggiTitoloContent(String text, Pageable page){
		return repo.findByTitleOrContentContainingIgnoreCase(text, page);
	}
	
	
	
	

}
