package br.edu.ifrn.sinapiPRO.dto;

public class OrcamentoBase {

	private String mes;
	private Integer totalNacional;
	private Integer totalInternacional;
	
	public OrcamentoBase() {
		
	}

	public OrcamentoBase(String mes, Integer totalNacional, Integer totalInternacional) {
		this.mes = mes;
		this.totalNacional = totalNacional;
		this.totalInternacional = totalInternacional;
	}

	public String getMes() {
		return mes;
	}

	public Integer getTotalNacional() {
		return totalNacional;
	}

	public void setTotalNacional(Integer totalNacional) {
		this.totalNacional = totalNacional;
	}

	public Integer getTotalInternacional() {
		return totalInternacional;
	}

	public void setTotalInternacional(Integer totalInternacional) {
		this.totalInternacional = totalInternacional;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	
}
