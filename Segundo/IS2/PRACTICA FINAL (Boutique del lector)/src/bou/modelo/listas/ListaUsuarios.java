package bou.modelo.listas;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import bou.ficheros.CargaDeFicheros;
import bou.modelo.observers.UsuariosObserver;
import bou.modelo.usuarios.Usuario;


/**
 * Clase que representa la lista de usuarios que hay en la base de datos
 */
public class ListaUsuarios {

	private List<Usuario> lista_usuarios=new ArrayList<Usuario>();
	private List<UsuariosObserver> lista_obs;
	
	/**
	 * Constructora sin parámetros
	 */
	public ListaUsuarios(){
		lista_usuarios = new ArrayList<Usuario>();
		lista_obs = new ArrayList<UsuariosObserver>();
		
	}
	
	/**
	 * Carga los usuarios del fichero en una lista de usuarios
	 */
	public void cargarUsuarios(){
		FileInputStream usuarios = null;
		try {
			usuarios = new FileInputStream("ficheros/usuarios.txt");
			lista_usuarios = CargaDeFicheros.cargarUsuarios(usuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que añade un usuario a la lista de usuarios
	 * @param usuario Usuario
	 */
	public void add(Usuario usuario){
		lista_usuarios.add(usuario);
	}
	
	/**
	 * Método que añade un observador a la lista de observadores
	 * @param obs UsuariosObserver
	 */
	public void addObserver(UsuariosObserver obs) {
		lista_obs.add(obs);
	}
	
	/**
	 * Método que elimina todos los observadores de la lista de observadores pasada por parámetro
	 * @param o una lista de observadores de usuarios
	 */
	public void removeAllObservers(List<UsuariosObserver> o){
		lista_obs.removeAll(o);
	}

	/**
	 * Método para dar de alta a un usuario en la base de datos
	 * @param usuario Usuario
	 */
	public void altaUsuario(Usuario usuario) {
		cargarUsuarios();
		if(!lista_usuarios.contains(usuario)){
			lista_usuarios.add(usuario);
			guardarDatos();
		}	
	}
	
	/**
	 * Método para escribir en el archivo de usuarios los usuarios que hay en la lista de usuarios
	 */
	private void guardarDatos() {
		
		FileOutputStream file = null;
		try {
			file = new FileOutputStream("ficheros/usuarios.txt");
			BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(file));
				
				for(int i=0 ; i < lista_usuarios.size(); i++){
					buffer.write(lista_usuarios.get(i) + "\n");
				}
				
				buffer.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	/**
	 * Método para iniciar sesión
	 * @param user String
	 * @param password String
	 */
	public void login(String user, String password) {
		  cargarUsuarios();
		  Usuario usuario = lista_usuarios.get(lista_usuarios.indexOf(new Usuario(user,password)));
			
			if(usuario != null && usuario.getPass().equalsIgnoreCase(password)){
				lista_obs.get(0).openView(usuario.getRol());
			}
			else{
				for(UsuariosObserver o: lista_obs) 
					o.errorLogin("Usuario o contraseña erroneos");
			}
			
			removeAllObservers(lista_obs);
		
	}
	
	/**
	 * Método para buscar en la lista de usuarios el usuario pasado por parámetro
	 * @param usuario Usuario
	 * @return ret Usuario
	 */
	public Usuario buscarUsuario(Usuario usuario) {
		boolean encontrado = false;
		Usuario ret = null;
		int i =0;
		while(!encontrado && i < lista_usuarios.size()) {
			if(0 == usuario.getUser().compareTo(lista_usuarios.get(i).getUser())) {
				encontrado = true;
				ret = lista_usuarios.get(i);
			}
			i++;
		}
		return ret;
	}

	/**
	 * Método para eliminar el usuario con el nombre pasado por parámetro de la lista de usuarios
	 * @param user String
	 */
	public void eliminarUsuario(String user) {
		boolean encontrado = false;
		Usuario ret = null;
		int i =0;
		while(!encontrado && i < lista_usuarios.size()) {
			if(0 == user.compareTo(lista_usuarios.get(i).getUser())) {
				encontrado = true;
				ret = lista_usuarios.get(i);
			}
			i++;
		}
		this.lista_usuarios.remove(ret);
	}

	/**
	 * Método para actualizar los datos de un usuario
	 * @param usuario UsuarioS
	 */
	public void actualizarUsuario(Usuario usuario) {
		cargarUsuarios();
		int pos = buscarPosUsuario(usuario.getUser());
		if(pos != -1){
			lista_usuarios.remove(pos);
			lista_usuarios.add(pos, usuario);
		}
		guardarDatos();
	}

	/**
	 * Método para buscar la posición del usuario daado por parámetro
	 * @param user String
	 * @return ret int
	 */
	private int buscarPosUsuario(String user) {
		boolean encontrado = false;
		int ret = -1;
		int i =0;
		while(!encontrado && i < lista_usuarios.size()) {
			if(0 == user.compareTo(lista_usuarios.get(i).getUser())) {
				encontrado = true;
				ret = i;
			}
			i++;
		}
		return ret;
	}

	/**
	 * Método para dar de baja el usuario pasado por parámetro
	 * @param user Usuario
	 */
	public void bajaUsuario(Usuario user) {
		cargarUsuarios();
		Usuario usu = buscarUsuario(user);
		if(lista_usuarios.contains(usu)){
			lista_usuarios.remove(usu);
			guardarDatos();
		}
	}
}
