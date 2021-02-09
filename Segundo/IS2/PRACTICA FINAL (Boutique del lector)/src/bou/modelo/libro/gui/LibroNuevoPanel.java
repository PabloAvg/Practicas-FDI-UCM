package bou.modelo.libro.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import bou.modelo.controllers.AdminController;
import bou.modelo.libro.CategoriaEnum;
import bou.modelo.libro.Libro;
import bou.modelo.observers.LibrosObserver;

/**
 * Clase que representa el JPanel de un libro con su informacion
 */
public class LibroNuevoPanel extends JPanel implements ActionListener, LibrosObserver {

	private static final long serialVersionUID = 1L;
	private AdminController admin_control;
	private JLabel id, titulo, precio_alquiler, autor, categoria;
	private JTextField tf_id, tf_titulo, tf_precio, tf_autor;
	private JComboBox<CategoriaEnum> cb_categoria;
	private JButton bt_vaciar, bt_agregar;
	
	
	/**
	 * Constructor con parámetro
	 * @param c
	 */
	public LibroNuevoPanel(AdminController c){
		super();
		admin_control = c;
		admin_control.registerLibrosObserver(this);
		initLibroNuevoPanel();
	}
	
	
	/**
	 * Inicializa todos los componentes
	 */
	private void initLibroNuevoPanel() {
		setLayout(new BorderLayout());
		TitledBorder titulob = BorderFactory.createTitledBorder("label nuevo libro");
		setBorder(titulob);
		
		JPanel datos = new JPanel();
		datos.setLayout(new GridLayout(8,2));
		
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		
		id = new JLabel("Id (ej: L02)");
		tf_id = new JTextField(15);
		
		titulo = new JLabel();
		tf_titulo = new JTextField(15);
		
		precio_alquiler = new JLabel();
		tf_precio = new JTextField(10);
		
		autor = new JLabel();
		tf_autor = new JTextField(10);
		
		categoria = new JLabel();
		cb_categoria = new JComboBox<CategoriaEnum>();
		cb_categoria.setBounds(10, 10, 80, 20);
		cb_categoria.addItem(CategoriaEnum.ARTE);
		cb_categoria.addItem(CategoriaEnum.CIENCIA);
		cb_categoria.addItem(CategoriaEnum.HISTORIA);
		cb_categoria.addItem(CategoriaEnum.INFANTIL);
		cb_categoria.addItem(CategoriaEnum.TERROR);
		cb_categoria.addActionListener(this);
		
		
		bt_vaciar = new JButton("Vaciar");
		bt_vaciar.setName("vaciar");
		bt_vaciar.addActionListener(this);
		
		bt_agregar = new JButton("Agregar");
		bt_agregar.setName("agregar");
		bt_agregar.addActionListener(this);
		
		datos.add(id);
		datos.add(tf_id);
		datos.add(titulo);
		datos.add(tf_titulo);
		datos.add(precio_alquiler);
		datos.add(tf_precio);
		datos.add(autor);
		datos.add(tf_autor);
		datos.add(categoria);
		datos.add(cb_categoria);
		
		botones.add(bt_vaciar);
		botones.add(bt_agregar);
		
		add(datos, BorderLayout.NORTH);
		add(botones, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("vaciar")){
			vaciarCampos();
		}
		else if(txt.equals("agregar")){
			
			try{
				String id = tf_id.getText();
				String titulo = tf_titulo.getText();
				int precio = Integer.parseInt(tf_precio.getText());
				String autor = tf_autor.getText();
				String categoria = String.valueOf(cb_categoria.getSelectedItem());
				
				if(!id.isEmpty() && !titulo.isEmpty() && !autor.isEmpty() && !categoria.isEmpty()){
					 admin_control.registerLibrosObserver(this);
					 admin_control.altaLibro(id, titulo, precio, autor, categoria);
					 vaciarCampos();
				}
				else{
					showInfoMsg("Id vacio");
				}
			}
			catch(NumberFormatException ex){
				showInfoMsg("Número erroneo");
			}
		}
		
	}
	
	/**
	 * Vacia los campos del panel
	 */
	private void vaciarCampos(){
		tf_id.setText("");
		tf_autor.setText("");
		tf_titulo.setText("");
		tf_precio.setText("");
	}
	
	/**
	 * Inicializa el combo box de las categorias
	 */
	@Override
	public void initCategoriasCombo(List<CategoriaEnum> _categorias) {
		for(int i = 0; i < _categorias.size(); i++){
			cb_categoria.addItem(_categorias.get(i));
		}
	}

	@Override
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Atención", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Error",JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void verLibro(Libro lib) {}


	@Override
	public void initLibrosTable(List<Libro> lista_libros) {}

}
