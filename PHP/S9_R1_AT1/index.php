<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulário para Empresa</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <main>
        <h1>Formulário para Empresa</h1>
        <form action="processa.php" method="POST">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" placeholder="Digite seu nome..." required>

            <label for="email">E-mail:</label>
            <input type="text" id="email" name="email" placeholder="Digite seu e-mail..." required>

            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" name="telefone" placeholder="Digite seu telefone..." required>

            <label for="cpf">CPF:</label>
            <input type="text" id="cpf" name="cpf" placeholder="Digite seu CPF..." required>

            <button type="submit" name="botaoEnviar">Enviar Dados</button>
        </form>
    </main>
</body>
</html>