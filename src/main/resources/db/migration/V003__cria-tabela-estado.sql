CREATE TABLE estado (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome varchar(80) NOT NULL,
	primary key (id)
) engine=InnoDB default charset=utf8;

INSERT INTO estado (nome) select DISTINCT nome_estado from cidade c;


ALTER TABLE cidade add column estado_id BIGINT NOT NULL;

UPDATE cidade c set c.estado_id = (select e.id from estado e where e.nome = c.nome_estado);

ALTER TABLE cidade add CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_id) REFERENCES estado (id);

ALTER TABLE cidade DROP COLUMN nome_estado;

ALTER TABLE cidade CHANGE nome_cidade nome varchar(80) not null;