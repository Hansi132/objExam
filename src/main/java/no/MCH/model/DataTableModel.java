package no.MCH.model;

import javax.swing.table.DefaultTableModel;

public class DataTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}
