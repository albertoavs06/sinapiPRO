package br.edu.ifrn.sinapiPRO.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import br.edu.ifrn.sinapiPRO.repository.listener.CervejaEntityListener;
import br.edu.ifrn.sinapiPRO.validation.SKU;

@EntityListeners(CervejaEntityListener.class)
@Entity
@Table(name = "obra")
public class Obra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column  
	private String codigoComposicao;

	@NotBlank(message = "A descrição é obrigatória")
	@Size(max = 50, message = "O tamanho da descrição deve estar entre 1 e 100")
    private String descricao;
	private String endereco; 
	private String cep; 
	private String cidade;
	private String estado; 

	@Column(name = "data_inicio")
	private LocalDateTime dataInicio;
	
	@Column(name = "data_conclusao")
	private LocalDateTime dataConclusao;
	
	private BigDecimal area; 
	private String telefone; 
	
	// Parametros da Obra 
	
	@ManyToOne
	@JoinColumn(name = "codigo_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "codigo_basePreco")
	private BasePrecos basePrecos;
	
	@ManyToOne
	@JoinColumn(name = "codigo_baseInsumo")
	private BaseInsumos baseInsumos;
	
	private String CEI; 
	private String ART; 
	
	private BigDecimal bdi_insumos;
	private BigDecimal bdi_terceiros; 
	private BigDecimal bdi_servicos; 
	private BigDecimal leisSociais; 
	private BigDecimal taxaAdministracao; 

	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCodigoComposicao() {
		return codigoComposicao;
	}

	public void setCodigoComposicao(String codigoComposicao) {
		this.codigoComposicao = codigoComposicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDateTime dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BasePrecos getBasePrecos() {
		return basePrecos;
	}

	public void setBasePrecos(BasePrecos basePrecos) {
		this.basePrecos = basePrecos;
	}

	public BaseInsumos getBaseInsumos() {
		return baseInsumos;
	}

	public void setBaseInsumos(BaseInsumos baseInsumos) {
		this.baseInsumos = baseInsumos;
	}

	public String getCEI() {
		return CEI;
	}

	public void setCEI(String cEI) {
		CEI = cEI;
	}

	public String getART() {
		return ART;
	}

	public void setART(String aRT) {
		ART = aRT;
	}

	public BigDecimal getBdi_insumos() {
		return bdi_insumos;
	}

	public void setBdi_insumos(BigDecimal bdi_insumos) {
		this.bdi_insumos = bdi_insumos;
	}

	public BigDecimal getBdi_terceiros() {
		return bdi_terceiros;
	}

	public void setBdi_terceiros(BigDecimal bdi_terceiros) {
		this.bdi_terceiros = bdi_terceiros;
	}

	public BigDecimal getBdi_servicos() {
		return bdi_servicos;
	}

	public void setBdi_servicos(BigDecimal bdi_servicos) {
		this.bdi_servicos = bdi_servicos;
	}

	public BigDecimal getLeisSociais() {
		return leisSociais;
	}

	public void setLeisSociais(BigDecimal leisSociais) {
		this.leisSociais = leisSociais;
	}

	public BigDecimal getTaxaAdministracao() {
		return taxaAdministracao;
	}

	public void setTaxaAdministracao(BigDecimal taxaAdministracao) {
		this.taxaAdministracao = taxaAdministracao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Obra other = (Obra) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
