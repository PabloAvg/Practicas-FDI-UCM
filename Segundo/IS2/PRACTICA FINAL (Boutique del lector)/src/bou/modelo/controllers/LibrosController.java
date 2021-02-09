package bou.modelo.controllers;

import bou.modelo.listas.ListaLibro;
import bou.modelo.observers.LibrosObserver;
import bou.modelo.observers.VentasObserver;

public class LibrosController {
	private ListaLibro libros=new ListaLibro();
	
	public LibrosController() {}
	
	
	/**
	 * Registra un observador de Libro
	 * @param obs
	 */
	public void registerLibrosObserver(LibrosObserver obs) {
		admin_fachada.registerLibrosObserver(obs);
		
	}
	

	/**
	 * Da de alta un libro en la bbdd
	 * @param id
	 * @param titulo
	 * @param precio_alquiler
	 * @param autor
	 * @param categoria
	 */
	public void altaLibro(String id, String titulo, int precio_alquiler, String autor, String categoria) {
		admin_fachada.altaLibro(id, titulo, precio_alquiler, autor, categoria);
	}
	
	/**
	 * Inicializa la tabla con los libros
	 */
	public void cargarTodosLosLibros() {
		admin_fachada.cargarTodosLosLibros();
		
	}
	/**
	 * Filtra los libros por titulo
	 * @param text
	 */
	public void buscarLibro(String text) {
		libros.filtrarListaLibrosPorTitulo(text);
		
	}
	
	/**
	 * Elimina un libro segun su id
	 * @param id
	 */
	public void eliminarLibroButton(String id) {
		admin_fachada.eliminarLibro(id);
		
	}
	/**
	 * Actualiza un libro de la bbdd
	 * @param id
	 * @param titulo
	 * @param precio
	 * @param autor
	 * @param categoria
	 */
	public void actualizarLibro(String id, String titulo, int precio, String autor, String categoria) {
		admin_fachada.actualizarLibro(id, titulo, precio, autor, categoria);
	}
	/**
	 * Registra un observer de ventas
	 * @param obs
	 */
	public void registerVentasObserver(VentasObserver obs) {
		admin_fachada.registerVentasObserver(obs);
	}
	/**
	 * Da la informacion de un libro
	 * @param id
	 */
	public void verLibro(String id) {
		admin_fachada.verLibro(id);
		
	}
	
	/**
	 * Busca un libro por su categoria
	 * @param cat
	 */
	public void buscarLibroCat(String cat) {
		admin_fachada.buscarLibroCat(cat);
	}
	/**
	 * Busca un libro por su titulo
	 * @param tit
	 */
	public void buscarLibroTit(String tit) {
		admin_fachada.buscarLibroTit(tit);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
