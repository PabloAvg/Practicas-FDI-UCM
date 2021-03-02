<!DOCTYPE html>
  <html>
    <head>
      <meta charset="utf-8">
      <title>Práctica 2 de Ampliación de bases de Datos</title>
    </head>
<body>

  <?php
    //Script de php:

    //Array de días de la semana (clave => Valor)
    $dias_semana = array(
      "L" => "Lunes",
      "M" => "Martes",
      "X" => "Miércoles",
      "J" => "Jueves",
      "V" => "Viernes",
      "S" => "Sábado",
      "D" => "Domíngo",
    );
    //var_dump($dias_semana);

    $dias_seleccionados = array(
      //"L",
      "M",
      "X",
      //"J",
      //"V",
      "S",
      "D",
    );
    //var_dump($dias_seleccionados);

    $nombre_dias_seleccionados = array();

    //Bucle para seleccionar dinamicamente los dias que estan en $dias_seleccionados
    foreach ($dias_seleccionados as $i => $value) {
      $nombre_dias_seleccionados[$i] = $dias_semana[$value];
    }

    //Formateo rapido para la salida
    echo('<pre>');
    print_r($nombre_dias_seleccionados);
    echo('</pre>');
  ?>

</body>
</html>
