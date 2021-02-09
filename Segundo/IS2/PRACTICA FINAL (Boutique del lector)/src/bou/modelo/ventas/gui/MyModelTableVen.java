package bou.modelo.ventas.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import bou.modelo.ventas.Venta;
/**
 * Clase que extiende a AbstractModel y que representa una tabla de ventas.
 */
public class MyModelTableVen extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] columnas = {"Id", "DNI Cliente" ,"Precio", "Fecha"};
	private List<Venta> lista_ventas;
	
	/**
	 * Constructora sin parámetros
	 */
	public MyModelTableVen(){
		super();
		lista_ventas = new ArrayList<Venta>();
	}
	
	@Override
	public int getRowCount() {
		return lista_ventas.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (rowIndex>= lista_ventas.size())
			return null;
		else {
			Venta v = lista_ventas.get(rowIndex);
			if (columnIndex==0)
				return v.getId();
			else if (columnIndex==1)
				return v.getCliente().getDNI();
			else if (columnIndex==2)
				return v.getPrecio();
			else if (columnIndex==3) {
				Date date = v.getDate();
				DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
		        String f = dateFormat.format(date);
				return f;
			}
			else
				return null;
		}
	}
	
	/**
	 * Devuelve el nombre de una columna
	 */
	public String getColumnName(int col) {
		return columnas[col];
	}
	
	/**
	 * Actualiza la tabla de ventas con la lista pasada como parámetro
	 * @param lista una lista de ventas
	 */
	public void initVentasTable(List<Venta> lista) {
		lista_ventas = lista;
		fireTableDataChanged();	
	}
	
}
