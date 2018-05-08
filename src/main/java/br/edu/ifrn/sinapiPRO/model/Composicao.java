package br.edu.ifrn.sinapiPRO.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "composicao")
public class Composicao {
	
	private static final long serialVersionUID = 1L;
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long codigo;
	
//	@NotBlank(message = "A descrição é obrigatória"
//	@Size(max = 200, message = "O tamanho da descrição deve estar entre 1 e 200")
//	private String descricao;
//	private 
	
	

	
}

/*
Código 			: Automatico, mas pode ser editado.
Descrição 		: Descrição da composição. Pode ser editado
Espécie 		: Lista todas as espécies cadastradas no sistema para serem vinculadas a nova composição.
Unidade 		: Lista todos os tipos de unidades cadastradas no sistema para serem vinculadas a nova composição.
Ativo 			: Ativa ou desativa a composição do sistema. Caso esteja desativado, a composição não é mais visível no sistema.
Base insumos 	: Listas todas as bases de insumos cadastradas no sistema para serem vinculadas a composição. Após o vínculo a nova composição fará parte desta base de insumos.
Descrição 		: Preenchida automaticamente. Apresenta o nome da composição que terá os itens inclusos.
Base de Preço 	: Permite escolher uma base de preço, com os insumos a serem adicionados. Ao ser escolhida, o sistema emite uma mensagem avisando o usuário se deseja substituir a base de preço e que ao fazê-lo os valores dos insumos já cadastrados serão também substituídos.
Última Alteração: Última data onde houve modificação na composição.
*/