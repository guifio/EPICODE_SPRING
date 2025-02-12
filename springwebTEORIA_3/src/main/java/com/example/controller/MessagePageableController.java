package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Message;
import com.example.model.MessageDTO;
import com.example.model.User;
import com.example.service.MessagePageableService;
import com.example.service.MessageService;
import com.example.service.UserPageableService;

@RestController
@RequestMapping("/messages")
public class MessagePageableController {

	@Autowired
	MessagePageableService service;	
	
	@Autowired
	UserPageableService userService;	
	
	@PostMapping("/addMessage")
	public ResponseEntity<String> nuovoMessaggio(@RequestBody Message messaggio, Long fromUser, Long toUser){
		service.nuovoMessaggio(messaggio, fromUser, toUser);
		return new ResponseEntity<>("Il messaggio è stato inserito correttamente", HttpStatus.OK);
	}
	
	@PostMapping("/addAllMessages")
	public ResponseEntity<String> popolaMessaggi(@RequestBody List<MessageDTO> listaMessaggi){
		
		for(MessageDTO messaggio : listaMessaggi) {
			service.nuovoMessaggioDTO(messaggio);
		}
		
		return new ResponseEntity<>(listaMessaggi.size()+ " messaggi inseriti correttamente", HttpStatus.CREATED);
	}
	
	@GetMapping("/allMessages")
	public ResponseEntity<List<Message>> vediMessaggi(){
		List<Message> messaggi = service.getAllMessages();
		return new ResponseEntity<>(messaggi, HttpStatus.FOUND);
	}
	
	// Organizzazione in Paginazione
	@GetMapping("/allMessagesPag")
	public ResponseEntity<Page<Message>> vediMessaggi(Pageable pageable){
		Page<Message> messaggi = service.getAllMessages(pageable);
		return new ResponseEntity<>(messaggi, HttpStatus.FOUND);
	}
	
	@GetMapping("/getMessage/{id}")
	public ResponseEntity<Message> getMessaggio(@PathVariable Long id){
		Optional<Message> messaggio = service.getMessaggio(id);
		
		if(messaggio.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(messaggio.get(),HttpStatus.FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateMessaggio(@PathVariable Long id, @RequestBody Message messaggio){
		service.updateMessage(messaggio, id);
		return new ResponseEntity<>("Messaggio modificato correttamente", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteMessaggio(@PathVariable Long id){
		service.deleteMessage(id);
		return new ResponseEntity<>("Il messaggio è stato cancellato correttamente", HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/findByTitlePag/{title}")
	public ResponseEntity<Page<Message>> cercaDaTitolo(@PathVariable String title, Pageable page){
		Page<Message> messaggiTrovati = service.cercaDaTitolo(title, page);
		return new ResponseEntity<>(messaggiTrovati,HttpStatus.FOUND); 
	}
	
	@GetMapping("/findByMittentePag/{username}")
	public ResponseEntity<Page<Message>> cercaDaMittente(@PathVariable String username, Pageable page){
		
		Optional<User> utenteTrovato = userService.getByUsername(username);
		
		if(utenteTrovato.isPresent()) {
			Page<Message> messaggiRecuperati = service.cercaMessaggiMittente(utenteTrovato.get(), page);
			return new ResponseEntity<>(messaggiRecuperati, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@GetMapping("/findByDestinatarioPag/{username}")
	public ResponseEntity<Page<Message>> cercaDaDestinatario(@PathVariable String username, Pageable page){
		
		Optional<User> utenteTrovato = userService.getByUsername(username);
		
		if(utenteTrovato.isPresent()) {
			Page<Message> messaggiRecuperati = service.cercaMessaggiDestinatario(utenteTrovato.get(), page);
			return new ResponseEntity<>(messaggiRecuperati, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@GetMapping("/getMessaggiByTitleContentPag/{text}")
	public ResponseEntity<Page<Message>> cercaDaTitoloContent(@PathVariable String text, Pageable page){
		
		Page<Message> messaggiRecuperati = service.cercaMessaggiTitoloContent(text, page);
		
		return new ResponseEntity<>(messaggiRecuperati, HttpStatus.OK);
		
	}
	
}
