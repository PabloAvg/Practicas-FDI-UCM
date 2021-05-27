<?php
	require ("../conexion.php");
	$message='';
	//eliminar cuota de la tabla remesa
	//actualizar la pagina

	$id_remesa = $_GET['id_remesa'];
	$dni_hijo = $_GET['dni_hijo'];

	$stmt = $conn->prepare("
		DELETE
		FROM
			remesa
		WHERE
			id_remesa = ?
	");

	$stmt->bind_param("i", $id_remesa);

	if ($stmt->execute()) {
		$message ='Cuota eliminada correctamente';
		$stmt->store_result();
	} else {
		$message ='Sorry error';
	}
	$stmt->close();
	header("Location: VerRemesas.php?dni='.$dni_hijo.'");
?>

