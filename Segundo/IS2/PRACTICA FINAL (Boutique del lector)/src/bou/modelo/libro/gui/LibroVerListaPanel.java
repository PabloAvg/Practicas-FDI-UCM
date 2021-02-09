package bou.modelo.libro.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import bou.modelo.controllers.AdminController;
import bou.modelo.controllers.UsuariosController;
import bou.modelo.libro.CategoriaEnum;
import bou.modelo.libro.Libro;
import bou.modelo.observers.LibrosObserver;

/**
 * Clase que extiende a JPanel. Crea el panel de la lista de libros.
 */
@SuppressWarnings("serial")
public class LibroVerListaPanel extends JPanel implements ActionListener, MouseListener, LibrosObserver{

	private AdminController admin_control;
	private MyModelTableLib modelo;
	private JTable jt;
	private JLabel filtro;
	private JTextField texto;
	private JRadioButton rb_categoria, rb_sinFiltro, rb_titulo;
	private ButtonGroup grupo;
	private JButton bt_buscar, bt_ver, bt_eliminar;
	private String id;
	
	/**
	 * Constructor con parámetro
	 * @param user_control
	 */
	public LibroVerListaPanel(UsuariosController user_control){
		super();
		admin_control = user_control;
		initLibroEditarPanel();
	}
	
	/**
	 * Init de los componentes
	 */
	private void initLibroEditarPanel(){
        setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Lista de libros");
		setBorder(titulo);
		
		admin_control.registerLibrosObserver(this);
		
		modelo = new MyModelTableLib();
		
		jt = new JTable(modelo);
		jt.addMouseListener(this);
		jt.setPreferredScrollableViewportSize(new Dimension(350,130));
		JScrollPane scrollPane = new JScrollPane(jt);
		admin_control.cargarTodosLosLibros();
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		
		JPanel panelboton = new JPanel();
		panelboton.setLayout(new FlowLayout());
		
		bt_ver = new JButton("ver");
		bt_ver.setName("ver");
		bt_ver.setEnabled(false);
		bt_ver.addActionListener(this);
		
		
		bt_eliminar = new JButton("eliminar");
		bt_eliminar.setName("eliminar");
		bt_eliminar.setEnabled(false);
		bt_eliminar.addActionListener(this);
		
		panelboton.add(bt_ver);
		panelboton.add(bt_eliminar);
		
		JPanel panelfiltro = new JPanel();
		panelfiltro.setLayout(new FlowLayout());
		
		filtro = new JLabel("Filtrar por:");
		grupo = new ButtonGroup();
		rb_categoria = new JRadioButton("Categoria",false);
		rb_sinFiltro = new JRadioButton("Sin flitro",true);
		rb_titulo = new JRadioButton("Título", false);
		rb_sinFiltro.addMouseListener(this);
		
		grupo.add(rb_categoria);
		grupo.add(rb_titulo);
		grupo.add(rb_sinFiltro);
		
		panelfiltro.add(filtro);
		panelfiltro.add(rb_categoria);
		panelfiltro.add(rb_titulo);
		panelfiltro.add(rb_sinFiltro);
		
		jp1.add(panelboton);
		jp1.add(panelfiltro);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		
		texto = new JTextField(10);
		
		bt_buscar = new JButton("Buscar");
		bt_buscar.setName("buscar");
		bt_buscar.addActionListener(this);
		
		jp2.add(texto);
		jp2.add(bt_buscar);
		
		add(scrollPane,BorderLayout.NORTH);
		add(jp1,BorderLayout.CENTER);
		add(jp2,BorderLayout.SOUTH);
        }
	
	/**
	 * Muestra la tabla de libros
	 */
	@Override
	public void initLibrosTable( List<Libro> lista_libros) {
		modelo.initLibrosTable(lista_libros);
	}
	
	/**
	 * Init del combo de categorias
	 */
	@Override
	public void initCategoriasCombo(List<CategoriaEnum> _categorias) {
		modelo.initCategoriasCombo(_categorias);
	}

	/**
	 * Mensaje de informacion
	 */
	@Override
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Aviso" ,JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Mensaje de error
	 */
	@Override
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Error",JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Muestra la informacion de un libro
	 */
	@Override
	public void verLibro(Libro lib) {
		new VentanaVerLibro(lib, admin_control);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		 if(e.getSource() == jt){
		        int row = jt.rowAtPoint(e.getPoint());
		        if (row >= 0 && jt.isEnabled())
		        {
		        	bt_ver.setEnabled(true);
		        	bt_eliminar.setEnabled(true);
		        	id = modelo.getValueAt(row,0).toString();
		        }
			 }
		 else if(e.getSource() == rb_sinFiltro){
				 texto.setText("");
				 admin_control.registerLibrosObserver(this);
				 admin_control.cargarTodosLosLibros();
			 }
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("buscar")){
			if(rb_categoria.isSelected()){
				admin_control.registerLibrosObserver(this);
				admin_control.buscarLibroCat(texto.getText());
			}
			if(rb_titulo.isSelected()) {
				admin_control.registerLibrosObserver(this);
				admin_control.buscarLibroTit(texto.getText());
			}
		}
		else if(txt.equals("ver")){
			admin_control.registerLibrosObserver(this);
			admin_control.verLibro(id);
		}
		else if(txt.equals("eliminar")){
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"Realmente desea eliminar este libro?", "Eliminar",JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
				admin_control.registerLibrosObserver(this);
				admin_control.eliminarLibroButton(id);	
			}
			
		}
	}

}
