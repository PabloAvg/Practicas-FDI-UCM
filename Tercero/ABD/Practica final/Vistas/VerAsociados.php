
<?php
include("../conexion.php");
$contenido = '';
if (!empty($_GET['dni'])) {
  $a = $_GET['dni'];
  $asociados = "SELECT * FROM hijos WHERE dni_tutor = '$a'";
  $stmt = $conn->query($asociados);
  if ($stmt->num_rows > 0) {
    while ($row = $stmt->fetch_array()) {
      $contenido .= "<tr>";
      $contenido .= "<td class='tg-0lax'>" . $row['nombre_completo'] . "</td>";
      $contenido .= "<td class='tg-0lax'>" . $row['direccion'] . " </td>";
      $contenido .= "<td class='tg-0lax'>" . $row['seccion'] . " </td>";
      $contenido .= "<td class='tg-0lax'>" . $row['dni_tutor'] . " </td>";
      $dni_tutor = $row['dni_tutor'];
      $contenido .= "<td class='tg-0lax'>" . $row['dni'] . "</td>";
      $dni_hijo = $row['dni'];
      $contenido .= '<td><span><a href="VerRemesas.php?dni_hijo='.$dni_hijo.'&dni_tutor='.$dni_tutor.'">Ver Cuotas</a></span></td>';
      $contenido .= '<td><span><a href="AltaCuota.php?dni='.$dni_hijo.'">Añadir Cuota</a></span></td>';
      $contenido .= '<td><span><a href="EliminarAsociado.php?dni_hijo='.$dni_hijo.'&dni_tutor='.$dni_tutor.'">Eliminar este asociado</a></span></td>';
      $contenido .= '<td><span><a href="ModificarAsociado.php?dni_hijo='.$dni_hijo.'&dni_tutor='.$dni_tutor.'">Modificar</a></span></td>';
      $contenido .= "</tr>";
    }
    $stmt->close();
  } else {
    $contenido = <<< HTML
        <tr>
          <td class="tg-0lax">No hay hijos asociados</td>
          <td class="tg-0lax"></td>
          <td class="tg-0lax"></td>
          <td class="tg-0lax"></td>
          <td class="tg-0lax"></td>
        </tr>
      HTML;
  }
} else {
  $resultado = $conn->query("SELECT * FROM hijos");

  while ($row = $resultado->fetch_array()) {
    $contenido .= "<tr>";
    $contenido .= "<td class='tg-0lax'>" . $row['nombre_completo'] . "</td>";
    $contenido .= "<td class='tg-0lax'>" . $row['direccion'] . " </td>";
    $contenido .= "<td class='tg-0lax'>" . $row['seccion'] . " </td>";
    $contenido .= "<td class='tg-0lax'>" . $row['dni_tutor'] . " </td>";
    $dni_tutor = $row['dni_tutor'];
    $contenido .= "<td class='tg-0lax'>" . $row['dni'] . "</td>";
    $dni_hijo = $row['dni'];
    $contenido .= '<td><span><a href="VerRemesas.php?dni_hijo='.$dni_hijo.'&dni_tutor='.$dni_tutor.'">Ver Cuotas</a></span></td>';
    $contenido .= '<td><span><a href="AltaCuota.php?dni='.$dni_hijo.'">Añadir Cuota</a></span></td>';
    $contenido .= '<td><span><a href="EliminarAsociado.php?dni_hijo='.$dni_hijo.'&dni_tutor='.$dni_tutor.'">Eliminar este asociado</a></span></td>';
    $contenido .= '<td><span><a href="ModificarAsociado.php?dni_hijo='.$dni_hijo.'&dni_tutor='.$dni_tutor.'">Modificar</a></span></td>';
    $contenido .= "</tr>";
  }

  $conn->close();
}


$html = <<< HTML
	<!DOCTYPE html>
	<html lang="en" dir="ltr">
		<head>
			<meta charset="utf-8">
			<title>Lista Asociados</title>
			<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
			<link rel="stylesheet" href="../assets/css/style.css">
		</head>
		<body>
			<h1>Asociados</h1>
      <span><a href="Menu.php">Volver al Menu</a></span>
      <br>
			<span><a href='VerFamilias.php'>Volver a Familias</a></span>
			<br><br>
      <style>
        .tg {
          border-collapse: collapse;
          border-color: #9ABAD9;
          border-spacing: 0;
        }

        .tg td {
          background-color: #EBF5FF;
          border-color: #9ABAD9;
          border-style: solid;
          border-width: 1px;
          color: #444;
          font-family: Arial, sans-serif;
          font-size: 14px;
          overflow: hidden;
          padding: 10px 5px;
          word-break: normal;
        }

        .tg th {
          background-color: #409cff;
          border-color: #9ABAD9;
          border-style: solid;
          border-width: 1px;
          color: #fff;
          font-family: Arial, sans-serif;
          font-size: 20px;
          font-weight: normal;
          overflow: hidden;
          padding: 10px 5px;
          word-break: normal;
        }

        .tg .tg-0lax {
          text-align: left;
          vertical-align: top
        }
        </style>
			<table class="tg">
				<thead>
					<tr>
						<th class="tg-0lax">Nombre</th>
						<th class="tg-0lax">Direccion</th>
						<th class="tg-0lax">Seccion</th>
						<th class="tg-0lax">Dni del tutor</th>
						<th class="tg-0lax">DNI</th>
					</tr>
				</thead>
				<tbody>
HTML;

$html .= <<< HTML
					$contenido
				</tbody>
			</table>
		</body>
	</html>
HTML;

echo $html;
?>