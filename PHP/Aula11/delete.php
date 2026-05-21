<?php

    require_once "connect_postgres.php";

    $sql = "DELETE FROM alunos WHERE id = 3";

    $conexao->exec($sql);

    echo "Aluno deletado com sucesso!";

?>