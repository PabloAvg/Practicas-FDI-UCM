package bou.modelo;

import java.text.ParseException;
import java.util.Date;

import bou.modelo.libro.CategoriaEnum;
import bou.modelo.libro.Libro;
import bou.modelo.listas.ListaCliente;
import bou.modelo.listas.ListaDeReservas;
import bou.modelo.listas.ListaDeVentas;
import bou.modelo.listas.ListaLibro;
import bou.modelo.listas.ListaUsuarios;
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
 * Clase que comunica la fachada administrador con las listas de usuarios, libros, clientes, ventas y
 * reservas.
 */
public class ModeloAdministrador {
	private ListaUsuarios lista_usuarios;
	private ListaCliente lista_Clientes;
	private ListaDeReservas lista_reservas;
	private ListaLibro lista_libros;
	private ListaDeVentas lista_ventas;
	
	/**
	 * Constructor sin parámetros
	 */
	public ModeloAdministrador(){
		lista_usuarios = new ListaUsuarios();
		lista_Clientes = new ListaCliente();
		lista_reservas = new ListaDeReservas();
		lista_libros = new ListaLibro();
		lista_ventas = new ListaDeVentas();
		initDatos();
	}

	/**
	 * Inicialización de los datos
	 */
	private void initDatos() {
		lista_Clientes.cargarTodosLosClientes();
		lista_reservas.cargarTodasLasReservas();
		lista_usuarios.cargarUsuarios();
		lista_ventas.cargarVentas();
		lista_libros.cargarLibros();
	}

	// OBSERVADORES
	/**
	 * Registra los observadores de Clientes
	 * @param obs clientesobserver
	 */
	public void registerObserver(ClientesObserver obs) {
		lista_Clientes.addObserver(obs);	
	}
	
	/**
	 * Registra los observadores de reservas
	 * @param obs reservasobserver
	 */
	public void registerReservasObserver(ReservasObserver obs) {
		lista_reservas.addObserver(obs);
	}
	
	// TABLAS
	
	/**
	 * Inicializa la tabla de los Clientes
	 */
	public void cargarTodosLosClientes() {
		lista_Clientes.initClientesTable();	
	}
	
	/**
	 * Inicializa la tabla de reservas
	 */
	public void cargarTodasLasReservas() {
		lista_reservas.initReservastable();	
	}
	
	
	// Clientes
	
	/**
	 * Busca un cliente por su dni
	 * @param dni String
	 */
	public void buscarCliente(String dni) {
		lista_Clientes.filtrarClienPorDNI(dni);	
	}

	
	/**
	 * Agrega un cliente por su dni y usuario
	 * @param cliente cliente
	 * @param usuario usuario
	 */
	public void agregarCliente(Cliente cliente,Usuario usuario) {
		lista_Clientes.añadirCliente(cliente);
		if(usuario != null){
			lista_usuarios.altaUsuario(usuario);
		}	
	}

	
	/**
	 * Llama al metodo ver Cliente de listaClientes para ver un cliente
	 * @param dni String
	 */
	public void verCliente(String dni) {
		Usuario user = lista_usuarios.buscarUsuario(new Usuario(dni));
		lista_Clientes.verCliente(dni, user);	
	}

	/**
	 * Actializa los clientes de la base de datos
	 * @param Cliente Cliente 
	 * @param usuario Usuario
	 */
	public void actualizarCliente(Cliente Cliente, Usuario usuario) {
		lista_Clientes.actualizarCliente(Cliente);
		if(usuario != null){
			lista_usuarios.actualizarUsuario(usuario);
		}
	}
	
	/**
	 * Elimina un cliente segun su dni de la base de datos
	 * @param dni String
	 */
	public void eliminarCliente(String dni) {
		Usuario user = lista_usuarios.buscarUsuario(new Usuario(dni));
		lista_Clientes.bajaCliente(dni);	
		lista_usuarios.bajaUsuario(user);
	}

	/**
	 * Agrega una reserva a la bbdd
	 * @param libro Libro
	 * @param cliente Cliente
	 */
	public void agregarReserva(Libro libro, Cliente cliente) {
		Date d = new Date();
		
		Reserva r=new Reserva(libro.get_id(), libro.get_precio(), d, cliente, libro, StatusReserva.ACTIVO);
		
		lista_reservas.altaReserva(r);
	}

	/**
	 * Busca una reserva
	 * @param id String
	 */
	public void buscarReserva(String id) {
		lista_reservas.FiltrarReservaPorID(id);	
	}

	/**
	 * Llama al metodo verReserva de lista_reservas
	 * @param id String
	 */
	public void verReserva(String id) {
		lista_reservas.verReserva(id);
	}

