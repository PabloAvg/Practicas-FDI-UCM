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
import bou.modelo.controllers.ClienteController;
import bou.modelo.reservas.gui.ReservaNuevaPanel;
import bou.modelo.reservas.gui.ReservasVerPanelCliente;
import bou.modelo.ventas.gui.VentaVerListaPanelCliente;
import bou.modelo.ventas.gui.VentanaNuevaVenta;

/**
 * Clase que extiende de GUI y crea la ventana del cliente.
 */
public class ClienteGui extends  GUI implements MouseListener, ActionListener{

	private static final long serialVersionUID = 1L;
	
	private ClienteController cliente_control;
	private ReservasVerPanelCliente res_panel;
	private VentaVerListaPanelCliente vent_panel;
	private ReservaNuevaPanel res_alta_panel;
	private VentanaNuevaVenta vent_nueva;
	private JPanel panel_actual;
	private JMenuBar jmb;
	private JMenu menuRes;
	private JMenu menuVentas;
	private JMenuItem alta_reserva, res, hacerCompra, verMIsCompras;
	private JMenu log_out;
	private JLabel img_label;
	private ImageIcon imagen;
	
	/**
	 * Constructora con parámetro
	 * @param c ClienteController
	 */
	public ClienteGui(ClienteController c) {
		super("Cliente");
		cliente_control = c;
		iniGUICliente();
	}

	/**
	 * Método que inicializa los componentes de la ventana
	 */
    private void iniGUICliente() {
		
		panelPrincipal = this.getContentPane();
		panelPrincipal.setLayout(new FlowLayout());
		
		jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		menuRes = new JMenu("Reservas");
		
		res = new JMenuItem("Mis reservas");
		res.setName("Ver mis reservas");
		res.addActionListener(this);
		menuRes.add(res);
		
		alta_reserva = new JMenuItem("Nueva reserva");
		alta_reserva.setName("Dar de alta una reserva");
		alta_reserva.addActionListener(this);
		menuRes.add(alta_reserva);
		
		jmb.add(menuRes);
		
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
	
		log_out = new JMenu("Salir");
		log_out.setName("logout");
		log_out.addMouseListener(this);	
		jmb.add(log_out);
		
		
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
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == log_out){
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"Deseas salir", "Salir", JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
				this.dispose();
			}
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
		JMenuItem j = (JMenuItem)e.getSource();
		String txt = j.getName();
		
		if(txt.equals("Ver mis reservas")){
			res_panel = new ReservasVerPanelCliente(cliente_control);
			changePanelActual(res_panel);	
		}
		else if(txt.equals("Dar de alta una reserva")){
			res_alta_panel = new ReservaNuevaPanel(cliente_control);
			changePanelActual(res_alta_panel);
		}
		else if(txt.equals("comprar libro")){
			vent_nueva = new VentanaNuevaVenta(cliente_control);
			changePanelActual(vent_nueva);
		}
		else if(txt.equals("historial compras")){
			vent_panel = new VentaVerListaPanelCliente(cliente_control);
			changePanelActual(vent_panel);
		}
	}

}
