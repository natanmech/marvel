package br.com.marvel.loja.comics;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;

import br.com.marvel.loja.integracao.Comics;
import br.com.marvel.loja.integracao.Creator;
import br.com.marvel.loja.integracao.MarvelResponse;
import br.com.marvel.loja.integracao.Price;

@Entity
public class Comic {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long comicId;
	private String title;
	private String isbn;
	private Boolean promoDay = false;
	@Length(max = 1000)
	private String description;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Criador> criadores = new ArrayList<>(); 
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Preco> precos = new ArrayList<>(); ; 
	
	public Comic(ResponseEntity<MarvelResponse> c) {
		var in = c.getBody().getData().getResults().get(0);
		this.comicId = in.getId();
		this.title = in.getTitle();
		this.isbn = in.getIsbn();
		this.description = in.getDescription();
		
		for(Creator x: in.getCreators().getItems()) {			
			Criador criador = new Criador(x);
			this.criadores.add(criador);	

		}
		
		for(Price p: in.getPrices()) {
			Preco preco = new Preco(p);
			this.precos.add(preco);
		}
	}

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
	public List<Preco> getPrecos() {
		return precos;
	}
	public List<Criador> getCriadores() {
		return criadores;
	}
	public Long getComicId() {
		return comicId;
	}
	
	public Comic(Long comicId, String title, String isbn, String description, List<Criador> criadores,
			List<Preco> precos) {
		this.comicId = comicId;
		this.title = title;
		this.isbn = isbn;
		this.description = description;
		this.criadores = criadores;
		this.precos = precos;
	}
	public Comic(Comics in) {
		this.comicId = in.getId();
		this.title = in.getTitle();
		this.isbn = in.getIsbn();
		this.description = in.getDescription();

		for(Creator c: in.getCreators().getItems()) {
			Criador criador = new Criador(c);
			this.criadores.add(criador);
		}
		
		for(Price p: in.getPrices()) {
			Preco preco = new Preco(p);
			this.precos.add(preco);
		}

	}
	public Comic() {
	}

	public void setPromoDay(Boolean bol) {
		this.promoDay = bol;		
	}

	public Boolean getPromoDay() {
		return promoDay;
	}
	
}
