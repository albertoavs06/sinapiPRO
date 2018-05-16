package br.edu.ifrn.sinapiPRO.service.event.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.edu.ifrn.sinapiPRO.model.Cerveja;
import br.edu.ifrn.sinapiPRO.model.ItemVenda;
import br.edu.ifrn.sinapiPRO.repository.Cervejas;

@Component
public class VendaListener {

	@Autowired
	private Cervejas cervejas;
	
	@EventListener
	public void vendaEmitida(VendaEvent vendaEvent) {
		for (ItemVenda item : vendaEvent.getVenda().getItens()) {
			Cerveja cerveja = cervejas.getOne(item.getCerveja().getCodigo());
			cerveja.setQuantidadeEstoque(cerveja.getQuantidadeEstoque() - item.getQuantidade());
			cervejas.save(cerveja);
		}
	}
	
}
