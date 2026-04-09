// Importa o prompt-sync para receber input do usuário
const prompt = require("prompt-sync")();
let index = 0;

// Menu principal
async function main() {
  const perguntas = await pegarPerguntas();

  if (perguntas && perguntas.length > 0) {
    fazerPerguntas(perguntas);
  }

}

main();

async function pegarPerguntas() {
  try {
    let numero = prompt("Digite o número de perguntas a serem feitas: ")
    numero = numero.trim();

    if (!numero || isNaN(numero)) {
      console.log("Dado inserido inválido (Insira apenas um número)");
      return [];
    }

    const url = `https://opentdb.com/api.php?amount=${numero}`;
    const resposta = await fetch(url);
    const dados = await resposta.json();

    return dados.results;

  } catch (erro) {
    console.log("Erro ao acessar a API");
    console.log(erro.message);
    return [];
  }
}

function limparTexto(texto) {
  if (typeof texto !== "string") return texto;

  const entidades = {
    "&quot;": '"', "&apos;": "'", "&amp;": "&", "&lt;": "<", "&gt;": ">", "&nbsp;": " ", "&iexcl;": "¡", "&cent;": "¢", "&pound;": "£", "&curren;": "¤", "&yen;": "¥", "&brvbar;": "¦", "&sect;": "§", "&uml;": "¨",
    "&copy;": "©", "&ordf;": "ª", "&laquo;": "«", "&not;": "¬", "&shy;": "", "&reg;": "®", "&macr;": "¯", "&deg;": "°", "&plusmn;": "±", "&sup2;": "²", "&sup3;": "³", "&acute;": "´", "&micro;": "µ", "&para;": "¶",
    "&middot;": "·", "&cedil;": "¸", "&sup1;": "¹", "&ordm;": "º", "&raquo;": "»", "&frac14;": "¼", "&frac12;": "½", "&frac34;": "¾", "&iquest;": "¿", "&times;": "×", "&divide;": "÷", "&Agrave;": "À", "&Aacute;": "Á",
    "&Acirc;": "Â", "&Atilde;": "Ã", "&Auml;": "Ä", "&Aring;": "Å", "&AElig;": "Æ", "&Ccedil;": "Ç", "&Egrave;": "È", "&Eacute;": "É", "&Ecirc;": "Ê", "&Euml;": "Ë", "&Igrave;": "Ì", "&Iacute;": "Í", "&Icirc;": "Î",
    "&Iuml;": "Ï", "&ETH;": "Ð", "&Ntilde;": "Ñ", "&Ograve;": "Ò", "&Oacute;": "Ó", "&Ocirc;": "Ô", "&Otilde;": "Õ", "&Ouml;": "Ö", "&Oslash;": "Ø", "&Ugrave;": "Ù", "&Uacute;": "Ú", "&Ucirc;": "Û", "&Uuml;": "Ü", 
    "&Yacute;": "Ý", "&THORN;": "Þ", "&szlig;": "ß", "&agrave;": "à", "&aacute;": "á", "&acirc;": "â", "&atilde;": "ã", "&auml;": "ä", "&aring;": "å", "&aelig;": "æ", "&ccedil;": "ç", "&egrave;": "è", "&eacute;": "é",
    "&ecirc;": "ê", "&euml;": "ë", "&igrave;": "ì", "&iacute;": "í", "&icirc;": "î", "&iuml;": "ï", "&eth;": "ð", "&ntilde;": "ñ", "&ograve;": "ò", "&oacute;": "ó", "&ocirc;": "ô", "&otilde;": "õ", "&ouml;": "ö",
    "&oslash;": "ø", "&ugrave;": "ù", "&uacute;": "ú", "&ucirc;": "û", "&uuml;": "ü", "&yacute;": "ý", "&thorn;": "þ", "&yuml;": "ÿ", "&OElig;": "Œ", "&oelig;": "œ", "&Scaron;": "Š", "&scaron;": "š", "&Yuml;": "Ÿ",
    "&fnof;": "ƒ", "&circ;": "ˆ", "&tilde;": "˜",  "&Alpha;": "Α", "&Beta;": "Β", "&Gamma;": "Γ", "&Delta;": "Δ", "&Epsilon;": "Ε", "&Zeta;": "Ζ", "&Eta;": "Η", "&Theta;": "Θ", "&Iota;": "Ι", "&Kappa;": "Κ", "&Lambda;": "Λ",
    "&Mu;": "Μ", "&Nu;": "Ν", "&Xi;": "Ξ", "&Omicron;": "Ο", "&Pi;": "Π", "&Rho;": "Ρ", "&Sigma;": "Σ", "&Tau;": "Τ", "&Upsilon;": "Υ", "&Phi;": "Φ", "&Chi;": "Χ", "&Psi;": "Ψ", "&Omega;": "Ω", "&alpha;": "α", "&beta;": "β", 
    "&gamma;": "γ", "&delta;": "δ", "&epsilon;": "ε", "&zeta;": "ζ", "&eta;": "η", "&theta;": "θ", "&iota;": "ι", "&kappa;": "κ", "&lambda;": "λ", "&mu;": "μ", "&nu;": "ν", "&xi;": "ξ", "&omicron;": "ο", "&pi;": "π",
    "&rho;": "ρ", "&sigmaf;": "ς", "&sigma;": "σ", "&tau;": "τ", "&upsilon;": "υ", "&phi;": "φ", "&chi;": "χ", "&psi;": "ψ", "&omega;": "ω", "&ndash;": "–", "&mdash;": "—", "&lsquo;": "‘", "&rsquo;": "’", "&sbquo;": "‚",
    "&ldquo;": "“", "&rdquo;": "”", "&bdquo;": "„", "&dagger;": "†", "&Dagger;": "‡", "&bull;": "•", "&hellip;": "…", "&permil;": "‰", "&prime;": "′", "&Prime;": "″", "&lsaquo;": "‹", "&rsaquo;": "›", "&oline;": "‾",
    "&euro;": "€", "&trade;": "™", "&larr;": "←", "&uarr;": "↑", "&rarr;": "→", "&darr;": "↓", "&harr;": "↔", "&crarr;": "↵", "&lceil;": "⌈", "&rceil;": "⌉", "&lfloor;": "⌊", "&rfloor;": "⌋", "&loz;": "◊", "&spades;": "♠",
    "&clubs;": "♣", "&hearts;": "♥", "&diams;": "♦", "&frasl;": "⁄",
  };

  return (
    texto
      // entidades nomeadas
      .replace(/&[a-zA-Z]+;?/g, (match) => entidades[match] ?? match)
      // entidades numéricas decimais: &#039; ou &#39
      .replace(/&#(\d+);?/g, (_, codigo) => {
        const n = Number(codigo);
        return Number.isNaN(n) ? _ : String.fromCodePoint(n);
      })
      // entidades numéricas hexadecimais: &#x27; ou &#X27
      .replace(/&#x([0-9a-fA-F]+);?/g, (_, codigo) => {
        const n = parseInt(codigo, 16);
        return Number.isNaN(n) ? _ : String.fromCodePoint(n);
      })
      // remove espaços repetidos
      .replace(/\s+/g, " ")
      .trim()
  );
}

