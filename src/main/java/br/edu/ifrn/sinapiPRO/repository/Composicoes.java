package br.edu.ifrn.sinapiPRO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.sinapiPRO.model.Composicao;
import br.edu.ifrn.sinapiPRO.repository.helper.composicao.ComposicaoQueries;

@Repository
public interface Composicoes extends JpaRepository<Composicao, Long>, ComposicaoQueries {

}
