package br.unisul.exemplo.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.TipoReceitaDAO;
import br.unisul.exemplo.domain.TipoReceita;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoReceitaBean extends GenericBean<TipoReceita> implements Serializable {

	@Override
	public void limpar() {
		this.entidade = new TipoReceita();
	}

	@PostConstruct
	public void carregar() {
		this.limpar();
		this.lista = new ArrayList<TipoReceita>();
		this.dao = new TipoReceitaDAO();
		this.listar();
	}
}
