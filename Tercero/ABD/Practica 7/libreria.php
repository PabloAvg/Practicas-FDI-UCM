<?php 
if (! empty($_POST)) {
    //var_dump($_POST); 
    $cat_post=$_POST['combo_box'];
    $min_post=$_POST['min'];
    $max_post=$_POST['max'];

    $db = @mysqli_connect("localhost","root","","practica 7 abd");
    if($db){
        //SLECCIONAMOS TODAS LAS CATEGORIAS
        $sql=  "SELECT Titulo, Categoria, Precio FROM libros WHERE Categoria='$cat_post' AND Precio BETWEEN '$min_post' AND '$max_post'";
        $consulta=mysqli_query($db, $sql);

        echo "<table border='1' align='center'>";

        while($fila = mysqli_fetch_array($consulta)){
            echo "
                <tr>
                    <td> $fila[0] </td>
                    <td> $fila[1] </td>
                    <td> $fila[2] </td>
                </tr>";
        }
        
    }   
    @mysqli_close($db);

} 
?>
<!DOCTYPE html>

<html>
  	<head>
		<meta charset="utf-8">
		<title>Libreria -> practica 7
             de Ampliacón de base de datos
        </title>
	</head>
	<body>
        <?php 
            $db = @mysqli_connect("localhost","root","","practica 7 abd");
            if($db){
                echo "<form action = libreria.php method = 'post' >";
                //echo 'Conexion establecida.<br/>' ;
                /*TODO
                    - CARGAR LISTA DE CATEGORIAS
                    - INPUTS PARA MAX Y MIN PRECIO
                    - TABLA LIBROS
                 */
                
                //SLECCIONAMOS TODAS LAS CATEGORIAS
                $sql=  "SELECT * FROM categorias";
                $consulta=mysqli_query($db, $sql);
                //COMBO BOX DE CATEGORIAS
                ?>
                Selecciona una categoria:
                <select name="combo_box">
                <option value = "CATEGORIAS" selected> CATEGORIAS </option>
                <?php
                while($fila = mysqli_fetch_array($consulta))
                    echo "<option value='". $fila[0]. "'>" . $fila[0] . "</option>";
                ?>
                </select>
                <br>
                <br>
                Precio Mínimo:
                <input type="text" name="min" required>
                <br>
                <br>
                Precio Máximo
                <input type="text" name="max" required>
                <br>
                <br>
                <input type="submit" name="aceptar">
                <input type="reset" value="Restaurar formulario">
                <?php
                echo "</form>";
            }   
            @mysqli_close($db);
        ?>
	</body>
</html>
