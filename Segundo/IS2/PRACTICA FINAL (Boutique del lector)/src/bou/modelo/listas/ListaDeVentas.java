package bou.modelo.listas;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import bou.modelo.ventas.Venta;
import bou.ficheros.CargaDeFicheros;
import bou.modelo.libro.Libro;
import bou.modelo.observers.VentasObserver;
import bou.modelo.usuarios.Cliente;;

/**
 * Clase que representa la lista de ventas
 */
public class ListaDeVentas {

	private List<Venta> listaVentas;
	private List<VentasObserver> lista_obs;
	
	/**
	 * Constructora sin parametros
	 */
	public ListaDeVentas() {
		listaVentas = new ArrayList<Venta>();
		lista_obs = new ArrayList<VentasObserver>();
	}

	/**
	 * Metodo para añadir una venta a la lista de ventas y guardar dicha venta en el fichero de texto de las ventas.
	 * @param v
	 */
	public void añadirVenta(Venta v) {
		if(!existeVentaPorID(v.getId())){
			   listaVentas.add(v);
			   v.getCliente().addVenta(v, v.verSiPagada());;
			   guardarDatos();
			   for(VentasObserver o: lista_obs)
				   o.showInfoMsg("Lista de ventas actualizada.");
		  }
		else{
			for(VentasObserver o: lista_obs)
				o.showErrorMsg("ID de compra ya existente");
		  }
			
		removeAllObservers(lista_obs);
	}
	
	/**
	 * Booleano que return si existe una venta con un id
	 * @param id
	 * @return encontrado
	 */
	private boolean existeVentaPorID(String id) {
		boolean encontrado = false;
		int i=0;
		while(i < listaVentas.size() && !encontrado){
			if(listaVentas.get(i).getId().equalsIgnoreCase(id)){
				encontrado = true;
			}
			i++;
		}
		return encontrado;
	}

	/**
	 * Metodo para modificar la venta pasada como parametro.
	 * @param v
	 */
	public void modificarVenta(Venta v){
		int ind = buscarInd(v);
		if (ind != -1){
			listaVentas.remove(ind);
			listaVentas.add(ind, v);
			guardarDatos();	
			
			for(VentasObserver o : lista_obs)
				o.showInfoMsg("Venta modificada con éxito.");
				//Mostrar mensaje de exito -> Venta modificada
		}
	}
	
	/**
	 * Elimina una venta
	 * @param v
	 */
	public void eliminarVenta(Venta v) {
		if(buscarVentaID(v.getId())) {
			listaVentas.remove(v);
			guardarDatos();
			
			for(VentasObserver o: lista_obs) {
				o.showInfoMsg("Lista de ventas actualizada.");
			}
		}
		else {
			for(VentasObserver o: lista_obs)
				o.showErrorMsg("Venta seleccionada no existe.");
		}
		eliminarVentaObservers(lista_obs);
	}
	
	/**
	 * Busqueda de una venta pasandole como parametro un objeto venta.
	 * @param a
	 * @return
	 */
	public Venta consultarVenta(Venta a){
		boolean encontrado = false;
		int i = 0;
		while(i < listaVentas.size() && !encontrado){
			if(listaVentas.get(i).compareTo(a) == 0){
				return listaVentas.get(i);
			}
			i++;
		}	
		return null;
	}
	
	/**
	 * Busqueda por id de la venta.
	 * @param id
	 * @return
	 */
	public boolean buscarVentaID(String id){
		boolean encontrado = false;
		int i=0;
		while(i < listaVentas.size() && !encontrado){
			if(listaVentas.get(i).getId().equalsIgnoreCase(id)){
				encontrado = true;
			}
			i++;
		}
		if(!encontrado) {
			for (VentasObserver o : lista_obs)
				o.showErrorMsg("Venta buscada no existe.");
				//Mostrar mensaje de error -> Venta no encontrada en la lista
		}
		return encontrado;
	}
	
	/**
	 * Busqueda por id de la venta.
	 * @param id
	 * @return
	 */
	public Venta buscarVentaID1(String id){
		boolean encontrado = false;
		Venta vent = null;
		int i=0;
		while(i < listaVentas.size() && !encontrado){
			if(listaVentas.get(i).getId().equalsIgnoreCase(id)){
				vent = listaVentas.get(i);
				encontrado = true;
			}
			i++;
		}
		if(!encontrado) {
			for (VentasObserver o : lista_obs)
				o.showErrorMsg("Venta buscada no existe.");
				//Mostrar mensaje de error -> Venta no encontrada en la lista
		}
		return vent;
	}
	
