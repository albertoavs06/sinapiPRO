package br.edu.ifrn.sinapiPRO.model;

public enum StatusOrcamento {

	ORCAMENTO("Orçamento"), 
	EMITIDO("Emitido"), 
	CANCELADO("Cancelado");

	private String descricao;

	StatusOrcamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
