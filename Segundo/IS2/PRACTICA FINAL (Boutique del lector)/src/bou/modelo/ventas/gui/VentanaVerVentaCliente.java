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
import bou.modelo.controllers.ClienteController;
import bou.modelo.libro.Libro;
import bou.modelo.observers.VentasObserver;
import bou.modelo.ventas.Venta;
import bou.modelo.ventas.VentaPagada;

/**
 * Clase que hereda de GUI e y que implementa ActionListener y VentasObserver, crea una ventana donde ver una venta concreta siendo un cliente y permite editarla para pagarla.
 */
public class VentanaVerVentaCliente extends GUI implements ActionListener, VentasObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClienteController clien_controller;
	private JPanel panel;
	private Venta venta;
	private JLabel labelId,label_status, labelPrecio, labelFecha, label_dni, label_libro;
	private JTextField textId, textPrecio, textFecha, tf_libro, tf_dni;
	private JComboBox<VentaPagada> cb_pagada;
	private JButton boton_editar, boton_pagar;
	
	/**
	 * Constructora con parámetros
	 * @param v Venta
	 * @param c ClienteController
	 */
	public VentanaVerVentaCliente(Venta v, ClienteController c) {
		super("Venta");
		clien_controller = c;
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
		
		boton_editar = new JButton("Editar");
		boton_editar.setName("editar");
		if(clien_controller == null){
			boton_editar.setVisible(false);
		}
		boton_editar.addActionListener(this);
		
		boton_pagar = new JButton("Pagar");
		boton_pagar.setName("pagar");
		boton_pagar.setVisible(false);
		boton_pagar.addActionListener(this);
		
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
	
		botones.add(boton_editar);
		botones.add(boton_pagar);

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
	
	/**
	 * Método para activar el botón de pagar cuando se pulsa sobre ditar.
	 */
	private void activarCampos(){
		boton_pagar.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton t = (JButton) e.getSource();
		String txt = t.getName();
		
		if(txt.equals("editar")){
			if(cb_pagada.getSelectedItem() != VentaPagada.PAGADA) {
				activarCampos();
				boton_editar.setVisible(false);
			}
			else {
				showInfoMsg("Venta ya pagada.");
			}
		}
		else if(txt.equals("pagar")){
			String id = textId.getText();
			Libro libro = new Libro(tf_libro.getText());
			int precio = Integer.parseInt(textPrecio.getText());
			String[] fechaTrozos = textFecha.getText().split("/");
			Date fecha = new Date();
			fecha.setDate(Integer.valueOf(fechaTrozos[0]));
			fecha.setMonth(Integer.valueOf(fechaTrozos[1]) - 1);
			fecha.setYear(Integer.valueOf(fechaTrozos[2]) - 1900);
				
			int seleccionada = JOptionPane.showConfirmDialog
				(this,"¿Realmente desea pagar esta compra?", "Pagar",JOptionPane.YES_NO_OPTION);
			if(seleccionada == JOptionPane.YES_OPTION){
				clien_controller.registerVentasObserver(this);
				clien_controller.actualizarVenta(id,libro, precio, fecha, VentaPagada.PAGADA);
			}
		}
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
	
}
