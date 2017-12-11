package br.unisul.exemplo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.unisul.exemplo.dao.DispensacaoDAO;
import br.unisul.exemplo.dao.EstoqueDAO;
import br.unisul.exemplo.dao.LoteDAO;
import br.unisul.exemplo.dao.MercadoriaDAO;
import br.unisul.exemplo.dao.PacienteDAO;
import br.unisul.exemplo.dao.TipoReceitaDAO;
import br.unisul.exemplo.domain.Dispensacao;
import br.unisul.exemplo.domain.Estoque;
import br.unisul.exemplo.domain.ItemDispensacao;
import br.unisul.exemplo.domain.Mercadoria;
import br.unisul.exemplo.domain.Paciente;
import br.unisul.exemplo.domain.TipoReceita;
import br.unisul.exemplo.domain.Usuario;
import br.unisul.exemplo.util.SessionContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DispensacaoBean extends GenericBean<Dispensacao> {

	private ItemDispensacao itemDispensacao;
	
	private List<Paciente> pacientes;
	private List<Estoque> estoques;
	private List<TipoReceita> tiposReceitas;
	private List<Mercadoria> mercadorias;
	
	private transient PacienteDAO pacienteDAO;
	private transient EstoqueDAO estoqueDAO;
	private transient TipoReceitaDAO tipoReceitaDAO;
	private transient MercadoriaDAO mercadoriaDAO;
	private transient LoteDAO loteDAO;
	
	private Usuario usuarioLogado;
	
	@Override
	public void limpar() {
		this.entidade = new Dispensacao();
		this.entidade.setDataDispensacao(new Date(System.currentTimeMillis()));
	}
	
	@PostConstruct
	public void carregar() {
		this.limpar();
		this.dao = new DispensacaoDAO();
		this.pacienteDAO = new PacienteDAO();
		this.estoqueDAO = new EstoqueDAO();
		this.tipoReceitaDAO = new TipoReceitaDAO();
		this.mercadoriaDAO = new MercadoriaDAO();
		this.loteDAO = new LoteDAO();
		
		this.lista = new ArrayList<Dispensacao>();
		this.pacientes = this.pacienteDAO.listar(this.entidade.getPaciente());
		this.tiposReceitas = this.tipoReceitaDAO.listar(this.entidade.getTipoReceita());
		this.mercadorias = this.mercadoriaDAO.listar(null);
		
		this.itemDispensacao = new ItemDispensacao();
		
		this.usuarioLogado = SessionContext.getInstance().getUsuarioLogado();
		
		this.listar();
	}
	
	@Override
	public void cadastrar() {
		
		try {
			this.entidade.setResponsavel(SessionContext.getInstance().getUsuarioLogado());
			
			if (this.entidade.getItens().isEmpty()){
				Messages.addGlobalInfo("Favor adicionar um item a lista");
				return;
			} else {
				for (ItemDispensacao item : this.entidade.getItens()) {
					
					Estoque estoque = new Estoque();
					estoque.setLote(item.getLote());
					estoque.setMercadoria(item.getMercadoria());
					
					estoque = this.estoqueDAO.selecionar(estoque);
					
					if (estoque != null){
						estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
						
						estoqueDAO.inserir(estoque);
					}
				}
			}
			
			super.cadastrar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selecionarPaciente(){
		if (this.entidade.getPaciente().getId() != null && this.entidade.getPaciente().getId() != 0)
			this.entidade.setPaciente(this.entidade.getPaciente());
	}
	
	public void adicionarItem(){
		
		try {
			if (this.itemDispensacao != null){
				
				this.itemDispensacao.setMercadoria(this.mercadoriaDAO.selecionar(this.itemDispensacao.getMercadoria()));
				this.itemDispensacao.setLote(this.loteDAO.selecionar(this.itemDispensacao.getLote()));
				
				this.entidade.getItens().add(this.itemDispensacao);
				Messages.addGlobalInfo("Item adicionado com sucesso");
			}
			
			this.itemDispensacao = new ItemDispensacao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removerItem(){
		if (this.itemDispensacao != null){
			this.entidade.getItens().remove(this.itemDispensacao);
			Messages.addGlobalInfo("Item removido com sucesso");
		}
	}

	public void buscarEstoque(){
		
		Estoque estoque = new Estoque();
		estoque.setMercadoria(this.itemDispensacao.getMercadoria());
		
		this.estoques = this.estoqueDAO.listar(estoque);
		
		if (this.estoques == null || this.estoques.isEmpty()){
			Messages.addGlobalInfo("Não existe estoque para esta mercadoria");
			this.estoques = new ArrayList<Estoque>();
		}
	}
	
	public ItemDispensacao getItemDispensacao() {
		return itemDispensacao;
	}

	public void setItemDispensacao(ItemDispensacao itemDispensacao) {
		this.itemDispensacao = itemDispensacao;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	public List<TipoReceita> getTiposReceitas() {
		return tiposReceitas;
	}

	public void setTiposReceitas(List<TipoReceita> tiposReceitas) {
		this.tiposReceitas = tiposReceitas;
	}

	public List<Mercadoria> getMercadorias() {
		return mercadorias;
	}

	public void setMercadorias(List<Mercadoria> mercadorias) {
		this.mercadorias = mercadorias;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
