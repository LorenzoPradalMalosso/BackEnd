<?php 
    // Uso do for (com array)
    $listaFuncionarios = ["Lorenzo", "Murilo", "Kaio", "Rian"];
    for ($i=1; $i <= count($listaFuncionarios); $i++) { 
        echo "Id: <strong>$i</strong>. Funcionário: <strong>".$listaFuncionarios[$i - 1]."</strong><br>";
    }

    echo "<hr>";

    // Uso do while
    $numero = 1;
    echo "<strong>Números de 1 a 100 divisíveis por 3 e 5:</strong><br><ul>";
    while ($numero <= 100) {
        // Verifica se o número é divisível por 3 e 5
        if ($numero % 3 == 0 && $numero % 5 == 0) {
            echo "<li>$numero</li>";
        }
        $numero++;
    }
    echo "</ul>";
?>