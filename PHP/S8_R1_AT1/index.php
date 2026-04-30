<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teste com Funções</title>
</head>
<body>
    <h1>Calculadora de 4 números:</h1>
    <form method="POST">
        <label for="num1">Número 1</label>
        <input type="number" id="num1" name="num1"> <hr>

        <label for="num2">Número 2</label>
        <input type="number" id="num2" name="num2"> <hr>

        <label for="num3">Número 3</label>
        <input type="number" id="num3" name="num3"> <hr>

        <label for="num4">Número 4</label>
        <input type="number" id="num4" name="num4"> <hr>

        <button type="submit" name="botaoCalcular">Calcular</button>
    </form>
</body>
</html>

<?php
    function somar(int $num1, int $num2, int $num3, int $num4) {
        return $num1 + $num2 + $num3 + $num4;
    }

    function duplicar(int $resultado) {
        return $resultado * 2;
    }

    function quadrado(int $resultado) {
        return $resultado * $resultado;
    }

    if (isset($_POST['botaoCalcular'])) {
        $num1 = (int) ($_POST["num1"] ?? 0);
        $num2 = (int) ($_POST["num2"] ?? 0);
        $num3 = (int) ($_POST["num3"] ?? 0);
        $num4 = (int) ($_POST["num4"] ?? 0);

        $resultado = somar($num1, $num2, $num3, $num4);
        $duplo = duplicar($resultado);
        $elevado = quadrado($resultado);

        echo "<h1>Resultado da soma:".$resultado."</h1>";
        echo "<hr><h1>Resultado duplicado:".$duplo."</h1>";
        echo "<hr><h1>Número elevado ao quadrado:".$elevado."</h1>";
    }
?>