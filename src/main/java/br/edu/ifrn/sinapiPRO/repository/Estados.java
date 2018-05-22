package br.edu.ifrn.sinapiPRO.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.sinapiPRO.model.Estado;
import br.edu.ifrn.sinapiPRO.repository.helper.estado.EstadosQueries;

public interface Estados extends JpaRepository<Estado, Long>, EstadosQueries {
	
	public Optional<Estado> findByNomeIgnoreCase(String nome);
}
