package br.com.marvel.loja.integracao;

import java.util.ArrayList;

import javax.persistence.Column;

public class Comics {

	private Long id;
	private String title;
	private String isbn;
	@Column(length = 1000)
	private String description;
	private ArrayList<Price> prices ;
	private Creators creators;
	
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getDescription() {
		return description;
	}
	public ArrayList<Price> getPrices() {
		return prices;
	}
	public Creators getCreators() {
		return creators;
	}

}



