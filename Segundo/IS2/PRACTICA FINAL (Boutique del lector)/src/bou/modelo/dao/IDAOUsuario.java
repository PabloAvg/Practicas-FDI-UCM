package bou.modelo.dao;

import java.util.List;

import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;

/**
 * Clase que se encarga de transferir y actualizar la informacion de la bbdd (El txt)
 *
 */
public interface IDAOUsuario {
	
	/**
	 * Guarda un nuevo usuario en la bbdd (si no existia previamente)
	 * @param user usuario que quieres meter en la bbdd
	 */
	void eAltaUsuario(Usuario u, Cliente c);
	
	/**
	 * Elimina un usuario de la bbdd (si existia previamente)
	 * @param u id del usuario que quieres eliminar de la bbdd
	 */
	void eEliminarUsuario(String u);
	
	/**
	 * Busca un usuario de la bbdd
	 * @param u id del usuario que quieres buscar
	 * @return boolean de exito en la busqueda o fallo
	 */
	boolean eBuscarUsuario(String u);
	
	/**
	 * Actualiza la informacion de un usuario (Si existia en la bbdd)
	 * @param user usuario que quieres actualizar
	 */
	void eModificarUsuario(Usuario u);
	
	/**
	 * Busca en la bbdd el usuario y devuelve su rol (en caso de que exista en la bbdd)
	 * @param u id del usuario
	 * @param p password del usuario
	 * @return rol del usuario
	 */
	String eIniciarSesion(String u, String p);
	
	/**
	 * Dado un id devuelve el usuario con ese id (si existe en la bdd)
	 *@param u id del usuario
	 *@return user usuario asociado
	 */
	Usuario eConsultarUsuario(String u);
	
	/**
	 * Busca un cliente de la bbdd
	 * @param u id del cliente que quieres buscar
	 * @return boolean de exito en la busqueda o fallo
	 */
	public boolean eBuscarCliente(String u);
}
