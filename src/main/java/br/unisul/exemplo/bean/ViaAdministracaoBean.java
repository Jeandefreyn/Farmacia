package br.unisul.exemplo.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.ViaAdministracaoDAO;
import br.unisul.exemplo.domain.ViaAdministracao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ViaAdministracaoBean extends GenericBean<ViaAdministracao>{

	@Override
	public void limpar() {
		this.entidade = new ViaAdministracao();
	}

	@PostConstruct
	public void carregar() {
		this.limpar();
		this.lista = new ArrayList<ViaAdministracao>();
		this.dao = new ViaAdministracaoDAO();
		this.listar();
	}
}