	/**
	 * Actualiza una reserva
	 * @param id String
	 * @param dni String
	 * @param pre int
	 * @param est StatusReserva
	 * @param l Libro
	 * @param fecha Date
	 * @throws ParseException error
	 */
	public void actualizarReserva(String id, String dni, int pre, StatusReserva est, Libro l,Date fecha) throws ParseException {
		lista_reservas.actualizarReserva(new Reserva(id, pre, fecha, new Cliente(null, null, dni, null), l, est));
	}


	/**
	 * Elimina una reserva
	 * @param id String
	 */
	public void eliminarReserva(String id) {
		lista_reservas.bajaReserva(new Reserva(id));
	}

	/**
	 * Registra un observador Cliente
	 * @param obs clientesobserver
	 */
	public void registerClientesObserver(ClientesObserver obs) {
		lista_Clientes.addObserver(obs);
		
	}

	/**
	 * Registra un observador Libro
	 * @param obs librosobserver
	 */
	public void registerLibrosObserver(LibrosObserver obs) {
		lista_libros.addObserver(obs);
		
	}

	/**
	 * Llama al metodo verLibro de lista_libros
	 * @param id String
	 */
	public void verLibro(String id) {
		lista_libros.verLibro(id);
		
	}

	/**
	 * Llama al metodo initLibrosTable de lista_libros
	 */
	public void cargarTodosLosLibros() {
		lista_libros.initLibrosTable();
		
	}

	/**
	 * Da de alta un libro en la bbdd
	 * @param id String
	 * @param titulo String
	 * @param precio_alquiler int
	 * @param autor String
	 * @param categoria String
	 */
	public void altaLibro(String id, String titulo, int precio_alquiler, String autor, String categoria) {
		lista_libros.altaLibro(new Libro(id,titulo,precio_alquiler,autor,CategoriaEnum.valueOf(categoria)));
	}
	
	/**
	 * Elimina un libro de la bbdd
	 * @param id String
	 */
	public void eliminarLibro(String id) {
		lista_libros.eliminarLibro(lista_libros.buscarLibroPorID1(id));
	}

	/**
	 * Busca un libro por categoria
	 * @param cat String
	 */
	public void buscarLibroCat(String cat) {
		lista_libros.buscarLibroCat(cat);
		
	}

	/**
	 * Busca un libro por titulo
	 * @param tit String
	 */
	public void buscarLibroTit(String tit) {
		lista_libros.filtrarListaLibrosPorTitulo(tit);
		
	}

	
	/**
	 * Actualiza un libro de la bbdd
	 * @param id String
	 * @param titulo String
	 * @param precio int
	 * @param autor String
	 * @param categoria String
	 */
	public void actualizarLibro(String id, String titulo, int precio, String autor, String categoria) {
		lista_libros.eliminarLibro(lista_libros.buscarLibroPorID1(id));
		lista_libros.altaLibro(new Libro(id,titulo,precio,autor,CategoriaEnum.valueOf(categoria)));
	}

	/**
	 * Filtra la reservas segun un dni de cliente
	 * @param dni String
	 */
	public void filtrarResPorCliente(String dni) {
		lista_reservas.filtrarListaResPorCliente(dni);
	}

	/**
	 * Registra un observador de Ventas
	 * @param obs ventasobserver
	 */
	public void registerVentasObserver(VentasObserver obs) {
		lista_ventas.addObserver(obs);
		
	}

	/**
	 * Llama al metodo initVentasTable de lista_ventas
	 */
	public void cargarTodasLasVentas() {
		lista_ventas.initVentasTable();
	}

	/**
	 * Llama al metodo verVenta de lista_ventas
	 * @param id String
	 */
	public void verVenta(String id) {
		lista_ventas.verVenta(id);
	}

	/**
	 * Busca una venta segun su id
	 * @param text String
	 */
	public void buscarVenta(String text) {
		lista_ventas.filtrarVentasPorID(text);
	}

	/**
	 * Busca las ventas de un cliente
	 * @param text String
	 */
	public void buscarVentaCliente(String text) {
		lista_ventas.filtrarListaVentPorCliente(text);
		
	}

	/**
	 * Elimina una venta de la bbdd
	 * @param id String
	 */
	public void eliminarVenta(String id) {
		lista_ventas.eliminarVenta(lista_ventas.buscarVentaID1(id));
		
	}

	/**
	 * Actializa una venta de la venta de
	 * @param v venta
	 */
	public void actualizarVenta(Venta v) {
		lista_ventas.eliminarVenta(lista_ventas.buscarVentaID1(v.getId()));
		lista_ventas.añadirVenta(v);
		
	}

}
