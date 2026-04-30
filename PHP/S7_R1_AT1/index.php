<?php 
    // Primeiro método
    echo "<h3>Exibindo com um foreach para cada lista</h3>";

    // Lista de produtos
    $produtos = ["Notebook Pro X15", "Smartphone Alpha Z", "Monitor UltraWide 29", "Teclado Mecânico RGB", "Mouse Gamer Precision",
        "Headset Wireless Pro", "Impressora Multifuncional Smart", "Tablet Vision Tab 10", "Roteador Wi-Fi 6 Turbo", "HD Externo 2TB"];
    // Exibir lista de setores
    echo "<strong>Lista de Produtos:</strong>";
    echo "<ol>";
    foreach ($produtos as $p) {
        echo "<li>$p</li>";
    }
    echo "</ol><hr>";

    // Lista de setores
    $setores = ["Administrativo", "Recursos Humanos (RH)", "Financeiro", "Tecnologia da Informação (TI)", "Comercial / Vendas", "Marketing", "Logística",
        "Atendimento ao Cliente (Suporte)", "Compras", "Pesquisa e Desenvolvimento (P&D)"];
    // Exibir lista de setores
    echo "<strong>Lista de Setores:</strong>";
    echo "<ol>";
    foreach ($setores as $s) {
        echo "<li>$s</li>";
    }
    echo "</ol><hr>";

    // Lista de funcionários
    $funcionarios = ["Carlos Silva - Gerente Geral", "Ana Souza - Analista de RH", "João Pereira - Desenvolvedor Backend", "Mariana Costa - Desenvolvedora Frontend",
        "Lucas Fernandes - Analista Financeiro", "Juliana Rocha - Coordenadora de Marketing", "Pedro Alves - Vendedor", "Fernanda Lima - Suporte Técnico",
        "Rafael Gomes - Analista de Logística", "Beatriz Martins - Compradora"];
    // Exibir lista de funcionários
    echo "<strong>Lista de Funcionários:</strong>";
    echo "<ol>";
    foreach ($funcionarios as $f) {
        echo "<li>$f</li>";
    }
    echo "</ol><hr>";





    // Segundo método
    echo "<br><br><br><h3>Exibindo usando um foreach com um for para cada lista</h3>";

    // Método mais enxuto, com um for dentro de um foreach
    $listas = [$produtos, $setores, $funcionarios];
    $nomeListas = ["Produtos", "Setores", "Funcionários"];
    $contador = 0;
    foreach ($listas as $ls) {
        echo "<strong>Lista de ".$nomeListas[$contador].":</strong>";
        echo "<ol>";
        for ($i=0; $i < count($ls); $i++) { 
            echo "<li>".$ls[$i]."</li>";
        }
        echo "</ol><hr>";
        $contador ++;
    }
?>