package bou.modelo.libro;

/**
 * Clase que representa un libro con sus atributos id, titulo, precio de aquiler, Autor y 
 * categoria.
 */
public class Libro implements Comparable<Libro>{
	
	private String _id;
	private String _titulo;
	private int _precio;
	private String _autor;
	private CategoriaEnum _categoria;
	
	/**
	 * Constructor con parámetros
	 * @param identificador String
	 * @param titulo String
	 * @param precio int
	 * @param autor String
	 * @param categoria (EnumCategoria)
	 */
	public Libro(String identificador, String titulo, int precio, String autor, CategoriaEnum categoria){
		_id = identificador;
		_titulo = titulo;
		_precio = precio;
		_autor = autor;
		_categoria = categoria;
	}
	
	/**
	 * Constructor con parámetros
	 * @param titulo
	 */
	public Libro(String titulo){
		_titulo = titulo;
	}

	/**
	 * @return id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * Set id
	 * @param _id
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return titulo
	 */
	public String get_titulo() {
		return _titulo;
	}

	/**
	 * Set titulo
	 * @param _titulo
	 */
	public void set_titulo(String _titulo) {
		this._titulo = _titulo;
	}

	/**
	 * @return precio
	 */
	public int get_precio() {
		return _precio;
	}

	/**
	 * Set precio
	 * @param _precio
	 */
	public void set_precio(int _precio) {
		this._precio = _precio;
	}

	/**
	 * @return autor
	 */
	public String get_autor() {
		return _autor;
	}

	/**
	 * Set autor
	 * @param _autor
	 */
	public void set_autor(String _autor) {
		this._autor = _autor;
	}

	/**
	 * @return categoria
	 */
	public CategoriaEnum get_categoria() {
		return _categoria;
	}

	/**
	 * Set categoria
	 * @param _categoria
	 */
	public void set_categoria(CategoriaEnum _categoria) {
		this._categoria = _categoria;
	}
	
	/**
	 * To string de libro
	 */
	public String toString(){
		return _id + " " + _titulo + " " + _precio + " " + _autor + " " + _categoria;
	}

	/**
	 * Compara libros por titulo
	 */
	@Override
	public int compareTo(Libro o) {
		 return this.get_titulo().compareTo(o.get_titulo());
	}

}
