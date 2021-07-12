package br.com.marvel.loja.comics;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.marvel.loja.cliente.Cliente;
import br.com.marvel.loja.integracao.ComicsFeign;
import br.com.marvel.loja.integracao.MarvelResponse;

@RestController
@RequestMapping("/comics")
public class ComicController {
	
	@Value("${privateKey}")
	private String privateKey;
	@Value("${apiKey}")
	private String apiKey ;
	private String ts = "1625975267";
	private String hash = "b1236ac53f6b1adbbc89be068e7caf27";
	
	@Autowired
	private ComicsFeign comicFeign;
	
	@PersistenceContext
	private EntityManager em;
	
	
	@GetMapping("cliente/{clienteId}")
	public ResponseEntity<?> getComic(@PathVariable Long clienteId,
									  @PathParam("comicId") Long comicId){
		
		var response = comicFeign.getComics(comicId, ts, apiKey , hash);
		if(!response.getStatusCode().is1xxInformational()) {
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}

		return ResponseEntity.ok(response);
	}
	
		
	@PostMapping("cliente/{clienteId}")
	@Transactional
	public ResponseEntity<?> cadastraComicDoCliente(@PathVariable Long clienteId,						  
									  @Valid @RequestBody IdComic idComic, UriComponentsBuilder uriBuilder){

		ResponseEntity<MarvelResponse>	comicResponse = comicFeign.getComics(idComic.getIdComic(), ts, apiKey , hash);
		
		if (comicResponse.getBody().getData().getResults().isEmpty()) {
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}
		
		Comic comic = new Comic(comicResponse);
		Cliente cliente = em.find(Cliente.class,clienteId);
		
		cliente.setComic(comic);
		em.persist(cliente);
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
		
	@GetMapping
	public ResponseEntity<?> getAllComic() {
		var response = comicFeign.getAllComics( ts, apiKey , hash).getBody().getData().getResults();
			
		return ResponseEntity.ok(response);	
	}

}
