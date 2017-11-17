package br.unisul.exemplo.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.unisul.exemplo.dao.DAOI;
import br.unisul.exemplo.domain.Usuario;
import br.unisul.exemplo.util.SessionContext;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public abstract class GenericBean<T> implements Bean, Serializable {

	protected T entidade;
	protected transient DAOI<T> dao;
	protected List<T> lista;
	
	private Usuario loginSession;
	
	public GenericBean() {
		this.loginSession = SessionContext.getInstance().getUsuarioLogado();
	}
	
	public void cadastrar(){
		try {
			this.dao.inserir(this.entidade);
			Messages.addGlobalInfo("Registro inserido/alterado com sucesso");
			this.limpar();
			this.listar();
			RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao inserir/alterar registro");
			e.printStackTrace();
		}
	}
	
	public void prepareExcluir(){
		this.selecionar();
	}
	
	public void excluir(){
		try {
			this.dao.excluir(this.entidade);
			Messages.addGlobalInfo("Registro removido com sucesso");
			this.limpar();
			this.listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao remover registro");
			e.printStackTrace();
		}
	}
	
	public void listar(){
		try {
			this.lista = this.dao.listar(this.entidade);
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao listar registros");
			e.printStackTrace();
		}
	}
	
	public void selecionar(){
		try {
			this.entidade = this.dao.selecionar(this.entidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(){
		this.selecionar();
	}
	
	public T getEntidade() {
		return entidade;
	}

	public void setEntidade(T entidade) {
		this.entidade = entidade;
	}

	public List<T> getLista() {
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	public Usuario getLoginSession() {
		return loginSession;
	}

	public void setLoginSession(Usuario loginSession) {
		this.loginSession = loginSession;
	}
}
