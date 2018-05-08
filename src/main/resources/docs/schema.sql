USE sinapiPRO;

-- Base de Precos 

CREATE TABLE base_precos (
    codigo_precos BIGINT NOT NULL AUTO_INCREMENT,
	estado VARCHAR(2) NOT NULL, 
    ano_mes VARCHAR(6) NOT NULL, 
    descricao VARCHAR(120) NOT NULL UNIQUE,
    constraint pk_codigo_precos primary key (codigo) 
    
);


CREATE TABLE base_precos_mensal ( 
	codigo_base_fk BIGINT NOT NULL, 
	estado VARCHAR(2) NOT NULL, 
	ano_mes VARCHAR(6) NOT NULL, 
    codigo_insumo VARCHAR(10) NOT NULL,
    preco decimal(10,2) NOT NULL, 
    constraint pk_base_precos_mensal primary key (codigo_base_fk, estado, ano_mes, codigo_insumo) 
); 

CREATE TABLE base_insumos ( 
	codigo BIGINT NOT NULL,
    descricao VARCHAR(120) NOT NULL,     
    base_precos_padrao_fk BIGINT, 
    constraint fk_tlb_base_preco foreign key (base_precos_padrao_fk) references base_precos(codigo),
	constraint pk_codigo_base_insumos primary key (codigo) 
); 

CREATE TABLE insumo ( 
    base_insumos BIGINT, 
    base_precos BIGINT, 
	codigo_insumo VARCHAR(10), 
    classe VARCHAR(100), 
    grupo VARCHAR(100), 
    descricao VARCHAR(400), 
    unidade VARCHAR(2), 
    constraint pk_codigo_insumo primary key (base_insumo, base_precos, codigo_insumo)
);

CREATE TABLE composicao (
	codigo_composicao VARCHAR(10),
    classe VARCHAR(100), 
    grupo VARCHAR(100), 
    descricao VARCHAR(400),
    unidade VARCHAR(2), 
    coeficiente DECIMAL(10,7), 
    preco_unitario BIGINT
); 


CREATE TABLE item_composicao ( 
	codigo_item VARCHAR(10),
	codigo_composicao_fk VARCHAR(10), 
    tipo CHAR(1),
    classe VARCHAR(100),
    grupo VARCHAR(100), 
    coeficiente DECIMAL(10,7),
    preco_unitario BIGINT, 
    constraint tipo check(ativo in ('C','c','I','i')),
    constraint fk_item_compo primary key (codigo_composicao_fk,  ) 
); 


CREATE TABLE orcamento (
	codigo BIGINT,
    
	
);

CREATE TABLE itemOrcamento (

);


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
    nome_modelo VARCHAR(120) not null unique; 
    ativo char(1) constraint modelo_ativo check(ativo in ('S','s','N','n')),
    constraint fk_tlb_marca foreign key (id_marca) references marca(id_marca),
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
        
        
        