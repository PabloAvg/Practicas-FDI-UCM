package bou.modelo.usuarios;

/**
 * Clase abstracta que representa a una persona con sus atributos nombre, apellido, dni, email
 */
public abstract class Persona {

	protected String nombre;
	protected String apellidos;
	protected String dni;
	protected String email;
	
	/**
	 * Constructora con parámetros
	 * @param n String Nombre de la persona
	 * @param a String Apellido de la persona
	 * @param d String DNI de la persona
	 * @param m String Email de la persona
	 */
	public Persona(String n,String a,String d,String m){
		nombre = n;
		apellidos = a;
		dni = d;
		email = m;
	}
	
	/**
	 * Constructora con un parámetro
	 * @param d String DNI de la persona
	 */
	public Persona(String d){
		dni = d;
	}
	
	/**
	 * Compara el dni de la persona con el dni de o
	 * @param o Object
	 * @return true si es el mismo y false si no son el mismo
	 */
	public boolean equals(Object o){
		return dni.equalsIgnoreCase(((Persona)o).dni);
	}
	
	/**
	 * Compara el dni de la persona con el dni de la Persona del parámetro persona
	 * @param persona Persona
	 * @return true si es el mismo y false si no son el mismo
	 */
	public int compareTo(Persona persona){
		return dni.compareTo(persona.dni);
	}
	
	/**
	 * Getter del nombre de la persona
	 * @return nombre String
	 */
	public String getNombre(){
		return nombre;
	}
	
	/**
	 * Getter del apellido de la persona
	 * @return apellidos String
	 */
	public String getApellidos(){
		return apellidos;
	}
	
	/**
	 * Getter del DNI de la persona
	 * @return dni String
	 */
	public String getDNI(){
		return dni;
	}
	
	/**
	 * Getter del email de la persona
	 * @return email String
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * Método para representar una persona como un string
	 */
	public String toString(){
		return nombre + " " + apellidos.replace(" ", "_") + " " + dni + " " + email;
	}
	
}
