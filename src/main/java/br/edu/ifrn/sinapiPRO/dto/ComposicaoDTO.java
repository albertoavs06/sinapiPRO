package br.edu.ifrn.sinapiPRO.dto;

import java.math.BigDecimal;

import br.edu.ifrn.sinapiPRO.model.Base;

public class ComposicaoDTO {

	private Long codigo;
	private String sku;
	private String nome;
	private String base;
	private BigDecimal valor;

	public ComposicaoDTO(Long codigo, String sku, String nome, Base base, BigDecimal valor) {
		this.codigo = codigo;
		this.sku = sku;
		this.nome = nome;
		this.base = base.getDescricao();
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

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	
}
