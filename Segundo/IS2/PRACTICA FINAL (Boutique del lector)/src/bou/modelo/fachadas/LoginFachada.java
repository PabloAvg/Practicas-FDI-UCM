package bou.modelo.fachadas;

import bou.modelo.ModeloUsuario;
import bou.modelo.observers.UsuariosObserver;

/**
 * Clase que comunica la fachada con el modelo de usuario
 */

public class LoginFachada {

private ModeloUsuario modelo_usuarios;
	
	/**
	 * Constructora con parametros
	 * @param m
	 */
	public LoginFachada(ModeloUsuario m) {
		modelo_usuarios = m;
	}

	/**
	 * Registra los observadores de usuarios
	 * @param obs
	 */
	public void registerObserver(UsuariosObserver obs) {
		modelo_usuarios.registerObserver(obs);	
	}
	
	/**
	 * Inicia sesión
	 * @param dni String
	 * @param password String
	 */
	public void login(String dni, String password) {
		modelo_usuarios.login(dni,password);	
	}
}

