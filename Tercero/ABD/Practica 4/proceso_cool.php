<!DOCTYPE html>
<html>
  	<head>
		<meta charset="utf-8">
		<title>Mostrando el Pedido. Parte de la practica 4 de Ampliacón de base de datos</title>
	</head>
	<body>

        <table border="1" align="center">
            <tr>
                <td width="117">Nombre</td>
                <td width="218"><?php echo $_POST['nom'];   ?></td>
            </tr>
            <tr>
                <td>Teléfono</td>
                <td><?php echo $_POST['tel'];  ?></td>
            </tr>
            <tr>
                <td>E - Mail</td>
                <td><?php echo $_POST['mail'];   ?></td>
            </tr>
            <tr>
                <td>Dirección</td>
                <td><?php echo $_POST['dir'];   ?></td>
            </tr>
            <tr>
                <td>Primero</td>
                <td><?php echo $_POST['primero'];   ?></td>
            </tr>
            <tr>
                <td>Segundo</td>
                <td><?php echo $_POST['segundo'];   ?></td>
            </tr>
            <tr>
                <td>Bebida</td>
                <td>
                <?php
                if($_POST['cafeote'] == "C") {
                    echo "Cafe";
                }else{
                    echo "Té";
                }
                ?>
                </td>
            </tr>
            <tr>
                <td>Observaciones</td>
                <td>
                <?php
                if($_POST['obs'] == "") {
                    echo "Sin observaciones";
                }else{
                    echo $_POST['obs'];
                }
                ?>
                </td>
            </tr>
        </table>
	</body>
</html>