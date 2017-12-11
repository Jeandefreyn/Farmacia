package br.unisul.exemplo.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

import br.unisul.exemplo.dao.UsuarioDAO;
import br.unisul.exemplo.domain.Usuario;
import br.unisul.exemplo.util.SessionContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	private UsuarioDAO usuarioDAO;
	private Usuario usuario;
	
	private String login;
	private String senha;
	
	@PostConstruct
	public void carregar(){
		
		this.usuarioDAO = new UsuarioDAO();
		this.usuario = new Usuario();
		
	}

	public String fazerLogin(){
		try {
			this.usuario = this.usuarioDAO.buscarLoginSenha(this.login, this.senha);

			if (this.usuario != null){
				
				SessionContext.getInstance().setAttribute("usuario", this.usuario);
				
				return "/root/principal.xhtml?faces-redirect=true";
			} else {
				Messages.addGlobalError("Usuário ou senha incorretos, favor verificar!");
				FacesContext.getCurrentInstance().validationFailed();
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String encerrarSessao(){
		SessionContext.getInstance().encerrarSessao();
		return "/root/login.xhtml";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
