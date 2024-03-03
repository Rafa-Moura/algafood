insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Brasileira');

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values('Dona juju doces Indianos', 5.00, 2, UTC_TIMESTAMP, UTC_TIMESTAMP);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values('A delante refeições Tailandesa', 10.00, 1, UTC_TIMESTAMP, UTC_TIMESTAMP);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values('Nosso Sabor', 10.00, 3, UTC_TIMESTAMP, UTC_TIMESTAMP);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Super lanches', 0.00, 1, UTC_TIMESTAMP, UTC_TIMESTAMP);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Valdete Doces', 0.00, 1, UTC_TIMESTAMP, UTC_TIMESTAMP);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Kleber Marmitas', 0.00, 1, UTC_TIMESTAMP, UTC_TIMESTAMP);

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (4, 'PIX');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3)
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 1), (2, 3)
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 1), (3, 2), (3, 1)
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (4, 3)
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (5, 3)
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (6, 1), (6, 2), (6, 3), (6, 4)

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bolo de cenoura', 'Bolo de cenoura com cobertura de chocolate', 15.18, true, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Pastel Indiano', 'Pastel indiano feito com muita higiene', 5.00, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Refeição completa PF', 'Almoço prato feito, serve 1 pessoa', 13.00, true, 3);