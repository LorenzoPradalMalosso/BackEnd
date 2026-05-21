<?php 

    $host = "localhost";
    $port = "5432";
    $dbname = "escola";
    $user = "postgres";
    $pass = "postgres";
    
    try {
        $conexao = new PDO(
            "pgsql:host=$host;
            port=$port;
            dbname=$dbname",
            $user,
            $pass
        );
        echo "Conexão com o Postgres realizada!<br>";
    } catch (PDOException $e) {
        echo "Erro na conexão: ".$e->getMessage();
    }
?>