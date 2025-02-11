package com.example.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class Blog {
	
	public static int idPost=1;
	public static int idAutore=1;
	
	ArrayList<Post> listaPost= new ArrayList<>();
	ArrayList<Author> listaAutori= new ArrayList<>();

	/**
	 * Metodo di inserimento di un nuovo Post
	 * @param nuovoPost
	 * @return
	 */
	public int nuovoPost(Post nuovoPost) {
		nuovoPost.setId(idPost);
		idPost++;
		listaPost.add(nuovoPost);
		return nuovoPost.getId();
	}
	
	/**
	 * Metodo di inserimento di un nuovo Autore
	 * @param nuovoAutore
	 * @return
	 */
	public int nuovoAutore(Author nuovoAutore){
		nuovoAutore.setId(idAutore);
		idAutore++;
		listaAutori.add(nuovoAutore);
		return nuovoAutore.getId();
	}
	
	/**
	 * Metodo che mostra tutti i post presenti nel sistema
	 * @return
	 */
	public List<Post> vediPost(){
		return listaPost;
	}
	
	/**
	 * Metodo che mostra tutti gli autori presenti nel sistema
	 * @return
	 */
	public List<Author> vediAutori(){
		return listaAutori;
	}
	
	/**
	 * Metodo che recupera un autore attraverso l'identificativo
	 * @param id
	 * @return
	 */
	public Author recuperaAutore(int id) {
		for(int i=0; i<listaAutori.size(); i++) {
			if(id==listaAutori.get(i).getId()) {
				return listaAutori.get(i);
			}
		}
		
		throw new RuntimeException("Autore non trovato");
	}
	
	/**
	 * Metodo che recupera un post attraverso l'identificativo
	 * @param id
	 * @return
	 */
	public Post recuperaPost(int id) {
		for(int i=0; i<listaPost.size(); i++) {
			if(id==listaPost.get(i).getId()) {
				return listaPost.get(i);
			}
		}
		
		throw new RuntimeException("Post non presente");
	}

	/**
	 * Metodo che sostituisce un autore
	 * @param autoreModificato
	 * @return
	 */
	public boolean modificaAutore(Author autoreModificato, int id) {
		Author autoreTrovato = this.recuperaAutore(id);
		if(autoreTrovato!=null) {
			int posizione = listaAutori.indexOf(autoreTrovato );
			autoreModificato.setId(id);
			listaAutori.set(posizione, autoreModificato);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Metodo che sostituisce un post
	 * @param postModificato
	 * @return
	 */
	public boolean modificaPost(Post postModificato, int id) {
		
		Post postTrovato = this.recuperaPost(id);
		if(postTrovato!=null) {
			int posizione= listaPost.indexOf(postTrovato);
			postModificato.setId(id);
			listaPost.set(posizione, postModificato);
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Metodo che cancella un post tramite l'identificativo
	 * @param id
	 * @return
	 */
	public String cancellaPost(int id) {
		Post postTrovato = recuperaPost(id);
		if(listaPost.remove(postTrovato)) {
			return "Il post " +id+ " è stato cancellato con successo!";
		}
		return "Il post " +id+ " NON è presente nel sistema";
	}
	
	/**
	 * Metodo che cancella un autore tramite l'identificativo
	 * @param id
	 * @return
	 */
	public String cancellaAutore(int id) {
		Author autoreTrovato = recuperaAutore(id);
		if(listaAutori.remove(autoreTrovato)) {
			return "L'autore " +id+ " è stato cancellato con successo!";
		}
		return "L'autore " +id+ " NON è presente nel sistema";
	}
}
