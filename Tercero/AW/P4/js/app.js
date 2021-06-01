$(document).ready(function() {
	
	function correoValido (email) 
    {
		var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
        return emailReg.test(email);
	};
 
    $("#campoEmail").change(function() {

		if (correoValido($("#campoEmail").val()))
		{
			const $img = $("#estado");
			$img.replaceWith('<img src="./img/ok.png" alt="estado" id="estado"/>');
		}
		else
		{
			const $img2 = $("#estado");
			$img2.replaceWith('<img src="./img/no.png" alt="estado" id="estado"/>');
		}
	});

	function usuarioExiste(query) {
		$.ajax({
			data: {
				query: query
			},
			success: function (data) {
				if (query == 'existe' ) {
					const $img = $("#estado2");
					$img.replaceWith('<img src="./img/no.png" alt="estado" id="estado2"/>');
				}
				else if (query == 'disponible')
				{
					const $img2 = $("#estado2");
					$img2.replaceWith('<img src="./img/ok.png" alt="estado" id="estado2"/>');
				}
			}
		});
	}
	
	
	$("#campoUser").change(function() {
		var url="comprobarUsuario.php?user=" + $("#campoUser").val();
		$.get(url,usuarioExiste);
	});
});
