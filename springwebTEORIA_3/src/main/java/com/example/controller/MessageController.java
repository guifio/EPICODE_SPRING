package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.service.MessageService;
import com.example.service.UserService;

//@RestController
//@RequestMapping("/messages")
public class MessageController {

	@Autowired
	MessageService service;	
	
	@Autowired
	UserService userService;	
	
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
	
	
	@GetMapping("/findByTitle/{title}")
	public ResponseEntity<List<Message>> cercaDaTitolo(@PathVariable String title){
		List<Message> messaggiTrovati = service.cercaDaTitolo(title);
		return new ResponseEntity<>(messaggiTrovati,HttpStatus.FOUND); 
	}
	
	@GetMapping("/findByMittente/{username}")
	public ResponseEntity<List<Message>> cercaDaMittente(@PathVariable String username){
		
		Optional<User> utenteTrovato = userService.getByUsername(username);
		
		if(utenteTrovato.isPresent()) {
			List<Message> messaggiRecuperati = service.cercaMessaggiMittente(utenteTrovato.get());
			return new ResponseEntity<>(messaggiRecuperati, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@GetMapping("/findByDestinatario/{username}")
	public ResponseEntity<List<Message>> cercaDaDestinatario(@PathVariable String username){
		
		Optional<User> utenteTrovato = userService.getByUsername(username);
		
		if(utenteTrovato.isPresent()) {
			List<Message> messaggiRecuperati = service.cercaMessaggiDestinatario(utenteTrovato.get());
			return new ResponseEntity<>(messaggiRecuperati, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@GetMapping("/getMessaggiByTitleContent/{text}")
	public ResponseEntity<List<Message>> cercaDaTitoloContent(@PathVariable String text){
		
		List<Message> messaggiRecuperati = service.cercaMessaggiTitoloContent(text);
		
		if(messaggiRecuperati.size()>0) {
			return new ResponseEntity<>(messaggiRecuperati, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
}
