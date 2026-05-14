<link rel="stylesheet" href="style.css">

<?php
    $salarioMinimo = 1621.00;
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $salario = $_POST["salario"] ?? 0;
    
        $qntdSalario = $salario / $salarioMinimo;
        $qntdSalario = (int) $qntdSalario;

        $restante = (int) $salario % $salarioMinimo;

        echo "<section><h2>Análise de Salário</h2>";
        echo "<p>Quem recebe um salário de <strong>R$ $salario</strong></p>";
        echo "<p>Ganha <strong>$qntdSalario</strong></p>";
        echo "<p>E sobra: <strong>R$ $restante<strong></p></section>";
    }
?>