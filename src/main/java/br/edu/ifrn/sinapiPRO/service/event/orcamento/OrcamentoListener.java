package br.edu.ifrn.sinapiPRO.service.event.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.edu.ifrn.sinapiPRO.model.Composicao;
import br.edu.ifrn.sinapiPRO.model.ItemOrcamento;
import br.edu.ifrn.sinapiPRO.repository.Composicoes;

@Component
public class OrcamentoListener {

	@Autowired
	private Composicoes composicoes;
	
	@EventListener
	public void orcamentoEmitida(OrcamentoEvent orcamentoEvent) {
		for (ItemOrcamento item : orcamentoEvent.getOrcamento().getItens()) {
			Composicao composicao = composicoes.getOne(item.getComposicao().getCodigo());
			composicao.setQuantidadeEstoque(composicao.getQuantidadeEstoque() - item.getQuantidade());
			composicoes.save(composicao);
		}
	}
	
}
