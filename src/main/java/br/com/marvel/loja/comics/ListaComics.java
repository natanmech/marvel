package br.com.marvel.loja.comics;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.http.ResponseEntity;

import br.com.marvel.loja.integracao.Comics;
import br.com.marvel.loja.integracao.ComicsFeign;
import br.com.marvel.loja.integracao.MarvelResponse;

public class ListaComics {
	
	private List<Long> comicIds;

	public List<Long> getComicId() {
		return comicIds;
	}

	public List<Comic> map(EntityManager em, ComicsFeign comicFeign, String ts, String apiKey, String hash) {
		    List<Comic> listaComics = new ArrayList<>();
		    
		    if(comicIds == null) {
		    	return null;
		    }
			for (int i = 0; i < comicIds.size(); i++) {
				
			ResponseEntity<MarvelResponse>	c = comicFeign.getComics(comicIds.get(i), ts, apiKey , hash);
					for(Comics in: c.getBody().getData().getResults()) {
						Comic comic = new Comic(in);
						listaComics.add(comic);
					}
			}			
			return listaComics;
	}


	@Deprecated
	public ListaComics() {
	}


}
