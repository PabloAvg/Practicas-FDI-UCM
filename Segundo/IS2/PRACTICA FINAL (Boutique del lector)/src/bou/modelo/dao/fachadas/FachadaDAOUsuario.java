package bou.modelo.dao.fachadas;

import bou.modelo.dao.DAOUsuario;
import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;

/**
 * Interfaz de Clase que comunica el S.A. con el DAO de usuario
 */
public class FachadaDAOUsuario implements IFachadaDAOUsuario{

	private DAOUsuario _daousuario;
	
	public FachadaDAOUsuario(DAOUsuario d) {
		_daousuario = d;
	}

	@Override
	public void AltaUsuario(Usuario u, Cliente c) {
		_daousuario.eAltaUsuario(u,c);
	}

	@Override
	public void EliminarUsuario(String u) {
		_daousuario.eEliminarUsuario(u);
	}

	@Override
	public boolean BuscarUsuario(String u) {
		return _daousuario.eBuscarUsuario(u);
	}

	@Override
	public void ModificarUsuario(Usuario u) {
		_daousuario.eModificarUsuario(u);
	}

	@Override
	public String IniciarSesion(String u, String p) {
		return _daousuario.eIniciarSesion(u, p);
	}

	@Override
	public Usuario ConsultarUsuario(String u) {
		return _daousuario.eConsultarUsuario(u);
	}
	
}
