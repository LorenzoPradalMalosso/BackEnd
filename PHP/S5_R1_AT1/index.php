<?php 
    // Uso de if / else
    $vivo = TRUE;

    if ($vivo) {
        echo "Você está vivo";
    } else {
        echo "Você está morto";
    };

    echo "<hr>";

    // Uso de elseif
    $idade = 20;

    if ($idade < 18) {
        echo "Menor de idade";
    } elseif ($idade < 60) {
        echo "Maior de idade";
    } else {
        echo "Idoso";
    };

    echo "<hr>";

    // Uso de switch
    $pais = "Espanha";

    switch ($pais) {
        case "Brasil":
            echo "Português";
            break;

        case "Estados Unidos":
            echo "Inglês";
            break;

        case "Espanha":
            echo "Espanhol";
            break;

        default:
            echo "País não identificado";
            break;
    };
?>