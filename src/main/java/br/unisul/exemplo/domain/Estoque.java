package br.unisul.exemplo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Estoque extends Entidade {

	@JoinColumn
	@OneToOne
	private Medicamento medicamento;
	
	@JoinColumn
	@OneToOne
	private Lote lote;

	@Column
	private Integer quantidade;
	
	public Estoque() {
		this.medicamento = new Medicamento();
		this.lote = new Lote();
	}
	
	public Medicamento getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	public Lote getLote() {
		return lote;
	}
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
