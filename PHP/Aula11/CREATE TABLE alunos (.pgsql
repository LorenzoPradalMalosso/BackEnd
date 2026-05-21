CREATE TABLE alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    sobrenome VARCHAR(255),
    data_nascimento VARCHAR(255),
    turma TEXT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO alunos(nome, sobrenome, data_nascimento, turma, ativo) VALUES ('Lorenzo', 'Pradal Malosso', '03-03-2009', 'Turma A', TRUE);

SELECT * FROM alunos;

INSERT INTO alunos(nome, sobrenome, data_nascimento, turma, ativo) VALUES ('Maria', 'Silva', '15-07-2010', 'Turma B', TRUE);
INSERT INTO alunos(nome, sobrenome, data_nascimento, turma, ativo) VALUES ('Matheus', 'Souza', '22-11-2009', 'Turma A', TRUE);
INSERT INTO alunos(nome, sobrenome, data_nascimento, turma, ativo) VALUES ('Ana', 'Costa', '05-05-2010', 'Turma B', TRUE);
INSERT INTO alunos(nome, sobrenome, data_nascimento, turma, ativo) VALUES ('Pedro', 'Almeida', '12-09-2009', 'Turma A', TRUE);