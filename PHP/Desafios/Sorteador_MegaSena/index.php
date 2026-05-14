<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sortear Mega Sena</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <style>
        body {
            background: radial-gradient(circle at top, rgba(220,53,69,0.15), transparent 35%),
                        linear-gradient(180deg, #fff 0%, #f8f9fa 100%);
        }
        .numero-bola {
            width: 60px;
            height: 60px;
            display: inline-flex;
            justify-content: center;
            align-items: center;
            border-radius: 50%;
            background: #dc3545;
            color: #fff;
            font-weight: 700;
            margin: 0.25rem;
        }
    </style>
</head>
<body>
    <?php 
        include 'header.html';

        $numerosSorteados = [];
    ?>

    <main class="py-5">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-6 col-md-8">
                    <div class="card shadow-sm rounded-4 border-0">
                        <div class="card-body p-4 p-md-5">
                            <h1 class="card-title text-center mb-3 text-danger fw-bold">Mega Sena</h1>
                            <p class="text-center text-secondary mb-4">Clique no botão para sortear 6 números entre 1 e 60.</p>

                            <form method="POST" class="d-grid gap-3">
                                <button type="submit" name="botaoCalcular" class="btn btn-danger btn-lg rounded-pill">
                                    Sortear números
                                </button>
                            </form>

                            <?php 
                                if (isset($_POST["botaoCalcular"])) {
                                    for ($num = 0; $num < 6; $num++) {
                                        $numerosSorteados[] = rand(1, 60);
                                    }
                                    $mensagem = implode(" | ", $numerosSorteados);
                            ?>

                            <div class="mt-4">
                                <div class="alert alert-danger rounded-4 shadow-sm" role="alert">
                                    <h4 class="alert-heading mb-3">Números sorteados</h4>
                                    <p class="mb-3 fw-semibold">Sequência: <?php echo $mensagem; ?></p>
                                    <div class="d-flex flex-wrap justify-content-center">
                                        <?php foreach ($numerosSorteados as $numero) : ?>
                                            <span class="numero-bola"><?php echo $numero; ?></span>
                                        <?php endforeach; ?>
                                    </div>
                                </div>
                            </div>

                            <?php 
                                }
                            ?>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDzYxF3NdmuJOoRKwpMrL1mLnumno23NmindbtQIB2n7f6eoKLJParqqCC" crossorigin="anonymous"></script>
</body>
</html>