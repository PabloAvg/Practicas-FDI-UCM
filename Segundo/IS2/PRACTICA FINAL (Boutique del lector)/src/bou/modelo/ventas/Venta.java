package bou.modelo.ventas;

import java.util.Date;

import bou.modelo.libro.Libro;
import bou.modelo.usuarios.Cliente;

/**
 * Clase que representa a una venta con sus atributos id, libro, precio, cliente, fechaVenta y pago (Si está pagada o no).
 */
public class Venta implements Comparable<Venta> {
	
	private String id;
	private Libro libro;
	private int precio;
	private Cliente cliente;
	private Date fechaVenta;
	private VentaPagada pago;
	
	/**
	 * Constuctora con parámetros.
	 * @param identificador String
	 * @param librovendido Libro
	 * @param precioVenta int
	 * @param cliente Cliente
	 * @param fechaVenta Date
	 * @param pago VentaPagada enumerado que indica si está pagada o no
	 */
	public Venta(String identificador, Libro librovendido, int precioVenta, Cliente cliente, Date fechaVenta, VentaPagada pago ) {
		this.id = identificador;
		this.setLibro(librovendido);
		this.precio = precioVenta;
		this.cliente = cliente;
		this.fechaVenta = fechaVenta;
		this.pago = pago;
	}
	
	/**
	 * Constructora con parámetro id
	 * @param _id String
	 */
	public Venta(String _id) {
		this.id = _id;
	}
	
	/**
	 * Getter para devolver el id de la venta
	 * @return id String
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Getter para devolver el precio de la venta
	 * @return precio int
	 */
	public int getPrecio() {
		return this.precio;
	}
	
	/**
	 * Getter para devolver el cliente de la venta
	 * @return cliente Cliente
	 */
	public Cliente getCliente() {
		return this.cliente;
	}
	
	/**
	 * Getter para devolver la fecha de la venta
	 * @return fechaVenta Date
	 */
	public Date getDate() {
		return this.fechaVenta;
	}

	@Override
	public int compareTo(Venta o) {
		return this.id.compareToIgnoreCase(o.id);
	}
	
	/**
	 * Método para escribir la venta como un String
	 */
	@SuppressWarnings("deprecation")
	public String toString(){
		return id + " " + libro.get_titulo() + " " + precio + " " + cliente.getDNI() + " " +
				fechaVenta.getDate() + " " + fechaVenta.getMonth() + " "  + fechaVenta.getYear() + " "
				+ pago.toString();
	}

	/**
	 * Método para actualizar el pago de la venta (Si está pagada o no)
	 * @param pagado VentaPagada Enumerado que indica si la venta está o no pagada
	 */
	public void actualizarPago(String pagado) {
		pago = VentaPagada.valueOf(pagado);
	}
	
	/**
	 * Método para devolver si la venta está o no pagada.
	 * @return pago VentaPagada Enumerado que indica si la venta está o no pagada
	 */
	public String verSiPagada() {
		return String.valueOf(pago);
	}

	/**
	 * Getter para devolver el libro de la venta
	 * @return libro Libro
	 */
	public Libro getLibro() {
		return libro;
	}

	/**
	 * Setter para asignar un libro a la venta
	 * @param libro Libro
	 */
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
	
}

