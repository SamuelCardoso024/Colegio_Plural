create table responsaveis (
	id serial primary key,
	nome varchar(55) not null,
	cpf char(11) unique not null,
	data_nascimento Date
);

create table alunos (
	id serial primary key,
	nome varchar(55) not null,
	cpf char(11) unique not null,
	data_nascimento Date,
	foreign key (id_responsavel) references responsaveis(id) on delete cascade,
	id_responsavel int not null
);

create table atendimentos (
	id serial primary key,
	horario timestamp unique not null,
	tipo varchar(20),
	foreign key (id_aluno) references alunos(id) on delete cascade,
	id_aluno int not null
);

create table usuario (
	id serial primary key,
	username varchar(55) unique not null,
	hash varchar(120) not null,
	role varchar(15) not null
);

INSERT INTO responsaveis (nome, cpf, data_nascimento) VALUES
('Carlos Eduardo Silva', '12345678901', '1980-05-14'),
('Maria Fernandes Costa', '23456789012', '1985-08-22'),
('Roberto Almeida Prado', '34567890123', '1978-11-03'),
('Ana Paula Souza',      '45678901234', '1990-01-30');

INSERT INTO alunos (nome, cpf, data_nascimento, id_responsavel) VALUES
('Lucas Silva Costa',     '56789012345', '2012-03-10', 1),
('Beatriz Silva Costa',   '67890123456', '2015-07-25', 1),
('Gabriel Fernandes',     '78901234567', '2010-12-05', 2),
('Juliana Almeida Prado', '89012345678', '2014-09-18', 3),
('Mateus Souza Santos',   '90123456789', '2016-04-02', 4);

INSERT INTO atendimentos (horario, tipo, id_aluno) VALUES
('2026-10-06 08:30:00', 'Pedagógico',   1),
('2026-10-06 10:00:00', 'Psicológico',  2),
('2026-10-07 09:15:00', 'Pedagógico',   3),
('2026-10-07 11:00:00', 'Orientação',   4),
('2026-10-08 15:30:00', 'Psicológico',  5);

INSERT INTO usuario (username, hash, role) VALUES
('admin',         '$2b$12$eImiTXuWVxfM37uY4JANjOL.88kw667SF.7251N34', 'ADMIN'),
('psicologa_ana', '$2b$12$eImiTXuWVxfM37uY4JANjOL.88kw667SF.7251N34', 'USER'),
('prof_marcos',   '$2b$12$eImiTXuWVxfM37uY4JANjOL.88kw667SF.7251N34', 'USER');