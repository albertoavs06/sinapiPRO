package br.edu.ifrn.sinapiPRO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.sinapiPRO.model.Orcamento;
import br.edu.ifrn.sinapiPRO.repository.helper.orcamento.OrcamentosQueries;

public interface Orcamentos extends JpaRepository<Orcamento, Long>, OrcamentosQueries {

}
