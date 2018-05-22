package br.edu.ifrn.sinapiPRO.repository.helper.estado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifrn.sinapiPRO.model.Estado;
import br.edu.ifrn.sinapiPRO.repository.filter.EstadoFilter;

public interface EstadosQueries {
	
	public Page<Estado> filtra(EstadoFilter filtro, Pageable pageable);
	
}
