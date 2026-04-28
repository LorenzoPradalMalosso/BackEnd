<?php 
    $funcionarios = ["Murilo", "Carlos", "João", "Isabela"];

    for ($i=0; $i < count($funcionarios); $i++) { 
        echo $funcionarios[$i]."<br>";
    }

    echo "<pre>";
    var_dump($funcionarios);
    echo "</pre>";
?>