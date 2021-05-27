<?php
  require ("../conexion.php");
  $message='';
  //eliminar asociado de la tabla hijos
  //actualizar la pagina

  $dni_hijo = $_GET['dni_hijo'];
  $dni_tutor = $_GET['dni_tutor'];

  $stmt = $conn->prepare("
		DELETE
		FROM
			hijos
		WHERE
			dni = ?
	");

	$stmt->bind_param("s", $dni_hijo);

	if ($stmt->execute()) {
		$message ='Asociado eliminado correctamente';
		$stmt->store_result();
	} else {
		$message ='Sorry error';
	}
	$stmt->close();
	header("Location: VerAsociados.php?dni=$dni_tutor");
?>