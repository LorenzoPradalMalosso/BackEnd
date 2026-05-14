<?php
session_start();

if (!isset($_SESSION["usuario"])) {
    header("Location: index.php");
    exit;
}

$notificacoes = isset($_POST["notificacoes"]) ? "1" : "0";
$novidades = isset($_POST["novidades"]) ? "1" : "0";

setcookie("notificacoes", $notificacoes, time() + 60 * 60 * 24 * 30, "/");
setcookie("novidades", $novidades, time() + 60 * 60 * 24 * 30, "/");

header("Location: index.php");
exit;
