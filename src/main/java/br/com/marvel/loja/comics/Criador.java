package br.com.marvel.loja.comics;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.marvel.loja.integracao.Creator;

@Entity
public class Criador {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String role;
	
	public Criador(Creator c) {
		this.name = c.getName();
		this.role = c.getRole();
	}

	public String getName() {
		return name;
	}
	
	public String getRole() {
		return role;
	}

	@Deprecated
	public Criador() {

	}
	
	
}
