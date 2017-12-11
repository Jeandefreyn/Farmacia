package br.unisul.exemplo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Mercadoria extends Entidade {

	@Column(nullable=false)
	private String nome;

	@Column
	private String concentracao;
	
	@Column
	private String unidadeMedida;

	@Column
	private String codigoBarra;

	@JoinColumn
	@OneToOne
	private ViaAdministracao viaAdministracao;
	
	@Transient
	private String caminhoTemporario;

	public Mercadoria() {
		this.viaAdministracao = new ViaAdministracao();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConcentracao() {
		return concentracao;
	}

	public void setConcentracao(String concentracao) {
		this.concentracao = concentracao;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public ViaAdministracao getViaAdministracao() {
		return viaAdministracao;
	}

	public void setViaAdministracao(ViaAdministracao viaAdministracao) {
		this.viaAdministracao = viaAdministracao;
	}

	public String getCaminhoTemporario() {
		return caminhoTemporario;
	}

	public void setCaminhoTemporario(String caminhoTemporario) {
		this.caminhoTemporario = caminhoTemporario;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
}
