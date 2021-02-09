package bou.gui;

import java.awt.Container;

import javax.swing.JFrame;

/**
 * Clase que extiende a JFrame y de la que heredan todas las vistas.
 */
@SuppressWarnings("serial")
public class GUI extends JFrame{

	protected Container panelPrincipal;
	
	/**
	 * Constructor con parámetro
	 * @param titulo String
	 */
	public GUI(String titulo){
		super(titulo);
	}
	
}
