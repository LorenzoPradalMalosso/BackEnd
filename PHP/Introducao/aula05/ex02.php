<?php
    $funcionarios = 120;

    if ($funcionarios < 50) {
        echo "Pequeno porte";
    } elseif ($funcionarios < 100){
        echo "Médio porte";
    } else {
        echo "Grande porte";
    }
?>