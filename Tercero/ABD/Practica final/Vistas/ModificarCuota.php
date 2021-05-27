<?php
	require ("../conexion.php");
	$message='';
	//modificar cuota de la tabla remesa
	//actualizar la pagina

  $dni_hijo = $_GET['dni_hijo'];
  $id_remesa = $_GET['id_remesa'];
  $asociado= "SELECT * FROM remesa WHERE id_remesa='$id_remesa'";
  $noeditado = $conn->query($asociado);
  if(!empty($_POST['mes']) && !empty($_POST['concepto']) && !empty($_POST['cantidad'])){
    $stmt = $conn->prepare("
      UPDATE
        remesa
      SET
        mes = ?,
        concepto = ?,
        cantidad = ?,
        subvencionado = ?,
        estado = ?
      WHERE
        id_remesa = ?
    ");

    $stmt->bind_param(
      "ssisss", 
      $_POST['mes'],
      $_POST['concepto'],
      $_POST['cantidad'],
      $_POST['subvencionado'],
      $_POST['estado'],
      $id_remesa
    );

    if ($stmt->execute()) {
      $message ='Cuota modificada correctamente';
      $stmt->store_result();
    } else {
      $message ='Sorry error';
    }
    $stmt->close();
    header('Location: VerRemesas.php?dni='.$dni_hijo.'');
  } 

  $messageAviso='';
		if(!empty($message))
		$messageAviso =$message;

    $contenido='';
  while($row = $noeditado->fetch_array()){
    $contenido .= '<input type="text" name="mes" placeholder="' .$row['mes'] .'" value="'.$row['mes'] .'">';
    $contenido .= '<input type="text" name="concepto" placeholder="' .$row['concepto'] .'" value="'.$row['concepto'] .'">';
    $contenido .= '<input type="number" name="cantidad"  placeholder="' .$row['cantidad'] .'" value="'.$row['cantidad'] .'">';
    $contenido .= 'Subvencionado: <select name="subvencionado">';
    $contenido .= '<option value="si">Si</option>';
    $contenido .= '<option value="no">No</option>';
    $contenido .= '</select><br>Pagado: ';
    $contenido .= '<select name="estado">';
    $contenido .= '<option value="pendiente">Pendiente de pago</option>';
    $contenido .= '<option value="pagado">Pagado</option>';
    $contenido .= '</select>';
    $contenido .= '<input type="text" name="dni_hijo" disabled="disabled"  placeholder="' .$row['dni_hijo'] .'" value="Dni del hijo asociado: '.$row['dni_hijo'] .'">';
  }
$html = <<< HTML
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Modifica la informacion de una cuota</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
    <link rel="stylesheet" href="../assets/css/style.css">
  </head>
  <body>

    <?php require '../partials/header.php'; ?>
		$messageAviso

    <h1>Modifica la informacion de una cuota</h1>
    <span>o <a href="Menu.php">Volver al Menú</a></span>
    <form action="ModificarCuota.php?id_remesa=$id_remesa&dni_hijo=$dni_hijo" method="post">
      $contenido
      <input type="submit" value="Send">
    </form>
  </body>
</html>
HTML;

echo $html;
?>