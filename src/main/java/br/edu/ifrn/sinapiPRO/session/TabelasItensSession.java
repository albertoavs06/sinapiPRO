package br.edu.ifrn.sinapiPRO.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.edu.ifrn.sinapiPRO.model.Composicao;
import br.edu.ifrn.sinapiPRO.model.ItemOrcamento;

@SessionScope
@Component
public class TabelasItensSession {

	private Set<TabelaItensOrcamento> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Composicao composicao, int quantidade) {
		TabelaItensOrcamento tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(composicao, quantidade);
		tabelas.add(tabela);
	}

	public void alterarQuantidadeItens(String uuid, Composicao composicao, Integer quantidade) {
		TabelaItensOrcamento tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItens(composicao, quantidade);
	}

	public void excluirItem(String uuid, Composicao composicao) {
		TabelaItensOrcamento tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(composicao);
	}

	public List<ItemOrcamento> getItens(String uuid) {
		return buscarTabelaPorUuid(uuid).getItens();
	}
	
	public Object getValorTotal(String uuid) {
		return buscarTabelaPorUuid(uuid).getValorTotal();
	}
	
	private TabelaItensOrcamento buscarTabelaPorUuid(String uuid) {
		TabelaItensOrcamento tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensOrcamento(uuid));
		return tabela;
	}

	
}
