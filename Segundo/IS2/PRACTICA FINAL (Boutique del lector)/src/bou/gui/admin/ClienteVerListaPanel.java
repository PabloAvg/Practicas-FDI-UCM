package bou.gui.admin;

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
import bou.modelo.observers.ClientesObserver;
import bou.modelo.usuarios.Cliente;
import bou.modelo.usuarios.Usuario;



/**
 * Clase que extiende a JPanel. Crea el panel de la lista de clientes,
 * implementa ActionListener, MouseListener, ClientesObserver
 */
@SuppressWarnings("serial")
public class ClienteVerListaPanel  extends JPanel implements ActionListener, MouseListener, ClientesObserver{

	private AdminController admin_control;
	private MyModelTableCli modelo;
	private JTable jt;
	private JLabel filtro;
	private JTextField texto;
	private JRadioButton rb_dni, rb_sinFiltro;
	private ButtonGroup grupo;
	private JButton bt_buscar, bt_ver, bt_eliminar;
	private String dni;
	
	/**
	 * Constructor con parámetro
	 * @param user_control un admincontroller
	 */
	public ClienteVerListaPanel(UsuariosController user_control){
		super();
		admin_control = user_control;
		initCliEditarPanel();
	}

	/**
	 * Inicializa todos los componentes
	 */
    private void initCliEditarPanel(){
        setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Ver lista de clientes");
		setBorder(titulo);
		
		admin_control.registerClientesObserver(this);
		
		modelo = new MyModelTableCli();
		jt = new JTable(modelo);
		jt.addMouseListener(this);
		jt.setPreferredScrollableViewportSize(new Dimension(350,130));
		JScrollPane scrollPane = new JScrollPane(jt);
		
		admin_control.cargarTodosLosClientes();
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,1));
		
		JPanel panelboton = new JPanel();
		panelboton.setLayout(new FlowLayout());
		
		bt_ver = new JButton("VER");
		bt_ver.setName("ver");
		bt_ver.setEnabled(false);
		bt_ver.addActionListener(this);
		
		bt_eliminar = new JButton("ELIMINAR");
		bt_eliminar.setName("eliminar");
		bt_eliminar.setEnabled(false);
		bt_eliminar.addActionListener(this);
		
		panelboton.add(bt_ver);
		panelboton.add(bt_eliminar);
		
		JPanel panelfiltro = new JPanel();
		panelfiltro.setLayout(new FlowLayout());
		
		filtro = new JLabel("Filtrar por:");
		grupo = new ButtonGroup();
		rb_dni = new JRadioButton("DNI",false);
		rb_sinFiltro = new JRadioButton("SIN FILTROS");
		rb_sinFiltro.addMouseListener(this);
		
		grupo.add(rb_dni);
		grupo.add(rb_sinFiltro);
		
		panelfiltro.add(filtro);
		panelfiltro.add(rb_dni);
		panelfiltro.add(rb_sinFiltro);
	
		
		jp1.add(panelboton);
		jp1.add(panelfiltro);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		
		texto = new JTextField(10);
		
		bt_buscar = new JButton("BUSCAR");
		bt_buscar.setName("buscar");
		bt_buscar.addActionListener(this);
		
		jp2.add(texto);
		jp2.add(bt_buscar);
		
		add(scrollPane,BorderLayout.NORTH);
		add(jp1,BorderLayout.CENTER);
		add(jp2,BorderLayout.SOUTH);
        }
        
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("buscar")){	
			if(rb_dni.isSelected()){
				admin_control.registerClientesObserver(this);
				admin_control.buscarCliente(texto.getText());
			}
				
		}
		else if(txt.equals("ver")){
			admin_control.registerClientesObserver(this);
			admin_control.verCliente(dni);
		}
		else if(txt.equals("eliminar")){
			int seleccionada = JOptionPane.showConfirmDialog
					(this,"Eliminar", "Eliminar",JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
				admin_control.registerClientesObserver(this);
				admin_control.eliminarCliente(dni);	
				admin_control.cargarTodosLosClientes();
				
				
			}
			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		 if(e.getSource() == jt){
		        int row = jt.rowAtPoint(e.getPoint());
		        if (row >= 0 && jt.isEnabled())
		        {
		        	bt_ver.setEnabled(true);
		        	bt_eliminar.setEnabled(true);
		        	dni = modelo.getValueAt(row,2).toString();
		        }
			 }
		 else if(e.getSource() == rb_sinFiltro){
				 texto.setText("");
				 admin_control.registerClientesObserver(this);
				 admin_control.cargarTodosLosClientes();
			 }
			
		
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Error",JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void initClientesTable(List<Cliente> lista_clientes) {
		modelo.initClientesTable(lista_clientes);
	}

	@Override
	public void verCliente(Cliente c, Usuario u) {
		new VentanaVerCliente(c, u, admin_control);
	}

	@Override
	public void eliminarReservaJList(){}

}
