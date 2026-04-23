<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Atividade 3 - PHP</title>
    <style>
        body {
            background-color: aliceblue;
        }
        h1 {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
    <h1>Empresa XPTO</h1>
    <?php 
        $nome = "Coca-Cola";
        $ano = 1886;
        $sede = "Atlanta, Geórgia, Estados Unidos";
        $preco = 9.99;
        $vendendo = true;

        echo "<br><strong>Nome da Empresa:</strong> $nome<hr>
              <strong>Ano de fundação:</strong> $ano<hr>
              <strong>Local da sede:</strong> $sede<hr>
              <strong>Preço de uma unidade do refrigerante:</strong> $preco<hr>
              <strong>Empresa em atividade:</strong> ", ($vendendo ? "Sim": "Não");
    ?>
</body>
</html>