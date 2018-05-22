package br.edu.ifrn.sinapiPRO.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.sinapiPRO.controller.page.PageWrapper;
import br.edu.ifrn.sinapiPRO.model.Estado;
import br.edu.ifrn.sinapiPRO.repository.Estados;
import br.edu.ifrn.sinapiPRO.repository.filter.EstadoFilter;
import br.edu.ifrn.sinapiPRO.service.CadastroEstadoService;
import br.edu.ifrn.sinapiPRO.service.exception.NomeEstadoJaCadastradoException;

@Controller
@RequestMapping("/estados")
public class EstadosController {

	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@Autowired
	private Estados estados;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Estado estado) {
		return new ModelAndView("estado/CadastroEstado");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estado estado, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(estado);
		}
		
		try {
			cadastroEstadoService.salvar(estado);
		} catch (NomeEstadoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estado);
		}
		
		attributes.addFlashAttribute("mensagem", "Estado salvo com sucesso");
		return new ModelAndView("redirect:/estados/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estado estado, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		estado = cadastroEstadoService.salvar(estado);
		return ResponseEntity.ok(estado);
	}
	
	@GetMapping
	public ModelAndView pesquisar(EstadoFilter estadoFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("estado/PesquisaEstados");
		
		PageWrapper<Estado> paginaWrapper = new PageWrapper<>(estados.filtra(estadoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
}
