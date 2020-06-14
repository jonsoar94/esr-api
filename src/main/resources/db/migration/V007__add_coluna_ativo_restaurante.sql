ALTER TABLE restaurante add column ativo tinyint(1) not null;
update restaurante set ativo = TRUE