const fs = require("fs");
const prompt = require('prompt-sync')();

// Menu Principal
function menu() {
    console.log("Menu de contatos: ");
    console.log("1. Adicionar contato");
    console.log("2. Listar contatos");
    console.log("3. Atualizar contato");
    console.log("4. Excluir contato");
    console.log("5. Sair");
}

// Escolha de grupo
function escolherGrupo() {
    console.log("\nTtipo de contato: ");
    console.log("1. Aluno");
    console.log("2. Professor");

    const opcao = prompt("Escolha: ");

    if (opcao === "1") return "alunos";
    if (opcao === "2") return "professores";

    console.log("Opção inválida");
    return null;
}

// Leitura do JSON
function lerDados() {
    const dados = fs.readFileSync("contatos.json", "utf-8");
    return JSON.parse(dados);
}

// Salva no JSON
function salvarDados(dados) {
    fs.writeFileSync("contatos.json", JSON.stringify(dados, null, 2));
}

function adicionar() {
    const grupo = escolherGrupo();
    if (!grupo) return; // (!grupo) == (grupo == null)

    const nome = prompt("Nome: ")
    const telefone = prompt("Telefone: ")

    const dados = lerDados();
    dados[grupo].push({nome, telefone});

    salvarDados(dados);
    console.log("Contato adicionado com sucesso!");
}

function listar() {
    const grupo = escolherGrupo();
    if (!grupo) return;

    const dados = lerDados();

    console.log(`\nLista de ${grupo.toUpperCase()}`);
    dados[grupo].forEach((contato, index) => {
        console.log(`${index + 1}. ${contato.nome} - ${contato.telefone}`);
    });
}

// Função para atualizar
function atualizar() {
    const grupo = escolherGrupo();
    if (!grupo) return;

    const dados = lerDados();

    const index = parseIn(prompt("Número do contato: ")) -1;

    if (index >= 0 && index < dados[grupo].length) {
        const nome = prompt("Novo nome: ");
        const telefone = prompt("Novo telefone: ");

        dados[grupo][index] = {nome, telefone};
        salvarDados(dados);

        console.log("Contato atualizado com sucesso!");
    } else {
        console.log("Índice inválido");
    }
}

// Função para apaga um contato
function excluir() {
    const grupo = escolherGrupo();
}