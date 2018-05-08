package br.edu.ifrn.sinapiPRO.controller.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.edu.ifrn.sinapiPRO.model.Orcamento;

@Component
public class OrcamentoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Orcamento.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "cliente.codigo", "", "Selecione um cliente na pesquisa rápida");
		
		Orcamento Orcamento = (Orcamento) target;
		validarSeInformouApenasHorarioEntrega(errors, Orcamento);
		validarSeInformouItens(errors, Orcamento);
		validarValorTotalNegativo(errors, Orcamento);
	}

	private void validarValorTotalNegativo(Errors errors, Orcamento venda) {
		if (venda.getValorTotal().compareTo(BigDecimal.ZERO) < 0) {
			errors.reject("", "Valor total não pode ser negativo");
		}
	}

	private void validarSeInformouItens(Errors errors, Orcamento orcamento) {
		if (orcamento.getItens().isEmpty()) {
			errors.reject("", "Adicione pelo menos uma composicao no orçamento");
		}
	}

	private void validarSeInformouApenasHorarioEntrega(Errors errors, Orcamento venda) {
		if (venda.getHorarioEntrega() != null && venda.getDataEntrega() == null) {
			errors.rejectValue("dataEntrega", "", "Informe uma data da entrega para um horário");
		}
	}

}
