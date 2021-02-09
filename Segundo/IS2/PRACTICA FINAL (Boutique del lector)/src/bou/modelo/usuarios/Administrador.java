package bou.modelo.usuarios;


import bou.modelo.usuarios.Persona;

/**
 * Clase que extiende de Persona y representa a un administrador 
 */
public class Administrador extends Persona{
	
	/**
	 * Constructora con parámetros
	 * @param n String nombre del administrador
	 * @param a String apellido del administrador
	 * @param d String DNI del administrador
	 * @param m String email del administrador
	 */
	public Administrador(String n, String a, String d, String m) {
		super(n, a, d, m);
		
	}
	
}