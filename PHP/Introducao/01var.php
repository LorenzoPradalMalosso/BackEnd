<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Introdução à variável</title>
</head>
<body>
    <h1>Informações do cliente:</h1>
    <?php 
        $nome = "Lorenzo";
        $idade = 17;
        $telefone = 19123456789;
        $estadoCivil = true;

        echo "<strong>Seu nome é:</strong> $nome<br>
              <strong>Idade:</strong> $idade<br>
              <strong>Telefone:</strong> $telefone<br>";
        echo "<strong>Casado:</strong> ", ($estadoCivil ? 'Sim': 'Não');
    ?>
</body>
</html>