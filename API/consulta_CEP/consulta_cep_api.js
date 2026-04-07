// Importa o prompt-sync para receber input do usuário
const prompt = require("prompt-sync")();

// Menu principal
function consultaCEP() {
    /*
    1. Solicita o CEP
    2. Monta URL
    3. Faz a requisição HTTPS (GET)
    4. Retorna a resposta
    */

    // Recebe o CEP do usuário
    let cep = prompt("Digite o CEP (somente números): ");
    // Retira os espaços do input
    cep = cep.trim();

    const url = `http://viacep.com.br/ws/${cep}/json`;
    
    fetch(url)
        .then((resposta) => {
            // Converte a resposta da API para JSON
            return resposta.json();
        })
        .then((dados) => {
            // Verifica se o CEP é inválido
            if (dados.erro) {
                console.log("CEP não encontrado");
                return;
            }

            // Exibe os dados do CEP
            console.log("Dados do CEP: ");
            console.log("CEP: ", dados.cep);
            console.log("Logradouro: ", dados.logradouro);
            console.log("Bairro: ", dados.bairro);
            console.log("Cidade: ", dados.localidade);
            console.log("UF: ", dados.uf);
        })
        .catch((erro) => {
            console.log("Erro ao acessar a API");
            console.log(erro.message);
        });
}

consultaCEP();