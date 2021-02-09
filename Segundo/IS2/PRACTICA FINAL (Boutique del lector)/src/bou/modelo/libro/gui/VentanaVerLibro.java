package bou.modelo.libro.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import bou.gui.GUI;
import bou.modelo.controllers.AdminController;
import bou.modelo.libro.CategoriaEnum;
import bou.modelo.libro.Libro;
import bou.modelo.observers.LibrosObserver;

/**
 * Clase que extiende a GUI. Crea una ventana con la información detallada de un libro.
 */
public class VentanaVerLibro extends GUI implements ActionListener, LibrosObserver, ItemListener{

	private static final long serialVersionUID = 1L;
	private AdminController admin_control;
	private JPanel panel;
	private Libro libro;
	private JLabel lb_id, lb_titulo, lb_autor, lb_precio_alquiler, lb_categoria;
	private JTextField tf_id, tf_titulo, tf_precio_alquiler, tf_autor;
	private JComboBox<CategoriaEnum> cb_categoria;
	private JButton bt_editar, bt_guardar;
	
	
	/**
	 * Constructora con parametros
	 * @param l
	 * @param c
	 */
	public VentanaVerLibro(Libro l, AdminController c) {
		super("Libro");
		admin_control = c;
		libro = l;
		initVentanaVerLibro();
	}
	
	/**
	 * Init de los componentes
	 */
	private void initVentanaVerLibro() {
		panelPrincipal = this.getContentPane();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Datos Libro");
		panel.setBorder(titulo);
		
		JPanel datos = new JPanel();
		datos.setLayout(new GridLayout(9,2));
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		
		lb_id = new JLabel("Id: ");
		tf_id = new JTextField(15);
		tf_id.setText(libro.get_id());
		tf_id.setEditable(false);
		
		lb_titulo = new JLabel("Titulo: ");
		tf_titulo = new JTextField(10);
		tf_titulo.setText(libro.get_titulo());
		tf_titulo.setEditable(false);
		
		lb_precio_alquiler = new JLabel("Precio: ");
		tf_precio_alquiler = new JTextField(10);
		tf_precio_alquiler.setText(String.valueOf(libro.get_precio()));
		tf_precio_alquiler.setEditable(false);
		
		lb_autor = new JLabel("Autor: ");
		tf_autor = new JTextField(10);
		tf_autor.setText(String.valueOf(libro.get_autor()));
		tf_autor.setEditable(false);
		
		lb_categoria = new JLabel("Categoria");
		cb_categoria = new JComboBox<CategoriaEnum>();
		cb_categoria.setBounds(10, 10, 80, 20);
		cb_categoria.addItem(CategoriaEnum.ARTE);
		cb_categoria.addItem(CategoriaEnum.CIENCIA);
		cb_categoria.addItem(CategoriaEnum.HISTORIA);
		cb_categoria.addItem(CategoriaEnum.INFANTIL);
		cb_categoria.addItem(CategoriaEnum.TERROR);
		cb_categoria.setSelectedItem(libro.get_categoria());
		cb_categoria.addItemListener(this);
		cb_categoria.setEnabled(false);
		
		
		bt_editar = new JButton("Editar");
		bt_editar.setName("editar");
		if(admin_control == null){
			bt_editar.setVisible(false);
		}
		bt_editar.addActionListener(this);
		
		bt_guardar = new JButton("Guardar");
		bt_guardar.setName("guardar");
		bt_guardar.setVisible(false);
		bt_guardar.addActionListener(this);
		

		datos.add(lb_id);
		datos.add(tf_id);
		
		datos.add(lb_titulo);
		datos.add(tf_titulo);
		
		datos.add(lb_precio_alquiler);
		datos.add(tf_precio_alquiler);
		
		datos.add(lb_autor);
		datos.add(tf_autor);
		
		datos.add(lb_categoria);
		datos.add(cb_categoria);
		
		botones.add(bt_editar);
		botones.add(bt_guardar);

		panel.add(datos, BorderLayout.NORTH);
		panel.add(botones, BorderLayout.SOUTH);

		panelPrincipal.add(panel);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
			
	}
	
	@Override
	public void initCategoriasCombo(List<CategoriaEnum> _categorias) {}

	/**
	 * Muestra un mesaje
	 */
	@Override
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Muestra un error
	 */
	@Override
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Error",JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void initLibrosTable(List<Libro> lista_libros) {}

	/**
	 * Activa los campos
	 */
	private void activarCampos(){
		bt_guardar.setVisible(true);
		tf_titulo.setEditable(true);
		tf_precio_alquiler.setEditable(true);
		tf_autor.setEnabled(true);
		cb_categoria.setEnabled(true);
		bt_editar.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("editar")){
			activarCampos();
		}
		else if(txt.equals("guardar")){
			
			try{
				String id = tf_id.getText();
				String titulo = tf_titulo.getText();
				String autor = tf_autor.getText();
				int precio = Integer.parseInt(tf_precio_alquiler.getText());
				String categoria = String.valueOf(cb_categoria.getSelectedItem());
				
				if(!id.isEmpty() && !titulo.isEmpty() && !autor.isEmpty() && !categoria.isEmpty()){
					int seleccionada = JOptionPane.showConfirmDialog
							(this,"Realmente dsese actualizar este libro?", "Actualizar",JOptionPane.YES_NO_OPTION);
					if(seleccionada == JOptionPane.YES_OPTION){
					  admin_control.registerLibrosObserver(this);
					  admin_control.actualizarLibro(id, titulo, precio, autor, categoria);
				    }
				}
				else{
					showInfoMsg("Se deben rellenar todos los campos.");
					
				}
			}
			catch(NumberFormatException ex){
				showInfoMsg(" Las letras no son válidas en el campo precio.");
			}
		}
	}

	@Override
	public void verLibro(Libro lib) {}

	@Override
	public void itemStateChanged(ItemEvent e) {}

}