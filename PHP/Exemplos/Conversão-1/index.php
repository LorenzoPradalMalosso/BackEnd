<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conversor de Moedas</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <h1>Conversor de Reais para Dolares</h1>
    </header>

    <main>
        <h2>Informe os valores</h2>
        <p>Preencha o valor em reais e a cotacao do dolar para calcular a conversao.</p>

        <form action="processa.php" method="post">
            <label for="valor">Valor em reais (R$)</label>
            <input type="number" name="valor" id="valor" step="0.01" min="0" placeholder="Ex: 150.00" required>

            <label for="dolar">Cotacao do dolar (US$)</label>
            <input type="number" name="dolar" id="dolar" step="0.01" min="0.01" placeholder="Ex: 5.20" required>

            <input type="submit" value="Converter">
            <input type="reset" value="Limpar">
        </form>
    </main>

    <footer>
        <p>Exemplo em PHP com formulario e processamento separado.</p>
    </footer>
</body>
</html>