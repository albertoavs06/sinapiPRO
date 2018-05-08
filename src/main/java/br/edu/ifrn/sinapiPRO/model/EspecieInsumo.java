package br.edu.ifrn.sinapiPRO.model;

public enum EspecieInsumo {

	MATERIAL("Material"),
	HORA_HOMEM("Hora Homem"),
	SERVICO("Servico"),
	VERBA("Verba"),
	EQUIPAMENTO("Equipamento"),
	MAO_DE_OBRA("Mao de Obra"), 
	OUTROS("OUTROS");
	
	private String descricao;
	
	EspecieInsumo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
