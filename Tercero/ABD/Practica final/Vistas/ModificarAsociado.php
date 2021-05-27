<?php
	require ("../conexion.php");
	$message='';
	//modificar asociado de la tabla hijos
	//actualizar la pagina

  $dni_hijo = $_GET['dni_hijo'];
  $dni_tutor = $_GET['dni_tutor'];
  $asociado= "SELECT * FROM hijos WHERE dni='$dni_hijo'";
  $noeditado = $conn->query($asociado);
  if(!empty($_POST['nombre_completo']) && !empty($_POST['direccion']) && !empty($_POST['seccion'])){
    $stmt = $conn->prepare("
      UPDATE
        hijos
      SET
        nombre_completo = ?,
        direccion = ?,
        seccion = ?
      WHERE
        dni = ?
    ");

    $stmt->bind_param(
      "ssss", 
      $_POST['nombre_completo'],
      $_POST['direccion'],
      $_POST['seccion'],
      $dni_hijo
    );

    if ($stmt->execute()) {
      $message ='Asociado modificado correctamente';
      $stmt->store_result();
    } else {
      $message ='Sorry error';
    }
    $stmt->close();
    header('Location: VerAsociados.php?dni='.$dni_tutor.'');
  } 

  $messageAviso='';
		if(!empty($message))
		$messageAviso =$message;

    $contenido='';
  while($row = $noeditado->fetch_array()){
    $contenido .= '<input type="text" name="nombre_completo" placeholder="' .$row['nombre_completo'] .'" value="'.$row['nombre_completo'] .'">';
    $contenido .= '<input type="text" name="direccion" placeholder="' .$row['direccion'] .'" value="'.$row['direccion'] .'">';
    $contenido .= '<select name="seccion">';
    $contenido .= '<option value="castores">castores</option>';
    $contenido .= '<option value="manada">manada</option>';
    $contenido .= '<option value="tropa">tropa</option>';
    $contenido .= '<option value="escultas">escultas</option>';
    $contenido .= '<option value="clan">clan</option>';
    $contenido .= '<option selected='.$row['seccion'].'>';
    $contenido .= '<input type="text" name="dni_tutor" disabled="disabled"  placeholder="' .$row['dni_tutor'] .'" value="Dni del tutor representante: '.$row['dni_tutor'] .'">';
    $contenido .= '<input type="text" name="dni" disabled="disabled"  placeholder="' .$row['dni'] .'" value="Dni del hijo asociado: '.$row['dni'] .'">';
  }
$html = <<< HTML
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Modifica la informacion de un asociado</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
    <link rel="stylesheet" href="../assets/css/style.css">
  </head>
  <body>

    <?php require '../partials/header.php'; ?>
		$messageAviso

    <h1>Modifica la informacion de un asociado</h1>
    <span>o <a href="Menu.php">Volver al Menú</a></span>
    <form action="ModificarAsociado.php?dni_hijo=$dni_hijo&dni_tutor=$dni_tutor" method="post">
      $contenido
      <input type="submit" value="Send">
    </form>
  </body>
</html>
HTML;

echo $html;
?>