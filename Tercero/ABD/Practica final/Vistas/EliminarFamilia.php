<?php
	require ("../conexion.php");
	$message='';
	//eliminar familia de la tabla tutores
	//actualizar la pagina

	$dni_tutor = $_GET['dni'];

	$stmt = $conn->prepare("
		DELETE
		FROM
			tutores
		WHERE
			dni = ?
	");

	$stmt->bind_param("s", $dni_tutor);

	if ($stmt->execute()) {
		$message ='Familia eliminada correctamente';
		$stmt->store_result();
	} else {
		$message ='Sorry error';
	}
	$stmt->close();
	header("Location: VerFamilias.php");
?>

