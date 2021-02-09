package bou.modelo.observers;

import bou.modelo.controllers.UsuariosController;

/**
 * Interfaz observadora de usuarios
 */
public interface UsuariosObserver {
	
	/**
	 * Abre la ventana de cliente o administrador, dependiendo del rol pasado por par�metro
	 * @param rol String
	 */
	public void openView(String rol, UsuariosController c);
	
	/**
	 * Ventana que muestra un mensaje de error al producirse un error al iniciar sesi�n.
	 * @param msg String
	 */
	public void errorLogin(String msg);
	
	
}