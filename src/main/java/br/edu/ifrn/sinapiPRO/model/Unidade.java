package br.edu.ifrn.sinapiPRO.model;

public enum Unidade {

	KG("kg"),
	M2("M²");
	
	private String descricao;
	
	Unidade(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
