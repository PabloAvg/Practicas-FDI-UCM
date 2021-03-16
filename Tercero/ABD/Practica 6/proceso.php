<!DOCTYPE html>
<html>
  	<head>
		<meta charset="utf-8">
		<title>Comprobando Usuario -> Parte de la practica 6
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

                $sql=  "SELECT user_id, password FROM usuarios WHERE user_id='$user' AND password='$pass'";
                
                $consulta=mysqli_query($db, $sql);
                $filas = mysqli_fetch_array($consulta);
                if ($filas!=null){
                    echo "Acceso correcto ";
                }else{
                    echo "Usuario inexistente: Registro";
                    ?>
                        <form action="proceso2.php" method="post">
                            <fieldset>
                                <legend>Datos personales</legend>
                                    Nombre:
                                    <br> 
                                    <input type="text" name="nom" required>
                                    <br>
                                    Contraseña: 
                                    <br> 
                                    <input type="text" name="pass" required>
                                    <br>
                                    Repite Contraseña: 
                                    <br> 
                                    <input type="text" name="pass_verify" required>
                                    <br>	
                            </fieldset>
                            <input type="submit" name="aceptar">
                            <input type="reset" value="Restaurar formulario">
                        </form>
                    <?php	
                }
            }else{
                echo 'ERROR :C %d: %s <br/> ',
                mysqli_connect_errno() , mysqli_connect_error() ;
            }
            @mysqli_close($db);
        ?>
	</body>
</html>
