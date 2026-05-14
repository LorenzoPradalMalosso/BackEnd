<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calcular Antecessor e Posterior</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    <?php 
        include 'header.html';

        $numero = 0;
        $antecessor = 0;
        $sucessor = 0;
    ?>

    <main class="bg-light py-5 min-vh-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-6 col-md-8">
                    <div class="card border-0 shadow-sm rounded-4">
                        <div class="card-body p-4 p-md-5">
                            <h2 class="text-center mb-4 fw-bold text-danger">Antecessor e Posterior</h2>
                            
                            <form method="POST">
                                <div class="mb-3">
                                    <label for="num" class="form-label fw-semibold">Digite um número:</label>
                                    <input type="number" id="num" name="num" class="form-control rounded-3" placeholder="Ex: 5" required>
                                </div>

                                <button type="submit" name="botaoCalcular" class="btn btn-danger w-100 rounded-pill py-2 fw-semibold">
                                    Calcular
                                </button>
                            </form>

                            <?php
                                if (isset($_POST["botaoCalcular"])) {
                                    $numero = (int) ($_POST["num"] ?? 0);
                                    $antecessor = $numero - 1;
                                    $sucessor = $numero + 1;
                            ?>
                            <div class="mt-4">
                                <div class="alert alert-danger alert-dismissible fade show rounded-3" role="alert">
                                    <strong class="d-block mb-2">Número digitado:</strong>
                                    <h3 class="mb-0"><?php echo $numero; ?></h3>
                                </div>

                                <div class="row g-3 mt-2">
                                    <div class="col-6">
                                        <div class="card border-danger rounded-3">
                                            <div class="card-body text-center">
                                                <p class="text-danger fw-semibold mb-2">Antecessor</p>
                                                <h4 class="text-danger fw-bold mb-0"><?php echo $antecessor; ?></h4>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="card border-danger rounded-3">
                                            <div class="card-body text-center">
                                                <p class="text-danger fw-semibold mb-2">Sucessor</p>
                                                <h4 class="text-danger fw-bold mb-0"><?php echo $sucessor; ?></h4>
                                            </div>
                                        </div>
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