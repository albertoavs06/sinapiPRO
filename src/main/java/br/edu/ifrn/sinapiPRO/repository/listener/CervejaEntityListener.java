package br.edu.ifrn.sinapiPRO.repository.listener;

import javax.persistence.PostLoad;

import br.edu.ifrn.sinapiPRO.SinapiProApplication;
import br.edu.ifrn.sinapiPRO.model.Cerveja;
import br.edu.ifrn.sinapiPRO.storage.FotoStorage;

public class CervejaEntityListener {

	@PostLoad
	public void postLoad(final Cerveja cerveja) {
		FotoStorage fotoStorage = SinapiProApplication.getBean(FotoStorage.class);
		
		cerveja.setUrlFoto(fotoStorage.getUrl(cerveja.getFotoOuMock()));
		cerveja.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + cerveja.getFotoOuMock()));
	}
	
}
