package br.unisul.exemplo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.CidadeDAO;
import br.unisul.exemplo.dao.EstadoDAO;
import br.unisul.exemplo.dao.ProfissaoDAO;
import br.unisul.exemplo.dao.RecursoDAO;
import br.unisul.exemplo.dao.SexoDAO;
import br.unisul.exemplo.dao.UsuarioDAO;
import br.unisul.exemplo.domain.Cidade;
import br.unisul.exemplo.domain.Estado;
import br.unisul.exemplo.domain.Profissao;
import br.unisul.exemplo.domain.Recurso;
import br.unisul.exemplo.domain.Sexo;
import br.unisul.exemplo.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean extends GenericBean<Usuario> {

	private List<Estado> estados;
	private List<Cidade> cidades;
	private List<Profissao> profissoes;
	private List<Sexo> sexos;
	private List<Recurso> recursos;
	
	private transient CidadeDAO cidadeDAO;
	
	@Override
	public void limpar() {
		this.entidade = new Usuario();
		this.cidades = new ArrayList<Cidade>();
	}

	@PostConstruct
	public void carregar() {
		this.limpar();
		this.lista = new ArrayList<Usuario>();

		this.dao = new UsuarioDAO();
		this.cidadeDAO = new CidadeDAO();
		
		this.estados = new EstadoDAO().listar(this.entidade.getCidadeNascimento().getEstado());
		this.profissoes = new ProfissaoDAO().listar(this.entidade.getProfissao());
		this.sexos = new SexoDAO().listar(this.entidade.getSexo());
		this.recursos = new RecursoDAO().listar(null);
		this.listar();
	}

	@Override
	public void selecionar() {
		super.selecionar();
		
		if (this.entidade.getCidadeNascimento() != null)
			this.cidades = this.cidadeDAO.listar(this.entidade.getCidadeNascimento());
		else
			this.entidade.setCidadeNascimento(new Cidade());
		
		if (this.entidade.getSexo() == null)
			this.entidade.setSexo(new Sexo());
		
		if (this.entidade.getProfissao() == null)
			this.entidade.setProfissao(new Profissao());
	}
	
	public void carregarCidades(){
		if (this.entidade.getCidadeNascimento() != null)
			this.cidades = this.cidadeDAO.listar(this.entidade.getCidadeNascimento());
	}
	
	public List<Profissao> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(List<Profissao> profissoes) {
		this.profissoes = profissoes;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

	public void setSexos(List<Sexo> sexos) {
		this.sexos = sexos;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
}
