package br.edu.ifrn.sinapiPRO.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.sinapiPRO.controller.page.PageWrapper;
import br.edu.ifrn.sinapiPRO.controller.validator.OrcamentoValidator;
import br.edu.ifrn.sinapiPRO.dto.OrcamentoMes;
import br.edu.ifrn.sinapiPRO.dto.OrcamentoOrigem;
import br.edu.ifrn.sinapiPRO.mail.Mailer;
import br.edu.ifrn.sinapiPRO.model.Cerveja;
import br.edu.ifrn.sinapiPRO.model.ItemOrcamento;
import br.edu.ifrn.sinapiPRO.model.StatusOrcamento;
import br.edu.ifrn.sinapiPRO.model.TipoPessoa;
import br.edu.ifrn.sinapiPRO.model.Orcamento;
import br.edu.ifrn.sinapiPRO.repository.Cervejas;
import br.edu.ifrn.sinapiPRO.repository.Orcamentos;
import br.edu.ifrn.sinapiPRO.repository.filter.OrcamentoFilter;
import br.edu.ifrn.sinapiPRO.security.UsuarioSistema;
import br.edu.ifrn.sinapiPRO.service.CadastroOrcamentoService;
import br.edu.ifrn.sinapiPRO.session.TabelasItensSession;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentosController {
	
	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private TabelasItensSession tabelaItens;
	
	@Autowired
	private CadastroOrcamentoService cadastroOrcamentoService;
	
	@Autowired
	private OrcamentoValidator orcamentoValidator;
	
	@Autowired
	private Orcamento orcamentos;
	
	@Autowired
	private Mailer mailer;
	
	@GetMapping("/nova")
	public ModelAndView nova(Orcamento orcamento) {
		ModelAndView mv = new ModelAndView("orcamento/CadastroOrcamento");
		
		setUuid(orcamento);
		
		mv.addObject("itens", orcamento.getItens());
		mv.addObject("valorFrete", orcamento.getValorFrete());
		mv.addObject("valorDesconto", orcamento.getValorDesconto());
		mv.addObject("valorTotalItens", tabelaItens.getValorTotal(orcamento.getUuid()));
		
		return mv;
	}
	
	@PostMapping(value = "/nova", params = "salvar")
	public ModelAndView salvar(Orcamento orcamento, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarOrcamento(orcamento, result);
		if (result.hasErrors()) {
			return nova(orcamento);
		}
		
		orcamento.setUsuario(usuarioSistema.getUsuario());
		
		cadastroOrcamentoService.salvar(orcamento);
		attributes.addFlashAttribute("mensagem", "Orcamento salvo com sucesso");
		return new ModelAndView("redirect:/orcamentos/nova");
	}

	@PostMapping(value = "/nova", params = "emitir")
	public ModelAndView emitir(Orcamento orcamento, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarOrcamento(orcamento, result);
		if (result.hasErrors()) {
			return nova(orcamento);
		}
		
		orcamento.setUsuario(usuarioSistema.getUsuario());
		
		cadastroOrcamentoService.emitir(orcamento);
		attributes.addFlashAttribute("mensagem", "Orçamento emitido com sucesso");
		return new ModelAndView("redirect:/orcamentos/nova");
	}
	
	@PostMapping(value = "/nova", params = "enviarEmail")
	public ModelAndView enviarEmail(Orcamento orcamento, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarOrcamento(orcamento, result);
		if (result.hasErrors()) {
			return nova(orcamento);
		}
		
		orcamento.setUsuario(usuarioSistema.getUsuario());
		
		orcamento = cadastroOrcamentoService.salvar(orcamento);
		mailer.enviar(orcamento);
		
		attributes.addFlashAttribute("mensagem", String.format("Orçamento nº %d salvo com sucesso e e-mail enviado", orcamento.getCodigo()));
		return new ModelAndView("redirect:/orcamentos/nova");
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCerveja, String uuid) {
		Cerveja cerveja = cervejas.getOne(codigoCerveja);
		tabelaItens.adicionarItem(uuid, cerveja, 1);
		return mvTabelaItensOrcamento(uuid);
	}
	
	@PutMapping("/item/{codigoCerveja}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoCerveja") Cerveja cerveja
			, Integer quantidade, String uuid) {
		tabelaItens.alterarQuantidadeItens(uuid, cerveja, quantidade);
		return mvTabelaItensOrcamento(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoCerveja}")
	public ModelAndView excluirItem(@PathVariable("codigoCerveja") Cerveja cerveja
			, @PathVariable String uuid) {
		tabelaItens.excluirItem(uuid, cerveja);
		return mvTabelaItensOrcamento(uuid);
	}
	
	@GetMapping
	public ModelAndView pesquisar(OrcamentoFilter orcamentoFilter,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("orcamento/PesquisaOrcamentos");
		mv.addObject("todosStatus", StatusOrcamento.values());
		mv.addObject("tiposPessoa", TipoPessoa.values());
		
		PageWrapper<Orcamento> paginaWrapper = new PageWrapper<>(orcamentos.filtrar(orcamentoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Orcamento orcamento = orcamentos.buscarComItens(codigo);
		
		setUuid(orcamento);
		for (ItemOrcamento item : orcamento.getItens()) {
			tabelaItens.adicionarItem(orcamento.getUuid(), item.getCerveja(), item.getQuantidade());
		}
		
		ModelAndView mv = nova(orcamento);
		mv.addObject(orcamento);
		return mv;
	}
	
	@PostMapping(value = "/nova", params = "cancelar")
	public ModelAndView cancelar(Orcamento orcamento, BindingResult result
				, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		try {
			cadastroOrcamentoService.cancelar(orcamento);
		} catch (AccessDeniedException e) {
			ModelAndView mv = new ModelAndView("error");
			mv.addObject("status", 403);
			return mv;
		}
		
		attributes.addFlashAttribute("mensagem", "Orçamento cancelado com sucesso");
		return new ModelAndView("redirect:/Orcamentos/" + orcamento.getCodigo());
	}
	
	@GetMapping("/totalPorMes")
	public @ResponseBody List<OrcamentoMes> listarTotalOrcamentoPorMes() {
		return orcamentos.totalPorMes();
	}
	
	@GetMapping("/porOrigem")
	public @ResponseBody List<OrcamentoOrigem> orcamentosPorNacionalidade() {
		return this.orcamentos.totalPorOrigem();
	}
	
	private ModelAndView mvTabelaItensOrcamento(String uuid) {
		ModelAndView mv = new ModelAndView("orcamento/TabelaItensOrcamento");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		mv.addObject("valorTotal", tabelaItens.getValorTotal(uuid));
		return mv;
	}
	
	private void validarOrcamento(Orcamento orcamento, BindingResult result) {
		orcamento.adicionarItens(tabelaItens.getItens(orcamento.getUuid()));
		orcamento.calcularValorTotal();
		
		orcamentoValidator.validate(orcamento, result);
	}
	
	private void setUuid(Orcamento orcamento) {
		if (StringUtils.isEmpty(orcamento.getUuid())) {
			orcamento.setUuid(UUID.randomUUID().toString());
		}
	}

}
