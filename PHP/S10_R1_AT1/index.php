<?php 
    session_start();

    include "header.html";

    if (isset($_SESSION["usuario"], $_SESSION["email"], $_SESSION["senha"], $_SESSION["curso"])) {
        require "perfil.php";
    } else {
        require "forms.html";
    }

    include "footer.html";
?>
