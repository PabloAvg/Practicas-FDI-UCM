package bou.modelo.sa;

import bou.modelo.dao.DAOUsuario;
import bou.modelo.dao.fachadas.FachadaDAOUsuario;
import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;

public class SAUsuario implements ISAUsuario{

	FachadaDAOUsuario fdusuario;
	DAOUsuario du;
	
	public SAUsuario() {
		du = new DAOUsuario();
		fdusuario = new FachadaDAOUsuario(du);
	}
	
	@Override
	public void eAltaUsuario(Usuario u, Cliente c) {
		if(!fdusuario.BuscarUsuario(u.getUser())) {	//No existe ese usuario (Bloque combinado)
			fdusuario.AltaUsuario(u, c);
		}else {
			throw new RuntimeException("Usuario ya existente");
		}
	}

	@Override
	public void eEliminarUsuario(String u) {
		if(fdusuario.BuscarUsuario(u)) {	//Existe ese usuario (Bloque combinado)
			fdusuario.EliminarUsuario(u);
		}else {
			throw new RuntimeException("Usuario no existente");
		}
	}

	@Override
	public boolean eBuscarUsuario(String u) {
		return fdusuario.BuscarUsuario(u);
	}

	@Override
	public void eModificarUsuario(Usuario u) {
		fdusuario.ModificarUsuario(u);
	}

	@Override
	public String eIniciarSesion(String u, String p) {
		String ret = fdusuario.IniciarSesion(u, p);
		if(  ret != null) {
			return ret;
		}else {
			throw new RuntimeException("Usuario no existente");
		}
	}

	@Override
	public Usuario eConsultarUsuario(String u) {
		Usuario ret = fdusuario.ConsultarUsuario(u);
		if(  ret != null) {
			return ret;
		}else {
			throw new RuntimeException("Usuario no existente");
		}
	}

}
