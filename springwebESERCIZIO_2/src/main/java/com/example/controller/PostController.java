package com.example.controller;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.model.Blog;
import com.example.model.Post;


@RestController
@RequestMapping("/blogPosts")
public class PostController {
	
	@Autowired
	Blog blog;
			
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public String newPost(@RequestBody Post post) {
		int id =blog.nuovoPost(post);	
		return "Il nuovo post con " +id+ " è stato inserito con successo";
	}
	
	
	@GetMapping(produces = "application/json")
	public List<Post> allPosts() {
		return blog.vediPost();
	}
	
	@GetMapping("/{id}")
	public Post vediPost(@PathVariable int id) {
			return blog.recuperaPost(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String modificaPost(@RequestBody Post postModificato, @PathVariable int id) {
		if(blog.modificaPost(postModificato, id)) {
			return "Modifica avvenuta con successo";
		}else {
			return "Impossibile effettuare la modifica. Post non presente nel sistema";
		}
	}
	
	
	@DeleteMapping("/{id}")
	public String cancellaPost(@PathVariable int id) {
		return blog.cancellaPost(id);
	}
	
	
	
	
}
