<?php 
    $nome = '';
    $email = '';
    $telefone = '';
    $cpf = '';

    if (isset($_POST['botaoEnviar'])) {
        $nome = htmlspecialchars($_POST["nome"]);
        $email = htmlspecialchars($_POST["email"]);
        $telefone = htmlspecialchars($_POST["telefone"]);
        $cpf = htmlspecialchars($_POST["cpf"]);
    }
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dados Enviados</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <main class="resultado">
        <h2>Dados Enviados</h2>
        <p><strong>Nome:</strong> <?php echo $nome; ?></p>
        <p><strong>E-mail:</strong> <?php echo $email; ?></p>
        <p><strong>Telefone:</strong> <?php echo $telefone; ?></p>
        <p><strong>CPF:</strong> <?php echo $cpf; ?></p>
        <p><a href="index.php">Voltar ao formulário</a></p>
    </main>
</body>
</html>