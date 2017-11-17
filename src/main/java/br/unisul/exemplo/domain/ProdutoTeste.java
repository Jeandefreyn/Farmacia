package br.unisul.exemplo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity(name="produto_teste")
public class ProdutoTeste extends Entidade {

	@Column(length=100)
	private String nome;
	
	@Transient
	private String caminhoTemporario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCaminhoTemporario() {
		return caminhoTemporario;
	}

	public void setCaminhoTemporario(String caminhoTemporario) {
		this.caminhoTemporario = caminhoTemporario;
	}
}
