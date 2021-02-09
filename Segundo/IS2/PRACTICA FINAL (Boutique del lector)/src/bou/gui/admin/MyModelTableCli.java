package bou.gui.admin;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import bou.modelo.usuarios.Cliente;;


/**
 * Clase que extiende a AbstractTableModel y representa una tabla de clientes
 */
@SuppressWarnings("serial")
public class MyModelTableCli extends AbstractTableModel{

	private String[] columnas =
		{"Nombre","Apellido","DNI"};
	private List<Cliente> lista_clientes;
	
	/**
	 * Constructor sin parámetros
	 */
	public MyModelTableCli(){
		super();
		lista_clientes =  new ArrayList<Cliente>();
	}
	
	/**
	 * Devuelve el número de filas de la tabla
	 */
	public int getRowCount() {
		return lista_clientes.size();
	}

	/**
	 * Devuelve el número de columnas de la tabla
	 */
	public int getColumnCount() {
		return 3;
	}

	/**
	 * Devuelve el valor de una fila rowIndex y una columna columnIndex en concreto.
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {	
		if (rowIndex>= lista_clientes.size())
			return null;
		else {
			Cliente c = lista_clientes.get(rowIndex);
			if (columnIndex==0)
				return c.getNombre();
			else if (columnIndex==1)
				return c.getApellidos();
			else if (columnIndex==2)
				return c.getDNI();
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
	 * Actualiza la tabla con la lista pasada como parámetro
	 * @param lista una lista de clientes
	 */
	public void initClientesTable(List<Cliente> lista) {
		lista_clientes = lista;
		fireTableDataChanged();	
	}
}
