package no.MCH.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import no.MCH.Run;

public class TableContent {
	JTable table;
	JScrollPane scrollPane;
	Object[][] data;
	String[] columnNames;
	
	public TableContent(Object[][] data, String[] columnNames) {
		this.data = data;
		this.columnNames = columnNames;
		table = new JTable(data, columnNames);
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		DefaultGui.componentModel.addComponent(scrollPane);
	}
	
	public void changeData(Object[][] data, String[] columnNames) {
		DefaultGui.removeComponent(Run.getInstance().getGui(), scrollPane);
		DefaultGui.componentModel.removeComponent(scrollPane);
		new TableContent(data, columnNames);
	}

}
