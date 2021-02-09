package bou.modelo.listas;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import bou.ficheros.CargaDeFicheros;
import bou.modelo.libro.CategoriaEnum;
import bou.modelo.libro.Libro;
import bou.modelo.observers.LibrosObserver;

/**
 * Clase que representa la lista de ventas
 */
public class ListaLibro {
	private List<Libro> lista_libros;
	private List<LibrosObserver> lista_observer;
	
	/**
	 * Constructora sin parametros
	 */
	public ListaLibro () {
		lista_libros = new ArrayList<Libro>();
		lista_observer = new ArrayList <LibrosObserver>();
	}
	
	/**
	 * Añade un observer a Libro
	 * @param obs
	 */
	public void addObserver (LibrosObserver obs){
		lista_observer.add(obs);
	}
	
	/**
	 * Elimina los observers de Libro
	 * @param lista
	 */
	public void removeAllObservers(List<LibrosObserver> lista) {
		lista_observer.removeAll(lista);
	}
	
	/**
	 * Da de alta un libro
	 * @param lib
	 */
	public void altaLibro(Libro lib) {
		if (!buscarLibroPorID(lib.get_id())) {	
			    lista_libros.add(lib);
			    guardarDatos();
				
				for(LibrosObserver o: lista_observer) {
					o.showInfoMsg("La base de datos de libros ha sido actualizada.");	
					o.initLibrosTable(lista_libros);
				}
		}
		
		else{
			for(LibrosObserver o: lista_observer)
				o.showErrorMsg("Libro ya existente");
		}
	
		removeAllObservers(lista_observer);	
	
	}
	
	/**
	 * Elimina el libro de la bbdd
	 * @param lib
	 */
	public void eliminarLibro(Libro lib){
		Libro libro = buscarLibroPorID1(lib.get_id());
		if(libro != null){
			lista_libros.remove(libro);
			guardarDatos();
			
			for(LibrosObserver o: lista_observer){
				o.showInfoMsg("La base de datos de libros ha sido actualizada.");
			    o.initLibrosTable(lista_libros);
			}
		}
		else{
			for(LibrosObserver o: lista_observer)
				o.showErrorMsg("Este libro no existe.");
		}
		
		removeAllObservers(lista_observer);
	}
	
	/**
	 * Busca un libro
	 * @param lib
	 * @return
	 */
	public Libro buscarLibro(Libro lib){
		return buscarLibroPorID1(lib.get_id());
	}
	
	/**
	 * Busca un libro por id
	 * @param id
	 * @return bool si o no
	 */
	public boolean buscarLibroPorID(String id){
		boolean enc = false;
		int i=0;
		while(i < lista_libros.size() && !enc){
			if(lista_libros.get(i).get_id().equalsIgnoreCase(id)){
				enc = true;
			}
			i++;
		}
		return enc;
	}
	
	/**
	 * Busca un libro por id
	 * @param id
	 * @return bool si o no
	 */
	public Libro buscarLibroPorID1(String id){
		boolean enc = false;
		Libro lib = null;
		int i=0;
		while(i < lista_libros.size() && !enc){
			if(lista_libros.get(i).get_id().equalsIgnoreCase(id)){
				enc = true;
				lib = lista_libros.get(i);
			}
			i++;
		}
		return lib;
	}
	
	/**
	 * Busca un libro por nombre
	 * @param name
	 * @return
	 */
	public Libro buscarLibroPorNombre(String name){
		boolean enc = false;
		Libro lib = null;
		int i=0;
		while(i < lista_libros.size() && !enc){
			if(lista_libros.get(i).get_titulo().equalsIgnoreCase(name)){
				enc = true;
				lib = lista_libros.get(i);
			}
			i++;
		}
		return lib;
	}
	
	/**
	 * Carga los libros de libros.txt
	 */
	public void cargarLibros() {
		FileInputStream libros;
		libros = null;
		try {
			libros = new FileInputStream("ficheros/libros.txt");
			lista_libros = CargaDeFicheros.cargarLibros(libros);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Guarda los datos en libros.txt 
	 */
	public void guardarDatos() {
		FileOutputStream arch = null;
		try {
			arch = new FileOutputStream("ficheros/libros.txt");
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(arch));
			
				for(int i=0 ; i < lista_libros.size(); i++){
					buffer.write(lista_libros.get(i).toString() + "\n");
				}
				
				buffer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Carga todos los libros
	 */
	public void cargarTodosLosLibros() {
		for(LibrosObserver o: lista_observer){
			o.initLibrosTable(lista_libros);
		}
		removeAllObservers(lista_observer);
		
	}
	
	/**
	 * Busca un libro
	 * @param id
	 */
	public void buscarLibro(String id) {
		List<Libro> busqueda = new ArrayList<Libro>();
		Libro lib = buscarLibro(buscarLibroPorID1(id));
		if(lib != null){
			busqueda.add(lib);
			
			for(LibrosObserver o: lista_observer)
				o.initLibrosTable( busqueda);
		}
		else{
			for(LibrosObserver o: lista_observer)
				o.showErrorMsg("No existe libro con ese ID.");
		}
		
		
		
		removeAllObservers(lista_observer);
	}
	
	/**
	 * Filtra los libros por titulo
	 * @param titulo
	 */
	public void filtrarListaLibrosPorTitulo(String titulo) {
		List<Libro> busqueda = new ArrayList<Libro>();
		
		for(Libro l : lista_libros) {
			if(l.get_titulo().equals(titulo)) {
				busqueda.add(l);
			}
		}
		
		if(!busqueda.isEmpty()){
			
			for(LibrosObserver o : lista_observer) {
				o.initLibrosTable(busqueda);
			}
			
		}
		else{
			for(LibrosObserver o: lista_observer)
				o.showErrorMsg("No se ha encontrado " + ": " + titulo);
		}
		
		
		
		removeAllObservers(lista_observer);
	}
	
	/**
	 * Mustra la informacion de un libro
	 * @param id
	 */
	public void verLibro(String id) {
		Libro lib = buscarLibroPorID1(id);
		for(LibrosObserver o: lista_observer)
			o.verLibro(lib);
		removeAllObservers(lista_observer);
	}

	/**
	 * Init de la tabla de libros
	 */
	public void initLibrosTable() {

		for(LibrosObserver o: lista_observer){
			o.initLibrosTable(lista_libros);
		}
		removeAllObservers(lista_observer);
		
		
	}

	/**
	 * Busca un libro por su categoria
	 * @param cat
	 */
	public void buscarLibroCat(String cat) {
		
		boolean catCorrecta = false;
		
		for(CategoriaEnum c : CategoriaEnum.values()) {
			if(String.valueOf(c).equals(cat))
				catCorrecta = true;
		}
		
		if(!catCorrecta)
			for(LibrosObserver o: lista_observer)
				o.showErrorMsg("No se ha encontrado la categoría");
		else {
			List<Libro> li = new ArrayList<Libro>();
			for(Libro l : lista_libros) {
				if(l.get_categoria() == CategoriaEnum.valueOf(cat)) {
					li.add(l);
				}
			}
			
			for(LibrosObserver o : lista_observer) {
				o.initLibrosTable(li);
			}
			
		}
		
	}
	
	/**
	 * @return lista libros
	 */
	public List<Libro> getListaLibros() {
		return this.lista_libros;
	}
	
}
