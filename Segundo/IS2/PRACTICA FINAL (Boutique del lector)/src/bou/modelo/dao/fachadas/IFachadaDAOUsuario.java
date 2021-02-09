package bou.modelo.dao.fachadas;

import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;

/**
 * Interfaz de Clase que comunica el S.A. con el DAO de usuario
 */
public interface IFachadaDAOUsuario {

	/**
	 * Guarda un nuevo usuario en la bbdd (si no existia previamente)
	 * @param user usuario que quieres meter en la bbdd
	 */
	void AltaUsuario(Usuario u, Cliente c);
	
	/**
	 * Elimina un usuario de la bbdd (si existia previamente)
	 * @param u id del usuario que quieres eliminar de la bbdd
	 */
	void EliminarUsuario(String u);
	
	/**
	 * Busca un usuario de la bbdd
	 * @param u id del usuario que quieres buscar
	 * @return boolean de exito en la busqueda o fallo
	 */
	boolean BuscarUsuario(String u);
	
	/**
	 * Actualiza la informacion de un usuario (Si existia en la bbdd)
	 * @param user usuario que quieres actualizar
	 */
	void ModificarUsuario(Usuario u);
	
	/**
	 * Busca en la bbdd el usuario y devuelve su rol (en caso de que exista en la bbdd)
	 * @param u id del usuario
	 * @param p password del usuario
	 * @return rol del usuario
	 */
	String IniciarSesion(String u, String p);
	
	/**
	 * Dado un id devuelve el usuario con ese id (si existe en la bdd)
	 *@param u id del usuario
	 *@return user usuario asociado
	 */
	Usuario ConsultarUsuario(String u);

}
