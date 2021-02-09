package bou.modelo.usuarios;

import java.util.ArrayList;
import java.util.List;

import bou.modelo.ventas.Venta;
import bou.modelo.reservas.Reserva;
import bou.modelo.usuarios.Persona;

/**
 * Clase que extiende de Persona y que representa a un cliente
 */
public class Cliente extends Persona{
	
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private List<Venta> ventas = new ArrayList<Venta>();
	
	/**
	 * Constructora con parámetros
	 * @param n String Nombre del cliente
	 * @param a String Apellido del cliente
	 * @param d String DNI del cliente
	 * @param m String Email del cliente
	 */
	public Cliente(String n, String a, String d, String m) {
		super(n, a, d, m);
	}
	
	/**
	 * Añade la reserva r a la lista de reservas
	 * @param r Reserva
	 */
	public void addReserva(Reserva r){
		reservas.add(r);
	}

	/**
	 * Devuelve la lista de reservas del cliente
	 * @return reservas una lista de reservas
	 */
	public List<Reserva> getReservas() {
		return reservas;
	}

	/**
	 * Añade una venta a la lista de ventas y actualiza su paga al del parámetro pagado
	 * @param venta Venta
	 * @param pagado String indica si está o no pagada la venta
	 */
	public void addVenta(Venta venta, String pagado) {
		ventas.add(venta);
		venta.actualizarPago(pagado);
	}
	
}

