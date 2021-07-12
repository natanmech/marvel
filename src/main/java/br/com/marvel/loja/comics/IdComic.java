package br.com.marvel.loja.comics;

import javax.validation.constraints.NotNull;
import br.com.marvel.loja.integracao.Comics;

public class IdComic {
	@NotNull
	private Long idComic;

	public Long getIdComic() {
		return idComic;
	}

	public Comic map(Comics comics) {
		Comics comic = new Comics();
		return new Comic(comic);
	}
	
}
