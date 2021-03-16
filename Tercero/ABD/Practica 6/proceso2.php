<!DOCTYPE html>
<html>
  	<head>
		<meta charset="utf-8">
		<title>Dando de alta al Usuario -> Parte de la practica 6
             de Ampliacón de base de datos
        </title>
	</head>
	<body>
        <?php 
            $db = @mysqli_connect("localhost","root","","practica 6 abd");
            if($db){
                echo 'Conexion establecida.<br/>' ;
                $user=$_POST['nom'];
                $pass=$_POST['pass'];
                $pass_verify=$_POST['pass_verify'];
                if($pass != $pass_verify){
                    echo "Las contraseñas no coinciden";
                }else{
                    $sql="INSERT INTO usuarios (user_id, password) VALUES ('$user', '$pass')";
                    $consulta=mysqli_query($db, $sql);
                    ?><h1>Nuevo usuario registrado</h1><?php
                }
            }else{
                echo 'ERROR :C %d: %s <br/> ',
                mysqli_connect_errno() , mysqli_connect_error() ;
            }
            @mysqli_close($db);
        ?>
	</body>
</html>
