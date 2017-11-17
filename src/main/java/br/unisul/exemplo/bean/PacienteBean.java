package br.unisul.exemplo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unisul.exemplo.dao.CidadeDAO;
import br.unisul.exemplo.dao.EstadoDAO;
import br.unisul.exemplo.dao.PacienteDAO;
import br.unisul.exemplo.dao.SexoDAO;
import br.unisul.exemplo.domain.Cidade;
import br.unisul.exemplo.domain.Estado;
import br.unisul.exemplo.domain.Paciente;
import br.unisul.exemplo.domain.Sexo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PacienteBean extends GenericBean<Paciente> {

	private List<Sexo> sexos;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	private transient CidadeDAO cidadeDAO;
	
	@Override
	public void limpar() {
		this.entidade = new Paciente();
	}

	@PostConstruct
	public void carregar() {
		this.dao = new PacienteDAO();
		this.limpar();
		this.lista = new ArrayList<Paciente>();
		
		this.cidadeDAO = new CidadeDAO();
		
		this.estados = new EstadoDAO().listar(this.entidade.getCidadeNascimento().getEstado());
		this.sexos = new SexoDAO().listar(this.entidade.getSexo());
		
		this.listar();
	}

	public void carregarCidades(){
		this.cidades = this.cidadeDAO.listar(this.entidade.getCidadeNascimento());
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
	}
	
	public List<Sexo> getSexos() {
		return sexos;
	}

	public void setSexos(List<Sexo> sexos) {
		this.sexos = sexos;
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
}
