package bou.modelo.ventas.gui;

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
import bou.modelo.libro.Libro;
import bou.modelo.observers.VentasObserver;
import bou.modelo.ventas.Venta;

/**
 * Clase que extiende de JPanel y que implemneta ActionListener, MouseListener y VentasObserver, crea la ventana con todas las ventas en la base de datos.
 */
public class VentaVerListaPanelAdmin extends JPanel implements ActionListener, MouseListener, VentasObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AdminController _ctrl;
	private MyModelTableVen modelo;
	
	private JTable jt;
	private ButtonGroup grupo;
	private JRadioButton rb_id,rb_cliente, rb_sinFiltro;
	private JButton bt_buscar, bt_ver,bt_borrar;
	private JTextField texto;
	private String id;
	
	/**
	 * Constructora con parámetro
	 * @param user_control AdminController
	 */
	public VentaVerListaPanelAdmin (UsuariosController user_control) {
		super();
		_ctrl = user_control;
		initVentaEditarPanel();
	}

	/**
	 * Método para inicializar los componentes de la ventana.
	 */
	private void initVentaEditarPanel() {
		setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Lista de ventas");
		setBorder(titulo);
		
		_ctrl.registerVentasObserver(this);

		modelo = new MyModelTableVen();

		jt = new JTable(modelo);
		jt.addMouseListener(this);
		jt.setPreferredScrollableViewportSize(new Dimension(350,130));
		JScrollPane scrollPane = new JScrollPane(jt);

		_ctrl.cargarTodasLasVentas();
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		
		JPanel panelBoton = new JPanel();
		panelBoton.setLayout(new FlowLayout());
		
		bt_ver = new JButton("ver");
		bt_ver.setName("ver");
		bt_ver.setEnabled(false);
		bt_ver.addActionListener(this);
		
		panelBoton.add(bt_ver);
		
		bt_borrar= new JButton("Borrar");
		bt_borrar.setName("Borrar");
		bt_borrar.setEnabled(false);
		bt_borrar.addActionListener(this);
		
		panelBoton.add(bt_borrar);
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.setLayout(new FlowLayout());
		
		JLabel filtro = new JLabel("Filtrar por:");
		grupo = new ButtonGroup();
		rb_id = new JRadioButton("ID",false);
		rb_cliente=new JRadioButton("Cliente",false);
		rb_sinFiltro = new JRadioButton("Sin filtro", true);
		rb_sinFiltro.addMouseListener(this);
		
		grupo.add(rb_id);
		grupo.add(rb_cliente);
		grupo.add(rb_sinFiltro);
		
		
		panelFiltro.add(filtro);
		panelFiltro.add(rb_id);
		panelFiltro.add(rb_cliente);
		panelFiltro.add(rb_sinFiltro);
		
		jp1.add(panelBoton);
		jp1.add(panelFiltro);
		
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
	
	@Override
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == jt){
	        int row = jt.rowAtPoint(e.getPoint());
	        if (row >= 0 && jt.isEnabled())
	        {
	        	bt_ver.setEnabled(true);
	        	bt_borrar.setEnabled(true);
	        	id = modelo.getValueAt(row,0).toString();
	        }
		 }
	 else if(e.getSource() == rb_sinFiltro){
			 texto.setText("");
			 _ctrl.registerVentasObserver(this);
			 _ctrl.cargarTodasLasVentas();
		 }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("buscar")){
			if(rb_id.isSelected()){
				_ctrl.registerVentasObserver(this);
				_ctrl.buscarVenta(texto.getText());
			}else if(rb_cliente.isSelected()){
				_ctrl.registerVentasObserver(this);
				_ctrl.buscarVentaCliente(texto.getText());
			}
		}else if(txt.equals("Borrar")){
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"¿Realmente desea eliminar esta venta?", "Eliminar",JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
				_ctrl.registerVentasObserver(this);
				_ctrl.eliminarVentaButton(id);	
				_ctrl.cargarTodasLasVentas();
				
			}
		}
		else if(txt.equals("ver")){
			_ctrl.registerVentasObserver(this);
			_ctrl.verVenta(id);
		}
	}

	@Override
	public void initLibrosCombo(List<Libro> _libros) {
		
	}

	@Override
	public void initVentasTable(List<Venta> listaVentas) {
		modelo.initVentasTable(listaVentas);
		
	}

	@Override
	public void verVenta(Venta v) {
		new VentanaVerVentaAdmin(v, _ctrl);
	}
}
