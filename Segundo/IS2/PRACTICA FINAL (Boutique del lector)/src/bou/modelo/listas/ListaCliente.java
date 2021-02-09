package bou.modelo.listas;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import bou.ficheros.CargaDeFicheros;
import bou.modelo.observers.ClientesObserver;
import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;;

/**
 * Clase que representa la lista de cliente
 */
public class ListaCliente {

	private List<Cliente> listaClientes;
	private List<ClientesObserver> lista_obs;
	
	/**
	 * Constructora sin parametros
	 */
	public ListaCliente() {
		listaClientes = new ArrayList<Cliente>();
		lista_obs = new ArrayList<ClientesObserver>();
		
	}
	
	/**
	 * Añade un cliente a la bbdd
	 * @param c cliente
	 */
	public void añadirCliente(Cliente c){
		cargarTodosLosClientes();
		if(!listaClientes.contains(c)){
			listaClientes.add(c);
			guardarDatos();
			
			for(ClientesObserver o: lista_obs)
				o.showInfoMsg("Cliente creado");
		}
		else{
			for(ClientesObserver o: lista_obs)
				o.showErrorMsg("Cliente ya existente");
		}
		this.borrarObservers(lista_obs);
	}
	
	/**
	 * Borra la lista de observers de cliente
	 * @param lista
	 */
	private void borrarObservers(List<ClientesObserver> lista) {
		this.lista_obs.removeAll(lista);
	}


	/**
	 * Metodo para modificar la venta pasada como parametro.
	 * @param c
	 */
	public void modificarVenta(Cliente c){
		int ind = buscarInd(c);
		if (ind != -1)
		{
			listaClientes.remove(ind);
			listaClientes.add(ind, c);
			guardarDatos();	
			
			for(ClientesObserver o : lista_obs)
				o.showInfoMsg("Cliente modificado con Ã©xito.");
				//Mostrar mensaje de exito -> Venta modificada
		}
	}
	
	/**
	 * Elimina un cliente de la bbdd
	 * @param c
	 */
	public void eliminarCliente(Cliente c) {
		if(buscarClienteID(c.getDNI())) {
			listaClientes.remove(c);
			guardarDatos();
			
			for(ClientesObserver o: lista_obs) {
				o.showInfoMsg("Cliente eliminado con exito.");
			}
		}
		else {
			for(ClientesObserver o: lista_obs)
				o.showErrorMsg("Cliente seleccionado no existe.");
		}
		eliminarClientesObservers(lista_obs);
	}
	
	/**
	 * Eliminar los observadores de la lista de observadores pasada por parámetro
	 * @param lista_obs List<ClientesObserver> una lista de observadores
	 */
	private void eliminarClientesObservers(List<ClientesObserver> lista_obs) {
		lista_obs.removeAll(lista_obs);
		
	}

	/**
	 *Busqueda de una venta pasandole como parametro un objeto Cliente
	 * @param c
	 * @return cliente
	 */
	public Cliente consultarVenta(Cliente c){
		boolean encontrado = false;
		int i = 0;
		while(i < listaClientes.size() && !encontrado){
			if(listaClientes.get(i).compareTo(c) == 0){
				return listaClientes.get(i);
			}
			i++;
		}	
		return c;
	}
	
	/**
	 * Busqueda por id de la venta.
	 * @param id
	 * @return boolean encontrado
	 */
	public boolean buscarClienteID(String id){
		boolean encontrado = false;
		int i=0;
		while(i < listaClientes.size() && !encontrado){
			if(listaClientes.get(i).getDNI().equalsIgnoreCase(id)){
				encontrado = true;
			}
			i++;
		}
		if(!encontrado) {
			for (ClientesObserver o : lista_obs)
				o.showErrorMsg("Cliente buscado no existe.");
				//Mostrar mensaje de error -> Venta no encontrada en la lista
		}
		return encontrado;
	}
	
