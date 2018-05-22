package br.edu.ifrn.sinapiPRO.repository.helper.composicao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.edu.ifrn.sinapiPRO.dto.ComposicaoDTO;
import br.edu.ifrn.sinapiPRO.dto.ValorItensEstoque;
import br.edu.ifrn.sinapiPRO.model.Composicao;
import br.edu.ifrn.sinapiPRO.repository.filter.ComposicaoFilter;
import br.edu.ifrn.sinapiPRO.repository.paginacao.PaginacaoUtil;

public class ComposicoesImpl implements ComposicoesQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Composicao> filtrar(ComposicaoFilter filtro, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Composicao.class);
		// Criteria criteria = manager.unwrap(Session.class).createQuery(Composicao.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public List<ComposicaoDTO> porSkuOuNome(String skuOuNome) {
		String jpql = "select new br.edu.ifrn.sinapiPRO.dto.ComposicaoDTO(codigo, sku, nome, base, valor) "
				+ "from Composicao where lower(sku) like lower(:skuOuNome) or lower(nome) like lower(:skuOuNome)";
		List<ComposicaoDTO> composicoesFiltradas = manager.createQuery(jpql, ComposicaoDTO.class)
					.setParameter("skuOuNome", skuOuNome + "%")
					.getResultList();
		return composicoesFiltradas;
	}
	
	@Override
	public ValorItensEstoque valorItensEstoque() {
		String query = "select new br.edu.ifrn.sinapiPRO.dto.ValorItensEstoque(sum(valor * quantidadeEstoque), sum(quantidadeEstoque)) from Composicao";
		return manager.createQuery(query, ValorItensEstoque.class).getSingleResult();
	}
	
	private Long total(ComposicaoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Composicao.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ComposicaoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (isEstadoPresente(filtro)) {
				criteria.add(Restrictions.eq("estado", filtro.getEstado()));
			}

			if (filtro.getSabor() != null) {
				criteria.add(Restrictions.eq("sabor", filtro.getSabor()));
			}

			if (filtro.getBase() != null) {
				criteria.add(Restrictions.eq("base", filtro.getBase()));
			}

			if (filtro.getValorDe() != null) {
				criteria.add(Restrictions.ge("valor", filtro.getValorDe()));
			}

			if (filtro.getValorAte() != null) {
				criteria.add(Restrictions.le("valor", filtro.getValorAte()));
			}
		}
	}
	
	private boolean isEstadoPresente(ComposicaoFilter filtro) {
		return filtro.getEstado() != null && filtro.getEstado().getCodigo() != null;
	}

}