function normalizarTexto(texto) {
  return limparTexto(texto).replace(/\s+/g, " ").toLowerCase();
}

function fazerPerguntas(perguntas) {
  let acertos = 1;

  while(index < perguntas.length) {
    const pergunta = perguntas[index];
    let perguntaLimpa = limparTexto(pergunta.question);

    console.log(`\nPergunta ${index + 1}: ${perguntaLimpa}`);

    let alternativas = [pergunta.correct_answer, pergunta.incorrect_answers[0], pergunta.incorrect_answers[1], pergunta.incorrect_answers[2]];

    alternativas = alternativas.map((alternativa) => limparTexto(alternativa));

    alternativas.sort(() => Math.random() - 0.5);
    
    for (let i = 0; i < alternativas.length; i++) {
      console.log(`${i + 1}) ${alternativas[i]}`);
    }

    const respostaUser = prompt("Digite o número da resposta: ").trim();

    const respostaEscolhida = alternativas[Number(respostaUser) -1];
    const respostaCorreta = pergunta.correct_answer;

    console.log(`Sua resposta: ${limparTexto(respostaEscolhida)}`);

    if(normalizarTexto(respostaEscolhida) === normalizarTexto(respostaCorreta)) {
      console.log("Parabéns, você acertou!");
      acertos++;
    } else {
      console.log(`Infelizmente você errou. Resposta correta: ${limparTexto(respostaCorreta)}`);
    }

    index++;
  }

  console.log(`\nQuiz finalizado! Você acertou ${acertos} de ${perguntas.length}.`);

}