	/**
	 * Busqueda por id de la venta.
	 * @param id
	 * @return cliente
	 */
	public Cliente buscarClienteID1(String id){
		boolean encontrado = false;
		int i=0;
		Cliente aux = null;
		while(i < listaClientes.size() && !encontrado){
			if(listaClientes.get(i).getDNI().equalsIgnoreCase(id)){
				encontrado = true;
				aux=listaClientes.get(i);
			}
			i++;
		}
		if(!encontrado) {
			for (ClientesObserver o : lista_obs)
				o.showErrorMsg("Cliente buscado no existe.");
				//Mostrar mensaje de error -> Venta no encontrada en la lista
		}
		return aux;
	}
	
	
	/**
	 *Busqueda del indice de una venta.
	 * @param c
	 * @return
	 */
	private int buscarInd(Cliente c)
	{
		int i = 0;
		int pos = -1;
		boolean e = false;
		while(i < listaClientes.size() && !e){
			if (listaClientes.get(i).equals(c)){
				e = true;
				pos = i;
			}
		}
		if(!e){
			for (ClientesObserver o : lista_obs)
				o.showErrorMsg("Cliente buscado no existe.");
		}
		return pos;
	}
	
	/**
	 * Guarda los datos en clientes.txt
	 */
	public void guardarDatos() {
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("ficheros/clientes.txt");
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file));
			
				for(int i=0 ; i < listaClientes.size(); i++){
					buffer.write(listaClientes.get(i).toString() + "\n");
				}
				
				buffer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return listaClientes
	 */
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	/**
	 * Añade un observer Cliente
	 * @param o
	 */
	public void addObserver(ClientesObserver o){
		lista_obs.add(o);
	}

	/**
	 * Carga los datos de clientes.txt
	 */
	public void cargarTodosLosClientes() {
		FileInputStream clientes = null;
		try {
			clientes = new FileInputStream("ficheros/clientes.txt");
			listaClientes = CargaDeFicheros.cargarClientes(clientes);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Muestra la informacion de un Cliente
	 * @param dni
	 * @param user
	 */
	public void verCliente(String dni, Usuario user) {
		Cliente c = buscarClienteID1(dni);
		
		for(ClientesObserver o: lista_obs){
			o.verCliente(c, user);
		}
		removeAllObservers(lista_obs);
	}


	/**
	 * Init de la tabla de clientes
	 */
	public void initClientesTable() {
		for(ClientesObserver o: lista_obs){
			o.initClientesTable(listaClientes);
		}
		removeAllObservers(lista_obs);
		
	}

	/**
	 * Elimina todos los observers de Clientes
	 * @param o
	 */
	public void removeAllObservers(List<ClientesObserver> o){
		lista_obs.removeAll(o);
	}

	/**
	 * Actualiza un cliente de la bbdd
	 * @param cliente
	 */
	public void actualizarCliente(Cliente cliente) {
		int pos = buscarPosCliente(cliente.getDNI());
		if(pos != -1){
			listaClientes.remove(pos);
			listaClientes.add(pos, cliente);
			guardarDatos();
			
			for(ClientesObserver o: lista_obs){
				o.showInfoMsg("Cliente actualizado satisfactoriamente.");
				o.initClientesTable(listaClientes);
			}
		}
		else{
			for(ClientesObserver o: lista_obs)
				o.showErrorMsg("No se encuentra el cliente en la base de datos.");
		}
		removeAllObservers(lista_obs);
	}


	/**
	 * Busca en indice de un cliente
	 * @param dni
	 * @return
	 */
	private int buscarPosCliente(String dni) {
		boolean ent = false;
		int ret = -1;
		int i = 0;
		while(!ent && i < listaClientes.size()) {
			if(0 == listaClientes.get(i).getDNI().compareTo(dni)) {
				ent = true;
				ret = i;
			}
			i++;
		}
		return ret;
	}

	/**
	 * Elimina un cliente de la bbdd
	 * @param dni
	 */
	public void bajaCliente(String dni) {
		Cliente c = buscarClienteID1(dni);
		if(c != null){
			listaClientes.remove(c);
			guardarDatos();
			
			for(ClientesObserver o: lista_obs){
				o.showInfoMsg("Cliente eliminado correctamente");
				o.initClientesTable(listaClientes);
			}
		}
		else{
			for(ClientesObserver o: lista_obs)
				o.showErrorMsg("No se ha encontrado el cliente");
		}
		removeAllObservers(lista_obs);
	}

	/**
	 * Filtra los clientes por dni
	 * @param dni
	 */
	public void filtrarClienPorDNI(String dni) {
		List<Cliente> lista_busq = new ArrayList<Cliente>();
		Cliente c = buscarClienteID1(dni);
		if(c != null){
			lista_busq.add(c);
			
			for(ClientesObserver o: lista_obs){
				o.initClientesTable(lista_busq);
			}
		}
		else{
			for(ClientesObserver o: lista_obs){
				o.showErrorMsg("No existe el cliente");
			}
		}	
		removeAllObservers(lista_obs);
	}
	
}
