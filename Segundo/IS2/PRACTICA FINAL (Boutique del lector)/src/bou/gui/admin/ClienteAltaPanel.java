package bou.gui.admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import bou.modelo.controllers.UsuariosController;
import bou.modelo.observers.ClientesObserver;
import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;

/**
 * Clase que extiende a JPanel. Crea el panel de alta cliente.
 */
@SuppressWarnings("serial")
public class ClienteAltaPanel extends JPanel implements ActionListener, ClientesObserver{

	private UsuariosController user_control;
	private JLabel nom, ape, dni, email,contraseña;
	private JTextField tf_nom, tf_ape, tf_dni, tf_email, tf_contraseña; 
	private JButton bt_enviar, bt_borrar;
	
	/**
	 * Constructora con parámetro
	 * @param user_control un admincontroller
	 */
	public ClienteAltaPanel(UsuariosController u){
		super();
		user_control = u;
		initClienteAltaPanel();
	}

	/**
	 * Inicializa todos los componentes
	 */
	private void initClienteAltaPanel() {
		setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Alta de cliente");
		setBorder(titulo);
		
		JPanel datos = new JPanel();
		datos.setLayout(new GridLayout(9,2));
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		
		
		nom = new JLabel("Nombre");
		tf_nom = new JTextField(10);
		
		ape = new JLabel("Apellido");
		tf_ape = new JTextField(15);
		
		dni = new JLabel("DNI");
		tf_dni = new JTextField(10);

		email = new JLabel("Email");
		tf_email = new JTextField(10);
		
		contraseña = new JLabel("Contraseña");
		tf_contraseña = new JTextField(10);
		
		
		
		
		tf_dni.addFocusListener(new FocusListener() {
			   public void focusLost(FocusEvent e) {
			      tf_dni.setText(((JTextField)e.getSource()).getText());
			   }
			   public void focusGained(FocusEvent e) {
			     
			   }
			});
		
		
		bt_borrar = new JButton("BORRAR");
		bt_borrar.setName("borrar");
		bt_borrar.addActionListener(this);
		
		bt_enviar = new JButton("ENVIAR");
		bt_enviar.setName("enviar");
		bt_enviar.addActionListener(this);
		

		
		datos.add(nom);
		datos.add(tf_nom);
		datos.add(ape);
		datos.add(tf_ape);
		
		datos.add(dni);
		datos.add(tf_dni);
		
		
		datos.add(email);
		datos.add(tf_email);
		
		botones.add(contraseña);
		botones.add(tf_contraseña);
		
		botones.add(bt_borrar);
		botones.add(bt_enviar);
		
	
	
		
		
		
		add(datos, BorderLayout.NORTH);
		add(botones, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("enviar")){
			if(!hayCampoVacio()){
				String name = tf_nom.getText();
				String apell = tf_ape.getText();
				String dni = tf_dni.getText();
				String email = tf_email.getText();
				String password = tf_contraseña.getText();
				Usuario usuario = new Usuario(dni,password,"cliente");
				user_control.agregarCliente(new Cliente(name, apell, dni, email), usuario);
				//user_control.cargarTodosLosClientes();
				vaciarCampos();
				
			}
			else{
				showInfoMsg("Se han cometido errores");
			}
			
		}
		else if(txt.equals("borrar")){
			vaciarCampos();
		}
	}

	/**
	 * Combrueba si no hay ningun campo vacio
	 * @return booleano false si hay algun campo vacio
	 */
	private boolean hayCampoVacio(){
		return (tf_nom.getText().isEmpty() || tf_ape.getText().isEmpty() ||
				tf_dni.getText().isEmpty() ||tf_email.getText().isEmpty()|| tf_contraseña.getText().isEmpty());
	}
	
	
	private void vaciarCampos(){
		tf_nom.setText("");
		tf_ape.setText("");
		tf_dni.setText("");
		tf_email.setText("");
		tf_contraseña.setText("");
	}
	
	@Override
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Error",JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void initClientesTable(List<Cliente> lista_clientes) {}

	@Override
	public void verCliente(Cliente c, Usuario u) {}

	@Override
	public void eliminarReservaJList() {}

}
