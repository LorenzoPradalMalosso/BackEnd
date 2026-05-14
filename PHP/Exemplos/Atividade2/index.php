<!DOCTYPE html>
<html lang="pt-br>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reajustador de Preços</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <h1>Reajustador de Preços</h1>
    </header>

    <main>
        <form action="processa.php" method="post">
            <label for="preco">Preço do Produto:</label>
            <input type="number" name="preco" id="preco" step="0.01" min="0" placeholder="Ex: 150.00" required>

            <label for="percentual" id="valorRange">Percentual de Reajuste </label>
            <input type="range" name="percentual" id="percentual" step="1" min="0" max="200" value="15" oninput="valorRange.innerText = this.value + '%'" required>

            <input type="submit" value="Calcular Reajuste">
        </form>
    </main>

    <footer>
        <p>Exemplo em PHP com formulário e processamento separado</p>
    </footer>

</body>
</html>