package br.unisul.exemplo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Dispensacao extends Entidade{

	@Column
	private Date dataDispensacao;
	
	@OneToOne
	@JoinColumn
	private Paciente paciente;
	
	@OneToOne
	@JoinColumn
	private Usuario responsavel;
	
	@OneToOne
	@JoinColumn
	private TipoReceita tipoReceita;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<ItemDispensacao> itens;
	
	public Dispensacao() {
		this.paciente = new Paciente();
		this.responsavel = new Usuario();
		this.itens = new ArrayList<ItemDispensacao>();
		this.tipoReceita = new TipoReceita();
	}

	public Date getDataDispensacao() {
		return dataDispensacao;
	}

	public void setDataDispensacao(Date dataDispensacao) {
		this.dataDispensacao = dataDispensacao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public List<ItemDispensacao> getItens() {
		return itens;
	}

	public void setItens(List<ItemDispensacao> itens) {
		this.itens = itens;
	}
}
