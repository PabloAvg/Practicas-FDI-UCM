package bou.modelo.controllers;

import java.util.Date;

import bou.modelo.fachadas.ClienteFachada;
import bou.modelo.libro.Libro;
import bou.modelo.observers.ClientesObserver;
import bou.modelo.observers.ReservasObserver;
import bou.modelo.observers.VentasObserver;
import bou.modelo.reservas.Reserva;
import bou.modelo.reservas.StatusReserva;
import bou.modelo.ventas.VentaPagada;

/**
 * Clase que comunica la vista cliente con la fachada cliente.
 */
public class ClienteController {

	ClienteFachada fachada_cliente;
	
	/**
	 * Constructora con parametro
	 * @param fachada_cliente
	 */
	public ClienteController(ClienteFachada fachada_cliente) {
		this.fachada_cliente=fachada_cliente;
	}

	public void pagarLibro(String id, String string) {}

	/**
	 * Registra un observador de Reserva
	 * @param obs
	 */
	public void registerReservaObserver(ReservasObserver obs) {
		fachada_cliente.registerReservaObserver(obs);
	}
	
	/**
	 * Muestra la informacion de una reserva
	 * @param id
	 */
	public void verReserva(String id) {
		fachada_cliente.verReserva(id);
	}

	public void cargarTodosLosClientes() {}

	public void agregarReservaCliente(String dni, Reserva reserva) {}

	public void buscarCliente(String text) {}

	public void cargarDniEnComboBox() {}

	/**
	 * Agrega una reserva a la bbdd
	 * @param id
	 * @param prec
	 * @param select
	 * @param date
	 * @param activo
	 */
	public void agregarReserva(String id, int prec, Libro select, Date date, StatusReserva activo) {
		fachada_cliente.agregarReserva(id, prec, select, date, activo);
	}

	/**
	 * Inicializa la combo box de libro
	 */
	public void initLibroComboBox() {
		fachada_cliente.initLibroComboBox();
		
	}

	public void registerClientesObserver(ClientesObserver obs) {}

	public void verVentanaclientes(String id) {}

	public void filtrarResPorFecha(String dia) {}

	/**
	 * Busca una reserva
	 * @param id
	 */
	public void buscarReserva(String id) {
		fachada_cliente.buscarReserva(id);
	}

	/**
	 * Carga la reservas en la tabla
	 */
	public void cargarTodasLasReservas() {
		fachada_cliente.cargarTodasLasReservas();
	}

	/**
	 * Registra un observador de Ventas
	 * @param obs
	 */
	public void registerVentasObserver(VentasObserver obs) {
		fachada_cliente.registerVentasObserver(obs);
		
	}

	/**
	 * Agrega una venta a la bbdd
	 * @param id
	 * @param prec
	 * @param l
	 * @param date
	 * @param sinPagar
	 */
	public void agregarVenta(String id, int prec, Libro l, Date date, VentaPagada sinPagar) {
		fachada_cliente.agregarVentaNueva(id, prec, l, date, sinPagar);
	}

	/**
	 * Inicializa el combo box de los libros en venta
	 */
	public void initLibroComboBoxVentas() {
		fachada_cliente.initLibroComboBoxVentas();
		
	}

	/**
	 * Carga todas las ventas en la tabla
	 */
	public void cargarTodasLasVentas() {
		fachada_cliente.cargarTodasLasVentas();
	}

	/**
	 * Busca una venta
	 * @param id
	 */
	public void buscarVenta(String id) {
		fachada_cliente.buscarVenta(id);
		
	}

	/**
	 * Muestra la informacion de una venta
	 * @param id
	 */
	public void verVenta(String id) {
		fachada_cliente.verVenta(id);
	}
	
	/**
	 * Actualiza una venta en la bbdd
	 * @param id
	 * @param libro
	 * @param precio
	 * @param fecha
	 * @param status
	 */
	public void actualizarVenta(String id, Libro libro, int precio, Date fecha, VentaPagada status) {
		fachada_cliente.agregarVenta(id, precio, libro, fecha, status);
	}

}
