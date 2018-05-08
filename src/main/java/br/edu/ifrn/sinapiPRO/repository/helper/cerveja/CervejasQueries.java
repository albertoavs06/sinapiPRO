package br.edu.ifrn.sinapiPRO.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifrn.sinapiPRO.dto.CervejaDTO;
import br.edu.ifrn.sinapiPRO.dto.ValorItensEstoque;
import br.edu.ifrn.sinapiPRO.model.Cerveja;
import br.edu.ifrn.sinapiPRO.repository.filter.CervejaFilter;

public interface CervejasQueries {

	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
	public List<CervejaDTO> porSkuOuNome(String skuOuNome);
	
	public ValorItensEstoque valorItensEstoque();
	
}
