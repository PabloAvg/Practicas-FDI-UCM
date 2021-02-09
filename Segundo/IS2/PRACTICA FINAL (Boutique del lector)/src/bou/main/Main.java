package bou.main;

import bou.modelo.ModeloUsuario;
import bou.modelo.controllers.LoginController;
import bou.modelo.controllers.UsuariosController;
import bou.modelo.dao.DAOUsuario;
import bou.modelo.dao.fachadas.FachadaDAOUsuario;
import bou.modelo.fachadas.LoginFachada;
import bou.modelo.fachadas.UsuariosFachada;
import bou.modelo.sa.SAUsuario;
import bou.views.LoginGui;

/**
 * Clase Main del programa
 */
public class Main {

	/**
	 * Constructora con parametros
	 * @param args argumentos por parametro (No necesarios)
	 */
	public static void main(String[] args) {
		UsuariosController controlador = new UsuariosController();
		new LoginGui(controlador);
	}

}