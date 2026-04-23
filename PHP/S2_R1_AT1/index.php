<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Atividade 2 - PHP</title>
</head>
<body>
    <?php 
        // Echo simples com tag HTML
        echo "<h2>Empresa XYZ</h2>";

        // Echo com lista e link de outras páginas
        echo '<ul>
                <li>
                    <a href="#">Home</a>
                </li>
                <li>
                    <a href="#">Sobre</a>
                </li>
                <li>
                    <a href="#">Contato</a>
                </li>
              </ul><hr>';

        // Echo com estilização simples na mensagem
        echo "<h3>O <i><u>PHP</u></i> é a melhor linguagem de programação do mundo</h3><hr>";

        // Echo com variável do tipo String
        $variavel = "Teste com variável";
        echo $variavel;
    ?>

    
</body>
</html>