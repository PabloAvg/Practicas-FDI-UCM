package bou.modelo.ventas.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import bou.modelo.controllers.ClienteController;
import bou.modelo.controllers.UsuariosController;
import bou.modelo.libro.Libro;
import bou.modelo.observers.VentasObserver;
import bou.modelo.ventas.Venta;
import bou.modelo.ventas.VentaPagada;

/**
 * Clase que extiende de JPanel y que implemente ActionListener, ItemListener y VentasObserver, crea la ventana para añadir una nueva venta.
 */
public class VentanaNuevaVenta extends JPanel implements ActionListener,ItemListener,VentasObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClienteController clien_control;
	private JLabel id, lista_libros, precio, fecha, pagada, pagada_estado;
	private JTextField tf_id, tf_precio;
	private JComboBox<String> cb_libros;
	private JDateChooser entradaFechaRes;
	private JButton bt_vaciar, bt_comprar;
	private List<Libro> listaaux;
	
	/**
	 * Constructora con parámetro
	 * @param user_control ClienteController
	 */
	public VentanaNuevaVenta (UsuariosController user_control) {
		this.clien_control = user_control;
		clien_control.registerVentasObserver(this);
		initVentanaNuevaVenta();
	}
	
	/**
	 * Método para inicializar los componentes de la ventana.
	 */
	private void initVentanaNuevaVenta() {
		setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Nueva Compra");
		setBorder(titulo);
		
		JPanel datos = new JPanel();
		datos.setLayout(new GridLayout(8,2));
		
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		
		id= new JLabel("id");
		tf_id = new JTextField(10);
		
		lista_libros = new JLabel("Libro");
		cb_libros = new JComboBox<String>();
		cb_libros.setBounds(10, 10, 80, 20);
		clien_control.initLibroComboBoxVentas();
		cb_libros.setSelectedIndex(0);
		cb_libros.addItemListener(this);
		
		precio = new JLabel("Precio");
		tf_precio = new JTextField(10);
		tf_precio.setEnabled(false);
		tf_precio.setText("0");
		
		fecha = new JLabel("Fecha");
		entradaFechaRes = new JDateChooser();
		entradaFechaRes.setCalendar(Calendar.getInstance());
		entradaFechaRes.setEnabled(false);
		
		bt_vaciar = new JButton("Vaciar");
		bt_vaciar.setName("vaciar");
		bt_vaciar.addActionListener(this);
		
		bt_comprar = new JButton("Comprar");
		bt_comprar.setName("comprar");
		bt_comprar.addActionListener(this);
		
		pagada = new JLabel("estado");
		pagada_estado = new JLabel(VentaPagada.SIN_PAGAR.toString());
		
		datos.add(id);
		datos.add(tf_id);
		datos.add(lista_libros);
		datos.add(cb_libros);
		datos.add(precio);
		datos.add(tf_precio);
		datos.add(fecha);
		datos.add(entradaFechaRes);
		datos.add(pagada);
		datos.add(pagada_estado);
		
		botones.add(bt_vaciar);
		botones.add(bt_comprar);
		
		add(datos, BorderLayout.NORTH);
		add(botones, BorderLayout.SOUTH);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cb_libros) {
			Libro l = new Libro(null, null, 0, null, null);
			String select = (String) cb_libros.getSelectedItem();
			int i =0;
			boolean encontrado = false;
			while(!encontrado && i < listaaux.size()) {
				if(select.equals(listaaux.get(i).get_titulo())) {
					l = listaaux.get(i);
				}
				i++;
			}
			tf_precio.setText(String.valueOf(l.get_precio()));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { // TODO
		
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("vaciar")){
			vaciarCampos();
		}
		else if(txt.equals("comprar")){
			
			Libro l = new Libro(null, null, 0, null, null);
			String id = tf_id.getText();
			String select = (String) cb_libros.getSelectedItem();
			int i =0;
			boolean encontrado = false;
			while(!encontrado && i < listaaux.size()) {
				if(select.equals(listaaux.get(i).get_titulo())) {
					l = listaaux.get(i);
				}
				i++;
			}
			
			int prec = Integer.valueOf(tf_precio.getText());
		
			Date date = entradaFechaRes.getDate();
				
			if(!id.isEmpty() && cb_libros.getSelectedItem() != " "){
			  clien_control.registerVentasObserver(this);
			  clien_control.agregarVenta(id, prec, l, date, VentaPagada.SIN_PAGAR);
			  vaciarCampos();
			}
			else{
				showInfoMsg("Debes de completar todos los campos.");
					
			}
			
		}
	}
	
	/**
	 * Método para vaciar textfield y combobox.
	 */
	private void vaciarCampos(){
		tf_id.setText("");
		cb_libros.setSelectedIndex(0);
	}
	
	@Override
	public void initLibrosCombo(List<Libro> _libros) {
		listaaux = _libros;
		cb_libros.addItem(" ");
		for(int i = 0; i < _libros.size(); i++){
			cb_libros.addItem(_libros.get(i).get_titulo());
		}
	}
	
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
	public void initVentasTable(List<Venta> listaVentas) {
		
	}

	@Override
	public void verVenta(Venta v) {
		
	}
	
}
