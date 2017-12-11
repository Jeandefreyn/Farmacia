package br.unisul.exemplo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class ItemDispensacao extends Entidade {

	@OneToOne
	@JoinColumn
	private Mercadoria mercadoria;
	
	@OneToOne
	@JoinColumn
	private Lote lote;
	
	@Column
	private Integer quantidade;

	public ItemDispensacao() {
		this.mercadoria = new Mercadoria();
		this.lote = new Lote();
	}

	public Mercadoria getMercadoria() {
		return mercadoria;
	}

	public void setMercadoria(Mercadoria mercadoria) {
		this.mercadoria = mercadoria;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
