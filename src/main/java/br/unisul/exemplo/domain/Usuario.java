package br.unisul.exemplo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Usuario extends Pessoa {

	@Column
	private String login;
	
	@Column
	private String senha;
	
	@JoinColumn
	@OneToOne(optional=true)
	private Profissao profissao;
	
	@OneToMany
	private List<Recurso> recursos;
	
	public Usuario() {
		this.profissao = new Profissao();
		this.recursos = new ArrayList<Recurso>();
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Profissao getProfissao() {
		return profissao;
	}
	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}
	public List<Recurso> getRecursos() {
		return recursos;
	}
	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
}
