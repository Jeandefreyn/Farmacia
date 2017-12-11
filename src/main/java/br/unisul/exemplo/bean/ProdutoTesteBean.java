package br.unisul.exemplo.bean;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.unisul.exemplo.dao.ProdutoTesteDAO;
import br.unisul.exemplo.domain.ProdutoTeste;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoTesteBean extends GenericBean<ProdutoTeste>{

	@Override
	public void limpar() {
		this.entidade = new ProdutoTeste();
	}

	@PostConstruct
	public void carregar() {
		this.dao = new ProdutoTesteDAO();
		this.limpar();
	}
	
	@Override
	public void cadastrar() {
		try {
			
			File diretorio = new File("C:/produtos_imgs/teste");
			
			if (!diretorio.exists())
				diretorio.mkdirs();
			
			ProdutoTeste teste = this.dao.salvar(this.entidade);
			
			Path origem = Paths.get(this.entidade.getCaminhoTemporario());
			Path destino = Paths.get(diretorio.getAbsolutePath() + "/teste_" + teste.getId()+ ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			Messages.addGlobalInfo(teste.getNome() + " inserido com sucesso");
			this.limpar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao salvar");
			e.printStackTrace();
		}
	}
	
	public void buscarImagem(){
		try {
			this.entidade = ((ProdutoTesteDAO) this.dao).buscarPorID(this.entidade.getId());

			if (this.entidade == null){
				this.entidade = new ProdutoTeste();
				this.entidade.setNome("Não encontrado");
				this.entidade.setId(0L);
			}
		
		} catch (Exception e) {
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
}
