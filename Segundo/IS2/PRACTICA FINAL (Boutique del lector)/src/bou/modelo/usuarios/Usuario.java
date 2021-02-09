package bou.modelo.usuarios;

/**
 * Clase que representa a un usuario con sus componentes dni, pass (contraseña) y rol
 */
public class Usuario {

		private String dni;
		private String pass;
		private String rol;
		
		/**
		 * Constructora con tres parámetros
		 * @param usuario String
		 * @param password String
		 * @param r String rol del usuario
		 */
		public Usuario(String usuario, String password, String r){
			dni = usuario;
			pass = password;
			rol = r;
		}
		
		/**
		 * Constructora con dos parámetros
		 * @param usuario String
		 * @param password String
		 */
		public Usuario(String usuario, String password){
			dni = usuario;
			pass = password;
		}
		
		/**
		 * Constructora con un parámetro
		 * @param usuario String
		 */
		public Usuario(String usuario){
			dni = usuario;
		}
		
		/**
		 * Getter que devuelve el DNI del usuario
		 * @return dni String
		 */
		public String getUser(){
			return dni;
		}
		
		/**
		 * Getter que devuelve la contraseña del usuario
		 * @return pass String
		 */
		public String getPass(){
			return pass;
		}
		
		/**
		 * Getter que devuelve el rol del usuario
		 * @return rol String
		 */
		public String getRol(){
			return rol;
		}

		/**
		 * @return true si el user y el pass coinciden con el de otro usuario false en caso contrario
		 */
		public boolean equals (Object o){	
			return (this.getClass()== o.getClass())&&(dni.equalsIgnoreCase(((Usuario)o).dni) &&
					(pass.equals(((Usuario)o).pass)));
		}
		
		/**
		 * Método para representar a un usuario como un string
		 */
		public String toString(){
			return dni + " " + pass + " " + rol;
		}
		
	}


