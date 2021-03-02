<!DOCTYPE html>
<html>
  	<head>
		<meta charset="utf-8">
		<title>Mostrando el Pedido. Parte de la practica 5 de Ampliacón de base de datos</title>
	</head>
	<body>
        <?php
            //Array de usuarios (nombre => contraseña)
            $database = array(
                "Pablo" => "admin",
                "Pedro" => "1234",
                "Paco" => "4444",
                "Julia" => "222",
                "Benito" => "222",
                "Sara" => "22222",
                "Domingo" => "33333",
            );

            //Formateo rapido para la salida
            $datos=false;

            //Mostrar todos los datos
            $datosentabla= true;
             
        ?>
        <table border="1" align="center">      
            <tr>
                <td>
                <?php
                if(array_key_exists($_POST['nom'] , $database)) {
                    echo "ERROR Usuario ya existente";
                }else{
                    $database  += [$_POST['nom'] => $_POST['pass']];

                    echo $_POST['nom'] . "</td>";
                    echo "<td>". $_POST['pass'] . "</td></tr>";
                    
                    if($datosentabla){
                        foreach($database as $user => $pass){
                            echo "
                                <tr>
                                    <td> $user </td>
                                    <td> $pass </td>
                                </tr>";
                        }
                    }

                    if($datos){
                        echo('<pre>');
                        print_r($database);
                        echo('</pre>');
                    }
                    
                }
                ?>
        </table>
	</body>
</html>