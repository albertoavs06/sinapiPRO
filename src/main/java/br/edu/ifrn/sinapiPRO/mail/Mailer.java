package br.edu.ifrn.sinapiPRO.mail;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.edu.ifrn.sinapiPRO.model.Composicao;
import br.edu.ifrn.sinapiPRO.model.ItemOrcamento;
import br.edu.ifrn.sinapiPRO.model.Orcamento;
 

@Component
public class Mailer {
	
	private static Logger logger = LoggerFactory.getLogger(Mailer.class);

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
 	
	@Async
	public void enviar(Orcamento orcamento) {
		Context context = new Context(new Locale("pt", "BR"));
		
		context.setVariable("orcamento", orcamento);
		context.setVariable("logo", "logo");
		
		Map<String, String> fotos = new HashMap<>();
		boolean adicionarMockComposicao = false;
		
		/*
		for (ItemOrcamento item : orcamento.getItens()) {
			
		Linha composicao = item.getComposicao();
			
			if (composicao.temFoto()) {
				String cid = "foto-" + composicao.getCodigo();
				context.setVariable(cid, cid);
				
				fotos.put(cid, composicao.getFoto() + "|" + composicao.getContentType());
			} else {
				adicionarMockComposicao = true;
				context.setVariable("mockComposicao", "mockComposicao");
			}
			
		}
		
		 
		 */
		try {
			String email = thymeleaf.process("mail/ResumoOrcamento", context);
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("teste@ifrn.edu.br");
			helper.setTo(orcamento.getCliente().getEmail());
			helper.setSubject(String.format("SinapiPRO - Orcamento nº %d", orcamento.getCodigo()));
			helper.setText(email, true);
			
			helper.addInline("logo", new ClassPathResource("static/images/logo-gray.png"));
			
		
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Erro enviando e-mail", e);
		}
	}
	
}
