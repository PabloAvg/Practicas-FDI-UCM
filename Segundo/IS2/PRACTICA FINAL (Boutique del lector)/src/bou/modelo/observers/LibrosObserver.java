package bou.modelo.observers;

import java.util.List;

import bou.modelo.libro.CategoriaEnum;
import bou.modelo.libro.Libro;

/**
 * Interfaz observadora de libros
 */
public interface LibrosObserver {
	
	/**
	 * Inicializa un comboBox con las categor�as de los libros
	 * @param _categorias una lista de categor�as de libro
	 */
	public void initCategoriasCombo(List<CategoriaEnum> _categorias);
	
	/**
	 * Ventana que muestra el mensaje de informaci�n pasado por par�metro
	 * @param msg String
	 */
	public void showInfoMsg(String msg);
	
	/**
	 * Ventana que muestra el mensaje de error pasado por par�metro
	 * @param msg String
	 */
	public void showErrorMsg(String msg);
	
	/**
	 * Inicializa la tabla con la lista de libros
	 * @param lista_libros una lista de libros
	 */
	public void initLibrosTable(List<Libro> lista_libros);
	
	/**
	 * Ventana que muestra la informaci�n de un libro
	 * @param lib Libro
	 */
	public void verLibro(Libro lib);
	
}
