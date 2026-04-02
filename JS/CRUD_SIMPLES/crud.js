// Importa módulos necessários
const fs = require("fs"); // Para leitura e gravação
const prompt = require("prompt-sync")();

// Criando o menu da aplicação
function menu(){
    console.log("\nMenu de contatos:");
    console.log("1. Adicionar Contato");
    console.log("2. Listar Contatos");
    console.log("3. Atualizar Contato");
    console.log("4. Excluir Contato");
    console.log("5. Sair");
}

function main(){
    do {
        menu();
        opcao = prompt("Escolha uma opção: ");
        switch (opcao) {
            case "1":
                adicionar(); // Chama a função para adicionar
                break;
            case "2":
                listar(); // Chama a função listar
                break;
            case "3":
                atualizar(); // Chama a função atualizar
                break;
            case "4":
                excluir(); // Chama a função exclui
                break;
            case "5":
                console.log("Saindo do program...");
                break;
            default:
                console.log("Opção inválida. Tente novamente.");
        }
    } while (opcao !== "5");
}

//Função para ler os dados do arquivo JSON
function lerDados() {
    const dados = fs.readFileSync("contatos.json","utf-8");
    return JSON.parse(dados || "[]"); // Transforma o JSON em um array, caso o JSON esteja vazio retorna um array vazio
}

function adicionar() {
    const nome = prompt("Digite o nome do contado: ");
    const telefone = prompt("Digite o telefone: ");

    const contatos = lerDados();
    contatos.push({nome, telefone});
    salvarDados(contatos);
    console.log("Contato adicionado com sucesso!");
}

function listar() {
    const contatos = lerDados();
    console.log("Contatos:");
    contatos.forEach((contato, index) => {
        console.log(`${index + 1}. ${contato.nome} - ${contato.telefone}`);
    });
}

// Função para atualizar um contato
function atualizar() {
    const indexAtualizar = parseInt(prompt("Digite o número do contato a ser atualizado: ")) -1;

    const contatos = lerDados();
    if (indexAtualizar >= 0 && indexAtualizar < contatos.length) {
        const novoNome = prompt("Digite o novo nome do contato: ");
        const novoTelefone = prompt("Digite o novo telefone do contato: ");

        contatos[indexAtualizar] = {nome: novoNome, telefone: novoTelefone};
        salvarDados(contatos);
        console.log("Contato atualizado com sucesso!");
    } else {
        console.log("Índice inválido");
    }
}

// Função para excluir um contato (DELETE)
function excluir() {
    const indexExcluir = parseInt(prompt("Digite o index do contato a ser excluído: ")) -1;

    const contatos = lerDados();
    if (indexExcluir >= 0 && indexExcluir < contatos.length) {
        contatos.splice(indexExcluir, 1);
        salvarDados(contatos);
        console.log("Contato excluído com sucesso!");
    } else {
        console.log("Índice inválido");
    }
}

// Função para salvar dados no arquivo JSON
function salvarDados(contatos) {
    fs.writeFileSync("contatos.json", JSON.stringify(contatos, null, 2));
}

// Inicia o programa
main();