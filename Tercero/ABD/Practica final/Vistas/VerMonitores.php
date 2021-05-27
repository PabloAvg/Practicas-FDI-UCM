<?php
include("../conexion.php");
$contenido='';
$monitores = "SELECT * FROM monitores";
$stmt = $conn->query($monitores);
if ($stmt->num_rows > 0) {
  while ($row = $stmt->fetch_array()) {
    
    $contenido .= "<tr>";
    $contenido .= "<td class='tg-0lax'>" . $row['nombre_completo'] . "</td>";
    $contenido .= "<td class='tg-0lax'>" . $row['seccion'] . " </td>";
    $contenido .= "<td class='tg-0lax'>" . $row['dni_monitor'] . " </td>";
    $dni_monitor=$row['dni_monitor'];
    $contenido .= "<td class='tg-0lax'>" . $row['cargo'] . " </td>";
    $contenido .= "<td class='tg-0lax'>" . $row['email'] . "</td>";
    $contenido .= '<td><span><a href="ModificarMonitor.php?dni='.$dni_monitor.'">Modificar</a></span></td>';
    $contenido .= "</tr>";
  }
  $stmt->close();
} else {
  $contenido = <<< HTML
      <tr>
        <td class="tg-0lax">No hay Monitores registrados</td>
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
			<title>Lista Monitores</title>
			<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap">
			<link rel="stylesheet" href="../assets/css/style.css">
		</head>
		<body>
			<h1>Monitores</h1>
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
						<th class="tg-0lax">Nombre del monitor</th>
						<th class="tg-0lax">Sección</th>
						<th class="telefono">Dni </th>
						<th class="tg-0lax">Cargo</th>
						<th class="tg-0lax">E-mail</th>
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