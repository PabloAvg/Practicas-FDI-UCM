package bou.modelo;

import bou.modelo.listas.ListaUsuarios;
import bou.modelo.observers.UsuariosObserver;

/**
 * Clase que comunica la lista de usuarios con el login y los observers
 */
public class ModeloUsuario {

	private ListaUsuarios lista_usuarios;
	
	/**
	 *  Constructor sin parámetros
	 */
	public ModeloUsuario(){
		lista_usuarios = new ListaUsuarios();
	}

	/**
	 * Registra los observadores de usuarios
	 * @param obs
	 */
	public void registerObserver(UsuariosObserver obs) {
		lista_usuarios.addObserver(obs);
		
	}
	
	/**
	 * Inicia sesión
	 * @param user
	 * @param password
	 */
	public void login(String user, String password) {
		lista_usuarios.login(user,password);
		
	}
	
}