package br.edu.ifrn.sinapiPRO.dto;

import java.math.BigDecimal;

import br.edu.ifrn.sinapiPRO.model.Origem;

public class ComposicaoDTO {

	private Long codigo;
	private String sku;
	private String nome;
	private String origem;
	private BigDecimal valor;

	public ComposicaoDTO(Long codigo, String sku, String nome, Origem origem, BigDecimal valor) {
		this.codigo = codigo;
		this.sku = sku;
		this.nome = nome;
		this.origem = origem.getDescricao();
		this.valor = valor;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	
}
