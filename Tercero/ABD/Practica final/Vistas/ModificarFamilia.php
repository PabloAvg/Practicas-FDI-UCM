<?php
	require ("../conexion.php");
	$message='';
	//modificar familia de la tabla tutores
	//actualizar la pagina
  $dni = $_GET['dni'];

  $asociado= "SELECT * FROM tutores WHERE dni='$dni'";
  $noeditado = $conn->query($asociado);
  
  if(!empty($_POST['nombre_completo']) && !empty($_POST['email']) && !empty($_POST['telefono'])&& !empty($_POST['numero_cuenta'])){
    $stmt = $conn->prepare("
      UPDATE
        tutores
      SET
        nombre_completo = ?,
        email = ?,
        telefono = ?,
        numero_cuenta = ?
      WHERE
        dni = ?
    ");

    $stmt->bind_param(
      "ssiss", 
      $_POST['nombre_completo'],
      $_POST['email'],
      $_POST['telefono'],
      $_POST['numero_cuenta'],
      $dni
    );

    if ($stmt->execute()) {
      $message ='Familia modificada correctamente';
      $stmt->store_result();
    } else {
      $message ='Sorry error';
    }
    $stmt->close();
    header("Location: VerFamilias.php");
  } 

  $messageAviso='';
		if(!empty($message))
		$messageAviso =$message;

    $contenido='';
  while($row = $noeditado->fetch_array()){
    $contenido .= '<input type="text" name="nombre_completo" placeholder="' .$row['nombre_completo'] .'" value="'.$row['nombre_completo'] .'">';
    $contenido .= '<input type="text" name="email" placeholder="' .$row['email'] .'" value="'.$row['email'] .'">';
    $contenido .= '<input type="text" name="dni" disabled="disabled"  placeholder="' .$row['dni'] .'" value="Dni del tutor representante: '.$row['dni'] .'">';
    $contenido .= '<input type="text" name="telefono" placeholder="' .$row['telefono'] .'" value="'.$row['telefono'] .'">';
    $contenido .= '<input type="text" name="numero_cuenta" placeholder="' .$row['numero_cuenta'] .'" value="'.$row['numero_cuenta'] .'">';
  }
$html = <<< HTML
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Modifica la informacion de una familia</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
    <link rel="stylesheet" href="../assets/css/style.css">
  </head>
  <body>

    <?php require '../partials/header.php'; ?>
		$messageAviso

    <h1>Modifica la informacion de una familia</h1>
    <span>o <a href="Menu.php">Volver al Menú</a></span>
    <form action="ModificarFamilia.php?dni=$dni" method="post">
      $contenido
      <input type="submit" value="Send">
    </form>
  </body>
</html>
HTML;

echo $html;
?>