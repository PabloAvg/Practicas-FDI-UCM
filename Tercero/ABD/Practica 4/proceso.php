<!DOCTYPE html>
<html>
  	<head>
		<meta charset="utf-8">
		<title>Mostrando el Pedido. Parte de la practica 4 de Ampliacón de base de datos</title>
	</head>
	<body>
        <?php
            
            echo "<h2>Your Input:</h2>";
            echo $_POST['nom'];
            echo "<br>";
            echo $_POST['tel'];
            echo "<br>";
            echo $_POST['mail'];
            echo "<br>";
            echo $_POST['dir'];
            echo "<br>";
            echo $_POST['primero'];
            echo "<br>";
            echo $_POST['segundo'];
            echo "<br>";
            echo $_POST['cafeote'];
            echo "<br>";
            echo $_POST['obs'];
        ?>
	</body>
</html>