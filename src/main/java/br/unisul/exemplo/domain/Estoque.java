package br.unisul.exemplo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Estoque extends Entidade {

	@JoinColumn
	@OneToOne
	private Mercadoria mercadoria;
	
	@JoinColumn
	@OneToOne
	private Lote lote;

	@Column
	private Integer quantidade;
	
	@Transient
	private Boolean aumentoEstoque;
	
	public Estoque() {
		this.mercadoria = new Mercadoria();
		this.lote = new Lote();
		this.quantidade = 0;
		this.aumentoEstoque = false;
	}
	
	public Mercadoria getMercadoria() {
		return mercadoria;
	}
	public void setMercadoria(Mercadoria mercadoria) {
		this.mercadoria = mercadoria;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
	public Boolean getAumentoEstoque() {
		return aumentoEstoque;
	}
	public void setAumentoEstoque(Boolean aumentoEstoque) {
		this.aumentoEstoque = aumentoEstoque;
	}
}
