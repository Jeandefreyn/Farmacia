package br.unisul.exemplo.domain;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Dispensacao extends Entidade{

	private Date dataDispensacao;
	private Paciente paciente;
	private Usuario responsavel;
	private List<Medicamento> itens;
	
	public Dispensacao() {
		this.paciente = new Paciente();
		this.responsavel = new Usuario();
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

	public List<Medicamento> getItens() {
		return itens;
	}
	public void setItens(List<Medicamento> itens) {
		this.itens = itens;
	}
}
