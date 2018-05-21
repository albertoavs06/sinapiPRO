package br.edu.ifrn.sinapiPRO.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifrn.sinapiPRO.model.StatusOrcamento;
import br.edu.ifrn.sinapiPRO.model.Orcamento;
import br.edu.ifrn.sinapiPRO.repository.Orcamentos;
import br.edu.ifrn.sinapiPRO.service.event.orcamento.OrcamentoEvent;

@Service
public class CadastroOrcamentoService {

	@Autowired
	private Orcamentos orcamentos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public Orcamento salvar(Orcamento orcamento) {
		if (orcamento.isSalvarProibido()) {
			throw new RuntimeException("Usuário tentando salvar um orcamento proibida");
		}
		
		if (orcamento.isNova()) {
			orcamento.setDataCriacao(LocalDateTime.now());
		} else {
			Orcamento orcamentoExistente = orcamentos.getOne(orcamento.getCodigo());
			orcamento.setDataCriacao(orcamentoExistente.getDataCriacao());
		}
		
		if (orcamento.getDataEntrega() != null) {
			orcamento.setDataHoraEntrega(LocalDateTime.of(orcamento.getDataEntrega()
					, orcamento.getHorarioEntrega() != null ? orcamento.getHorarioEntrega() : LocalTime.NOON));
		}
		
		return orcamentos.saveAndFlush(orcamento);
	}

	@Transactional
	public void emitir(Orcamento orcamento) {
		orcamento.setStatus(StatusOrcamento.EMITIDO);
		salvar(orcamento);
		
		publisher.publishEvent(new OrcamentoEvent(orcamento));
	}

	@PreAuthorize("#orcamento.usuario == principal.usuario or hasRole('CANCELAR_ORCAMENTO')")
	@Transactional
	public void cancelar(Orcamento orcamento) {
		Orcamento orcamentoExistente = orcamentos.getOne(orcamento.getCodigo());
		
		orcamentoExistente.setStatus(StatusOrcamento.CANCELADO);
		orcamentos.save(orcamentoExistente);
	}

}
