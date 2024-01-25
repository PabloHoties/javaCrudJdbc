create table pessoa(
	id			uuid			primary key,
	nome		varchar(100)	not null,
	email		varchar(50)		not null,
	cpf			varchar(14)		not null);