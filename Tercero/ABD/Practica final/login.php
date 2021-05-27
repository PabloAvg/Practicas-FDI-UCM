<?php
//CONEXION A LA BBDD...
require("conexion.php");
if(!empty($_POST['email']) && !empty($_POST['password'])){
  if ($stmt = $conn->prepare('SELECT dni_monitor, password FROM monitores WHERE email = ?'))
  {
      $stmt->bind_param('s', $_POST['email']);
      $stmt->execute();
      $stmt->store_result();

      if ($stmt->num_rows > 0)
      {
          $stmt->bind_result($dni, $password);
          $stmt->fetch();
          if (password_verify($_POST['password'], $password))
          {
              session_regenerate_id();
              $_SESSION['loggedin'] = TRUE;
              $_SESSION['email'] = $_POST['email'];
              header('Location: ./Vistas/Menu.php');
          }
          else
          {
              echo 'Usuario y/o contraseña incorrectos.';
              header('Location: login.php');
          }
      }
      else
      {
          echo 'Usuario y/o contraseña incorrectos.';
          header('Location: login.php');
      }

      $stmt->close();
  }
}
?>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
    </style>
    <link rel="stylesheet" href="assets/css/style.css">
  </head>
  <body>
    <br>
  <span><a href="index.php">Página principal</a></span>
    <h1>Acceso Monitor</h1>
      <span>o <a href="signup.php">Registro</a></span>

    <?php if(!empty($message)): ?>
      <p> <?= $message ?></p>
    <?php endif; ?>

    <form action="login.php" method="post">
    <input type="text" name="email" placeholder="Introduce tu e-mail">
    <input type="password" name="password" placeholder="Introduce tu contraseña">
    <input type="submit" value="Entrar">
    </form>
  </body>
</html>
