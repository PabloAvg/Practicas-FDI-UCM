package bou.modelo.controllers;

import bou.modelo.fachadas.UsuariosFachada;
import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;

public class UsuariosController {
	private UsuariosFachada fachada_usuarios;
	
	
	public UsuariosController() {
		fachada_usuarios = new UsuariosFachada();
	}
	/**
	 * Hace login de usuario
	 * @param user
	 * @param password
	 */
	public String login(String user, String password) {
		return fachada_usuarios.login(user,password);	
	}
	
	/**
	 * Agrega un cliente
	 * @param cliente
	 * @param usuario
	 */
	public void agregarCliente(Cliente cliente, Usuario usuario) {
		fachada_usuarios.agregarCliente(cliente, usuario);
		
	}
}
