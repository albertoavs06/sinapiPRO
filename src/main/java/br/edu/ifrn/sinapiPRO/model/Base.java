package br.edu.ifrn.sinapiPRO.model;

public enum Base {

	SINAPI("Sinapi"),
	PROPRIA("Propria");
	
	private String descricao;
	
	Base(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() { 
		return descricao;
	}
	
}
