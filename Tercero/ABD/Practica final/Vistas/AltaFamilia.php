<?php
require ("../conexion.php");
$message='';
if(!empty($_POST['nombre_completo']) && !empty($_POST['email'])
    && !empty($_POST['dni'])&& !empty($_POST['telefono'])&& !empty($_POST['numero_cuenta'])){

  $stmt = $conn->prepare("
				INSERT
				INTO
					tutores
					(
						nombre_completo,
						email,
            dni,
						telefono,
						numero_cuenta
					)
				VALUES
					(
            ?,
            ?,
            ?,
            ?,
            ?
          )
	");

  $stmt->bind_param(
    "sssis", 
    $_POST['nombre_completo'],
    $_POST['email'],
    $_POST['dni'],
    $_POST['telefono'],
    $_POST['numero_cuenta'] 
  );
  
  if ($stmt->execute()) {
    $message ='Familia añadida correctamente';
    $stmt->store_result();
  } else {
    var_dump($stmt);
    $message ='Sorry error';
  }
  $stmt->close();
}
?>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Registrar Familias</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
    </style>
    <link rel="stylesheet" href="../assets/css/style.css">
  </head>
  <body>

    <?php require '../partials/header.php'; ?>
    <?php if(!empty($message)):?>
      <p><?= $message; ?></p>
    <?php endif; ?>

    <h1>Registrar Familias</h1>
    <span>o <a href="Menu.php">ir a Menu</a></span>
    <form action="AltaFamilia.php" method="post">
    <input type="text" name="nombre_completo" placeholder="Nombre completo del tutor" required='required'>
    <input type="text" name="email" placeholder="Correo electronico del tutor" required='required'>
    <input type="text" name="dni" placeholder="DNI del tutor" required='required'>
    <input type="text" name="telefono" placeholder="Telefono del tutor" required='required'>
    <input type="text" name="numero_cuenta" placeholder="Numero de cuenta (IVAN) del tutor" required='required'>
    
    <input type="submit" value="Registrar">
    </form>
  </body>
</html>