package bou.modelo.controllers;

import java.text.ParseException;
import java.util.Date;

import bou.modelo.fachadas.AdminFachada;
import bou.modelo.listas.ListaCliente;
import bou.modelo.listas.ListaLibro;
import bou.modelo.observers.ClientesObserver;
import bou.modelo.observers.LibrosObserver;
import bou.modelo.observers.ReservasObserver;
import bou.modelo.observers.VentasObserver;
import bou.modelo.reservas.Reserva;
import bou.modelo.reservas.StatusReserva;
import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;
import bou.modelo.ventas.Venta;

/**
 * Clase que comunica la vista administrador con la fachada administrador.
 */
public class AdminController {
	private ListaLibro libros=new ListaLibro();
	private ListaCliente clientes= new ListaCliente();
	private AdminFachada admin_fachada;
	
	/**
	 * Constructora con parametros
	 * @param admin_fachada
	 */
	public AdminController(AdminFachada admin_fachada) {
		this.admin_fachada = admin_fachada;
	}

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
	 * Actualiza una venta
	 * @param v
	 */
	public void actualizarVenta(Venta v){
		admin_fachada.actualizarVenta(v);
	}

	/**
	 * Agrega un cliente
	 * @param cliente
	 * @param usuario
	 */
	public void agregarCliente(Cliente cliente, Usuario usuario) {
		admin_fachada.agregarCliente(cliente, usuario);
	}

	/**
	 * Registra un observer de Cliente
	 * @param obs
	 */
	public void registerClientesObserver(ClientesObserver obs) {
		admin_fachada.registerClientesObserver(obs);
	}

	/**
	 * Busca un cliente
	 * @param text
	 */
	public void buscarCliente(String text) {
		admin_fachada.buscarCliente(text);
		
	}

	/**
	 * Elimina un cliente
	 * @param dni
	 */
	public void eliminarCliente(String dni) {
		admin_fachada.eliminarCliente(dni);
		
	}

	/**
	 * Actualiza un cliente
	 * @param cliente
	 * @param usuario
	 */
	public void actualizarCliente(Cliente cliente, Usuario usuario) {
		admin_fachada.actualizarCliente(cliente, usuario);
	}

	/**
	 * Elimina una reserva de un cliente
	 * @param cliente
	 * @param reserva
	 */
	public void eliminarReservaCliente(Cliente cliente, Reserva reserva) {
		cliente.getReservas().remove(reserva);
		
	}
	
	/**
	 * Carga la tabla de clientes
	 */
	public void cargarTodosLosClientes() {
		admin_fachada.cargarTodosLosClientes();
	}
	
	/**
	 * get clientes
	 * @return lista de clientes
	 */
	public ListaCliente getClientes() {
		return clientes;
	}

	/**
	 * Actualiza una reserva de la bbdd
	 * @param id
	 * @param dni
	 * @param pre
	 * @param est
	 * @param titulo
	 * @param fecha
	 * @throws ParseException
	 */
	public void actualizarReserva(String id, String dni, int pre, StatusReserva est, String titulo, Date fecha) throws ParseException {
		admin_fachada.actualizarReserva(id, dni, pre, est, titulo, fecha);
		
	}

	/**
	 * Registra un observer de una reserva
	 * @param obs
	 */
	public void registerReservasObserver(ReservasObserver obs) {
		admin_fachada.registerReservasObserver(obs);
		
	}

	/**
	 * Elimina una reserva
	 * @param id
	 */
	public void eliminarReservaButton(String id) {
		admin_fachada.eliminarReserva(id);
	}

	/**
	 * Da la informacion de una reserva
	 * @param id
	 */
	public void verReserva(String id) {
		admin_fachada.verReserva(id);
		
	}

	/**
	 * Filtra las reservas por cliente
	 * @param dni
	 */
	public void filtrarResPorCliente(String dni) {
		admin_fachada.filtrarResPorCliente(dni);
	}

	/**
	 * Busca una reserva
	 * @param id
	 */
	public void buscarReserva(String id) {
		admin_fachada.buscarReserva(id);
	}

	/**
	 * Carga la tabla de reservas
	 */
	public void cargarTodasLasReservas() {
		admin_fachada.cargarTodasLasReservas();
		
	}

	/**
	 * Da la informacion de un cliente
	 * @param dni
	 */
	public void verCliente(String dni) {
		admin_fachada.verCliente(dni);
		
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

	/**
	 * Carga todas las ventas en la tabla
	 */
	public void cargarTodasLasVentas() {
		admin_fachada.cargarTodasLasVentas();
		
	}

	/**
	 * Da la informacion de una venta
	 * @param id
	 */
	public void verVenta(String id) {
		admin_fachada.verVenta(id);
		
	}

	/**
	 * Busca una venta
	 * @param text
	 */
	public void buscarVenta(String text) {
		admin_fachada.buscarVenta(text);
		
	}

	/**
	 * Busca las ventas de un cliente
	 * @param text
	 */
	public void buscarVentaCliente(String text) {
		admin_fachada.buscarVentaCliente(text);
		
	}

	/**
	 * Elimina una venta
	 * @param id
	 */
	public void eliminarVentaButton(String id) {
		admin_fachada.eliminarVenta(id);
		
	}

}
