<?php
include("../conexion.php");
//header("refresh: 3");
$contenido = '';
if (!empty($_GET['dni_hijo'])) {
  $a = $_GET['dni_hijo'];
  $remesa = "SELECT * FROM remesa WHERE dni_hijo = '$a'";
  $stmt = $conn->query($remesa);
  if ($stmt->num_rows > 0) {
    while ($row = $stmt->fetch_array()) {
      $contenido .= "<tr>";
      $contenido .= "<td class='tg-0lax'>" . $row['mes'] . "</td>";
      $contenido .= "<td class='tg-0lax'>" . $row['dni_hijo'] . " </td>";
      $dni_hijo = $row['dni_hijo'];
      $contenido .= "<td class='tg-0lax'>" . $row['concepto'] . " </td>";
      $contenido .= "<td class='tg-0lax'>" . $row['cantidad'] . " </td>";
      $contenido .= "<td class='tg-0lax'>" . $row['subvencionado'] . "</td>";
      $contenido .= "<td class='tg-0lax'>" . $row['id_remesa'] . "</td>";
      $id_remesa = $row['id_remesa'];
      $contenido .= "<td class='tg-0lax'>" . $row['estado'] . "</td>";
      $contenido .= '<td><span><a href="EliminarRemesa.php?id_remesa='.$id_remesa.'&dni_hijo='.$dni_hijo.'">Eliminar esta cuota</a></span></td>';
      $contenido .= '<td><span><a href="ModificarCuota.php?id_remesa='.$id_remesa.'&dni_hijo='.$dni_hijo.'">Modificar esta cuota</a></span></td>';
      $contenido .= "</tr>";
    }
    $stmt->close();
  } else {
    $contenido = <<< HTML
        <tr>
          <td class="tg-0lax">No hay cuotas registradas</td>
          <td class="tg-0lax"></td>
          <td class="tg-0lax"></td>
          <td class="tg-0lax"></td>
          <td class="tg-0lax"></td>
        </tr>
      HTML;
  }
} else {
  $stmt = $conn->query("SELECT * FROM remesa");

  while ($row = $stmt->fetch_array()) {
    $contenido .= "<tr>";
    $contenido .= "<td class='tg-0lax'>" . $row['mes'] . "</td>";
    $contenido .= "<td class='tg-0lax'>" . $row['dni_hijo'] . " </td>";
    $dni_hijo = $row['dni_hijo'];
    $contenido .= "<td class='tg-0lax'>" . $row['concepto'] . " </td>";
    $contenido .= "<td class='tg-0lax'>" . $row['cantidad'] . " </td>";
    $contenido .= "<td class='tg-0lax'>" . $row['subvencionado'] . "</td>";
    $contenido .= "<td class='tg-0lax'>" . $row['id_remesa'] . "</td>";
    $id_remesa = $row['id_remesa'];
    $contenido .= "<td class='tg-0lax'>" . $row['estado'] . "</td>";
    $contenido .= '<td><span><a href="EliminarRemesa.php?id_remesa='.$id_remesa.'&dni_hijo='.$dni_hijo.'">Eliminar esta cuota</a></span></td>';
    $contenido .= '<td><span><a href="ModificarCuota.php?id_remesa='.$id_remesa.'&dni_hijo='.$dni_hijo.'">Modificar esta cuota</a></span></td>';
    $contenido .= "</tr>";
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
			<h1>Cuotas</h1>
      <span><a href='Menu.php'>Volver al Menú</a></span><br>
      <span><a href='VerFamilias.php'>Volver a familias</a></span>
      <br>
			<br>
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
						<th class="tg-0lax">Mes</th>
						<th class="tg-0lax">Dni del hijo</th>
						<th class="tg-0lax">Concepto</th>
						<th class="tg-0lax">Cantidad</th>
						<th class="tg-0lax">Subvencionado</th>
            <th class="tg-0lax">Id remesa</th>
            <th class="tg-0lax">Estado</th>
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