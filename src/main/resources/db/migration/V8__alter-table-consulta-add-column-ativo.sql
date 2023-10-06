ALTER TABLE consultas ADD ativo tinyint;
update consultas set ativo = 1;