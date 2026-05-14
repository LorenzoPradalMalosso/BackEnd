<?php
  $usuario = $_SESSION["usuario"];
  $email = $_SESSION["email"];
  $senha = $_SESSION["senha"];
  $curso = $_SESSION["curso"];

  $senhaOculta = str_repeat("*", strlen($senha));
  $notificacoes = ($_COOKIE["notificacoes"] ?? "0") === "1";
  $novidades = ($_COOKIE["novidades"] ?? "0") === "1";
?>

<main class="bg-body-tertiary min-vh-100 py-5">
  <div class="container">

    <div class="text-center mb-5">
      <h1 class="fw-bold mb-2">
        Seja bem-vindo, <?php echo $usuario; ?>
      </h1>

      <p class="small text-body-secondary mb-0">
        Gerencie suas informações pessoais e preferências da plataforma Pradal.
      </p>
    </div>

    <div class="row justify-content-center">
      <div class="col-lg-7 col-md-9">

        <div class="card border-0 shadow-sm rounded-4">
          <div class="card-body p-4 p-md-5">

            <div class="mb-4">
              <h4 class="fw-bold mb-1">Dados da conta</h4>
              <p class="small text-body-secondary mb-0">
                Confira abaixo as informações cadastradas no seu perfil.
              </p>
            </div>

            <div class="border rounded-4 p-3 mb-3 bg-white">
              <p class="small text-body-secondary mb-1">E-mail</p>
              <h6 class="fw-semibold mb-0">
                <?php echo $email; ?>
              </h6>
            </div>

            <div class="border rounded-4 p-3 mb-3 bg-white">
              <p class="small text-body-secondary mb-1">Senha</p>
              <h6 class="fw-semibold mb-0">
                <?php echo $senhaOculta; ?>
              </h6>
            </div>

            <div class="border rounded-4 p-3 mb-4 bg-white">
              <p class="small text-body-secondary mb-1">Preferência de curso</p>
              <h6 class="fw-semibold mb-0 text-capitalize">
                <?php echo $curso; ?>
              </h6>
            </div>

            <a href="deletar.php" class="btn btn-outline-danger btn-sm rounded-pill px-4">
              Clique aqui para DELETAR seus dados
            </a><br><br>

            <div class="border rounded-4 p-3 mb-4 bg-white">
              <div class="d-flex justify-content-between align-items-start gap-3">
                <div>
                  <h6 class="fw-semibold mb-1">Preferências</h6>
                </div>
              </div>

              <form action="notificacoes.php" method="POST" class="mt-3">
                <div class="form-check form-switch mb-3">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    role="switch"
                    id="notificacoes"
                    name="notificacoes"
                    value="1"
                    <?php echo $notificacoes ? 'checked' : ''; ?>
                  >
                  <label class="form-check-label" for="notificacoes">
                    Notificações
                  </label>
                </div>

                <div class="form-check form-switch mb-3">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    role="switch"
                    id="novidades"
                    name="novidades"
                    value="1"
                    <?php echo $novidades ? 'checked' : ''; ?>
                  >
                  <label class="form-check-label" for="novidades">
                    Receber novidades
                  </label>
                </div>

                <button type="submit" class="btn btn-danger btn-sm rounded-pill px-4">
                  Salvar preferências
                </button>
              </form>
            </div>
          </div>
        </div>

      </div>
    </div>

  </div>
</main>
