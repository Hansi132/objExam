package no.MCH.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import no.MCH.model.DataTableModel;

public class TableContent {
	JTable table;
	JScrollPane scrollPane;
	
	public TableContent(Object[][] data, String[] columnNames) {
		table = new JTable();
		table.setModel(new DataTableModel());
		table.setFillsViewportHeight(true);
		
		scrollPane = new JScrollPane(table);
		DefaultGui.componentModel.addComponent(scrollPane);
	}
}
