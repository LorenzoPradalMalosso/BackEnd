// Importa o Express (Framework para APIs)
const express = require('express');
// Importar o FS para leitura e escrita de arquivos
const fs = require('fs');
// Cria a aplicação express
const app = express();

//Definir uma porta para o server
const PORT = 3000;
// Permite o servidor entender JSON enviado no Body
// Sem isso, req.body = undefined
app.use(express.json());
// Caminho Arquivo
const ARQUIVO = "./contatos.json";

// Início das funções:
function lerDados() {
    const dados = fs.readFileSync(ARQUIVO, "utf-8");
    // Converter o JSON para objeto JS
    return JSON.parse(dados);
}

function salvarDados(dados) {
    fs.writeFileSync(ARQUIVO, JSON.stringify(dados, null, 
        2)); // null, 2 = Identação de 2 espaços
}

// Rota GET - Lista os contatos
app.get("/contatos/:grupo", (req, res) => {
    const grupo = req.params.grupo;
    const dados = lerDados();

    // Verificar se o grupo existe
    if (!dados[grupo]) {
        return res.status(404).json({ erro: "Grupo não encontrado!"});
    }

    res.json(dados[grupo]);
});

// Rota POST - Adiciona um contato
app.post("/contatos/:grupo", (req, res) => {
    const grupo = req.params.grupo;
    const { nome, telefone } = req.body;

    // Verificar se o grupo existe
    if (!dados[grupo]) {
        return res.status(404).json({ erro: "Grupo não encontrado!" });
    }

    // Criando obrigatoriedade para nome e telefone
    if (!nome || !telefone) {
        return res.status(400).json({
            erro: "Nome e telefone são obrigatórios"
        });
    }
    // Adiciona o contato

    dads[grupo].push({ nome, telefone });
    // Salva no JSON
    salvarDados(dados);

    res.status(201).json({
        mensagem: "Contato adicionado com sucesso!",
        contato: { nome, telefone }
    });
});

// Rota PUT - Atualiza um contato
app.put("/contatos/:grupo/:index", (req, res) => {
  const grupo = req.params.grupo;
  const index = parseInt(req.params.index);
  const { nome, telefone } = req.body;

  const dados = lerDados();

  // Verificar se o grupo existe
  if (!dados[grupo]) {
    return res.status(404).json({ erro: "Grupo não encontrado!" });
  }

  if (index < 0 || index >= dados[grupo].length) {
    return res.status(404).json({ erro: "Contato não encontrado"});
  }

  // Atualizada o contato
  dados[grupo][index] = { nome, telefone };

  salvarDados(dados);

  res.json({
    mensagem: "Contato atualizado com sucesso!",
    contato: dados[grupo][index]
  });
});

// Rota DELETE - Exclóio um conato
app.delete("/contatos/:grupo/:index", (req, res) => {
    const grupo = req.params.grupo;
    const index = parseInt(req.params.index);

    const dados = lerDados();
    if (!dados[grupo]) {
      return res.status(404).json({ erro: "Grupo não encontrado!" });
    }

    if (index < 0 || index >= dados[grupo].length) {
      return res.status(404).json({ erro: "Contato não encontrado" });
    }

    const removido = dados[grupo].splice(index, 1);
    salvarDados(dados);

    res.json({
        mensagem: "Contato excluído com sucesso!",
        contato: removido[0]
    });
});

// Iniciar o servidor
app.listen(PORT, () => {
    console.log(`API rodando em: http://localhost:${PORT}`);
});
