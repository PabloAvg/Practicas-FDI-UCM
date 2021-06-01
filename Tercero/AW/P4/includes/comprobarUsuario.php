<?php
require_once __DIR__.'/UsuarioBD.php';

$usuario = $_GET['user'];

$conn = getConexionBD();
$query =<<< SQL
	SELECT
		*
	FROM
		usuarios
	WHERE
		username = ?
SQL;
$sentencia = $conn->prepare($query);
$sentencia->bind_param("s", $usuario);
$sentencia->execute();
$sentencia->store_result();
$existe = $sentencia->num_rows > 0;
$sentencia->close();
return $existe;
?>