<?php
require ("../conexion.php");
$message='';
$id ='';
$dni=$_GET['dni'];

if(!empty($_POST['mes']) && !empty($_POST['concepto'])&& !empty($_POST['cantidad'])
    && !empty($_POST['subvencionado'])&& !empty($_POST['estado'])){

	$stmt = $conn->prepare("
				INSERT
				INTO
					remesa
					(
						mes,
						dni_hijo,
						concepto,
						cantidad,
						subvencionado,
            estado
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

	$stmt->bind_param(
		"sssiss", 
		$_POST['mes'],
    $dni,
		$_POST['concepto'],
		$_POST['cantidad'],
		$_POST['subvencionado'],
    $_POST['estado'],
	);

	if ($stmt->execute()) {
		$message ='Cuota añadida correctamente';
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
		<title>Registrar Cuota</title>
		<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
		<link rel="stylesheet" href="../assets/css/style.css">
	</head>
	<body>
		<?php require '../partials/header.php'; ?>
		$messageAviso

		<h1>Registrar Cuota</h1>
		<span><a href="Menu.php">ir al Menu</a></span><br>
    <span><a href="VerRemesas.php">ir a la tabla de cuotas</a></span>
		<form action="AltaCuota.php?dni=$dni" method="post">
		<input type="text" name="mes" placeholder="Mes" required='required'>
    <input type="text" name="dni_hijo" disabled="disabled" value="Dni del Asociado: $dni">
		<input type="text" name="concepto" placeholder="Concepto" required='required'>
    <div>Cantidad</div>
    <input type="number" name="cantidad" placeholder="Cantidad" required='required'>
    <br><br>
    <div>Subvención</div>
		  <select name="subvencionado">
        	<option value="si">Si</option>
        	<option value="no">No</option>
    	</select>
      <br><br>
    <div>Estado</div>
      <select name="estado">
          <option value="pendiente">Pendiente de pago</option>
        	<option value="pagado">Pagado</option>
    	</select><br><br>
		<input type="submit" value="Registrar">
		
		</form>
	</body>
</html>
HTML;

echo $html;
?>
