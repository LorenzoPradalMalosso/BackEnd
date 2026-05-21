# O que cada comando faz?

## CREATE DATABASE `nome`
- Cria o banco de dados onde será armazenado nossos dados e tabelas

## CREATE TABLE `nome` (campo TIPO PARÂMETRO)
- Cria a tabela com os campos desejados, especificando os tipos de cada dado e seus parâmetros

## SELECT * FROM `nome`
- Seleciona todos os dados de todos os campos da tabela especificada

## INSERT INTO `nome` (campos) VALUES (`informações`)
- Insere novos dados especificados nos campos designados dentro da tabela citada

## UPDATE `nome` SET `campo` = `novo_dado` WHERE `campoReferencia` = x
- Muda o valor de um campo de uma tabela, para um novo valor de um registro que atender ao requisito do WHERE (Campo de Referência)

## DELETE FROM `nome` WHERE `campoReferencia` = x
- Deleta o registro de uma certa tabela que atender ao requisito do WHERE (Campo de Referência)

## Onde os dados ficam armazenados?
- Os dados ficam armazenados no banco `filmes`, dentro da tabela `filmes`, em registros organizados em linhas e colunas.

## Difereça entre INSERT e SELECT
- `INSERT` adiciona novos dados à tabela, enquanto `SELECT` consulta e exibe dados já armazenados.

## Estrutura da tabela
- A tabela possui as colunas `id`, `nome`, `genero`, `ano`, `diretor` e `duracao`, cada uma com tipos e regras específicas.