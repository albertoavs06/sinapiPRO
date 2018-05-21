package br.edu.ifrn.sinapiPRO.controller;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrn.sinapiPRO.dto.PeriodoRelatorio;
import br.edu.ifrn.sinapiPRO.service.RelatorioService;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/orcamentosEmitidos")
	public ModelAndView relatorioOrcamentosEmitidos() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioOrcamentosEmitidos");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}
	
	@PostMapping("/orcamentosEmitidos")
	public ResponseEntity<byte[]> gerarRelatorioOrcamentoEmitidos(PeriodoRelatorio periodoRelatorio) throws Exception {
		byte[] relatorio = relatorioService.gerarRelatorioOrcamentosEmitidos(periodoRelatorio); 
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
}












