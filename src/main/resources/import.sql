-- COZINHA
INSERT INTO cozinha (nome) values ('Italiana');
INSERT INTO cozinha (nome) values ('Portuguesa');

-- RESTAURANTE
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) values ('Funiculi Funiculá', 10.0, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) values ('Ristorante La Spezia', 8.50, 2);

-- ESTADO
insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

-- CIDADE
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

-- FORMA DE PAGAMENTO 
insert into forma_de_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_de_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_de_pagamento (id, descricao) values (3, 'Dinheiro');

-- PERMISSAO
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');