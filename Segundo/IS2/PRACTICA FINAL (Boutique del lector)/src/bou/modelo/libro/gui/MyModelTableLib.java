package bou.modelo.libro.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import bou.modelo.libro.CategoriaEnum;
import bou.modelo.libro.Libro;

/**
 * Clase que extiende a AbstractTableModel y representa una tabla de Libro
 */
@SuppressWarnings("serial")
public class MyModelTableLib extends AbstractTableModel {

	private String[] columnas = {"ID","Titulo","Precio","Autor", "Categoria"};
	private List<Libro> lista_libros;
	
	/**
	 * Constructor sin parámetros
	 */
	public MyModelTableLib(){
		super();
		lista_libros = new ArrayList<Libro>();
	}
	
	/**
	 * Devuelve las columnas
	 */
	@Override
	public int getRowCount() {
		return lista_libros.size();
	}

	/**
	 * Devuelve las filas
	 */
	@Override
	public int getColumnCount() {
		return 5;
	}

	/**
	 * Devuelve el valor en columna / fila
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (rowIndex>= lista_libros.size())
			return null;
		else {
			Libro l = lista_libros.get(rowIndex);
			if (columnIndex==0)
				return l.get_id();
			else if (columnIndex==1)
				return l.get_titulo();
			else if (columnIndex==2)
				return l.get_precio();
			else if (columnIndex==3)
				return l.get_autor();
			else if (columnIndex==4)
				return l.get_categoria().toString();
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
	 * Init de la tabla de libros
	 * @param lista
	 */
	public void initLibrosTable(List<Libro> lista) {
		lista_libros = lista;
		fireTableDataChanged();	
	}

	public void initCategoriasCombo(List<CategoriaEnum> _categorias) {}
	
}
