package br.edu.ifrn.sinapiPRO.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Insumo")
public class Insumo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private Boolean ativo;
	private String descricao; 
	private String divisao; 
	private String subDivisao; 
	
	@NotNull(message = "A unidade é obrigatório")
	@Enumerated(EnumType.STRING)
	private Unidade unidade;
	
	@NotNull(message = "A espécie é obrigatório")
	@Enumerated(EnumType.STRING)
	private EspecieInsumo especieInsumo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDivisao() {
		return divisao;
	}

	public void setDivisao(String divisao) {
		this.divisao = divisao;
	}

	public String getSubDivisao() {
		return subDivisao;
	}

	public void setSubDivisao(String subDivisao) {
		this.subDivisao = subDivisao;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public EspecieInsumo getEspecieInsumo() {
		return especieInsumo;
	}

	public void setEspecieInsumo(EspecieInsumo especieInsumo) {
		this.especieInsumo = especieInsumo;
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
	
}
