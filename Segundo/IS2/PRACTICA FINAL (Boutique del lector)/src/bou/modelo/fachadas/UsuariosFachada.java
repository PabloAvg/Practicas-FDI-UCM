package bou.modelo.fachadas;


import bou.modelo.sa.SAUsuario;
import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;

public class UsuariosFachada {
	private SAUsuario sa_usuarios;
	
	public UsuariosFachada() {
		sa_usuarios = new SAUsuario();
	}

	public String login(String dni, String password) {
		return sa_usuarios.eIniciarSesion(dni,password);	
	}
	public void agregarCliente(Cliente cliente, Usuario usuario) {
		sa_usuarios.eAltaUsuario(cliente,usuario);
	}
}
