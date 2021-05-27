<?php
	require ("../conexion.php");
	$message='';
	//modificar familia de la tabla tutores
	//actualizar la pagina
  $dni = $_GET['dni'];

  $monitor= "SELECT * FROM monitores WHERE dni_monitor='$dni'";
  $noeditado = $conn->query($monitor);
  
  if(!empty($_POST['nombre_completo']) && !empty($_POST['seccion']) && !empty($_POST['cargo'])){
    $stmt = $conn->prepare("
      UPDATE
        monitores
      SET
        nombre_completo = ?,
        seccion = ?,
        cargo = ?
      WHERE
        dni_monitor = ?
    ");

    $stmt->bind_param(
      "ssss", 
      $_POST['nombre_completo'],
      $_POST['seccion'],
      $_POST['cargo'],
      $dni
    );

    if ($stmt->execute()) {
      $message ='Monitor modificado correctamente';
      $stmt->store_result();
    } else {
      $message ='Sorry error';
    }
    $stmt->close();
    header("Location: VerMonitores.php");
  } 

  $messageAviso='';
		if(!empty($message))
		$messageAviso =$message;

    $contenido='';
  while($row = $noeditado->fetch_array()){
    $contenido .= '<input type="text" name="nombre_completo" placeholder="' .$row['nombre_completo'] .'" value="'.$row['nombre_completo'] .'">';
    $contenido .= 'Seccion: <select name="seccion">';
    $contenido .= '<option value="castores">Castores</option>';
    $contenido .= '<option value="manada">Manada</option>';
    $contenido .= '<option value="tropa">Tropa</option>';
    $contenido .= '<option value="escultas">Escultas</option>';
    $contenido .= '<option value="clan">Clan</option>';
    $contenido .= '<input type="text" name="cargo" placeholder="' .$row['cargo'] .'" value="'.$row['cargo'] .'">';
  }
$html = <<< HTML
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Modifica la informacion de un monitor</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
    <link rel="stylesheet" href="../assets/css/style.css">
  </head>
  <body>

    <?php require '../partials/header.php'; ?>
		$messageAviso

    <h1>Modifica la informacion de un monitor</h1>
    <span>o <a href="Menu.php">Volver al Menú</a></span>
    <form action="ModificarMonitor.php?dni=$dni" method="post">
      $contenido
      <input type="submit" value="Send">
    </form>
  </body>
</html>
HTML;

echo $html;
?>