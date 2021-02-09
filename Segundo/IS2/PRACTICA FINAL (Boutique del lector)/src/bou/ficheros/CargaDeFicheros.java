package bou.ficheros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bou.modelo.libro.CategoriaEnum;
import bou.modelo.libro.Libro;
import bou.modelo.reservas.Reserva;
import bou.modelo.reservas.StatusReserva;
import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;
import bou.modelo.ventas.Venta;
import bou.modelo.ventas.VentaPagada;

public class CargaDeFicheros {
	
	/**
	 * Carga los datos del fichero 'usuarios.txt'
	 * @param file archivo de carga de usuarios
	 * @return lista_usuarios devuelve una lista de usuarios
	 * @throws IOException excepcion de Input o Output
	 */
	public static List<Usuario> cargarUsuarios(InputStream file) throws IOException{
	
		List<Usuario>  lista_usuarios = new ArrayList<Usuario>();
		
		String line = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(file));
		 
				 while((line = br.readLine()) != null){
					 String[] w = line.split(" ");
						 if(w.length == 3){
							 lista_usuarios.add(new Usuario(w[0], w[1], w[2]));
						 }
						 
					 }
					 			 	
				 br.close();
		
		return lista_usuarios;
		
	}
	
	
	/**
	 * Carga los datos del fichero 'clientes.txt'
	 * @param file fichero de carga de clientes
	 * @return lista de clientes de la aplicacion
	 * @throws IOException excepcion de Input o Output
	 */
	public static List<Cliente> cargarClientes(InputStream file) throws IOException{
		List<Cliente> lista_clientes = new ArrayList<Cliente>();
		
		 String line = "";
		 BufferedReader br = new BufferedReader(new InputStreamReader(file));
		 
				 while((line = br.readLine()) != null){
					 String[] w = line.split(" ");
						 if(w.length == 4){
							 w[1] = w[1].replace("_"," ");
							 lista_clientes.add(new Cliente(w[0],w[1],w[2],w[3])); 
						 }
						 
					 }
					 			 	
				 br.close();
		
		
		return lista_clientes;
	}
	
	/**
	 * Carga los datos del fichero 'ventas.txt'
	 * @param file fichero de ventas
	 * @return una lista de ventas
	 * @throws IOException io exception
	 */
	@SuppressWarnings("deprecation")
	public static List<Venta> cargarVentas(InputStream file) throws IOException {
	
		List<Venta> lista_ventas = new ArrayList<Venta>();
		
		String id;
		Venta vent;;
		int precio;
		String dniCli;
		Cliente cli;
		Date fecha;
		String tituloLibro;
		Libro libro;
		VentaPagada pagada;
		 
		String line = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(file));
		 
				 while((line = br.readLine()) != null){
					 String[] w = line.split(" ");
						 if(w.length == 8){
							dniCli = w[3];
							cli = new Cliente(null, null, dniCli, null);
							
							fecha = new Date();
							fecha.setDate(Integer.parseInt(w[4]));
							fecha.setMonth(Integer.parseInt(w[5]));
							fecha.setYear(Integer.parseInt(w[6]));
		
							precio = Integer.parseInt(w[2]);
							
							tituloLibro = w[1];
							libro = new Libro(tituloLibro);
							
							pagada = VentaPagada.valueOf(w[7]);
							
							id = w[0];
							
							vent = new Venta(id, libro, precio, cli, fecha, pagada);
							lista_ventas.add(vent);
						 }
						 
					 }
					 			 	
				 br.close();
		
		return lista_ventas;
	}
	
	/**
	 * Carga los datos del fichero 'libros.txt'
	 * @param file fichero de libros
	 * @return una lista de libros
	 * @throws IOException io exception
	 */

	public static List<Libro> cargarLibros(InputStream file) throws IOException {
	
		List<Libro> lista_libros = new ArrayList<Libro>();
		
		String id;
		String titulo;
		String autor;
		Libro libro;
		int precio;
		CategoriaEnum categoria;
		 
		String line = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(file));
		 
				 while((line = br.readLine()) != null){
					 String[] w = line.split(" ");
						 if(w.length == 5){
							
							precio = Integer.parseInt(w[2]);
							
							titulo = w[1]/*.replace("_"," ")*/;
							
							autor = w[3];
							
							categoria = CategoriaEnum.valueOf(w[4]);
							
							id = w[0];
							
							libro = new Libro(id, titulo, precio, autor, categoria);
							lista_libros.add(libro);
						 }
						 
					 }
					 			 	
				 br.close();
		
		return lista_libros;
	}
	
}
