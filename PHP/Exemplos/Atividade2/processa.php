<link rel="stylesheet" href="style.css">

<?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $preco = $_POST["preco"] ?? 0;
        $percentual = $_POST["percentual"] ?? 0;

        $resultado = $preco * (1 + ($percentual / 100));

        echo "<section><h2>Resultado do Reajuste</h2>";
        echo "<p>O Produto que custava <strong>R$ $preco</strong> com um reajuste de <strong>$percentual%</strong> passará a custar <strong>R$ $resultado.</strong></section>";
    }
?>