package br.unisul.exemplo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.FabricanteDAO;
import br.unisul.exemplo.dao.MarcaDAO;
import br.unisul.exemplo.domain.Fabricante;
import br.unisul.exemplo.domain.Marca;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class MarcaBean extends GenericBean<Marca> {

	private List<Fabricante> fabricantes;
	
	@Override
	public void limpar() {
		this.entidade = new Marca();
	}

	@PostConstruct
	public void carregar() {
		this.dao = new MarcaDAO();
		this.limpar();
		this.lista = new ArrayList<Marca>();
		
		this.fabricantes = new FabricanteDAO().listar(this.entidade.getFabricante());
		
		this.listar();
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
}
