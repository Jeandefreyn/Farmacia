package br.unisul.exemplo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity(name="via_administracao")
public class ViaAdministracao extends Entidade {

	@Column(nullable=false)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
