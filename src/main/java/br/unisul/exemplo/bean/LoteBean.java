package br.unisul.exemplo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.LoteDAO;
import br.unisul.exemplo.dao.MarcaDAO;
import br.unisul.exemplo.domain.Lote;
import br.unisul.exemplo.domain.Marca;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LoteBean extends GenericBean<Lote>{

	private List<Marca> marcas;
	
	@Override
	public void limpar() {
		this.entidade = new Lote();
	}

	@PostConstruct
	public void carregar() {
		this.dao = new LoteDAO();
		this.limpar();
		this.lista = new ArrayList<Lote>();
		
		this.marcas = new MarcaDAO().listar(this.entidade.getMarca());
		
		this.listar();
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}
}
