<!DOCTYPE html>
  <html>
    <head>
      <title>Prueba de un mini PHP en XAMP</title>
    </head>
<body>
  <h1>Secuencia de 15 Hola Mundo</h1>
  <?php
    for ($i = 0; $i < 15; $i++) {
        echo '<p>' . $i . ' - ¡Hola Mundo!</p>';
      }
  ?>
</body>
</html>
