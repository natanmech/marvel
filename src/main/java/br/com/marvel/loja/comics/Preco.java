package br.com.marvel.loja.comics;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.marvel.loja.integracao.Price;

@Entity
public class Preco {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double preco;
	private String tipo;
	
	public Preco(Price p) {
		this.preco = p.getPrice();
		this.tipo = p.getType();
	}

	public Double getPreco() {
		return preco;
	}
	
	public String Tipo() {
		return tipo;
	}

	public Preco() {
	}

	public void setPreco() {
		this.preco = preco * 0.90;
	}
	
	
}
