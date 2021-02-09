package bou.modelo.sa;

import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;

public interface ISAUsuario {
	
	void eAltaUsuario(Usuario u, Cliente c);
	
	void eEliminarUsuario(String u);
	
	boolean eBuscarUsuario(String u);
	
	void eModificarUsuario(Usuario u);
	
	String eIniciarSesion(String u, String p);
	
	Usuario eConsultarUsuario(String u);
	
}
