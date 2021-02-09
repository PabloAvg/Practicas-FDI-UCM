package bou.modelo.controllers;

import bou.modelo.fachadas.LoginFachada;
import bou.modelo.observers.UsuariosObserver;

/**
 * Clase que comunica la vista login con la fachada login.
 */
public class LoginController {

		private LoginFachada fachada_usuarios;
		
		/**
		 * Contrstuctora con parametros
		 * @param f
		 */
		public LoginController(LoginFachada f) {
			fachada_usuarios = f;
		}

		/**
		 * Registra un observador de Usuario
		 * @param obs
		 */
		public void registerObserver(UsuariosObserver obs) {
			fachada_usuarios.registerObserver(obs);	
		}
		
		/**
		 * Hace login de usuario
		 * @param user
		 * @param password
		 */
		public void login(String user, String password) {
			fachada_usuarios.login(user,password);	
		}

	
}
