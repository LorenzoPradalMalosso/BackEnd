CREATE DATABASE filmes;

CREATE TABLE filmes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    ano VARCHAR(4) NOT NULL,
    diretor VARCHAR(255) NOT NULL,
    duracao INT NOT NULL
);

SELECT * FROM filmes;

INSERT INTO filmes (nome, genero, ano, diretor, duracao) VALUES
    ('Interestelar', 'Ficção Científica', '2014', 'Christopher Nolan', 169),
    ('Clube da Luta', 'Drama', '1999', 'David Fincher', 139),
    ('Vingadores: Ultimato', 'Ação', '2019', 'Anthony Russo e Joe Russo', 181),
    ('O Poderoso Chefão', 'Crime', '1972', 'Francis Ford Coppola', 175),
    ('Parasita', 'Suspense', '2019', 'Bong Joon-ho', 132),
    ('Toy Story', 'Animação', '1995', 'John Lasseter', 81),
    ('Titanic', 'Romance', '1997', 'James Cameron', 194),
    ('Corra!', 'Terror', '2017', 'Jordan Peele', 104),
    ('Homem-Aranha: Sem Volta para Casa', 'Ação', '2021', 'Jon Watts', 148),
    ('A Viagem de Chihiro', 'Animação', '2001', 'Hayao Miyazaki', 125);

SELECT * FROM filmes;

UPDATE filmes SET nome = 'Get Out' WHERE id = 8;

SELECT * FROM filmes;

DELETE FROM filmes WHERE id = 10;

SELECT * FROM filmes;