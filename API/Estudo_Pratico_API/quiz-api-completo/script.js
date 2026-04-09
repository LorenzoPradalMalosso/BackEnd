let perguntas = [];
let index = 0;
let acertos = 0;

const numeroPerguntasInput = document.getElementById("numeroPerguntas");
const btnIniciar = document.getElementById("btnIniciar");
const mensagem = document.getElementById("mensagem");

const quiz = document.getElementById("quiz");
const contador = document.getElementById("contador");
const pontuacao = document.getElementById("pontuacao");
const perguntaEl = document.getElementById("pergunta");
const alternativasEl = document.getElementById("alternativas");
const feedback = document.getElementById("feedback");
const btnProxima = document.getElementById("btnProxima");
const resultadoFinal = document.getElementById("resultadoFinal");

btnIniciar.addEventListener("click", iniciarQuiz);
btnProxima.addEventListener("click", proximaPergunta);

async function iniciarQuiz() {
  const numero = numeroPerguntasInput.value.trim();

  mensagem.textContent = "";
  resultadoFinal.classList.add("oculto");
  resultadoFinal.innerHTML = "";

  if (!numero || isNaN(numero) || Number(numero) <= 0) {
    mensagem.textContent = "Digite um número válido de perguntas.";
    return;
  }

  perguntas = await pegarPerguntas(numero);

  if (!perguntas || perguntas.length === 0) {
    mensagem.textContent = "Não foi possível carregar as perguntas.";
    return;
  }

  index = 0;
  acertos = 0;
  quiz.classList.remove("oculto");

  mostrarPergunta();
}

async function pegarPerguntas(numero) {
  try {
    const url = `https://opentdb.com/api.php?amount=${numero}`;
    const resposta = await fetch(url);
    const dados = await resposta.json();

    if (!dados.results || dados.results.length === 0) {
      return [];
    }

    return dados.results;
  } catch (erro) {
    console.error("Erro ao acessar a API:", erro.message);
    return [];
  }
}

function normalizarTexto(texto) {
  return String(texto).trim().replace(/\s+/g, " ").toLowerCase();
}

function embaralharArray(array) {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
  return array;
}

function mostrarPergunta() {
  const perguntaAtual = perguntas[index];

  contador.textContent = `Pergunta ${index + 1} de ${perguntas.length}`;
  pontuacao.textContent = `Acertos: ${acertos}`;
  feedback.textContent = "";
  btnProxima.classList.add("oculto");
  alternativasEl.innerHTML = "";

  perguntaEl.innerHTML = perguntaAtual.question;

  let alternativas = [
    perguntaAtual.correct_answer,
    ...perguntaAtual.incorrect_answers,
  ];

  alternativas = embaralharArray(alternativas);

  alternativas.forEach((alternativa) => {
    const botao = document.createElement("button");
    botao.className = "alternativa";
    botao.innerHTML = alternativa;

    botao.addEventListener("click", () => {
      verificarResposta(botao.textContent, perguntaAtual.correct_answer);
    });

    alternativasEl.appendChild(botao);
  });
}

function decodificarHtml(texto) {
  const txt = document.createElement("textarea");
  txt.innerHTML = texto;
  return txt.value;
}

function verificarResposta(respostaEscolhida, respostaCorreta) {
  const botoes = document.querySelectorAll(".alternativa");
  const respostaCorretaDecodificada = decodificarHtml(respostaCorreta);

  const acertou =
    normalizarTexto(respostaEscolhida) ===
    normalizarTexto(respostaCorretaDecodificada);

  botoes.forEach((botao) => {
    botao.disabled = true;

    if (
      normalizarTexto(botao.textContent) ===
      normalizarTexto(respostaCorretaDecodificada)
    ) {
      botao.classList.add("correta");
    }
  });

  if (acertou) {
    acertos++;
    feedback.textContent = "Parabéns, você acertou!";
  } else {
    feedback.textContent = `Infelizmente você errou. Resposta correta: ${respostaCorretaDecodificada}`;

    botoes.forEach((botao) => {
      if (
        normalizarTexto(botao.textContent) ===
        normalizarTexto(respostaEscolhida)
      ) {
        botao.classList.add("errada");
      }
    });
  }

  pontuacao.textContent = `Acertos: ${acertos}`;
  btnProxima.classList.remove("oculto");
}

function proximaPergunta() {
  index++;

  if (index < perguntas.length) {
    mostrarPergunta();
  } else {
    finalizarQuiz();
  }
}

function finalizarQuiz() {
  quiz.classList.add("oculto");
  resultadoFinal.classList.remove("oculto");
  resultadoFinal.innerHTML = `
    <h2>Quiz finalizado!</h2>
    <p>Você acertou ${acertos} de ${perguntas.length} perguntas.</p>
  `;
}
