-- COZINHA
INSERT INTO cozinha (nome) values ('Italiana');
INSERT INTO cozinha (nome) values ('Portuguesa');
INSERT INTO cozinha (nome) values ('Brasileira');
INSERT INTO cozinha (nome) values ('Argentina');

-- ESTADO
insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');
insert into estado (id, nome) values (4, 'Santa Catarina');

-- CIDADE
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);
insert into cidade (id, nome, estado_id) values (6, 'Pomerode', 4);

-- RESTAURANTE
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Funiculi Funiculá', 10.0, 1, '89107-000', 'Avenida 21 de Janeiro', '3180', 'Casa', 'Centro', 6, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Ristorante La Spezia', 8.50, 2, '89010-205', 'Rua Curt Hering', '25', 'Casa', 'Capão da Silva', 3,utc_timestamp,utc_timestamp);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Madero Steak House', 5.50, 3, '89010-205', 'R. Chicungunha', '90', 'Flat', 'Centro', 4, utc_timestamp, utc_timestamp);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Las Chicas Calientes', 10.0, 1, '89102-000', 'La Avenidade de Santa', '22', 'Galpão', 'Avenida la Cihica', 4, utc_timestamp, utc_timestamp);


-- PRODUTO
insert into produto (nome, descricao, valor, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, valor, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, valor, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, valor, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, valor, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, valor, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, valor, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (nome, descricao, valor, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 2);


-- FORMA DE PAGAMENTO 
insert into forma_de_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_de_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_de_pagamento (id, descricao) values (3, 'Dinheiro');

-- PERMISSAO
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

-- RESTAURANTE FORMA PAGAMENTO
insert into restaurante_forma_pagamento (restaurante_id, forma_de_pagamento_id) values (1, 1), (1, 2), (1,3), (2,1), (2,2), (2,3), (3,1), (3,2), (3,3), (4,1), (4,2), (4,3);