package br.unisul.exemplo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.unisul.exemplo.dao.CidadeDAO;
import br.unisul.exemplo.dao.EnderecoDAO;
import br.unisul.exemplo.dao.EstadoDAO;
import br.unisul.exemplo.dao.FabricanteDAO;
import br.unisul.exemplo.domain.Cidade;
import br.unisul.exemplo.domain.Endereco;
import br.unisul.exemplo.domain.Estado;
import br.unisul.exemplo.domain.Fabricante;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class FabricanteBean extends GenericBean<Fabricante> {

	private transient EstadoDAO estadoDAO;
	private transient CidadeDAO cidadeDAO;
	private transient EnderecoDAO enderecoDAO;
	
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	@Override
	public void limpar() {
		this.entidade = new Fabricante();
	}

	@PostConstruct
	public void carregar() {
		this.dao = new FabricanteDAO();
		this.estadoDAO = new EstadoDAO();
		this.cidadeDAO = new CidadeDAO();
		this.enderecoDAO = new EnderecoDAO();
		this.limpar();
		this.lista = new ArrayList<Fabricante>();
		
		this.estados = this.estadoDAO.listar(this.entidade.getEndereco().getCidade().getEstado());
		
		this.listar();
	}
	
	@Override
	public void cadastrar() {
		
		try {
			if (this.entidade.getEndereco() != null) {

				if (this.entidade.getEndereco().getId() == null)
					this.entidade.setEndereco(this.enderecoDAO.salvar(this.entidade.getEndereco()));
				else
					this.enderecoDAO.inserir(this.entidade.getEndereco());
			}
			
			super.cadastrar();
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao inserir/alterar registro");
			e.printStackTrace();
		}
	}
	
	@Override
	public void selecionar() {
		super.selecionar();
		
		if (this.entidade != null && this.entidade.getEndereco() != null && this.entidade.getEndereco().getCidade() != null)
			this.cidades = this.cidadeDAO.listar(this.entidade.getEndereco().getCidade());
	}
	
	public void procurarEndereco(){
		
		try {
			Endereco endereco = this.enderecoDAO.selecionar(this.entidade.getEndereco());
			
			if (endereco != null){
				this.entidade.getEndereco().setCep(endereco.getCep());
				this.entidade.getEndereco().setBairro(endereco.getBairro());
				this.entidade.getEndereco().setLogradouro(endereco.getLogradouro());
				this.entidade.getEndereco().setCidade(endereco.getCidade());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void limparNumeroEndereco(){
		if (this.entidade.getEndereco().isSemNumero())
			this.entidade.getEndereco().setNumero(null);
	}

	public void carregarCidades(){
		this.cidades = this.cidadeDAO.listar(this.entidade.getEndereco().getCidade());
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
