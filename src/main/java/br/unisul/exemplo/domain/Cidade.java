package br.unisul.exemplo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Cidade extends Entidade {

	@Column(length=100)
	private String nome;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Estado estado;
	
	public Cidade() {
		this.estado = new Estado();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
