package bou.gui.login;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bou.modelo.controllers.LoginController;
import bou.modelo.controllers.UsuariosController;
import bou.modelo.observers.UsuariosObserver;
import bou.views.UsuarioGUI;

/**
 * Clase que extiende a JPanel, crea el panel para hacer login,
 * implementa ActionListener, UsuariosObserver
 */
public class LoginPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
		private UsuariosController login_control;
		private JLabel img_label, user, pass, msg;
		private JTextField tf_user;
		private JPasswordField jpass;
		private JButton bt_entrar;
		private ImageIcon imagen;
		private URL url_imagen;
		private String user_dni;
		private boolean admin;
		
		/**
		 * Constructor con parámetro
		 * @param controlador logincontroller
		 */
		public LoginPanel(UsuariosController controlador){
			super();
			login_control = controlador;
			initLoginPanel();
		}

		/**
		 * Inicializa todas las componentes del panel
		 */
		private void initLoginPanel() {
			setLayout(new GridLayout(1, 2));
			
			// panel para la imagen
			JPanel panel_img = new JPanel();
			panel_img.setLayout(new FlowLayout());
			
			url_imagen = getClass().getResource("lib.jpg");
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

		
		public void actionPerformed(ActionEvent e) {
			JButton t = (JButton) e.getSource();
			String txt = t.getName();
			
			if(txt.equals("entrar")){
				
				user_dni = tf_user.getText();
				char[] clave = jpass.getPassword();
				String password = new String(clave);
				
				if(user_dni.isEmpty()){
					JOptionPane.showMessageDialog
					(this,"Introduce un dni", "Aviso",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(password.isEmpty()){
					JOptionPane.showMessageDialog
					(this,"Introduce una contraseña", "Aviso",JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					try {
						openView(login_control.login(user_dni,password));
						
						
					}
					catch(IndexOutOfBoundsException ex) {
						errorLogin("Usuario o contraseña incorrecto.");
					}
					
				}
					
			}
			
		}

		
		public void openView(String rol) {
			new UsuarioGUI(rol);
		}

		
		public void errorLogin(String msg) {
			JOptionPane.showMessageDialog
			(this, msg, "Error",JOptionPane.ERROR_MESSAGE);
			
		}

		
		
}
