package bou.modelo.ventas.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import bou.gui.GUI;
import bou.modelo.controllers.AdminController;
import bou.modelo.libro.Libro;
import bou.modelo.observers.VentasObserver;
import bou.modelo.usuarios.Cliente;
import bou.modelo.ventas.Venta;
import bou.modelo.ventas.VentaPagada;

/**
 * Clase que hereda de GUI e implementa ActionListener y VentasObserver, crea la ventana para ver una venta concreta siendo un administrador y permite editarla.
 */
public class VentanaVerVentaAdmin extends GUI implements ActionListener, VentasObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AdminController admin_controller;
	private JPanel panel;
	private Venta venta;
	private JLabel labelId,label_status, labelPrecio, labelFecha, label_dni, label_libro;
	private JButton bt_editar,bt_guardar;
	private JTextField textId, textPrecio, textFecha, tf_libro, tf_dni;
	private JComboBox<VentaPagada> cb_pagada;
	
	/**
	 * Constructora con parámetro.
	 * @param v Venta
	 * @param c AdminController
	 */
	public VentanaVerVentaAdmin(Venta v, AdminController c) {
		super("Venta");
		admin_controller = c;
		venta = v;
		initVentanaVerVenta();
	}
	
	/**
	 * Método para inicializar los componentes de la ventana.
	 */
	private void initVentanaVerVenta() {
		panelPrincipal = this.getContentPane();
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		TitledBorder titulo = BorderFactory.createTitledBorder("Datos Venta");
		panel.setBorder(titulo);
		
		JPanel datos = new JPanel();
		datos.setLayout(new GridLayout(9,2));
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		
		labelId = new JLabel("ID:");
		textId = new JTextField(15);
		textId.setText(venta.getId());
		textId.setEditable(false);
		
		label_dni = new JLabel("DNI:");
		tf_dni = new JTextField(15);
		tf_dni.setText(String.valueOf(venta.getCliente().getDNI()));
		tf_dni.setEditable(false);
		
		label_libro = new JLabel("Libro:");
		tf_libro = new JTextField(15);
		tf_libro.setText(venta.getLibro().get_titulo());
		tf_libro.setEditable(false);
		
		label_status = new JLabel("Estado:");
		cb_pagada = new JComboBox<VentaPagada>();
		cb_pagada.setBounds(10, 10, 80, 20);
		for(VentaPagada p : VentaPagada.values()) {
			cb_pagada.addItem(p);
		}
		cb_pagada.setSelectedItem(VentaPagada.valueOf(venta.verSiPagada()));
		cb_pagada.setEnabled(false);

		labelPrecio = new JLabel("Precio:");
		textPrecio = new JTextField(10);
		textPrecio.setText(String.valueOf(venta.getPrecio()));
		textPrecio.setEditable(false);
		
		labelFecha = new JLabel("Fecha:");
		Date date = venta.getDate();
		DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
        String f = dateFormat.format(date);
		textFecha = new JTextField(10);
		textFecha.setText(f);
		textFecha.setEditable(false);
		
		bt_editar=new JButton("editar");
		bt_editar.setName("editar");
		bt_editar.addActionListener(this);
		
		bt_guardar=new JButton("guardar");
		bt_guardar.setName("guardar");
		bt_guardar.setVisible(false);
		bt_guardar.addActionListener(this);
		
		botones.add(bt_editar);
		botones.add(bt_guardar);
		
		datos.add(labelId);
		datos.add(textId);
		
		datos.add(label_dni);
		datos.add(tf_dni);
		
		datos.add(labelFecha);
		datos.add(textFecha);
		
		datos.add(label_libro);
		datos.add(tf_libro);
		
		datos.add(labelPrecio);
		datos.add(textPrecio);
		
		datos.add(label_status);
		datos.add(cb_pagada);
	
		panel.add(datos, BorderLayout.NORTH);
		panel.add(botones, BorderLayout.SOUTH);

		panelPrincipal.add(panel);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		
	}

	@Override
	public void showInfoMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public void showErrorMsg(String msg) {
		JOptionPane.showMessageDialog
		(this,msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void initLibrosCombo(List<Libro> _libros) {
		
	}

	@Override
	public void initVentasTable(List<Venta> listaVentas) {
		
	}

	@Override
	public void verVenta(Venta v) {
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("editar")){
			activarCampos();
			bt_guardar.setVisible(true);
		}
		else if(txt.equals("guardar")){
			
			
			
				String id = textId.getText();
				int pre = Integer.valueOf(textPrecio.getText());
				String dni = tf_dni.getText();
				String titulo = tf_libro.getText();
				String[] fechaTrozos = textFecha.getText().split("/");
				Date fecha = new Date();
				fecha.setDate(Integer.valueOf(fechaTrozos[0]));
				fecha.setMonth(Integer.valueOf(fechaTrozos[1]) - 1);
				fecha.setYear(Integer.valueOf(fechaTrozos[2]) - 1900);

				
				if(!id.isEmpty() && !dni.isEmpty()){
					int seleccionada = JOptionPane.showConfirmDialog
							(this,"¿Desea actualizar a esta venta?", "Actualizar",JOptionPane.YES_NO_OPTION);
					if(seleccionada == JOptionPane.YES_OPTION){
					  admin_controller.registerVentasObserver(this);
					  Venta v=new Venta(id, new Libro(titulo), pre, new Cliente(null,null,dni,null), fecha, (VentaPagada)cb_pagada.getSelectedItem());
					  admin_controller.actualizarVenta(v);
					   }
				}
				else{
					showInfoMsg("Se deben rellenar todos los campos.");
						
				}
			
		}
	}
	
	/**
	 * Método para activar botones, textfield y combobox para editar.
	 */
	private void activarCampos(){
		bt_guardar.setVisible(true);
		tf_dni.setEditable(false);
		textId.setEditable(false);
		tf_libro.setEditable(false);
		cb_pagada.setEnabled(true);
		textPrecio.setEditable(true);
		bt_editar.setVisible(false);
	}

}
