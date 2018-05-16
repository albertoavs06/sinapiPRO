package br.edu.ifrn.sinapiPRO.service.event.orcamento;

import br.edu.ifrn.sinapiPRO.model.Orcamento;

public class OrcamentoEvent {

	private Orcamento orcamento;

	public OrcamentoEvent(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

}
