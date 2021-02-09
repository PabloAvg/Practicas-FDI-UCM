package bou.modelo;

import java.util.Date;

import bou.modelo.libro.Libro;
import bou.modelo.listas.ListaCliente;
import bou.modelo.listas.ListaDeReservas;
import bou.modelo.listas.ListaDeVentas;
import bou.modelo.listas.ListaLibro;
import bou.modelo.observers.ReservasObserver;
import bou.modelo.observers.VentasObserver;
import bou.modelo.reservas.Reserva;
import bou.modelo.reservas.StatusReserva;
import bou.modelo.usuarios.Cliente;
import bou.modelo.ventas.Venta;
import bou.modelo.ventas.VentaPagada;

/**
 * Clase que comunica la fachada cliente con las listas de clientes, reservas, libros y ventas
 */
public class ModeloCliente{
	
	private ListaCliente lista_clientes;
	private Cliente cliente;
	private ListaDeReservas lista_reservas;
	private ListaLibro lista_libros;
	private ListaDeVentas lista_ventas;
	private String cliente_dni;
	
	/**
	 * Constructora con parametros
	 * @param c
	 */
	public ModeloCliente(String c){
		lista_clientes = new ListaCliente();
		lista_reservas = new ListaDeReservas();
		lista_libros = new ListaLibro();
		lista_ventas = new ListaDeVentas();
		cliente_dni = c;
		initDatos();
	}
	
	/**
	 * Llama a los metodos de las listas para cargar los datos de la bbdd
	 */
	public void initDatos(){
		lista_libros.cargarLibros();
		lista_clientes.cargarTodosLosClientes();
		lista_reservas.cargarReservas();
		cliente = lista_clientes.buscarClienteID1(cliente_dni);
		lista_ventas.cargarVentasCliente(cliente);
		lista_reservas.cargarReservasCliente(cliente);
	}
	
	/**
	 * Registra los observadores de clientes
	 * @param obs
	 */
	public void registerReservasObserver(ReservasObserver obs) {
		lista_reservas.addObserver(obs);
	}


	/**
	 * Carga todas las reservas de un cliente
	 */
	public void cargarTodasMisReservas(){
		lista_reservas.initReservastable();
	}

	/**
	 * Ver la información de una reserva
	 * @param id
	 */
	public void verReserva(String id) {
		lista_reservas.verReserva(id);
	}

	/**
	 * Busca una reserva segun su id
	 * @param id
	 */
	public void buscarReserva(String id) {
		lista_reservas.FiltrarReservaPorID(id);
	}

	/**
	 * Agrega una reserva a la bdd
	 * @param id
	 * @param prec
	 * @param select
	 * @param date
	 * @param activo
	 */
	public void agregarReserva(String id, int prec, Libro select, Date date, StatusReserva activo) {
		lista_reservas.cargarReservas();
		lista_reservas.altaReserva(new Reserva(id, prec, date, cliente, select, activo));
		lista_reservas.cargarReservasCliente(cliente);
	}

	/**
	 * Inicia la combo box de Libro
	 */
	public void initLibroComboBox() {
		lista_reservas.initLibroComboBox(lista_libros.getListaLibros());
	}

	/**
	 * Registra un observador de Venta
	 * @param obs
	 */
	public void registerVentasObserver(VentasObserver obs) {
		lista_ventas.addObserver(obs);
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
		lista_ventas.cargarVentas();
		lista_ventas.añadirVenta(new Venta(id, l, prec, cliente, date, sinPagar));
		lista_ventas.cargarVentasCliente(cliente);
		lista_libros.eliminarLibro(lista_libros.buscarLibroPorNombre(l.get_titulo()));
		lista_libros.cargarLibros();
	}

	/**
	 * Inicia la combo box de los libros vendidos
	 */
	public void initLibroComboBoxVentas() {
		lista_ventas.initLibroComboBox(lista_libros.getListaLibros());
	}

	/**
	 * Carga las ventas de la Tabla de ventas
	 */
	public void cargarTodasLasVentas() {
		lista_ventas.initVentasTable();
	}

	/**
	 * Busca una venta segun su id
	 * @param id
	 */
	public void buscarVenta(String id) {
		lista_ventas.filtrarVentasPorID(id);
		
	}

	/**
	 * Muestra la informacion de una venta
	 * @param id
	 */
	public void verVenta(String id) {
		lista_ventas.verVenta(id);
	}

	/**
	 * Hace el pago de una venta
	 * @param id String
	 * @param prec int 
	 * @param l libro
	 * @param date Date
	 * @param sinPagar ventaPagada enum que indica si la venta está pagada o no
	 */
	public void pagarVenta(String id, int prec, Libro l, Date date, VentaPagada sinPagar) {
		lista_ventas.cargarVentas();
		lista_ventas.eliminarVenta(lista_ventas.buscarVentaID1(id));
		lista_ventas.añadirVenta(new Venta(id, l, prec, cliente, date, sinPagar));
		lista_ventas.cargarVentasCliente(cliente);
	}
	
	
}
