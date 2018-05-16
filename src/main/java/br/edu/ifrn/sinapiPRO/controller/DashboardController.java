package br.edu.ifrn.sinapiPRO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.sinapiPRO.repository.Clientes;
import br.edu.ifrn.sinapiPRO.repository.Orcamentos;

@Controller
public class DashboardController {

	@Autowired
	private Orcamentos orcamentos;
	 
	@Autowired
	private Clientes clientes;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		mv.addObject("orcamentosNoAno", orcamentos.valorTotalNoAno());
		mv.addObject("orcamentosNoMes", orcamentos.valorTotalNoMes());
		mv.addObject("ticketMedio", orcamentos.valorTicketMedioNoAno());
		mv.addObject("totalClientes", clientes.count());
		
		return mv;
	}
	
}
