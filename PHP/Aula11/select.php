<?php

    require_once "connect_postgres.php";

    $sql = "SELECT * FROM alunos";

    // stmt = statement refere-se a um objeto PDOStatement no contexro do PDO
    $stmt = $conexao->prepare($sql);
    $stmt->execute();

    $alunos = $stmt->fetchAll(PDO::FETCH_ASSOC);

    foreach ($alunos as $aluno) {
        echo "ID: {$aluno['id']}<br>";
        echo "Nome: {$aluno['nome']} {$aluno['sobrenome']}<br>";
        echo "Data Nascimento: {$aluno['data_nascimento']}<br>";
        echo "Turma: {$aluno['turma']}<br>";
        echo "Ativo: ".($aluno['ativo'] ? "Ativo" : "Inativo")."<hr><br>";
    }

?>