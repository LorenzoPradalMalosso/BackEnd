<!DOCTYPE html>
<html lang="pt-br>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verificador de salário</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <h1>Verificador de salário</h1>
    </header>
    <?php 
        $salarioMinimo = 1621.00;
    ?>
    <main>
        <form action="processa.php" method="post">
            <label for="salario">Salário (R$)</label>
            <input type="number" name="salario" id="salario" step="0.01" min="0" placeholder="Ex: 1500.00" required>

            <p>Considerando o salário mínimo de R$ <strong><?php echo $salarioMinimo?></strong></p>

            <input type="submit" value="Analisar">
        </form>
    </main>

    <footer>
        <p>Exemplo em PHP com formulário e processamento separado</p>
    </footer>

</body>
</html>