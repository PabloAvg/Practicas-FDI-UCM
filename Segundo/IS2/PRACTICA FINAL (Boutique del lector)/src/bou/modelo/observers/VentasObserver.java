package bou.modelo.observers;

import java.util.List;

import bou.modelo.libro.Libro;
import bou.modelo.ventas.Venta;

/**
 * Interfaz observadora de ventas
 */
public interface VentasObserver {
	
	/**
	 * Ventana que muestra el mensaje de información pasado por parámetro
	 * @param msg String
	 */
	public void showInfoMsg(String msg);
	
	/**
	 * Ventana que muestra el mensaje de error pasado por parámetro 
	 * @param msg String
	 */
	public void showErrorMsg(String msg);

	/**
	 * Inicializa un comboBox con una lista de libros 
	 * @param _libros una lista de libros
	 */
	void initLibrosCombo(List<Libro> _libros);

	/**
	 * Inicializa la tabla con la lista de ventas
	 * @param listaVentas una lista de ventas
	 */
	public void initVentasTable(List<Venta> listaVentas);
	
	/**
	 * Ventana que muestra la información de una venta
	 * @param v Venta
	 */
	public void verVenta(Venta v);
}
