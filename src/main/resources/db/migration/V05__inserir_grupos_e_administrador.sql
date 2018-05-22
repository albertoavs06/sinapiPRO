INSERT INTO grupo (codigo, nome) VALUES (1, 'Administrador');
INSERT INTO grupo (codigo, nome) VALUES (2, 'Vendedor');

-- Hash = admin 
-- $2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG

INSERT INTO usuario (nome, email, senha, ativo) VALUES ('Admin', 'admin@sinapipro.com', '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG', 1)