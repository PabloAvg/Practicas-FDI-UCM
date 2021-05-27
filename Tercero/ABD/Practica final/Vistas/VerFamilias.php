<?php
include("../conexion.php");
$contenido='';
$familias = "SELECT * FROM tutores";
$stmt = $conn->query($familias);
if ($stmt->num_rows > 0) {
  while ($row = $stmt->fetch_array()) {
    
    $contenido .= "<tr>";
    $contenido .= "<td class='tg-0lax'>" . $row['nombre_completo'] . "</td>";
    $nombre_completo = $row['nombre_completo'];
    $contenido .= "<td class='tg-0lax'>" . $row['email'] . " </td>";
    $email = $row['email'];
    $contenido .= "<td class='tg-0lax'>" . $row['dni'] . " </td>";
    $dni = $row['dni'];
    $contenido .= "<td class='tg-0lax'>" . $row['telefono'] . " </td>";
    $telefono = $row['telefono'];
    $contenido .= "<td class='tg-0lax'>" . $row['numero_cuenta'] . "</td>";
    $numero_cuenta = $row['numero_cuenta'];
    $contenido .= '<td><span><a href="VerAsociados.php?dni='.$dni.'">Ver Asociados</a></span></td>';
    $contenido .= '<td><span><a href="AltaAsociado.php?dni='.$dni.'">Añadir Asociado</a></span></td>';
    $contenido .= '<td><span><a href="EliminarFamilia.php?dni='.$dni.'">Eliminar</a></span></td>';
    $contenido .= '<td><span><a href="ModificarFamilia.php?dni='.$dni.'">Modificar</a></span></td>';
    $contenido .= "</tr>";
  }
  $stmt->close();
} else {
  $contenido = <<< HTML
      <tr>
        <td class="tg-0lax">No hay familias registradas</td>
        <td class="tg-0lax"></td>
        <td class="tg-0lax"></td>
        <td class="tg-0lax"></td>
        <td class="tg-0lax"></td>
      </tr>
    HTML;
}


$html = <<< HTML
	<!DOCTYPE html>
	<html lang="en" dir="ltr">
		<head>
			<meta charset="utf-8">
			<title>Lista familias</title>
			<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
			<link rel="stylesheet" href="../assets/css/style.css">
		</head>
		<body>
			<h1>Familias</h1>
			<span><a href="Menu.php">Volver al Menú</a></span>
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
						<th class="tg-0lax">Nombre del tutor</th>
						<th class="tg-0lax">Email</th>
						<th class="telefono">Dni del tutor</th>
						<th class="tg-0lax">Telefono de contacto</th>
						<th class="tg-0lax">Numero de cuenta</th>
					</tr>
				</thead>
				<tbody>
HTML;

$html .=<<< HTML
					$contenido
				</tbody>
			</table>
		</body>
	</html>
HTML;

echo $html;
?>