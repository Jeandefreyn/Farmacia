package br.unisul.exemplo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@MappedSuperclass
public class Pessoa extends Entidade {
	
	@Column(nullable=false)
	private String nome;
	
	@Column(length=30)
	private String rg;
	
	@Column(length=14)
	private String cpf;
	
	@Column
	private Date dataNascimento;
	
	@OneToOne(optional=true)
	@JoinColumn
	private Sexo sexo;
	
	@OneToOne(optional=true)
	@JoinColumn
	private Cidade cidadeNascimento;
	
	public Pessoa() {
		this.sexo = new Sexo();
		this.cidadeNascimento = new Cidade();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Cidade getCidadeNascimento() {
		return cidadeNascimento;
	}
	public void setCidadeNascimento(Cidade cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}
}
