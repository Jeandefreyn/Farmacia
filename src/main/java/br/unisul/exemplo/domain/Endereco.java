package br.unisul.exemplo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Endereco extends Entidade {

	@Column
	private String cep;
	
	@Column
	private String logradouro;
	
	@Column
	private String complemento;

	@Column
	private String bairro;
	
	@Column
	private boolean semNumero;
	
	@Column
	private String numero;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Cidade cidade;
	
	public Endereco() {
		this.cidade = new Cidade();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public boolean isSemNumero() {
		return semNumero;
	}

	public void setSemNumero(boolean semNumero) {
		this.semNumero = semNumero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
}
