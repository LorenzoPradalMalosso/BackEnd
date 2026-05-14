<?php 
    session_start();
    if (isset($_POST["nome"], $_POST["email"], $_POST["senha"], $_POST["curso"])) {

    $_SESSION["usuario"] = $_POST["nome"];
    $_SESSION["email"] = $_POST["email"];

    $senhaMascarada = str_repeat("*", strlen($_POST["senha"]));
    $_SESSION["senha"] = $senhaMascarada;

    $_SESSION["curso"] = $_POST["curso"];

    }
    header("Location: index.php");
    exit;

?>