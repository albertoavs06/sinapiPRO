create table marca (
    id_marca number(7),
    nome_marca varchar2(120) not null unique,
    ativo char(1) not null,
    constraint pk_id_marca primary key (id_marca),
    constraint marca_ativo check(ativo in ('s','S','n','N'))
);

CREATE table modelo ( 
    id_marca number(7) NOT NULL, 
    id_modelo number(7) NOT NULL, 
    nome_modelo VARCHAR3(120) not null unique; 
    ativo char(1) constraint modelo_ativo check(ativo in ('S','s','N','n')),
    constraint fk_tlb_marca foreign ke (id_marca) references marca(id_marca),
    constraint pk_modelo primary key (id_marca, id_modelo) 
); 

CREATE table versao number(7) not null, 
    id_marca number(7) not null, 
    id_modelo number(7) not null, 
    id_versao number(7) not null, 
    nome_versao varchar2(120) not null, 
    ativo char(1), 
    constraint versao_ativo check(ativo in ('S','s','N','n')),
    constraint fk_tbl_modelo foreign key (id_marca, id_modelo) references modelo (id_marca, id_modelo),
    constraint pk_versao primary key (id_marca, id_modelo, id_versao) 
); 

select * from StudentSchool  
where (StudentId = 233) and (SchoolID = 22)      

CREATE TABLE base_precos ( 
    codigo      BIGINT, 
    descricao   VARCHAR(255), 
    estado      VARCHAR(2), 
    ano_mes     VARCHAR(6),
    PRIMARY KEY (codigo) 
);

==================================================


CREATE TABLE base_precos ( 
    codigo      BIGINT, 
    descricao   VARCHAR(255), 
    estado      VARCHAR(2), 
    recordmonth DATE, 
    recodrdyear DATE, 
    PRIMARY KEY (employeeid, recordmonth, recodrdyear) 
  ) 





Menu Lateral 
------------
Orçamentos 
Orcamento Atual 
Composições 
    Base Sinapi 
    Base Propria 
        Lista 
        Cadastro
Insumos
    Base Sinapi 
    Base Propria 
        Lista   
        Cadastro                    



Orçamentos 
----------
numero 
descricao
obra 
Período

Orçamento
---------
Estado
Data
TipoObra
Descricao 
BDI

SALVAR/INICIAR ORCAMENTO 

____pesquisar itens orcamento_____  ___selecionar Nivel____ 
ADICIONAR_ETAPA   ADICIONAR_INSUMO  ADICIONAR_COMPOSICAO     
-----------------------------------------------------------





Lista de Insumos do Orçamento
-----------------------------
Código descricao       Tipo         Unidade  Quant     Preco   Total  Ajustado Ações
4790   PREDREIRO       Mão de Obra  H       2.0000   R$15,00  R$30,00          Editar       



BaseInsumos (1) <-----> BasePrecos(*)
 

BasePrecos (1)
    codigo 
    descricao
    estado
    ano_mes
     
    BasePrecosMensal (*)
        PK (codigo_base_fk, estado_fk, mes_ano_fk, codigo_insumo)
        codigo_base_fk
        estado_fk
        mes_ano_fk
        codigo_insumo_fk
        preco_insumo 

BaseInsumos (1)
    codigo 
    descricao 
    codigo_basePrecosPadrao 

    Insumo (*)
        id_insumo (codigo_base_insumo, codigo_insumo) 
        classe
        grupo
        descricao
        unidade
         

    Composicao (*)
        id_composicao (codigo_base_insumo, codigo_composicao)
        classe  
        grupo
        descricao
        unidade
        quantidade
        preco_unitario
        total

        ItemComposicao
            id_item (id_composicao, codigo_item)
            tipo (C-composicao, I-Insumo)
            classe
            id_item (pode ser composicao ou insumo)
            coeficiente
            preco_unitario 
            total
            

    * Insumo ou composicao pode ser duplicado para outra base       

    * Ao cadastrar o insumo - Incluir BaseInsumo / Base de Preco



Orcamento 
    codigo
    data_criacao
    TipoObra
    Descricao
    base_Insumo
    base_preco
    BDI
    Total

    Etapa
        cod_orcamento 
        Nivel
        SubNivel
        Descricao


        ItemOrcamento 
            Id (codigo_orcamento, Nivel, SubNivel, Codigo_Item)
            Nivel
            SubNivel 
            tipo (N=Nivel, S=Subnivel, C-Composição, I-Insumo)
            codigo_item
            ordem
            base
            descricao_item
            unidade
            coeficiente
            preco_unitario
            preco_bdi
            total


