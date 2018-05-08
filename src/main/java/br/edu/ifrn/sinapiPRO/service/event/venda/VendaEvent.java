package br.edu.ifrn.sinapiPRO.service.event.venda;

import br.edu.ifrn.sinapiPRO.model.Venda;

public class VendaEvent {

	private Venda venda;

	public VendaEvent(Venda venda) {
		this.venda = venda;
	}

	public Venda getVenda() {
		return venda;
	}

}
