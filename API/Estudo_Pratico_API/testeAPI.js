// Importa o prompt-sync para receber input do usuário
const prompt = require("prompt-sync")();

// Menu principal
async function main() {
  try {

    // Recebe o CEP do usuário
    let XXXXXX = prompt("Digite o XXXXXX: ");
    // Retira os espaços do input
    XXXXXX = XXXXXX.trim();

    const url = `http://apiXXXXXX`;

    const resposta = await fetch(url);
    const dados = await resposta.json();

    if (dados.erro) {
      console.log("Mensagem de erro");
      return;
    }

    // Exibe os dados do CEP
    console.log("Dados do XXXXXX: ");
    console.log("XXXXX: ", dados.dadoConsumidoJSON);
  } catch (erro) {
    console.log("Erro ao acessar a API");
    console.log(erro.message);
  }
}

main();