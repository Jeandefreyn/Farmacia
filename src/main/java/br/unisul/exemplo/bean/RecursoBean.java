package br.unisul.exemplo.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.RecursoDAO;
import br.unisul.exemplo.domain.Recurso;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RecursoBean extends GenericBean<Recurso>{

	@Override
	public void limpar() {
		this.entidade = new Recurso();
	}

	@PostConstruct
	public void carregar() {
		this.limpar();
		this.dao = new RecursoDAO();
		this.lista = new ArrayList<Recurso>();
		
		this.listar();
	}

}
