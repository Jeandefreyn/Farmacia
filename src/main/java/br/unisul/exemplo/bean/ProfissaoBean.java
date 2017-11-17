package br.unisul.exemplo.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.ProfissaoDAO;
import br.unisul.exemplo.domain.Profissao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProfissaoBean extends GenericBean<Profissao>{

	@Override
	public void limpar() {
		this.entidade = new Profissao();
	}

	@PostConstruct
	public void carregar() {
		this.dao = new ProfissaoDAO();
		this.limpar();
		this.lista = new ArrayList<Profissao>();
		
		this.listar();
	}
}
