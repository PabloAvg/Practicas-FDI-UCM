<?php
	require_once __DIR__.'/includes/config.php';

	$tituloPagina = 'Login';

	$raizApp = RUTA_APP;
	$contenidoPrincipal=<<<EOS
		<h1>Registro</h1>

		<form id="formLogin" action="${raizApp}/registro.php" method="POST">
		<fieldset>
			<legend>Correo, usuario y contraseña</legend>
			<div><label>Correo:</label> <input type="text" name="correo" id="campoEmail"/><img src="" id="estado"/></div>
			<div><label>User:</label> <input type="text" name="username" id="campoUser"/><img src="" id="estado2"/></div>
			<div><label>Password:</label> <input type="password" name="password"/></div>
			<div><button type="submit">Entrar</button></div>
		</fieldset>
	EOS;

	require __DIR__.'/includes/comun/layout.php';