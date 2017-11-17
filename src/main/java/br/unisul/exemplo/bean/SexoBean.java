package br.unisul.exemplo.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.SexoDAO;
import br.unisul.exemplo.domain.Sexo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SexoBean extends GenericBean<Sexo>{

	@Override
	public void limpar() {
		this.entidade = new Sexo();
	}

	@PostConstruct
	public void carregar() {
		this.limpar();
		this.lista = new ArrayList<Sexo>();
		this.dao = new SexoDAO();
		this.listar();
	}
}
