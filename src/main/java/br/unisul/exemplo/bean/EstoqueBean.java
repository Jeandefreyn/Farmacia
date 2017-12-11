package br.unisul.exemplo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.unisul.exemplo.dao.EstoqueDAO;
import br.unisul.exemplo.dao.LoteDAO;
import br.unisul.exemplo.dao.MarcaDAO;
import br.unisul.exemplo.dao.MercadoriaDAO;
import br.unisul.exemplo.domain.Estoque;
import br.unisul.exemplo.domain.Lote;
import br.unisul.exemplo.domain.Marca;
import br.unisul.exemplo.domain.Mercadoria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstoqueBean extends GenericBean<Estoque>{

	private List<Lote> lotes;
	private List<Marca> marcas;
	private List<Mercadoria> mercadorias;
	
	private transient MarcaDAO marcaDAO;
	private transient MercadoriaDAO mercadoriaDAO;
	private transient LoteDAO loteDAO;
	
	@Override
	public void limpar() {
		this.entidade = new Estoque();
	}

	@PostConstruct
	public void carregar() {
		this.limpar();
		
		this.dao = new EstoqueDAO();
		this.marcaDAO = new MarcaDAO();
		this.mercadoriaDAO = new MercadoriaDAO();
		this.loteDAO = new LoteDAO();
		
		this.lista = new ArrayList<Estoque>();
		this.lotes = new ArrayList<Lote>();
		this.marcas = this.marcaDAO.listar(this.entidade.getLote().getMarca());
		this.mercadorias = this.mercadoriaDAO.listar(this.entidade.getMercadoria());
		
		this.listar();
	}

	@Override
	public void selecionar() {
		super.selecionar();
		this.entidade.setAumentoEstoque(true);
		this.carregarLotes();
	}
	
	public void buscarMercadoria(){
		try {
			
			if (this.entidade.getMercadoria().getCodigoBarra() != null && !this.entidade.getMercadoria().getCodigoBarra().isEmpty()){
				this.entidade.setMercadoria(this.mercadoriaDAO.selecionar(this.entidade.getMercadoria()));
				
				if (this.entidade.getMercadoria() == null){
					this.entidade.setMercadoria(new Mercadoria());
					Messages.addGlobalError("Não foi encontrado mercadoria com esse código de barras");
				}
			} else {
				this.entidade.setMercadoria(new Mercadoria());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregarLotes(){
		if (this.entidade.getLote() != null && this.entidade.getLote().getMarca() != null && this.entidade.getLote().getMarca().getId() != null)
			this.lotes = this.loteDAO.listar(this.entidade.getLote());
	}
	
	public void selecionarLote(){
		try {
			if (this.entidade.getLote().getId() != null && this.entidade.getLote().getId() != 0)
				this.entidade.setLote(this.loteDAO.selecionar(this.entidade.getLote()));
			else
				this.entidade.setLote(new Lote());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selecionarMercadoria() {
		try {
			if (this.entidade.getMercadoria().getId() != null && this.entidade.getMercadoria().getId() != 0){
				
				Mercadoria mercadoria = new Mercadoria();
				mercadoria.setId(this.entidade.getMercadoria().getId());
				
				this.entidade.setMercadoria(this.mercadoriaDAO.selecionar(mercadoria));
				
				if (this.entidade.getMercadoria() == null)
					this.entidade.setMercadoria(new Mercadoria());
				
			} else {
				this.entidade.setMercadoria(new Mercadoria());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Mercadoria> getMercadorias() {
		return mercadorias;
	}

	public void setMercadorias(List<Mercadoria> mercadorias) {
		this.mercadorias = mercadorias;
	}
}
