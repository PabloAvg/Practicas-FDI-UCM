package bou.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bou.gui.GUI;
import bou.gui.admin.ClienteAltaPanel;
import bou.gui.admin.ClienteVerListaPanel;
import bou.modelo.controllers.AdminController;
import bou.modelo.libro.gui.LibroVerListaPanel;
import bou.modelo.libro.gui.VentanaNuevoLibro;
import bou.modelo.reservas.gui.ReservaVerPanelAdmin;
import bou.modelo.ventas.gui.VentaVerListaPanelAdmin;




/**
 * Clase que extiende de GUI y crea la ventana del administrador.
 */
public class AdminGui extends GUI implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AdminController admin_control;
	private JPanel panel_actual;
	private JMenuBar jmb;
	private JMenu emp, res,lib ,log_out;
	private JMenuItem alta, lista, editar_res, alta_libro, lista_libros,lista_ventas;
	private ClienteAltaPanel cli_alta_panel;
	private ClienteVerListaPanel cli_lista_panel;
	private ReservaVerPanelAdmin res_lista_panel;
	private LibroVerListaPanel lib_lista_panel;
	private VentanaNuevoLibro libro_alta_panel;
	private VentaVerListaPanelAdmin venta_lista;
	
	private JLabel img_label;
	private ImageIcon imagen;
	
	/**
	 * Constructors con parámetro
	 * @param c AdminController
	 */
	public AdminGui(AdminController c) {
		super("Administrador");
		admin_control = c;
		iniGUIAdmin();
	}
	
	/**
	 * Método que nicializa los componentes de la ventana
	 */
	private void iniGUIAdmin() {
		panelPrincipal = this.getContentPane();
		panelPrincipal.setLayout(new FlowLayout());
		
		jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		emp = new JMenu("Clientes");
			
			
		alta = new JMenuItem("Dar de alta");
		alta.setName("alta");
		alta.addActionListener(this);
		
		
		lista = new JMenuItem("Ver lista");
		lista.setName("lista");
		lista.addActionListener(this);
		
		
		emp.add(alta);
		emp.add(lista);
		
		jmb.add(emp);
		
		res = new JMenu("Reservas");
		
		editar_res = new JMenuItem("Ver lista");
		editar_res.setName("Editar reserva");
		editar_res.addActionListener(this);
		
		res.add(editar_res);

		
		jmb.add(res);
		
		lib= new JMenu("Libros");
		
		alta_libro=new JMenuItem("Dar de alta");
		alta_libro.setName("alta libro");
		alta_libro.addActionListener(this);
		
		lista_libros=new JMenuItem("Ver lista");
		lista_libros.setName("Editar libro");
		lista_libros.addActionListener(this);
		
		lib.add(alta_libro);
		lib.add(lista_libros);
		
		jmb.add(lib);
		lista_ventas=new JMenuItem("Ver ventas");
		lista_ventas.setName("ver ventas");
		lista_ventas.addActionListener(this);
		
		jmb.add(lista_ventas);
	
		log_out = new JMenu("Salir");
		log_out.setName("logout");
		log_out.addMouseListener(this);
		
		
		jmb.add(log_out);
		

		panel_actual = new JPanel();
		
		imagen = new ImageIcon(getClass().getResource("fondo.jpg"));
		img_label = new JLabel(imagen);
		img_label.setSize(5,5);
		panel_actual.add(img_label);
		
		panelPrincipal.add(panel_actual);
		
		
		setSize(600, 490);
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
	
	/**
	 * Método para que se abra la ventana de la acción que desea realizar el cliente y se cambie a ella
	 * @param p JPanel
	 */
	public void changePanelActual(JPanel p){	
		panelPrincipal.remove(panel_actual);
		setVisible(false);
		panel_actual = p;
		panelPrincipal.add(panel_actual);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem j = (JMenuItem)e.getSource();
		String txt = j.getName();
		
		if(txt.equals("alta")){
			cli_alta_panel = new ClienteAltaPanel(admin_control);
			changePanelActual(cli_alta_panel);	
		}
		else if(txt.equals("lista")){
			cli_lista_panel = new ClienteVerListaPanel(admin_control);
			changePanelActual(cli_lista_panel);
		}
		else if(txt.equals("Editar reserva")){	
			res_lista_panel = new ReservaVerPanelAdmin(admin_control);
			changePanelActual(res_lista_panel);
		}else if(txt.equals("alta libro")){	
			libro_alta_panel = new VentanaNuevoLibro(admin_control);
			changePanelActual(libro_alta_panel);
		}else if(txt.equals("Editar libro")){	
			lib_lista_panel = new LibroVerListaPanel(admin_control);
			changePanelActual(lib_lista_panel);
		}else if(txt.equals("ver ventas")){	
		venta_lista = new VentaVerListaPanelAdmin(admin_control);
		changePanelActual(venta_lista);
			}
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == log_out){
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"¿Desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
			    this.dispose();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
