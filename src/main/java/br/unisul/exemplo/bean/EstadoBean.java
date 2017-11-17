package br.unisul.exemplo.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.EstadoDAO;
import br.unisul.exemplo.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean extends GenericBean<Estado> implements Serializable{

	@Override
	public void limpar() {
		this.entidade = new Estado();
	}

	@PostConstruct
	public void carregar() {
		this.dao = new EstadoDAO();
		this.limpar();
		this.lista = new ArrayList<Estado>();

		this.listar();
	}
}
