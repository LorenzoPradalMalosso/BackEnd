<?php

    require_once "connect_postgres.php";

    $sql = "UPDATE alunos SET sobrenome = 'Costa' WHERE id = 2";

    $conexao->exec($sql);

    echo "Aluno atualizado com sucesso!"

?>