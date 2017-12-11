package br.unisul.exemplo.bean;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.unisul.exemplo.dao.MercadoriaDAO;
import br.unisul.exemplo.dao.ViaAdministracaoDAO;
import br.unisul.exemplo.domain.Mercadoria;
import br.unisul.exemplo.domain.ViaAdministracao;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MercadoriaBean extends GenericBean<Mercadoria>{

	private List<ViaAdministracao> viasAdministracoes;
	
	@PostConstruct
	public void carregar() {
		this.limpar();
		this.dao = new MercadoriaDAO();
		this.lista = new ArrayList<Mercadoria>();
		
		this.viasAdministracoes = new ViaAdministracaoDAO().listar(this.entidade.getViaAdministracao());
		this.listar();
	}

	@Override
	public void limpar() {
		this.entidade = new Mercadoria();
	}
	
	@Override
	public void cadastrar() {
		try {

			if (this.entidade.getCaminhoTemporario() != null) {
				File diretorio = new File("C:/produtos_imgs/mercadoria");
				
				if (!diretorio.exists())
					diretorio.mkdirs();
				
				Mercadoria mercadoria = this.dao.salvar(this.entidade);
				
				Path origem = Paths.get(this.entidade.getCaminhoTemporario());
				Path destino = Paths.get(diretorio.getAbsolutePath() + "/mercadoria_" + mercadoria.getId()+ ".png");
				Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
				
				Messages.addGlobalInfo("Registro inserido/alterado com sucesso");
				
				this.limpar();
				this.listar();
				RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
			} else {
				super.cadastrar();
			}
			
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao inserir/alterar registro");
			e.printStackTrace();
		}
	}
	
	@Override
	public void excluir() {

		try {
			this.dao.excluir(this.entidade);

			File imagem = new File("C:/produtos_imgs/mercadoria/mercadoria_" + this.entidade.getId() + ".png");
			
			if (imagem != null)
				imagem.delete();
			
			Messages.addGlobalInfo("Registro removido com sucesso");
			this.limpar();
			this.listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao remover registro");
			e.printStackTrace();
		}
	}
	
	public void upload(FileUploadEvent event){
		
		try {

			UploadedFile imagem = event.getFile();
			Path caminho = Files.createTempFile(null, null); // Cria espaço temporário no servidor (Sistema operacional)
			Files.copy(imagem.getInputstream(), caminho, StandardCopyOption.REPLACE_EXISTING);
			
			this.entidade.setCaminhoTemporario(caminho.toString());
			Messages.addGlobalInfo(this.entidade.getCaminhoTemporario());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ViaAdministracao> getViasAdministracoes() {
		return viasAdministracoes;
	}

	public void setViasAdministracoes(List<ViaAdministracao> viasAdministracoes) {
		this.viasAdministracoes = viasAdministracoes;
	}
}
