INSERT INTO estado (id, nome, uf) VALUES (1, 'Santa Catarina', 'SC');
INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'Tubarão', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (2, 'Jaguaruna', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (3, 'Gravatal', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (4, 'Laguna', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (5, 'Capivari de Baixo', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (6, 'Braço do Norte', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (7, 'Rio Fortuna', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (8, 'Florianópolis', (SELECT MAX(id) FROM estado));

INSERT INTO estado (id, nome, uf) VALUES (2, 'Rio Grande do Sul', 'RS');
INSERT INTO cidade (id, nome, estado_id) VALUES (9, 'Porto Alegre', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (10, 'Alegrete', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (11, 'Caxias do Sul', (SELECT MAX(id) FROM estado));

INSERT INTO estado (id, nome, uf) VALUES (3, 'Paraná', 'PR');
INSERT INTO cidade (id, nome, estado_id) VALUES (12, 'Paranavaí', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (13, 'Umuarama', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (14, 'Curitiba', (SELECT MAX(id) FROM estado));

INSERT INTO estado (id, nome, uf) VALUES (4, 'São Paulo', 'SP');
INSERT INTO cidade (id, nome, estado_id) VALUES (15, 'Cotia', (SELECT MAX(id) FROM estado));
INSERT INTO cidade (id, nome, estado_id) VALUES (16, 'São Paulo', (SELECT MAX(id) FROM estado));

INSERT INTO profissao (id, nome, cbo) VALUES (1,  'Médico da estratégia de saúde da família', '2251.42');
INSERT INTO profissao (id, nome, cbo) VALUES (2, 'Agente comunitário de saúde', '5151.05');
INSERT INTO profissao (id, nome, cbo) VALUES (3, 'Farmacêutico', '2234.05');

INSERT INTO sexo (id, nome) VALUES (1, 'Masculino');
INSERT INTO sexo (id, nome) VALUES (2, 'Feminino');

INSERT INTO tipo_receita (id, nome) VALUES (1, 'Azul');
INSERT INTO tipo_receita (id, nome) VALUES (2, 'Branca');
INSERT INTO tipo_receita (id, nome) VALUES (3, 'Básica');
INSERT INTO tipo_receita (id, nome) VALUES (4, 'Amarela');

INSERT INTO via_administracao (id, nome) VALUES (1, 'Oral');
INSERT INTO via_administracao (id, nome) VALUES (2, 'Nasal');
INSERT INTO via_administracao (id, nome) VALUES (3, 'Subcutânea');
INSERT INTO via_administracao (id, nome) VALUES (4, 'Tópica Ocular');

INSERT INTO usuario (id, nome, login, senha, cidadenascimento_id, sexo_id, profissao_id) VALUES (1, 'Admin', 'admin', 'admin', 1, 1, 3);

INSERT INTO endereco (id, bairro, cep, logradouro, semnumero, cidade_id) VALUES (1, 'Moinho Velho', '06707-000', 'Rodovia Raposo Tavares, km 26.9', true, 15);
INSERT INTO fabricante (id, cnpj, nomefantasia, razaosocial, endereco_id) VALUES (1, '60.318.797/0001-00', 'AstraZeneca AB', 'AstraZeneca do Brasil Ltda.', 1);
INSERT INTO marca (id, nome, fabricante_id) VALUES (1, 'Ticagrelor', 1);

INSERT INTO endereco (id, bairro, cep, logradouro, semnumero, numero, cidade_id) VALUES (2, 'Butantã', '05359-001', 'Avenida N.S. da Assunção', false, '736', 16);
INSERT INTO fabricante (id, cnpj, nomefantasia, razaosocial, endereco_id) VALUES (2, '56.994.502/0001-30', 'NOVARTIS BIOCIÊNCIAS S.A.', 'NOVARTIS BIOCIÊNCIAS S.A.', 2);
INSERT INTO marca (id, nome, fabricante_id) VALUES (2, 'Alcon', 2);