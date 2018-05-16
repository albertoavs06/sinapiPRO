package br.edu.ifrn.sinapiPRO.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifrn.sinapiPRO.model.Composicao;
import br.edu.ifrn.sinapiPRO.repository.Composicoes;
import br.edu.ifrn.sinapiPRO.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroComposicaoService {

	@Autowired
	private Composicoes composicoes;
	
	@Transactional
	public void salvar(Composicao composicao) {
		composicoes.save(composicao);
	}
	
	@Transactional
	public void excluir(Composicao composicao) {
		try {
			composicoes.delete(composicao);
			composicoes.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar Composição. Já foi usada em algum orçamento.");
		}
	}
	
}
