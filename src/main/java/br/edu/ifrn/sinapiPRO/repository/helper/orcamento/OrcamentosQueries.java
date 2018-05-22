package br.edu.ifrn.sinapiPRO.repository.helper.orcamento;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifrn.sinapiPRO.dto.OrcamentoMes;
import br.edu.ifrn.sinapiPRO.dto.OrcamentoBase;
import br.edu.ifrn.sinapiPRO.model.Orcamento;
import br.edu.ifrn.sinapiPRO.repository.filter.OrcamentoFilter;

public interface OrcamentosQueries {

	public Page<Orcamento> filtrar(OrcamentoFilter filtro, Pageable pageable);
	
	public Orcamento buscarComItens(Long codigo);
	
	public BigDecimal valorTotalNoAno();
	public BigDecimal valorTotalNoMes();
	public BigDecimal valorTicketMedioNoAno();
	
	public List<OrcamentoMes> totalPorMes();
	public List<OrcamentoBase> totalPorBase();
	
}
