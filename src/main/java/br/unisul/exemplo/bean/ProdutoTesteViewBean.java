package br.unisul.exemplo.bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class ProdutoTesteViewBean {

	public StreamedContent getImagem() throws IOException {
		StreamedContent imagem = null;
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String idImagem = context.getExternalContext().getRequestParameterMap().get("parId");

			File f = new File("C:/produtos_imgs/" + idImagem + ".png");

			if (f.isFile()) {
				Path path = Paths.get(f.getAbsolutePath());
				InputStream stream = Files.newInputStream(path);
				imagem = new DefaultStreamedContent(stream);
			}
			return imagem;
		}
	}
}
