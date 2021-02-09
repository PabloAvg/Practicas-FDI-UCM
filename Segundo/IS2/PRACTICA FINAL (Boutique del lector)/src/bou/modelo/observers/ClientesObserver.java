package bou.modelo.observers;

import java.util.List;

import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;

/**
 * Interfaz observadora de clientes
 */
public interface ClientesObserver {

	/**
	 * Ventana que muestra un mensaje de 'Información' pasado por parámetro
	 * @param string String
	 */
	void showInfoMsg(String string);

	/**
	 * Ventana que muestra un mensaje de 'Error' pasado por parámetro
	 * @param string String
	 */
	void showErrorMsg(String string);

	/**
	 * Inicializa la tabla con la lista de clientes pasada por parámetro
	 * @param lista_clientes una lista de clientes
	 */
	void initClientesTable(List<Cliente> lista_clientes);

	/**
	 * Ventana que permite ver la información de un cliente
	 * @param c un cliente
	 * @param u un usuario
	 */
	void verCliente(Cliente c, Usuario u);

	void eliminarReservaJList();

}
