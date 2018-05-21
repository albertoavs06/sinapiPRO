package br.edu.ifrn.sinapiPRO.repository.helper.composicao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifrn.sinapiPRO.dto.ComposicaoDTO;
import br.edu.ifrn.sinapiPRO.dto.ValorItensEstoque;
import br.edu.ifrn.sinapiPRO.model.Composicao;
import br.edu.ifrn.sinapiPRO.repository.filter.ComposicaoFilter;

public interface ComposicoesQueries {

	public Page<Composicao> filtrar(ComposicaoFilter filtro, Pageable pageable);
	
	public List<ComposicaoDTO> porSkuOuNome(String skuOuNome);
	
	public ValorItensEstoque valorItensEstoque();
	
}
