<?php
    // 1º Cálculo
    $receita = 12500;
    $custo = 5000;
    $lucro = $receita - $custo;

    echo "A empresa teve uma <strong>receita</strong> de: R$".$receita."<br><br>E um <strong>custo</strong> de: R$".$custo."<br><br>Resultando em um <strong>lucro</strong> de: R$ ".$lucro;


    // 2º Cálculo
    $funcionarios = 5000;
    $jovemAprendiz = 1500;
    $funcionariosMaiores = $funcionarios - $jovemAprendiz;

    echo "<hr><h4>A empresa possui:</h4><strong>".$funcionariosMaiores."</strong> Funcionários maiores de idade<br><br><strong>".$jovemAprendiz."</strong> Jovens Aprendizes";


    // Comparação
    $pedidos = 3000;
    $estoque = 2500;
    $disponivel = $estoque > $pedidos;
    
    if ($disponivel == TRUE) {
        echo "<hr><h3>Tem estoque disponível</h3>";
    } else {
        echo "<hr><h3>Não tem estoque disponível</h3>";
    };
?>