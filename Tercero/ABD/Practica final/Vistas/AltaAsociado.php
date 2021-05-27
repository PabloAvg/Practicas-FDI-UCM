<?php
require ("../conexion.php");
$message='';
$dni_tutor=$_GET['dni'];

if(!empty($_POST['nombre_completo']) && !empty($_POST['direccion'])
		&& !empty($_POST['seccion'])&& !empty($_POST['dni'])){

	$stmt = $conn->prepare("
				INSERT
				INTO
					hijos
					(
						nombre_completo,
						direccion,
						seccion,
						dni_tutor,
						dni
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
		"sssss", 
		$_POST['nombre_completo'],
		$_POST['direccion'],
		$_POST['seccion'],
		$dni_tutor,
		$_POST['dni'],
	);

	if ($stmt->execute()) {
		$message ='Asociado añadido correctamente';
		$stmt->store_result();
	} else {
		$message ='Sorry error';
	}
	$stmt->close();
}

		$messageAviso='';
		 if(!empty($message))
			$messageAviso =$message; 
		

$html = <<< HTML
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Registrar Asociado</title>
		<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
		<link rel="stylesheet" href="../assets/css/style.css">
	</head>
	<body>
		<?php require '../partials/header.php'; ?>
		$messageAviso

		<h1>Registrar Asociado</h1>
		<span> <a href="Menu.php">Volver al Menú</a></span><span>
		<br>
		<span><a href='VerFamilias.php'>Volver a familias</a></span>
		<form action="AltaAsociado.php?dni=$dni_tutor" method="post">
		<input type="text" name="nombre_completo" placeholder="Nombre completo del asociado" required='required'>
		<input type="text" name="direccion" placeholder="Dirección" required='required'>
		<input type="text" name="dni_tutor" disabled="disabled" value="Dni tutor: $dni_tutor">
		<input type="text" name="dni" placeholder="DNI del asociado" required='required'>
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
HTML;

echo $html;
?>