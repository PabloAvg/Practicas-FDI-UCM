<?php
require ("conexion.php");
$message='';
if(!empty($_POST['email']) && !empty($_POST['password'])){

  $stmt = $conn->prepare("
				INSERT
				INTO
					monitores
					(
						nombre_completo,
						seccion,
						dni_monitor,
						cargo,
						email,
						password
					)
				VALUES
					(
            ?,
            ?,
            ?,
            ?,
            ?,
            ?
          )
	");

  $password = password_hash($_POST['password'], PASSWORD_BCRYPT);

  $stmt->bind_param(
    "ssssss", 
    $_POST['nombre_completo'],
    $_POST['seccion'],
    $_POST['dni_monitor'],
    $_POST['cargo'],
    $_POST['email'],
    $password
  );

  if ($stmt->execute()) {
    $message ='Monitor añadido correctamente';
    $stmt->store_result();
  } else {
    $message ='Sorry error';
  }
  $stmt->close();
}
?>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>SignUp</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
    </style>
    <link rel="stylesheet" href="assets/css/style.css">
  </head>
  <body>
  <br>
  <span><a href="index.php">Página principal</a></span>
    <?php if(!empty($message)):?>
      <p><?= $message; ?></p>
    <?php endif; ?>

    <h1>Registrar Monitor</h1>
    <span>or <a href="login.php">Login</a></span>
    <form action="signup.php" method="post">
    <input type="text" name="nombre_completo" placeholder="Nombre completo" required='required'>
    <input type="text" name="dni_monitor" placeholder="DNI" required='required'>
    <input type="text" name="cargo" placeholder="Cargo" required='required'>
    <input type="text" name="email" placeholder="E-mail" required='required'>
    <input type="password" name="password" placeholder="Contraseña" required='required'>
    <input type="password" name="confirma_password" placeholder="Confirmar contraseña" required='required'>
    <select name="seccion">
        	<option value="castores">castores</option>
        	<option value="manada">manada</option>
        	<option value="tropa">tropa</option>
        	<option value="escultas">escultas</option>
        	<option value="clan">clan</option>
    	</select><br><br>
    <input type="submit" value="Registrar">
    </form>
  </body>
</html>


