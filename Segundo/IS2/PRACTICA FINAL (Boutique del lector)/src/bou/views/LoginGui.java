package bou.views;

import bou.gui.GUI;
import bou.gui.login.LoginPanel;
import bou.modelo.controllers.LoginController;
import bou.modelo.controllers.UsuariosController;

/**
 * Clase que extiende a GUI y se encarga de crear la ventana de login 
 */

public class LoginGui extends GUI {
	
	private static final long serialVersionUID = 1L;
	private UsuariosController login_control;
	private LoginPanel login_panel;
	
	/**
	 * Constructora con parámetro
	 * @param c LoginController
	 */
	public LoginGui(UsuariosController c) {
		super("Login");
		login_control = c;
		initGUILogin();
	}

	/**
	 * Método que inicializa los componentes de la ventana
	 */
	private void initGUILogin() {
		panelPrincipal = this.getContentPane();
		login_panel = new LoginPanel(login_control);
		panelPrincipal.add(login_panel);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}
