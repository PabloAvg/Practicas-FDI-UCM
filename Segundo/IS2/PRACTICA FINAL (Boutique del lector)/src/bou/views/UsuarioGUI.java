package bou.views;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bou.gui.GUI;
import bou.gui.admin.ClienteAltaPanel;
import bou.gui.admin.ClienteVerListaPanel;
import bou.modelo.controllers.AdminController;
import bou.modelo.controllers.ClienteController;
import bou.modelo.controllers.LoginController;
import bou.modelo.controllers.UsuariosController;
import bou.modelo.libro.gui.LibroVerListaPanel;
import bou.modelo.libro.gui.VentanaNuevoLibro;
import bou.modelo.ventas.gui.VentaVerListaPanelAdmin;
import bou.modelo.ventas.gui.VentaVerListaPanelCliente;
import bou.modelo.ventas.gui.VentanaNuevaVenta;

/**
 * Clase que extiende de GUI y crea la ventana del administrador.
 */
public class UsuarioGUI extends GUI implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginController login_control;
	private JLabel  user, pass, msg;
	private JTextField tf_user;
	private JPasswordField jpass;
	private JButton bt_entrar;
	private ImageIcon fondo;
	private URL url_imagen;
	private String user_dni;
	private boolean admin;
	
	private UsuariosController user_control;
	private JPanel panel_actual;
	private JMenuBar jmb;
	private JMenu emp,lib ,log_out;
	private JMenuItem alta, lista, alta_libro, lista_libros,lista_ventas;
	private ClienteAltaPanel cli_alta_panel;
	private ClienteVerListaPanel cli_lista_panel;
	private LibroVerListaPanel lib_lista_panel;
	private VentanaNuevoLibro libro_alta_panel;
	private VentaVerListaPanelAdmin venta_lista;
	
	private VentaVerListaPanelCliente vent_panel;
	private VentanaNuevaVenta vent_nueva;
	private JMenu menuVentas;
	private JMenuItem hacerCompra, verMIsCompras;
	private JMenu log_out2;
	
	private JLabel img_label;
	private ImageIcon imagen;
	
	/**
	 * Constructors con parámetro
	 * @param c 
	 * @param c AdminController
	 */
	public UsuarioGUI(String rol) {
		super(rol);
		initLoginPanel();
		user_control = new UsuariosController();
		if(rol.equalsIgnoreCase("administrador")) {
			iniGUIAdmin();
		}
		else {
			iniGUICliente();
		}
	}

	/**
	 * Inicializa todas las componentes del panel
	 */
	private void initLoginPanel() {
		setLayout(new GridLayout(1, 2));
		
		// panel para la imagen
		JPanel panel_img = new JPanel();
		panel_img.setLayout(new FlowLayout());
		
		url_imagen = getClass().getResource("fondo.jpg");
		imagen = new ImageIcon(url_imagen);
		img_label = new JLabel(imagen);
		img_label.setSize(10,10);
		panel_img.add(img_label);
				
		// panel para el login
		JPanel panel_login = new JPanel();
		panel_login.setLayout(new BoxLayout(panel_login, BoxLayout.Y_AXIS));
		
		JPanel jp = new JPanel();
		msg = new JLabel("Datos");
		jp.add(msg);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new FlowLayout());
		
		user = new JLabel("DNI");
		tf_user = new JTextField(10);
		
		jp1.add(user);
		jp1.add(tf_user);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		
		pass = new JLabel("Contraseña");
		jpass = new JPasswordField(10);
		
		jp2.add(pass);
		jp2.add(jpass);
		
		JPanel jp3 = new JPanel();
		jp3.setLayout(new FlowLayout());
		
		bt_entrar = new JButton("Acceder");
		bt_entrar.setName("entrar");
		bt_entrar.addActionListener(this);
		
		jp3.add(bt_entrar);
		
		
		
		panel_login.add(jp);
		panel_login.add(jp1);
		panel_login.add(jp2);
		panel_login.add(jp3);
		
		// agregamos los paneles imagen y login
		add(panel_img);
		add(panel_login);
		
	}
	
	private void iniGUICliente() {
		panelPrincipal = this.getContentPane();
		panelPrincipal.setLayout(new FlowLayout());
		
		jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		menuVentas = new JMenu("Compras");
		
		hacerCompra = new JMenuItem("Comprar un libro");
		hacerCompra.setName("comprar libro");
		hacerCompra.addActionListener(this);
		menuVentas.add(hacerCompra);
		
		verMIsCompras = new JMenuItem("Historial de compras");
		verMIsCompras.setName("historial compras");
		verMIsCompras.addActionListener(this);
		menuVentas.add(verMIsCompras);
		
		jmb.add(menuVentas);
	
		log_out2 = new JMenu("Salir");
		log_out2.setName("logout");
		log_out2.addMouseListener(this);	
		jmb.add(log_out2);
		
		
		panel_actual = new JPanel();
		
		
		
		imagen = new ImageIcon(getClass().getResource("FondoCliente.png"));
		img_label = new JLabel(imagen);
		img_label.setSize(5,5);
		panel_actual.add(img_label);
		
		panelPrincipal.add(panel_actual);
		
		
		setSize(600, 490);
		setVisible(true);
		setLocationRelativeTo(null);
		
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
			cli_alta_panel = new ClienteAltaPanel(user_control);
			changePanelActual(cli_alta_panel);	
		}
		else if(txt.equals("lista")){
			cli_lista_panel = new ClienteVerListaPanel(user_control);
			changePanelActual(cli_lista_panel);
		}
		else if(txt.equals("alta libro")){	
			libro_alta_panel = new VentanaNuevoLibro(user_control);
			changePanelActual(libro_alta_panel);
		}else if(txt.equals("Editar libro")){	
			lib_lista_panel = new LibroVerListaPanel(user_control);
			changePanelActual(lib_lista_panel);
		}else if(txt.equals("ver ventas")){	
			venta_lista = new VentaVerListaPanelAdmin(user_control);
			changePanelActual(venta_lista);
		}
		else if(txt.equals("comprar libro")){
			vent_nueva = new VentanaNuevaVenta(user_control);
			changePanelActual(vent_nueva);
		}
		else{
			vent_panel = new VentaVerListaPanelCliente(user_control);
			changePanelActual(vent_panel);
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