	/**
	 * Busqueda del indice de una venta.
	 * @param v
	 * @return
	 */
	private int buscarInd(Venta v){
		int i = 0;
		int pos = -1;
		boolean e = false;
		while(i < listaVentas.size() && !e){
			if (listaVentas.get(i).equals(v)){
				e = true;
				pos = i;
			}
		}
		if(!e){
			for (VentasObserver o : lista_obs)
				o.showErrorMsg("Venta buscada no existe.");
		}
		return pos;
	}
	
	/**
	 * Guarda los datos en ventas.txt
	 */
	public void guardarDatos() {
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("ficheros/ventas.txt");
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file));
			
				for(int i=0 ; i < listaVentas.size(); i++){
					buffer.write(listaVentas.get(i).toString() + "\n");
				}
				
				buffer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Elimina los observers de Ventas
	 * @param o
	 */
	public void eliminarVentaObservers(List<VentasObserver> o){
		lista_obs.removeAll(o);
	}
	
	/**
	 * Añade un observer Venta
	 * @param o
	 */
	public void addObserver(VentasObserver o){
		lista_obs.add(o);
	}
	
	/**
	 * Carga las ventas de ventas.txt
	 */
	public void cargarVentas() {
		FileInputStream ventas;
		ventas = null;
		try {
			ventas = new FileInputStream("ficheros/ventas.txt");
			listaVentas = CargaDeFicheros.cargarVentas(ventas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Carga las ventas de un cliente
	 * @param cliente
	 */
	public void cargarVentasCliente(Cliente cliente) {
		List<Venta> lista_busq = new ArrayList<Venta>();
		cargarVentas();
		for(int i = 0; i < this.listaVentas.size(); i++){
			if(this.listaVentas.get(i).getCliente().getDNI().equals(cliente.getDNI())){
			lista_busq.add(listaVentas.get(i));
			
			}
		}
			listaVentas = lista_busq;
	}

	/**
	 * Init del menu de libro
	 * @param listaLibros
	 */
	public void initLibroComboBox(List<Libro> listaLibros) {
		for(VentasObserver o: lista_obs)
			o.initLibrosCombo(listaLibros);
		removeAllObservers(lista_obs);
	}

	/**
	 * Elimina los observadores de venta
	 * @param o
	 */
	private void removeAllObservers(List<VentasObserver> o) {
		lista_obs.removeAll(o);
	}

	/**
	 * Inite de la tabla de ventas
	 */
	public void initVentasTable() {
		for(VentasObserver o: lista_obs){
			o.initVentasTable(listaVentas);
		}
		removeAllObservers(lista_obs);
	}

	/**
	 * Filtra las ventas por id
	 * @param id
	 */
	public void filtrarVentasPorID(String id) {
		List<Venta> busqueda = new ArrayList<Venta>();
		for(Venta v : listaVentas) {
			if(v.getId().equals(id)) {
				busqueda.add(v);
			}
		}
		if(!busqueda.isEmpty()){	
			for(VentasObserver o : lista_obs) {
				o.initVentasTable(busqueda);
			}	
		}
		else{
			for(VentasObserver o: lista_obs)
				o.showErrorMsg("No se ha encontrado " + ": " + id);
		}
		removeAllObservers(lista_obs);
	}

	/**
	 * Muestra la informacion de una venta
	 * @param id
	 */
	public void verVenta(String id) {
		Venta v = buscarVentaID1(id);
		for(VentasObserver o: lista_obs)
			o.verVenta(v);
		
		removeAllObservers(lista_obs);
	}
	
	/**
	 * Filtra la lista de ventas por un cliente
	 * @param dni
	 */
	public void filtrarListaVentPorCliente(String dni) {
		List<Venta> busqueda = new ArrayList<Venta>();
		for(Venta v : listaVentas) {
			if(v.getCliente().getDNI().equals(dni)) {
				busqueda.add(v);
			}
		}
		if(!busqueda.isEmpty()){
			for(VentasObserver o : lista_obs) {
				o.initVentasTable(busqueda);
			}
		}
		else{
			for(VentasObserver o: lista_obs)
				o.showErrorMsg("No se ha encontrado al cliente" + ": " + dni);
		}
		removeAllObservers(lista_obs);
	}
}
