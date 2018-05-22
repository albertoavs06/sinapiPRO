package br.edu.ifrn.sinapiPRO.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sinapi_insumos")
public class Insumo {

	@Id
	private Integer codigo;
	private String uf; 
	private String anoMes; 
	private String base; 
	
	@Size(max = 400)
	private String descricao; 
	private String unidade; 
	private String origem; 
	
	private BigDecimal preco;
	
	@NotNull(message = "O estado é obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_estado")
	private Estado estado;

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getAnoMes() {
		return anoMes;
	}	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insumo other = (Insumo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Insumo [codigo=" + codigo + ", descricao=" + descricao + ", unidade=" + unidade + ", origem="
				+ origem + ", preco=" + preco + "]";
	} 
}
