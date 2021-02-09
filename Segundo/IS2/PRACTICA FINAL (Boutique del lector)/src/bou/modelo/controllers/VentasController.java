package bou.modelo.controllers;

import bou.modelo.observers.VentasObserver;
import bou.modelo.ventas.Venta;

public class VentasController {

	
	
	
	
	

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

}
