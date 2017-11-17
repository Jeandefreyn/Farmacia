package br.unisul.exemplo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.CidadeDAO;
import br.unisul.exemplo.dao.EstadoDAO;
import br.unisul.exemplo.domain.Cidade;
import br.unisul.exemplo.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean extends GenericBean<Cidade> implements Serializable {

	private transient EstadoDAO estadoDAO;;
	private List<Estado> estados;
	
	@Override
	public void limpar() {
		this.entidade = new Cidade();
		this.estados = this.estadoDAO.listar(this.entidade.getEstado());
	}

	@PostConstruct
	public void carregar() {
		this.estadoDAO = new EstadoDAO();
		this.dao = new CidadeDAO();
		this.limpar();
		this.lista = new ArrayList<Cidade>();
		
		this.listar();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}
