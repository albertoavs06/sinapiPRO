package br.edu.ifrn.sinapiPRO.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import br.edu.ifrn.sinapiPRO.model.Cerveja;
import br.edu.ifrn.sinapiPRO.model.ItemOrcamento;

class TabelaItensOrcamento {

	private String uuid;
	private List<ItemOrcamento> itens = new ArrayList<>();
	
	public TabelaItensOrcamento(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemOrcamento::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Cerveja cerveja, Integer quantidade) {
		Optional<ItemOrcamento> itemOrcamentoOptional = buscarItemPorCerveja(cerveja);
		
		ItemOrcamento itemOrcamento = null;
		if (itemOrcamentoOptional.isPresent()) {
			itemOrcamento = itemOrcamentoOptional.get();
			itemOrcamento.setQuantidade(itemOrcamento.getQuantidade() + quantidade);
		} else {
			itemOrcamento = new ItemOrcamento();
			itemOrcamento.setCerveja(cerveja);
			itemOrcamento.setQuantidade(quantidade);
			itemOrcamento.setValorUnitario(cerveja.getValor());
			itens.add(0, itemOrcamento);
		}
	}
	
	public void alterarQuantidadeItens(Cerveja cerveja, Integer quantidade) {
		ItemOrcamento itemOrcamento = buscarItemPorCerveja(cerveja).get();
		itemOrcamento.setQuantidade(quantidade);
	}
	
	public void excluirItem(Cerveja cerveja) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getCerveja().equals(cerveja))
				.findAny().getAsInt();
		itens.remove(indice);
	}
	
	public int total() {
		return itens.size();
	}

	public List<ItemOrcamento> getItens() {
		return itens;
	}
	
	private Optional<ItemOrcamento> buscarItemPorCerveja(Cerveja cerveja) {
		return itens.stream()
				.filter(i -> i.getCerveja().equals(cerveja))
				.findAny();
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensOrcamento other = (TabelaItensOrcamento) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
